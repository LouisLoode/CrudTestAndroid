package com.borislaporte.lasalle.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.borislaporte.lasalle.R;
import com.borislaporte.lasalle.model.Event;
import com.borislaporte.lasalle.network.EventManager;

public class MainActivity extends AppCompatActivity {

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
                Event event = new Event();
                event.setName("On sort quand ?");
                event.setImageUrl("https://s-media-cache-ak0.pinimg.com/236x/e4/2e/45/e42e45ddb572057f0a37574ed66b9d9c.jpg");
                event.setDescription("Allez !! On sort quand ??????");
                event.setInformation("La flème de remplir ce champ.");
                Event.Location location = new Event.Location();
                location.setLongitude(79.9449);
                location.setLatitude(-58.2758);
                event.setLocation(location);
                EventManager.createEvent(event);
        }

        return super.onOptionsItemSelected(item);
    }
}
