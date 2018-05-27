package com.campos.bianca.layoutexamples;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.topTV);
        tv.setText(R.string.topLabelChanged);
        // String of the ID selected
        String tvString = getResources().getString(R.string.topLabel);
        // ID of the selected array
        int a = R.array.tmnt;

        Spinner spinner = findViewById(R.id.tmnt_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = findViewById(R.id.topTV);
                tv.setText("" + parent.getSelectedItem());

                ImageView img = (ImageView) findViewById(R.id.imageView);
                if (position == 0){
                    img.setImageResource(R.drawable.nt);
                } else if (position == 1) {
                    img.setImageResource(R.drawable.leonardo);
                } else if (position == 2){
                    img.setImageResource(R.drawable.raphael);
                } else if (position == 3){
                    img.setImageResource(R.drawable.michelangelo);
                } else {
                    img.setImageResource(R.drawable.donatello);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        RadioButton rbninjaturtles, brleo, rbrafh, rbmich, don;
            rbninjaturtles = (RadioButton) findViewById(R.id.rbNT);
            brleo = (RadioButton) findViewById(R.id.rbLeonardo);
            rbrafh = (RadioButton) findViewById(R.id.rbRaphael);
            rbmich = (RadioButton) findViewById(R.id.rbMichelangelo);
            don = (RadioButton) findViewById(R.id.rbDonatello);

         View.OnClickListener radio_listener0 = new View.OnClickListener() {
            public void onClick(View v) {
                ImageView i = (ImageView) findViewById(R.id.imageView);
                i.setImageResource(R.drawable.nt);
                setTextViewRB((RadioButton) v);
            }
        };

        View.OnClickListener radio_listener1= new View.OnClickListener() {
            public void onClick(View v) {
                ImageView i = (ImageView) findViewById(R.id.imageView);
                i.setImageResource(R.drawable.leonardo);
                setTextViewRB((RadioButton) v);
            }
        };

        View.OnClickListener radio_listener2 = new View.OnClickListener() {
            public void onClick(View v) {
                ImageView i = (ImageView) findViewById(R.id.imageView);
                i.setImageResource(R.drawable.raphael);
                setTextViewRB((RadioButton) v);
            }
        };

        View.OnClickListener radio_listener3 = new View.OnClickListener() {
            public void onClick(View v) {
                ImageView i = (ImageView) findViewById(R.id.imageView);
                i.setImageResource(R.drawable.michelangelo);
                setTextViewRB((RadioButton) v);
            }
        };

        View.OnClickListener radio_listener4 = new View.OnClickListener() {
            public void onClick(View v) {
                ImageView i = (ImageView) findViewById(R.id.imageView);
                i.setImageResource(R.drawable.donatello);
                setTextViewRB((RadioButton) v);
            }
        };

        rbninjaturtles.setOnClickListener(radio_listener0);
        brleo.setOnClickListener(radio_listener1);
        rbrafh.setOnClickListener(radio_listener2);
        rbmich.setOnClickListener(radio_listener3);
        don.setOnClickListener(radio_listener4);

        }

    private void setTextViewRB(RadioButton v) {
        TextView tv = (TextView) findViewById(R.id.topTV);
        String str = v.getText().toString();
        tv.setText(str);
    }


}

