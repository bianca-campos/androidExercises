package com.campos.bianca.namequiz;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    private static final int REQ_CODE_ADD_NAME = 1111;
    private static final String TAG = "MenuActivity";
    private MediaPlayer mp;
    private int length;

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
        setContentView(R.layout.activity_menu);

        MediaPlayer mp = MediaPlayer.create(this, R.raw.shape_of_you);
        mp.start();

        Log.v(TAG, "OnCreate method got called.");
    }

    public void playButtonClicked(View view) {
        // I want to start a MainActivity
       Intent intent = new Intent(this, MainActivity.class);
       // add extra data into the next screen
       intent.putExtra("secret", "yeah");  // "id", ""data;

        // just star an Activity
       startActivity(intent);
    }


    public void addNameButtonClicke(View view) {
        // start an Activity, but come back later when you're done
        Intent intent = new Intent(this, AddNameActivity.class);
        startActivityForResult(intent, REQ_CODE_ADD_NAME);
    }

    private void toast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE_ADD_NAME){
            if (resultCode == RESULT_OK){
               String newName = data.getStringExtra("newName");
               String[] names = newName.split(" " );
               toast(newName + " is added!");
            } else {
                toast("ERROR!! Please, add a valid name!");
            }
        }
    }
}
