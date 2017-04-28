package com.borislaporte.lasalle.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.borislaporte.lasalle.R;
import com.borislaporte.lasalle.model.Event;
import com.borislaporte.lasalle.network.EventManager;

import butterknife.BindView;
import butterknife.ButterKnife;

// https://sourcey.com/beautiful-android-login-and-signup-screens-with-material-design/
public class EventAddActivity extends AppCompatActivity {

    private static final String TAG = "AddEventActivity";
    private static final int REQUEST_ADD_EVENT = 0;

    // C'EST L'ID DE LA DEMANDE DE PERMISSION QUI FAIT CHIER LE MONDE ROYALEMENT
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 69;


    @BindView(R.id.input_name)
    EditText _nameText;
    @BindView(R.id.input_imageUrl)
    EditText _imageUrlText;
    @BindView(R.id.input_description)
    EditText _descriptionText;
    @BindView(R.id.input_information)
    EditText _informationText;
    @BindView(R.id.btn_event_add)
    Button _addEventButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_add);

        ButterKnife.bind(this);


        _addEventButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addEvent();
            }
        });
    }

    public void addEvent() {
        Log.d(TAG, "addEvent");

        if (!validate()) {
            onAddEventFailed();
            return;
        }

        _addEventButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(EventAddActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Posting new event...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String imageUrl = _imageUrlText.getText().toString();
        String description = _descriptionText.getText().toString();
        String information = _informationText.getText().toString();

        double latitude = 0;
        double longitude = 0;

        // TODO: Implement your own authentication logic here.

        Event event = new Event();
        event.setName(name);
        event.setImageUrl(imageUrl);
        event.setDescription(description);
        event.setInformation(information);

        //Event.Location location = getLocation();
        Event.Location location = new Event.Location();
        location.setLongitude(longitude);
        location.setLatitude(latitude);
        event.setLocation(location);
        EventManager.createEvent(event);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onAddEventSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_ADD_EVENT) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onAddEventSuccess() {
        _addEventButton.setEnabled(true);
        finish();
    }

    public void onAddEventFailed() {
        Toast.makeText(getBaseContext(), "Post event failed", Toast.LENGTH_LONG).show();

        _addEventButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _nameText.getText().toString();
        String imageUrl = _imageUrlText.getText().toString();
        String description = _descriptionText.getText().toString();
        String information = _informationText.getText().toString();


        if (name.isEmpty()) {
            _nameText.setError("enter a name");
            valid = false;
        } else {
            _nameText.setError(null);
        }

        if (imageUrl.isEmpty() || !Patterns.WEB_URL.matcher(imageUrl).matches()) {
            _imageUrlText.setError("enter a valid image URL");
            valid = false;
        } else {
            _imageUrlText.setError(null);
        }

        if (description.isEmpty()) {
            _descriptionText.setError("enter a description");
            valid = false;
        } else {
            _descriptionText.setError(null);
        }

        if (information.isEmpty()) {
            _informationText.setError("enter informations");
            valid = false;
        } else {
            _informationText.setError(null);
        }

        return valid;
    }
/*
    private void getCurrentLocation() {
        CustomLocationManager.getCustomLocationManager().getCurrentLocation(this, locationValue);
    }

    public LocationValue locationValue = new LocationValue() {
        @Override
        public void getCurrentLocation(Location location) {
            // You will get location here if the GPS is enabled
            if(location != null) {
                Log.d("LOCATION", location.getLatitude() + ", " + location.getLongitude());
            }
        }
    };


    private Event.Location getLocation() {
        Event.Location eventLocation = new Event.Location();
        eventLocation.setLongitude(1);
        eventLocation.setLatitude(1);

        Activity activity = this;

        // Here, thisActivity is the current activity
        // DEMANDE PERMISSION MA GUEULE
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                    Manifest.permission.ACCESS_COARSE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }

        // FIN DEMANDE PERMISSIONS






        // CHECK SI ON A BIEN LES PERMISSIONS PUTAIN

        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( activity, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return eventLocation;
        }

        // FIN CHECK PERMISSION FILS DE PUTE

        eventLocation.setLongitude(3);
        eventLocation.setLatitude(3);


        // ON TRY PCK CA PEUT FOIRER BITCH
        try {
            LocationManager locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            eventLocation.setLongitude(5);
            eventLocation.setLatitude(5);

            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Log.d("LOCATION", location.getLatitude() + ", " + location.getLongitude());
            return eventLocation;
        } catch (Exception ex)  {

        }
        return eventLocation;
    };


    // ET CA C'EST LE CALLBACK LORSQUE NOTRE PUTE REPOND OUI OU NON AUX PERMISSIONS
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
      */
}