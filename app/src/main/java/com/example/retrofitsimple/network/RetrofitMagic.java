package com.example.retrofitsimple.network;

import com.example.retrofitsimple.entities.Student;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 8/10/2016.
 */
public class RetrofitMagic {

    public static void main(String... args){

        //3. Creating Retrofit builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mocky.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Retrofit instance with
        //attribute url - http "http://www.mocky.io/"
        //attribute converter -- gson

        //4. Instantiating the interface via the Retrofit object
        NamesInterface namesInterface
                = retrofit.create(NamesInterface.class);

        //5. Setting up the method to be called from the interface
        Call<ArrayList<Student>> studentCall
                = namesInterface.retrieveStudents();

        try {
            //6. Executing the Retrofit call
            List<Student> students =
                    studentCall.execute().body();

            for(Student student: students){
                System.out.println(student.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
