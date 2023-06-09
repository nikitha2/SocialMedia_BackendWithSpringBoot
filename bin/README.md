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
              
