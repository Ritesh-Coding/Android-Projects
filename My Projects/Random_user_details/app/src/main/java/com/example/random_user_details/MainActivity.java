package com.example.random_user_details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

import static com.android.volley.Request.*;
public class MainActivity extends AppCompatActivity {
    // declaring widgets here for example
    TextView firstName, lastName,titleT,genderT,cityT,stateT,countryT,emailT,phoneT,cellT;
    ImageView userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userProfile = findViewById(R.id.userProfilePic);
        firstName = findViewById(R.id.firstNameT);
        lastName = findViewById(R.id.lastNameT);
        genderT = findViewById(R.id.GenderT);
        titleT = findViewById(R.id.userTitleT);
        cityT = findViewById(R.id.CityT);
        stateT = findViewById(R.id.StateT);
        countryT = findViewById(R.id.CountryT);
        emailT = findViewById(R.id.EmailT);
        phoneT = findViewById(R.id.PhoneT);
        cellT = findViewById(R.id.CellT);

        // am going to create a timer here to schedule our task - to load or Refresh the data wihtin a specific time period like i takin (5Sec).a new user Data will be appear after every 5sec
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //am calling a user-Defined method here- which will be written outside of our "onCreate"_Method and will executed when call it here
                userGetter();
            }
        },0,5000);





    }

    public void userGetter (){
        /*This is our - UserDefined Method. where we use the volley Library to get the json data(Array/Obeject/Strings .etc)
        and in this sample we will get the the data from a "Random user api provider"-website(https://randomuser.me) Link to get url :"https://randomuser.me/api/"
        * first go "AndroidManifest.xml" File and add the internet Permission <uses-permission android:name="android.permission.INTERNET"/> because internet will be used here
         *then go your Gradle script (Module App) and add the volley library (Check from official Website :https://developer.android.com/training/volley)
        *  in the "dependency -section
        * */

        //        * then you need to create a Request Queue
        RequestQueue queue = Volley.newRequestQueue(this);
//        * create a String for api url
        String url = "https://randomuser.me/api/";

        //*creating a new jsonObjectRequest to start our API - json Data fetching process
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Here we will get the response from our provided API-URL
                //Here we write our command codes with "try & catch to avoid and trace the exceptions
                try {
                    /*According to this code we are geting 1-Image and User's 2-firstName and 3-lastName
                     *show then in our App  */
                    JSONArray resultsArray = response.getJSONArray("results"); //we are getting the main Array(in which our all data is stored/displaying) from our Json Data
//
                    JSONObject mainResultArray = resultsArray.getJSONObject(0); //we are creating and attaching an JsonObject with our MainArray - to get UserProfile-Picture
                    JSONObject pictureObjectO = mainResultArray.getJSONObject("picture"); /*we are giving our picture Holder Object-Name (which is written as "picture" in json)
                                                                                       to JsonObject which we had created before "userPictures" on line_number:83  */
                    String userPicUrl = pictureObjectO.getString("large");  /* here we are creating a String to get the url link (from the pictureObject we created before) of our desired picture -

                                                                                there were three sizes of pictures on json api page (large,medium,thumbnail) i select the "large"*/
//                    JSONObject Name = resultsArray.getJSONObject(0); //creating and attching the JsonObject with Main JsonArray
//                    JSONObject UserNamesT= Name.getJSONObject("name");//giving json JsonObject  Name . it "name" on json data. and name has further two Object (first) for first_name and (last) for last_name.


                    JSONObject UserNamesT = mainResultArray.getJSONObject("name");


                    String firstNameU = UserNamesT.getString("first"); //creating string to get the first_name of user
                    String lastNameU = UserNamesT.getString("last"); // creating string to get the last name of user
                    firstName.setText(firstNameU);// set the first Name with a String which we had created before (Line-Number : 90)
                    lastName.setText(lastNameU); // set the first Name with a String which we had created before (Line-Number : 91)
                    titleT.setText(UserNamesT.getString("title"));
                    genderT.setText("Gender : " + mainResultArray.getString("gender"));
                    emailT.setText("Email : " + mainResultArray.getString("email"));
                    phoneT.setText("Phone : " + mainResultArray.getString("phone"));
                    cellT.setText("Cell Phone : "+ mainResultArray.getString("cell"));

                    JSONObject locationDetails = mainResultArray.getJSONObject("location");
                    cityT.setText("City : "+ locationDetails.getString("city"));
                    stateT.setText("State : "+ locationDetails.getString("state"));
                    countryT.setText("Country : "+ locationDetails.getString("country"));

                    // now we are going to set our user_profile-picture and name (first and Last) to our Views
                    Picasso.get().load(userPicUrl).into(userProfile); /*we used "Picasso Plugin to get the image from a Url and show then into our app's ImageView Widget.
                    to  use the Picasso Plugin you need to add Picasso Library (implementation 'com.squareup.picasso:picasso:2.71828') in to you Gradle Build (Module-App) in the dependency Section*/

                } catch (JSONException e) {
                    //here we are handling the Exception to trace the error and avoid the app crash.
                    Toast.makeText(MainActivity.this, "faild to get user details from server", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //here you can define : what to do if your api is not working
                Toast.makeText(MainActivity.this, "something is went wrong", Toast.LENGTH_SHORT).show();

            }
        });
        //Ad a request to the request Queue to get Json Data from API - Good Luck
        // Note: You can fetch/parse data as mush as you want am just use a little bit stuff to make it easy to understand
        queue.add(jsonObjectRequest);
    }

}

