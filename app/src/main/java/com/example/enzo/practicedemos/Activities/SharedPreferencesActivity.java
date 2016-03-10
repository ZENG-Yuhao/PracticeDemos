package com.example.enzo.practicedemos.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.enzo.practicedemos.R;

public class SharedPreferencesActivity extends AppCompatActivity
{
    private static final String PrefName = "MyPrefs";
    private Button btn_commit, btn_query;
    private EditText editxt_key, editxt_value;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        preferences = getSharedPreferences(PrefName, MODE_PRIVATE);

        editxt_key = (EditText) findViewById(R.id.editxt_key);

        editxt_value = (EditText) findViewById(R.id.editxt_value);

        btn_commit = (Button) findViewById(R.id.btn_commit);
        btn_commit.setOnClickListener(new CommitClickedListener());


        btn_query = (Button) findViewById(R.id.btn_query);
        btn_query.setOnClickListener(new QueryClickedListener());
    }

    private class CommitClickedListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            SharedPreferences.Editor editor = preferences.edit();
            String key = editxt_key.getText().toString();
            String value = editxt_value.getText().toString();
            if (!key.equals(""))
            {
                editor.putString(key, value);
                editor.commit();
            } else
                Toast.makeText(SharedPreferencesActivity.this, "The key cannot be null.", Toast.LENGTH_SHORT).show();
        }
    }

    private class QueryClickedListener implements View.OnClickListener
    {

        @Override
        public void onClick(View v)
        {
            String key = editxt_key.getText().toString();
            String value = preferences.getString(key, "The key specified does not exist.");
            editxt_value.setText(value);
        }
    }
}
