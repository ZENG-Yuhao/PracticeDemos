package com.example.enzo.practicedemos.Activities;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.enzo.practicedemos.R;

public class ToolBarActivity extends AppCompatActivity
{

    private Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);
        toolBar = (Toolbar) findViewById(R.id.my_toolbar);
        toolBar.setTitle("");
        setSupportActionBar(toolBar);

        // Get a support ActionBar corresponding to this toolbar
        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_tool_bar, menu);

        // get the reference and configure it
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        // do something when the action is expanded or collapsed
        MenuItemCompat.OnActionExpandListener expandListener = new MenuItemCompat
                .OnActionExpandListener()
        {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item)
            {
                Toast.makeText(ToolBarActivity.this, "Expanded: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item)
            {
                Toast.makeText(ToolBarActivity.this, "Collapsed: " + item.getTitle(), Toast.LENGTH_SHORT).show();
                return true;
            }
        };


        // Assign the listener to that action item
        MenuItemCompat.setOnActionExpandListener(searchItem, expandListener);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_favorite:
                Toast.makeText(this, "Favorite clicked.", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "Settings clicked.", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
