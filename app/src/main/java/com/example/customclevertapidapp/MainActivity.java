package com.example.customclevertapidapp;

import android.os.Bundle;

import com.clevertap.android.pushtemplates.PushTemplateNotificationHandler;
import com.clevertap.android.sdk.CleverTapAPI;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.customclevertapidapp.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        EditText editText = findViewById(R.id.customid);

        findViewById(R.id.onUserLoginCall).setOnClickListener(view -> {

            if (editText.getText() != null || TextUtils.isEmpty(editText.getText().toString())) {
                String customId = editText.getText().toString();


                CleverTapAPI clevertapDefaultInstance =
                        CleverTapAPI.getDefaultInstance(getApplicationContext(), customId);

                if (!clevertapDefaultInstance.isPushPermissionGranted()) {
                    clevertapDefaultInstance.promptForPushPermission(true);
                }
                CleverTapAPI.setNotificationHandler(new PushTemplateNotificationHandler());
                CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE);

                HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
                profileUpdate.put("Name", "Jack Montana");    // String
                profileUpdate.put("Identity", customId);      // String or number
                clevertapDefaultInstance.onUserLogin(profileUpdate, customId);
            }
        });

    }

}