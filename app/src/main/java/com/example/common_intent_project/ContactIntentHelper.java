package com.example.common_intent_project;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.MediaStore;
import android.widget.Toast;

public class ContactIntentHelper {

    private final Context context;

    public ContactIntentHelper(Context context) {
        this.context = context;
    }

    // 1. Call a Contact
    public void dialNumber(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        startIntentIfResolvable(intent, "No dialer app found.");
    }

    // 2. Send SMS
    public void sendSms(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("smsto:" + phoneNumber));
        startIntentIfResolvable(intent, "No SMS app found.");
    }

    // 3. Send Email
    public void sendEmail(String emailAddress) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + emailAddress));
        startIntentIfResolvable(intent, "No email app found.");
    }

    // 4. Open Website
    public void openWebsite(String url) {
        String parsedUrl = url;
        if (!parsedUrl.startsWith("http://") && !parsedUrl.startsWith("https://")) {
            parsedUrl = "http://" + parsedUrl;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(parsedUrl));
        startIntentIfResolvable(intent, "No web browser found.");
    }

    // 5. Open Address in Maps
    public void openAddressInMap(String address) {
        String encodedAddress = Uri.encode(address);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("geo:0,0?q=" + encodedAddress));
        startIntentIfResolvable(intent, "No map application found.");
    }

    // 6. Share Contact Details
    public void shareContact(String name, String phone, String email) {
        String contactDetails = "Contact Info:\nName: " + name + "\nPhone: " + phone + "\nEmail: " + email;
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, contactDetails);

        // ACTION_SEND usually shows a chooser automatically, but creating one explicitly is safer
        Intent chooser = Intent.createChooser(intent, "Share contact via...");
        context.startActivity(chooser);
    }

    // 7. Add Calendar Reminder (e.g., Birthday or Meeting)
    public void addCalendarEvent(String title, String description, long timeInMillis) {
        Intent intent = new Intent(Intent.ACTION_INSERT);
        intent.setData(CalendarContract.Events.CONTENT_URI);
        intent.putExtra(CalendarContract.Events.TITLE, title);
        intent.putExtra(CalendarContract.Events.DESCRIPTION, description);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, timeInMillis);
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

        startIntentIfResolvable(intent, "No calendar app found.");
    }

    // 8. Take Photo Intent
    // Note: To actually retrieve the image, you should use ActivityResultLauncher in your Activity.
    // This method just creates and returns the Intent so your Activity can launch it.
    public Intent getTakePhotoIntent() {
        return new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    }

    // Helper method to prevent app crashes if no app can handle the intent
    private void startIntentIfResolvable(Intent intent, String errorMessage) {
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else {
            Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show();
        }
    }
}
