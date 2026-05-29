package com.example.common_intent_project;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ContactIntentHelper intentHelper;   
    private ImageView contactPhotoView;

    // This launcher handles the camera intent and receives the resulting Bitmap
    private final ActivityResultLauncher<Void> takePictureLauncher = registerForActivityResult(
            new ActivityResultContracts.TakePicturePreview(),
            result -> {
                if (result != null) {
                    // Update the ImageView with the newly taken photo
                    contactPhotoView.setImageBitmap(result);
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize helper and view
        intentHelper = new ContactIntentHelper(this);
        contactPhotoView = findViewById(R.id.img_contact_photo);

        // Dummy data for testing our intents
        String dummyPhone = "555-0199";
        String dummyEmail = "jane.doe@example.com";
        String dummyWebsite = "www.android.com";
        String dummyAddress = "1600 Amphitheatre Parkway, Mountain View, CA";
        String dummyName = "Jane Doe";

        // Wire up all buttons using lambda expressions for cleaner code
        findViewById(R.id.btn_call).setOnClickListener(v ->
                intentHelper.dialNumber(dummyPhone));

        findViewById(R.id.btn_sms).setOnClickListener(v ->
                intentHelper.sendSms(dummyPhone));

        findViewById(R.id.btn_email).setOnClickListener(v ->
                intentHelper.sendEmail(dummyEmail));

        findViewById(R.id.btn_website).setOnClickListener(v ->
                intentHelper.openWebsite(dummyWebsite));

        findViewById(R.id.btn_map).setOnClickListener(v ->
                intentHelper.openAddressInMap(dummyAddress));

        findViewById(R.id.btn_share).setOnClickListener(v ->
                intentHelper.shareContact(dummyName, dummyPhone, dummyEmail));

        findViewById(R.id.btn_calendar).setOnClickListener(v -> {
            // Setting a dummy event for exactly 1 day (86,400,000 ms) from right now
            long tomorrow = System.currentTimeMillis() + 86400000;
            intentHelper.addCalendarEvent(dummyName + "'s Birthday", "Buy a gift!", tomorrow);
        });

        // The photo button uses the launcher we defined at the top of the class
        findViewById(R.id.btn_photo).setOnClickListener(v ->
                takePictureLauncher.launch(null));
    }
}