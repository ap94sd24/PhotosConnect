/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

      /**
       * Add a class containing some fields to parse
       */
    /*ParseObject score = new ParseObject("Score");

    score.put("username", "Joe");
    score.put("score", 78);
    score.put("username", "James");
    score.put("score", 69);
    score.saveInBackground(new SaveCallback() {
      @Override
      public void done(ParseException e) {
        if (e == null) {
          Log.i("Success", "We saved the score");
        } else {
          e.printStackTrace();
        }
      }
    }); */

      /**
       * Get an object from parse
       * Update an object from parse
       */
   /* ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");
    query.getInBackground("GCMKqOq3t1", new GetCallback<ParseObject>() {
      @Override
      public void done(ParseObject object, ParseException e) {
        if (e == null && object != null) {

            object.put("score", 95);
            object.saveInBackground();

          Log.i("username", object.getString("username"));
          Log.i("score", Integer.toString(object.getInt("score")));
        }
      }
    }); */



      /**
       * Get all the objects from parse
       */
  /* ParseQuery<ParseObject> query = ParseQuery.getQuery("Score");

   query.whereEqualTo("username", "Adam");
   query.setLimit(1);

   query.findInBackground(new FindCallback<ParseObject>() {
       @Override
       public void done(List<ParseObject> objects, ParseException e) {
           if (e == null) {
               if (objects.size() > 0) {
                   for (ParseObject obj: objects) {
                       Log.i("username", obj.getString("username"));
                       Log.i("score", Integer.toString(obj.getInt("score")));
                   }
               }
           }
       }
   }); */



    
    ParseAnalytics.trackAppOpenedInBackground(getIntent());
  }

}