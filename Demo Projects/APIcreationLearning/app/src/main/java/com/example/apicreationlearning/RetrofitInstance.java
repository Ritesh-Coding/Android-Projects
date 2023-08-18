package com.example.apicreationlearning;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance { //class for retrofit instance
    private static Retrofit retrofit; //here we made a variable that we can call without calling of object
    private static final String BASEURL = "https://jsonplaceholder.typicode.com/";
    //we define the server base url here in this class ..suppose that in our project we have 10 API and all the same URL we write it


    //jis v class k andar hume apne retrofit instance ko use krna h vha se private variable ko get krne k liye getter ki jarurat hogi
//    with the help of these retrofit object can be get
    public static Retrofit getRetrofit() {
        //hum ek codition laga dete h to baar baar hamare memory k andar retrofit ka object create na ho
        if (retrofit == null) {
            retrofit=new Retrofit.Builder().baseUrl(BASEURL).addConverterFactory(GsonConverterFactory.create()).build();
//            convertor:-->From the server we get the data in the Json format we have to convert the data into the JAVA OBJECT NOTATIOON
        }

     return retrofit;  //RETURN THE ALREADY RETROFIT OBJECT
}


}
