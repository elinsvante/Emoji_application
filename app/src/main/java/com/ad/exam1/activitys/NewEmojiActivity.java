package com.ad.exam1.activitys;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ad.exam1.R;
import com.ad.exam1.activitys.EmojiActivity;
import com.ad.exam1.models.Emoji;
import com.google.android.material.textfield.TextInputEditText;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewEmojiActivity extends AppCompatActivity {

    static final int REQUEST_TAKE_PHOTO = 1;
    private ImageView imageEmoji;
    private TextView textViewAlert;
    private TextInputEditText textInputName;
    private String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_emoji);

        getViewElements();
        if(imageEmoji.getDrawable() == null) {
            imageEmoji.setImageResource(R.drawable.noimage);
        }
    }

    private void getViewElements() {
        imageEmoji = findViewById(R.id.imageEmoji);
        textViewAlert = findViewById(R.id.textViewAlert);
        textInputName = findViewById(R.id.textInputName);
    }

    public void onClickTakePhoto(View view) {
        dispatchTakePictureIntent();
    }

    public void onClickSavePhoto(View view) {
        Intent intent = new Intent(getApplicationContext(), EmojiActivity.class);
        String textInputNameString = ((EditText)textInputName).getText().toString();
        if(textInputNameString.equals("")) {
            Toast.makeText(getApplicationContext(), "Please insert a name for your emoji.", Toast.LENGTH_SHORT).show();
        }

        else {
            Intent resultIntent = new Intent();
            resultIntent.putExtra("emojiName", textInputNameString);
            resultIntent.putExtra("imagePath", currentPhotoPath);
            setResult(RESULT_OK, resultIntent);
            finish();
        }
    }


    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.example.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_TAKE_PHOTO  && resultCode == RESULT_OK) {
            setPic();
        }
    }

    private void setPic() {
        // Get the dimensions of the View
        int targetW = imageEmoji.getWidth();
        int targetH = imageEmoji.getHeight();

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;

        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(currentPhotoPath, bmOptions);
        imageEmoji.setImageBitmap(bitmap);
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
}
