package com.borislaporte.lasalle.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.borislaporte.lasalle.R;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_ADD_EVENT = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_create:

                // Start the Add Event activity
                Intent intent = new Intent(getApplicationContext(), EventAddActivity.class);
                startActivityForResult(intent, REQUEST_ADD_EVENT);

        }

        return super.onOptionsItemSelected(item);
    }
}
