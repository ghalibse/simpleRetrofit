package com.example.retrofitsimple.network;

import com.example.retrofitsimple.entities.Student;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by admin on 8/10/2016.
 */
public interface NamesInterface {

    //2. Add interface for the End points
    @GET("/v2/57a4dfb40f0000821dc9a3b8")
    Call<ArrayList<Student>> retrieveStudents();
}
