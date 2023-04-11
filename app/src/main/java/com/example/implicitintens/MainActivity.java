package com.example.implicitintens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText mWebsiteEditText;
    private EditText mLocationEditText;
    private EditText mShareTextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebsiteEditText = findViewById(R.id.searchInput);
        mLocationEditText = findViewById(R.id.locationInput);
        mShareTextEditText = findViewById(R.id.textInput);
    }

    public void openWeb(View view) {
//        get url text
        String url = mWebsiteEditText.getText().toString();

//        parse Uri -> create intent
        Uri webpage = Uri.parse(url);

//        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
            Intent intent =  new Intent();
            intent.setAction("com.android.realmadrid.zydanex");
//            startActivity(intent);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), "Aplikasi Tidak Ditemukan", Toast.LENGTH_LONG).show();
            Log.d("ImplicitIntents", "Can't Handle this!");
        }

    }

    public void openLocation(View view) {
        String loc = mLocationEditText.getText().toString();

        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);

        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't Handle this!");
        }
    }

    public void openText(View view) {
        String txt = mShareTextEditText.getText().toString();
        String mimeType = "text/plain";
        ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(txt)
                .startChooser();

    }

    public void newPage(View view) {
        Intent intent =  new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }
}