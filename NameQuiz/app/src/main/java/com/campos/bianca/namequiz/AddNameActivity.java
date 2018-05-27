package com.campos.bianca.namequiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

public class AddNameActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_name);
    }


    private void writeToFile(String data,Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("new_names.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    /**
     * VALIDATION
     * @param text - first name and last name
     * @return TRUE if the text has no numbers, no special char, no spaces. Otherwise, false.
     */
    public boolean isNameValid(String text){
        return text.matches("^([A-Za-z]+)(\\s[A-Za-z]+)*\\s?$");
    }

    public void addNewNames(View view) throws FileNotFoundException {
        String fullName;
        // add a new name
        // 1. get first name from the (edit text)
        EditText first = findViewById(R.id.firstname_et);
        String fn = first.getText().toString();

        // 2. get last name from the (edit text)
        EditText second = findViewById(R.id.lastname_et);
        String ln = second.getText().toString();

        // 3. validation check (your job later)
        // 4. add name to the database (file)
        // 5. Send new name back to the menu activity to alert the user
        Intent intent = new Intent();
        if (isNameValid(fn) == true && isNameValid(ln) == true){
            fullName = fn + " " + ln;
            intent.putExtra("newName", fullName);
            setResult(RESULT_OK, intent);
            try {
                PrintStream output = new PrintStream(openFileOutput("new_names2.txt", MODE_PRIVATE | MODE_APPEND));
                output.println(fn + "\t" + ln);
                output.close();
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
        } else {
            setResult(RESULT_CANCELED, intent);
        }
        finish();
    }
}
