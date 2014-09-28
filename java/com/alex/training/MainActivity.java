package com.alex.training;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

/**
 * Created by alex on 9/28/14.
 */


public class MainActivity extends Activity {

    private EditText name, telephone;

    private HashMap<String, String> entries = new HashMap<String, String>();


    public static final String EXTRA_MESSAGE = "telephones";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name_edit);
        telephone = (EditText) findViewById(R.id.telephone_edit);
    }

    public void addEntry(View view) {

        String nameString = name.getText().toString(),
                telephoneString = telephone.getText().toString();

        if (nameString.matches("")) {
            Toast.makeText(this, R.string.empty_name_toast, Toast.LENGTH_SHORT).show();
            return;
        }

        if (telephoneString.matches("")) {
            Toast.makeText(this, R.string.empty_telephone_toast, Toast.LENGTH_SHORT).show();
            return;
        }

        hideKeyboard(name);

        entries.put(nameString, telephoneString);
        Toast.makeText(this, R.string.entry_added_toast, Toast.LENGTH_SHORT).show();

        name.setText("");
        telephone.setText("");
    }

    public void nextActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_MESSAGE, entries);
        startActivity(intent);
    }

    private void hideKeyboard(EditText editText) {
        InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}