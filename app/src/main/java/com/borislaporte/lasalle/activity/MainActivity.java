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

                /*Event event = new Event();
                event.setName("Bitches Party");
                event.setImageUrl("http://data.whicdn.com/images/211084613/large.jpg");
                event.setDescription("Viens bouger tes fesses comme un bitches !");
                event.setInformation("De nombreuses Bitches seront la pour un maximum d'ambiance !");
                Event.Location location = new Event.Location();
                location.setLongitude(79.9449);
                location.setLatitude(-58.2758);
                event.setLocation(location);
                EventManager.createEvent(event);*/

                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), EventAddActivity.class);
                startActivityForResult(intent, REQUEST_ADD_EVENT);

        }

        return super.onOptionsItemSelected(item);
    }
}
