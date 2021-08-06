package com.example.android.sqlconnect;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button bt_display, searchByActor, searchByActress;
    EditText name;
    ListView movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_display = findViewById(R.id.display_all);
        searchByActor = findViewById(R.id.searchByActor);
        searchByActress = findViewById(R.id.searchByActress);
        name = findViewById(R.id.name);
        movieList = findViewById(R.id.list_view);


        MovieModel movieModel = new MovieModel(0, "INTERSTELLAR", 2014, "Matthew McConaughey", "Anne Hathaway" );
        MovieModel movieMode2 = new MovieModel(0, "INCEPTION", 2010, "Leonardo DiCaprio", " " );
        MovieModel movieMode3 = new MovieModel(0, "PARASITE", 2019, "Seo-joon", " Yeo-jeong" );
        MovieModel movieMode4 = new MovieModel(0, "YOUR NAME", 2016, "", " " );
        MovieModel movieMode5 = new MovieModel(0, "ARRIVAL", 2016, "Amy Adams", "Jeremy Renner" );
        MovieModel movieModel6 = new MovieModel(0, "MIMI", 2021, "Pankaj Tripathi" , "Kriti Sanon");
        MovieModel movieModel7 = new MovieModel(0, "JHONNY ENGLISH", 2003, "Rowan Atkinson" , "");

        DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
        dataBaseHelper.addData(movieModel);
        dataBaseHelper.addData(movieMode2);
        dataBaseHelper.addData(movieMode3);
        dataBaseHelper.addData(movieMode4);
        dataBaseHelper.addData(movieMode5);
        dataBaseHelper.addData(movieModel6);
        dataBaseHelper.addData(movieModel7);


        bt_display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                List<MovieModel> everyone = dataBaseHelper.displayall();
//                Toast.makeText(MainActivity.this, everyone.toString(), Toast.LENGTH_SHORT).show();

                ArrayAdapter customArrayAdapter = new ArrayAdapter<MovieModel>(MainActivity.this, android.R.layout.simple_list_item_1,everyone);
                movieList.setAdapter(customArrayAdapter);
            }
        });

        searchByActor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView name = (TextView) findViewById(R.id.name);
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                Cursor search = dataBaseHelper.searchData(name.toString());
                if(search.getCount() == 0){
                    Toast.makeText(MainActivity.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(search.moveToNext())
                {
                    buffer.append("Actor: "+ search.getString(3) + "\n");
                    buffer.append("Name :" +search.getString(1) + "\n");
//                    buffer.append("Year :" +search.getInt(2)+ "\n");

//                    buffer.append("Actress: "+ search.getString(4) + "\n");
                    buffer.append("\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Movies Found: ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        searchByActress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView name = (TextView) findViewById(R.id.name);
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                Cursor search = dataBaseHelper.searchData(name.toString());
                if(search.getCount() == 0){
                    Toast.makeText(MainActivity.this, "Data Not Found", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(search.moveToNext())
                {
                    buffer.append("Actress: "+ search.getString(4) + "\n");
                    buffer.append("Name :" +search.getString(1) + "\n");
//                    buffer.append("Year :" +search.getInt(2)+ "\n");

//                    buffer.append("Actress: "+ search.getString(4) + "\n");
                    buffer.append("\n");
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Movies Found: ");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });
    }



}