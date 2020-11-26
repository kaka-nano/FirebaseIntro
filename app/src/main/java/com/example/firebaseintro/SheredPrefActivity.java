package com.example.firebaseintro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class SheredPrefActivity extends AppCompatActivity {

    // declare object
    private TextView textView;
    private EditText editText;
    private Button applyTextButton;
    private Button saveButton;
    private Switch switch1;

    // define variable to red our shered Preference object
    public static final String SHARED_PREFS = "sharedPrefs";

    //Variables to set text and switch
    public static final String TEXT = "text";
    public static final String SWITCH1 = "switch1";

    private String text;
    private boolean switchOnOff;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // references to views
        textView = (TextView) findViewById(R.id.textview);
        editText = (EditText) findViewById(R.id.edittext);
        applyTextButton = (Button) findViewById(R.id.apply_text_button);
        saveButton = (Button) findViewById(R.id.save_button);
        switch1 = (Switch) findViewById(R.id.switch1);

        // button on click implementations
        applyTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get input from user
                textView.setText(editText.getText().toString());
                textView.setText(text);
            }
        });

        // save data in the shared preferences
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveData();
            }
        });

        loadData();
        updateViews();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        //Acces the values/data using the key and tha shared preferences
        text = sharedPreferences.getString(TEXT, "");
        switchOnOff = sharedPreferences.getBoolean(SWITCH1, false);
    }


    // save data to the shared preferences
    public void saveData() {
        // initialize the shared preference class, this code is universal that is what we will always use
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        // create a new editor instance
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // pick the set Text
        // store data in the ket values
        String textsaved = textView.getText().toString().trim();
        // the below code is what came from code in flow we have changed it
       // editor.putString(TEXT, textView.getText().toString());
        editor.putBoolean(SWITCH1, switch1.isChecked());

        editor.apply();

        Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show();
    }

    public void updateViews() {
        textView.setText(text);
        switch1.setChecked(switchOnOff);
    }
}