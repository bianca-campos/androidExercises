package com.campos.bianca.lab3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String[] files = {"catch_me_if_you_can", "flight_club", "forrest_gump", "good_will_hunting", "pulp_fiction", "the_godfather",
                "the_hangover", "the_redemption", "titanic"};
        Spinner spinner = findViewById(R.id.name_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String describe = "";
                int img_id = getResources().getIdentifier(files[position], "drawable", getPackageName());
                int txt_id = getResources().getIdentifier(files[position], "raw", getPackageName());
                ImageView imgView = (ImageView) findViewById(R.id.img_iv);
                imgView.setImageResource(img_id);
                TextView txtView = findViewById(R.id.name_tv);
                //res/raw/name.txt
                txtView.setText(txt_id);
                Scanner scan = new Scanner(getResources().openRawResource(txt_id));
                    while (scan.hasNextLine()) {
                        describe += scan.nextLine();
                    }
                txtView.setText(describe);
                scan.close();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
