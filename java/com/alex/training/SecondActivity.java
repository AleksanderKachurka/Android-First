package com.alex.training;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by alex on 9/28/14.
 */
public class SecondActivity extends Activity {

    private ScrollView mainView;
    private TableLayout table;

    private HashMap<String, String> entries;
    private String[] names;

    @Override
    @SuppressWarnings("unchecked")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainView = new ScrollView(this);

        table = new TableLayout(this);

        table.addView(generateTop(R.string.second_top));

        entries = new HashMap<String, String>((HashMap<String, String>) getIntent().getSerializableExtra(MainActivity.EXTRA_MESSAGE));

        names = new String[entries.size()];
        entries.keySet().toArray(names);
        Arrays.sort(names);

        for (String entry : names) {

            final Button button = generateButton(entry);

            button.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: " + entries.get(button.getText())));
                    startActivity(intent);
                }
            });

            table.addView(button);
        }

        mainView.addView(table);
        setContentView(mainView);
    }

    private Button generateButton(String text) {
        Button button = new Button(this);
        button.setTextSize(20);
        button.setText(text);

        return button;
    }

    private TextView generateTop(int textPointer) {
        TextView textView = new TextView(this);
        textView.setTextSize(30);
        textView.setGravity(1);
        textView.setText(textPointer);

        return textView;
    }
}
