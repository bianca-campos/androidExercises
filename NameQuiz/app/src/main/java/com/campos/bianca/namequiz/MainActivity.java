package com.campos.bianca.namequiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private static final String TAG = "MainActivity";
    private HashMap<String, String> namesMap;
    private TextView mTextView;
    private int current_score;
    private int highest_score;

    @Override
    protected void onPause() {
        super.onPause();
//        length = mp.getCurrentPosition();
//        mp.pause();
        Log.v(TAG, "OnPause method got called.");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        mp.seekTo(length);
//        mp.start();
        Log.v(TAG, "OnResume method got called.");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.v(TAG, "OnStop method got called.");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.v(TAG, "OnStart method got called.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.v(TAG, "OnDestroy method got called.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.v(TAG, "OnRestart method got called.");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        current_score = 0;
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        highest_score = prefs.getInt("highest", 0);

//        MediaPlayer mp = MediaPlayer.create(this, R.raw.shape_of_you);
//        mp.start();

        // access the intent that store the activity
        Intent intent = getIntent();
        String secret = intent.getStringExtra("secret");
        toast(secret);

        namesMap = new HashMap<>();
        // put the data into map
        try {
            readContentFromFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        getQuestion();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // store instance state before gets destroyed.
        TextView tv = findViewById(R.id.firstname_tv);
        ListView lv = findViewById(R.id.lastname_lv);
        // get all names in ListView
        ListAdapter listAdapter = lv.getAdapter();
        ArrayList<String> currentOptions = new ArrayList<>();

        for (int i = 0; i < listAdapter.getCount(); i++){
            currentOptions.add(listAdapter.getItem(i).toString());

        }
        outState.putInt("current_score", current_score);
        outState.putString("firstname",tv.getText().toString()); // store current first name
        outState.putStringArrayList("options", currentOptions); // store current last names

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // getting instance state after getting created.
        String savedName = savedInstanceState.getString("firstname", "Mark");
        ArrayList<String> savedOptions = savedInstanceState.getStringArrayList("options");
        current_score = savedInstanceState.getInt("current_score", 0);

        TextView tv = findViewById(R.id.firstname_tv);
        tv.setText(savedName);

        ListView lv = findViewById(R.id.lastname_lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, savedOptions);
        lv.setAdapter(adapter);


    }

    private void getQuestion() {
        ArrayList<String> firstnames = new ArrayList<>(namesMap.keySet());
        ArrayList<String> lastnames = new ArrayList<>(namesMap.values());
        int rand = (int) (Math.random() * firstnames.size());

        String fn = firstnames.get(rand);
        String ln = namesMap.get(fn);

        lastnames.remove(ln);
        Collections.shuffle(lastnames);

        ArrayList<String> option = new ArrayList<>(lastnames.subList(0,4));
        option.add(ln);

        Collections.shuffle(option);


        mTextView = findViewById(R.id.firstname_tv);
        mTextView.setText(fn);
        ListView lv = findViewById(R.id.lastname_lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, option);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);
    }


    // 1. set onItemClickListener
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        // 2. check the answer
        String question = mTextView.getText().toString();
        String answer = namesMap.get(question);
        String selected = parent.getItemAtPosition(position).toString();
        if (answer.equals(selected)){
            current_score++;
            if (current_score > highest_score){
                highest_score = current_score;
                // 1. write to a text file
                // 2. use database
                // ==> use Preference!

                SharedPreferences prefs = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = prefs.edit();

                prefsEditor.putInt("highest", highest_score);
                prefsEditor.commit();
            }
            toast("Right! " + current_score + " Highest: " + highest_score);
            getQuestion();
        } else{
            current_score--;
            toast("Wrong! " + current_score + " Highest: " + highest_score);
        }
    }

    private void toast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void readContentFromFile() throws FileNotFoundException {
        // read file "names" in raw folder
        Scanner scan = new Scanner(getResources().openRawResource(R.raw.names));
        while (scan.hasNextLine()){
            // scan each line
            String line = scan.nextLine();
            String[] fullname = line.split("\t");
            namesMap.put(fullname[0], fullname[1]);
        }
        Scanner scan2 = new Scanner(openFileInput("new_names2.txt"));
        while (scan2.hasNextLine()){
            String line = scan2.nextLine();
            String[] fullname2 = line.split("\t");
            namesMap.put(fullname2[0], fullname2[1]);
        }
        scan.close();
        scan2.close();
    }
}
