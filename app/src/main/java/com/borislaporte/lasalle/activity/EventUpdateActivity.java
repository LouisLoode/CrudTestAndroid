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
public class EventUpdateActivity extends AppCompatActivity {

    private static final String TAG = "UpdateEventActivity";
    private static final int REQUEST_UPDATE_EVENT = 0;
    private static final String EXTRA_EVENT_ID = null;


    @BindView(R.id.input_name) EditText _nameText;
    @BindView(R.id.input_imageUrl) EditText _imageUrlText;
    @BindView(R.id.input_description) EditText _descriptionText;
    @BindView(R.id.input_information) EditText _informationText;
    @BindView(R.id.btn_event_update) Button _updateEventButton;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_update);

        ButterKnife.bind(this);

        _updateEventButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addEvent();
            }
        });
    }

    public void addEvent() {


        Log.d(TAG, "uptadeEvent");

        if (!validate()) {
            onUpdateEventFailed();
            return;
        }

        _updateEventButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(EventUpdateActivity.this,
                R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Update new event...");
        progressDialog.show();

        String name = _nameText.getText().toString();
        String imageUrl = _imageUrlText.getText().toString();
        String description = _descriptionText.getText().toString();
        String information = _informationText.getText().toString();


        // TODO: Implement your own authentication logic here.

        Event event = new Event();
        String eventId = getIntent().getStringExtra(EXTRA_EVENT_ID);


        Log.d("EventID", eventId);

                event.setName(name);
                event.setImageUrl(imageUrl);
                event.setDescription(description);
                event.setInformation(information);
                Event.Location location = new Event.Location();
                location.setLongitude(0);
                location.setLatitude(0);
                event.setLocation(location);
                EventManager.updateEvent(eventId, event);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onUpdateEventSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_UPDATE_EVENT) {
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

    public void onUpdateEventSuccess() {
        _updateEventButton.setEnabled(true);
        finish();
    }

    public void onUpdateEventFailed() {
        Toast.makeText(getBaseContext(), "Update event failed", Toast.LENGTH_LONG).show();

        _updateEventButton.setEnabled(true);
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
}