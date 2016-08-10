package com.example.retrofitsimple;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.retrofitsimple.entities.Student;
import com.example.retrofitsimple.network.NamesInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private LibuAdapter mLibuAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<Student> mStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

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
            ArrayList<Student> students =
                    studentCall.execute().body();

/*            for(Student student: students){
                System.out.println(student.toString());
            }*/

            mLibuAdapter = new LibuAdapter(students);

            mRecyclerView = (RecyclerView) findViewById(R.id.a_main_recycler);
            mRecyclerView.setAdapter(mLibuAdapter);
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
