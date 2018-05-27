package com.campos.bianca.lab1;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private int score;
    private String sortedColor1;
    private String sortedColor2;
    int leftColor;
    int rightColor;
    int showColorText;
    private ArrayList<Integer> colorsArrayList = new ArrayList<Integer>();
    private HashMap<Integer, String> txtMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMap();
        colorsArrayList.add(Color.BLACK);
        colorsArrayList.add(Color.BLUE);
        colorsArrayList.add(Color.CYAN);
        colorsArrayList.add(Color.YELLOW);
        colorsArrayList.add(Color.GRAY);
        colorsArrayList.add(Color.GREEN);
        colorsArrayList.add(Color.RED);
        colorsArrayList.add(Color.MAGENTA);
        colorsArrayList.add(Color.DKGRAY);
        colorsArrayList.add(Color.LTGRAY);
        generateRandomColor();
    }

    private void initMap(){
        txtMap.put(Color.BLACK, "Black");
        txtMap.put(Color.BLUE, "Blue");
        txtMap.put(Color.CYAN, "Cyan");
        txtMap.put(Color.YELLOW, "Yellow");
        txtMap.put(Color.GRAY, "Gray");
        txtMap.put(Color.GREEN, "Green");
        txtMap.put(Color.RED, "Red");
        txtMap.put(Color.MAGENTA, "Magenta");
        txtMap.put(Color.DKGRAY, "Dark Gray");
        txtMap.put(Color.LTGRAY, "Light Gray");

    }

    private void generateRandomColor(){
        int randomColor1 = (int) (Math.random() * 10);
        int randomColor2 = (int) (Math.random() * 10);
        while (randomColor1 == randomColor2){
             randomColor2 = (int) (Math.random() * 10);
        }

        Button leftButton = findViewById(R.id.leftButton);
        leftColor = colorsArrayList.get(randomColor1);
        leftButton.setBackgroundColor(leftColor);

        Button rightButton = findViewById(R.id.rightButton);
        rightColor = colorsArrayList.get(randomColor2);
        rightButton.setBackgroundColor(rightColor);

        int[] colors = {leftColor, rightColor};
        int randomText = (int) Math.random() * 2;
        showColorText = colors[randomText];

        TextView textView = findViewById(R.id.showColor);
        textView.setText(txtMap.get(showColorText));

    }

    public void buttonLeftClicked(View view) {
        String show = "";
        if (view.getId() == R.id.leftButton) {
            if (leftColor == showColorText) {
                score++;
                show = "Right";
            } else {
                score--;
                show = "Wrong";
            }

            Toast.makeText(this, "You Are " + show, Toast.LENGTH_SHORT).show();
            TextView scoreView = findViewById(R.id.score);
            scoreView.setText("Score: " + score);
            generateRandomColor();
        }
    }


    public void buttonRightClicked(View view) {
        String show = "";
        if (view.getId() == R.id.rightButton) {
            if (rightColor == showColorText) {
                score++;
                show = "Right";
            } else {
                score--;
                show = "Wrong";
            }

            Toast.makeText(this, "You Are " + show, Toast.LENGTH_LONG).show();
            TextView scoreView = findViewById(R.id.score);
            scoreView.setText("Score: " + score);
            generateRandomColor();
        }
    }
}
