package com.campos.bianca.randomquotes;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.sdsmdg.harjot.rotatingtext.RotatingTextWrapper;
import com.sdsmdg.harjot.rotatingtext.models.Rotatable;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private final int NUMBER_FONT = 7;
    public TextView tv;
    private ArrayList<Integer> colorsArrayListBack = new ArrayList<Integer>();
    private ArrayList<Integer> colorsArrayListFont = new ArrayList<Integer>();
    private int counterBackground = 0;
    private int counterColorFont = 0;
    private int counterSizeFont = 0;
    private int counterFontType = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tvJson);
        tv.setMovementMethod(new ScrollingMovementMethod());

        colorsArrayListBack.add(Color.rgb(247, 206, 224)); // Pantone Cherry Blossom
        colorsArrayListBack.add(Color.rgb(255, 245, 193)); // light yellow
        colorsArrayListBack.add(Color.rgb(159, 134, 170)); // Pantone rhapsody
        colorsArrayListBack.add(Color.rgb(252, 197, 162)); // light orange
        colorsArrayListBack.add(Color.rgb(204, 255, 255)); //light blue
        colorsArrayListBack.add(Color.rgb(82, 233, 213)); // light green
        colorsArrayListBack.add(Color.rgb(216, 216, 216)); // light gray

        colorsArrayListFont.add(Color.rgb(255, 176, 37)); // Pantone Citrus
        colorsArrayListFont.add(Color.rgb(198, 33, 104)); // Pantone Pink Peacock
        colorsArrayListFont.add(Color.rgb(238, 234, 227)); // Pantone Gardenia
        colorsArrayListFont.add(Color.rgb(4, 51, 255)); // blue
        colorsArrayListFont.add(Color.rgb(4, 117, 111)); // green


        // set a change listener on the SeekBar
        SeekBar seekBar = findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(seekBarChangeListener);
        int progress = seekBar.getProgress();

    }





    private void getJson() {
        Ion.with(this)
                .load("http://api.forismatic.com/api/1.0/")
                .setBodyParameter("method", "getQuote")
                .setBodyParameter("key", "")
                .setBodyParameter("format", "json")
                .setBodyParameter("lang", "en")
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {
                        TextView tv = findViewById(R.id.tvJson);
                        String quote = result.get("quoteText").getAsString();
                        String author = result.get("quoteAuthor").getAsString();
                        tv.setText(quote + "\n\n" + author);
                        YoYo.with(Techniques.FadeIn)
                                .duration(700)
                                .repeat(0)
                                .playOn(tv);
                    }
                });
    }

    public void callApi(View view) {
        getJson();
    }

    public void setBackgroundColor(View view) {
        Button btnBackground = (Button) findViewById(R.id.btnBackground);
        LinearLayout ll = (LinearLayout) findViewById(R.id.llColor);
        if (counterBackground >= colorsArrayListBack.size()) {
            counterBackground = 0;
        }
        ll.setBackgroundColor(colorsArrayListBack.get(counterBackground));
        counterBackground++;
        toast("Background Color: " + counterBackground + " of: " + colorsArrayListBack.size());

    }


    public void setFontType(View view) {
        Button btnFont = (Button) findViewById(R.id.btnFontType);
        if (counterFontType >= NUMBER_FONT) {
            counterFontType = 0;
        }

        counterFontType++;
        if (counterFontType == 1) {
            Typeface typeface = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                typeface = getResources().getFont(R.font.cac_champagne);
            }
            tv.setTypeface(typeface);
        } else if (counterFontType == 2) {
            Typeface typeface = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                typeface = getResources().getFont(R.font.josefinsans_bold);
            }
            tv.setTypeface(typeface);
        } else if (counterFontType == 3) {
            Typeface typeface = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                typeface = getResources().getFont(R.font.pacifico);
            }
            tv.setTypeface(typeface);
        } else if (counterFontType == 4) {
            Typeface typeface = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                typeface = getResources().getFont(R.font.roboto_regular);
            }
            tv.setTypeface(typeface);
        } else if (counterFontType == 5) {
            Typeface typeface = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                typeface = getResources().getFont(R.font.robotocondensed_bold);
            }
            tv.setTypeface(typeface);
        } else if (counterFontType == 6) {
            Typeface typeface = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                typeface = getResources().getFont(R.font.seasrn);
            }
            tv.setTypeface(typeface);
        } else if (counterFontType == 7) {
            Typeface typeface = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                typeface = getResources().getFont(R.font.roboto_black);
            }
            tv.setTypeface(typeface);
        }

        toast("Type Font: " + counterFontType + " of: " + NUMBER_FONT);

    }

    public void setFontColor(View view) {
        Button btnFontColor = (Button) findViewById(R.id.btnFontColor);
        if (counterColorFont >= colorsArrayListFont.size()) {
            counterColorFont = 0;
        }
        tv.setTextColor(colorsArrayListFont.get(counterColorFont));
        counterColorFont++;
        toast("Color Font: " + counterColorFont + " of: " + colorsArrayListFont.size());
    }


    SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            tv.setTextSize(progress/2);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };

    public void downloadImg(View view) {

    }

    private void toast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}

