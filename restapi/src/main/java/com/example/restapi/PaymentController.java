package com.example.restapi;

import java.util.Map;

import org.antlr.v4.runtime.ParserInterpreter;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@RestController
public class PaymentController {
	
	@PostMapping("/payments/{username}/{data}")
	public String createOrder(@PathVariable String data, @PathVariable String username) throws Exception
	{
		RazorpayClient clinet = new RazorpayClient("rzp_test_K4NpbduVrnlxyr", "mP1ypKanltBbEUfPsxQEzamu");
		JSONObject orderRequest = new JSONObject();
		int amount = Integer.parseInt(data);
//		System.out.println(amount);
		orderRequest.put("amount",amount*100);
		orderRequest.put("currency","INR");
		orderRequest.put("receipt", "receipt_23412");
		Order order =clinet.orders.create(orderRequest);
		//System.out.println(order.toString());
		return order.toString();
	}
}
