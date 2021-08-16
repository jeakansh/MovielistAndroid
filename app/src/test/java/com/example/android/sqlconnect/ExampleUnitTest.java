package com.example.android.sqlconnect;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void tes(){
        String MOVIE_TABLE="MOVIE_TABLE";
         String ACTOR = "ACTOR";
         String name = "pankaj";
        String queryString = "SELECT * FROM " + MOVIE_TABLE + " WHERE " + ACTOR + " = '" + name +"'";
        System.out.println(queryString);
    }

}
