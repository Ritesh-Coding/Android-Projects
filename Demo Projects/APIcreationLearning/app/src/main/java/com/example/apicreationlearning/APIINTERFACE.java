package com.example.apicreationlearning;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIINTERFACE {
    //In these with multiple annotation we declare our endoints
//    annotation such post,delete,header,etc.
//    most widely used annotation we use post and Get
//    Get is used to get all the data(List of data without any parameters)
//    post is used when we want to transfer the data into the server from the application

    //for test purpose we take the fake json api from the json placeholder website and test in the postman application
//    website fake : https://jsonplaceholder.typicode.com/posts
//    postman is an API platform for building and usig APi


    //declare end points

    @GET("posts")
    //    builtin retrofit funtion THAT generate HTTP request
    Call<List<PostPOJO>>  getposts();//IN THESE WE PASS THE  DATA FORMAT IN PARAMETERS THAT COMES FROM THE SERVER  RESPONSE FOR THESE WE USE CREATE POJO
//    CLASS FOR API RESPONSE
    //here we define the getposts() method name manually

}


