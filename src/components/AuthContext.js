import { createContext, useContext, useState } from "react";
import React from 'react'
import { apiClient, retrieveSingleCustomer, retrieveSingleSeller } from "./api/api";

export const AuthContext = createContext()

export const useAuth = () => useContext(AuthContext)

//2: Share the created context with other components
export default function AuthProvider({ children }) {

    //3: Put some state in the context
    const [isAuthenticated, setAuthenticated] = useState(false)

    const [userName, setUserName] = useState("")

    const [userType, setUserType] = useState("")

    async function login(username, password, usertype) {

        const baToken = "Basic " + window.btoa("Harsh" + ":" + "password")
        if(usertype=="seller")
        {try{
           
            const response = await retrieveSingleSeller(username, baToken)
            if(response.data.password===password)
            {
                console.log("pass")
                setUserName(username)
                setUserType(usertype)
                setAuthenticated(true)
                apiClient.interceptors.request.use(
                    (config) => {
                        config.headers.Authorization = baToken
                        return config
                    }
                )
                return true;
            }
            else
            {
                setUserName("")
                setUserType("")
                setAuthenticated(false)
                return false;
            }
        }
        catch (error){
            setUserName("")
            setUserType("")
            setAuthenticated(false)
            return false
        }}
        else
        {try{
           
            const response = await retrieveSingleCustomer(username, baToken)
            console.log(response)
            if(response.data.password===password)
            {
                setUserName(username)
                setUserType(usertype)
                setAuthenticated(true)
                apiClient.interceptors.request.use(
                    (config) => {
                        config.headers.Authorization = baToken
                        return config
                    }
                )
                return true;
            }
            else
            {
                setUserName("")
                setUserType("")
                setAuthenticated(false)
                return false;
            }
        }
        catch (error){
            setUserName("")
            setUserType("")
            setAuthenticated(false)
            return false
        }
        }
        // basicAuthService(baToken)
        // .then(response=> console.log(response))
        // .catch(error=>console.log(error))
        //setAuthenticated(false)
        // if((username==='Harsh' && password==='password' && usertype==="seller") || (username==='Himanshu' && password==='password' && usertype==="customer") ){
        //     setUserName(username)
        //     setUserType(usertype)
        //     setAuthenticated(true)
        //     return true            
        // } else {
        //     setAuthenticated(false)
        //     return false
        // }        
    }

    function logout() {
        setAuthenticated(false)
        setUserName("")
        setUserType("")
    }

    return (
        <AuthContext.Provider value={ {isAuthenticated, login, logout, userName, userType}  }>
            {children}
        </AuthContext.Provider>
    )
} 