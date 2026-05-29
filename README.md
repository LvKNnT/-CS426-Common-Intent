# Android Address Book - Intent Showcase

A simple Android application built in Java to demonstrate the power of **Implicit Intents**. Currently, this project serves as a functional prototype that showcases how to seamlessly hand off tasks to other specialized applications on an Android device.

## 🚀 Current Progress

The project currently features a single-screen "Contact Details" view populated with dummy data (Jane Doe). All primary action buttons are fully wired up and functional. 

Implemented features include:
* **Phone Dialer (`ACTION_DIAL`)**: Opens the dialer with a pre-filled number.
* **SMS (`ACTION_SENDTO`)**: Opens the default messaging app.
* **Email (`ACTION_SENDTO`)**: Launches the email client with a specific `mailto:` URI.
* **Web Browser (`ACTION_VIEW`)**: Parses and opens a URL.
* **Google Maps (`ACTION_VIEW`)**: Encodes and searches for a physical address.
* **Share (`ACTION_SEND`)**: Triggers the Android system share sheet to share contact details as plain text.
* **Calendar (`ACTION_INSERT`)**: Pre-fills a calendar event (e.g., a birthday reminder).
* **Camera (`MediaStore.ACTION_IMAGE_CAPTURE`)**: Uses the modern `ActivityResultLauncher` to take a photo and return a thumbnail bitmap to the UI.
* **Android 11+ Compatibility**: The `AndroidManifest.xml` includes the necessary `<queries>` block for package visibility compliance.

## 📂 Folder and File Structure

The core logic is contained within the standard Android directory structure:

```text
app/src/main/
├── AndroidManifest.xml 
│   # Contains the <queries> block required for intent resolution on API 30+
│
├── java/com/yourname/addressbook/
│   ├── MainActivity.java 
│   │   # Handles UI initialization, button click listeners, and the Camera Result Launcher
│   └── ContactIntentHelper.java 
│       # A reusable utility class containing all the intent creation and validation logic
│
└── res/layout/
    └── activity_main.xml 
        # The frontend XML layout utilizing a ScrollView and vertical LinearLayout

```

## 🔮 Future Improvements

To evolve this prototype into a fully functional Address Book application, the following features are planned:

1. **Data Persistence:** Integrate **Room Database** (SQLite) to store, update, and delete real user contacts instead of using hardcoded dummy variables.
2. **Dynamic List View:** Implement a `RecyclerView` to display a scrollable list of all saved contacts on a main screen.
3. **Navigation:** Add an "Add/Edit Contact" activity or fragment with input fields to create new entries.
4. **Full-Resolution Photos:** Upgrade the camera implementation from capturing a preview thumbnail (Bitmap) to saving full-resolution images to the device's local storage via a `FileProvider` URI.
5. **Runtime Permissions:** Implement checks for dangerous permissions (like `CALL_PHONE` if moving away from `ACTION_DIAL`, or explicit storage permissions if needed).
