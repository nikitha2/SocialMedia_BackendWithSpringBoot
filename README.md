# SocialMedia_BackendWithSpringBoot


Tech Stack: Spring-boot, java, JPA, h2 database


Create rest apis around users and posts

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
   
   
   
   
   
   
   
   
STEPS TO CONNECT TO MYSQL DATABASE:
   
a. Create container using command 
  *** docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=social-media-user --env MYSQL_PASSWORD=dummypassword --env MYSQL_DATABASE=social-media-database --name mysql --publish 3306:3306 mysql:8-oracle***
  
b. Now add details to eclipse pom.xml

//To connect application to mySql DB in c=docker container mysql. Point datasourse to container.***
**	 spring.datasource.url=jdbc:mysql://localhost:3306/social-media-database**

**	 spring.datasource.username=social-media-user**

**	 spring.datasource.password=dummypassword**

//When using h2 DB spring auto-configuration will create tabled. But we need below line to make database schema to work for mySql**

**	 spring.jpa.hibernate.ddl-auto=update**

**	 spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect **

c. Now run command application to see if it is running. You should be able to run commands on talend API tester[chrome-extension://aejoelaoggembcahagimdiliamlcdmfm/index.html#requests] and browsers..But how to make sure its running on mySQL db?

d. To confirm (c) make sure you have mysqlsh. Download if you dont have it in your computer from here[https://dev.mysql.com/doc/mysql-shell/8.0/en/mysql-shell-install-macos-quick.html].
   
   Then in your terminal run **mysqlsh**. This will show the version of mysqlsh you have in your computer
   
   
   
              
