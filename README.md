# SocialMedia_BackendWithSpringBoot


Tech Stack: Spring-boot, java, JPA, h2 database

  --------------------------------------------- Create rest apis around users and posts ------------------------------------------------

Reference: https://gullapalli-nikitha.medium.com/restful-webservices-101things-to-remember-while-developing-restful-webservices-5e3b0fbc8da2

1. Create/Update/Patch/Read/Delete users.

    User:  ID, name, DOB

   Create user -> POST /users  -> This should create a user and return back an {id}
   
   Read user  -> GET /users -> returns back all users
                 GET /users/{id} -> returns back a user {id}
                 
   Update     -> UPDATE /users/{id} -> update user {id}
   
   Delete     -> DELETE /users/{id} -> delete user {id}
                 DELETE /users      -> delete all users in DB
   
   Patch      -> Used to update part of a resource

2. Create/Update/Patch/Read/Delete posts for each user

    Post: ID, Description

   Create user -> POST /users/{id}/posts  -> This should create a post for user {id}
   
   Read user   -> GET /users/{id}/posts -> returns back all posts for user {id}
                  GET /users/{id}/posts/{postId} -> returns back a post {postID} for user {id}
                 
   Update      -> UPDATE /users/{id}/posts/{postId} -> update post {postId} for user {id}
   
   Delete      -> DELETE /users/{id}/posts/{postId} -> delete post {postId} for user {id}
                  DELETE /users/{id}/posts    -> delete all posts for user {id}
              
   Patch       -> Used to update part of a resource
   
   
   
   
   
   
  --------------------------------------------- STEPS TO CONNECT TO MYSQL DATABASE ------------------------------------------------
     
a. Create container using command 

    docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=social-media-user --env MYSQL_PASSWORD=dummypassword --env MYSQL_DATABASE=social-media-database --name mysql --publish 3306:3306 mysql:8-oracle***
  
b. Now add details to eclipse pom.xml

//To connect application to mySql DB in c=docker container mysql. Point datasourse to container.***
    
    spring.datasource.url=jdbc:mysql://localhost:3306/social-media-database**

    spring.datasource.username=social-media-user**

    spring.datasource.password=dummypassword**

//When using h2 DB spring auto-configuration will create tabled. But we need below line to make database schema to work for mySql**

    spring.jpa.hibernate.ddl-auto=update**

    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect **

c. Now run command application to see if it is running. You should be able to run commands on talend API tester[chrome-extension://aejoelaoggembcahagimdiliamlcdmfm/index.html#requests] and browsers..But how to make sure its running on mySQL db?

d. To confirm (c) make sure you have mysqlsh. Download if you dont have it in your computer from here[https://dev.mysql.com/doc/mysql-shell/8.0/en/mysql-shell-install-macos-quick.html].
   
   d.1. Then in your terminal run **mysqlsh**. This will show the version of mysqlsh you have in your computer. 
  
   d.2. Connect to your social-media DB- **connect social-media-user@localhost:3306** and enter password. If schema is not set. Set it as to your schema **\use social-media-database** defined in step (a).
   
   d.3. Now change mode to run sql query's with command **/sql** and run select queries to see if you see the users and posts. You can add users and posts to table via talend API tester
   
   d.4. Also, data in DB will persists even if you restart application. This shows it is not h2 db, but mysql database that is not reseting every time application restarts.
   
   <img width="1449" alt="Screenshot 2023-05-06 at 8 09 23 PM" src="https://user-images.githubusercontent.com/23514932/236655654-f8e324e5-be55-40fe-b54f-6c92f9fe5a0a.png">


   ----------------------------------------- spring-security-----------------------------------------
   
   1. Right now anyone can add/read users and posts from out db,but this is not good. To avoid this we can add security and a password.
   
   2. Add sprint-security dependency and run application. This should create password. You will see it in the console.
        
         <dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
              
   3. Now if you try accessing db it will ask for credentials. Enter credentials below
    
    user name: user 
    password: the one generated in the console.
    
   <img width="1103" alt="Screenshot 2023-05-06 at 8 35 23 PM" src="https://user-images.githubusercontent.com/23514932/236656263-87ca77fa-fd72-4098-b6d0-5e1dc861f1d6.png">
 
   4. Or you can create custom username and password in application.properties file. as shown below
      
    spring.security.user.name=username
    spring.security.user.password=mysqlpassword
    
   5. When you run a POST sql you will get a forbidden error. The reason is Spring-security runs a series of **filter chain commands**.
      Some of them are
       
    5.1) All requests should be authenticated  -> This makes sure all requests are authenticated
    5.2) If a request is not authenticated, a web page is shown  -> if not authenticated will show a webpage for username and password. But for rest services we want to show a popup instead of webpage
    5.3) CSRF -> POST, PUT are blocked because of this. So we need to diable it.
    
   However in spring when you are overridding a certain filter chain we need to re-write a all of them. For this reason added class **SpringSecurityConfiguration** to  define spring-security configurations.

