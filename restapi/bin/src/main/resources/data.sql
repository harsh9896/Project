insert into seller(id,dob,name)
values(101,'16-10-2001','Harsh');
insert into seller(id,dob,name)
values(102,'20-03-2004','Sahil');
insert into seller(id,dob,name)
values(103,'25-12-1998','Simran');
insert into seller(id,dob,name)
values(104,'14-02-1996','Ankur');
insert into articles(id,price,description,name,seller_id)
values(10001,100,'This Article is about', 'Solve the problems',101);
insert into articles(id,price,description,name,seller_id)
values(10002,500,'This Article is about', 'Data Structures',101);
insert into articles(id,price,description,name,seller_id)
values(10003,200,'This Article is about', 'Algorithms',101);
insert into articles(id,price,description,name,seller_id)
values(10004,400,'This Article is about', 'Mathematics',102);
insert into articles(id,price,description,name,seller_id)
values(10005,150,'This Article is about', 'Science',102);
insert into articles(id,price,description,name,seller_id)
values(10006,1000,'This Article is about', 'Cooking',103);
insert into articles(id,price,description,name,seller_id)
values(10007,700,'This Article is about', 'Amazing Problems',104);
insert into customer(id,dob,name)
values(101,'16-10-2001','Himanshu');
insert into customer(id,dob,name)
values(102,'20-03-2004','Deepankur');
insert into customer(id,dob,name)
values(103,'25-12-1998','Anjali');
insert into customer(id,dob,name)
values(104,'14-02-1996','Adarsh');
insert into articles_customers(articles_id,customers_id)
values(10001,101);
insert into articles_customers(articles_id,customers_id)
values(10001,102);
insert into articles_customers(articles_id,customers_id)
values(10002,101);
insert into articles_customers(articles_id,customers_id)
values(10003,103);