package com.example.customclevertapidapp;

import android.app.Application;
import android.app.NotificationManager;
import android.util.Log;

import com.clevertap.android.pushtemplates.PushTemplateNotificationHandler;
import com.clevertap.android.pushtemplates.TemplateRenderer;
import com.clevertap.android.sdk.ActivityLifecycleCallback;
import com.clevertap.android.sdk.CleverTapAPI;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;

public class MyApplication extends Application {

    private static final String TAG = MyApplication.class.getName();
    private static MyApplication singleton;
    private CleverTapAPI clevertapDefaultInstance;

    public static MyApplication getInstance() {
        return singleton;
    }

    // Called when the application is starting, before any other application objects have been created.
    // Overriding this method is totally optional!
    @Override
    public void onCreate() {
        ActivityLifecycleCallback.register(this);
        super.onCreate();
//        registerActivityLifecycleCallbacks(this);
        singleton = this;
//        clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());
        CleverTapAPI.setDebugLevel(CleverTapAPI.LogLevel.VERBOSE);
        TemplateRenderer.setDebugLevel(3);
        CleverTapAPI.setNotificationHandler(new PushTemplateNotificationHandler());

//        FirebaseMessaging.getInstance().getToken()
//                .addOnCompleteListener(task -> {
//                    if (!task.isSuccessful()) {
//                        Log.w("TAG", "Fetching FCM registration token failed", task.getException());
//                        return;
//                    }
//
//                    // Get new FCM registration token
//                    String token = task.getResult();
//                    Log.v("TAG", "token: " + token);
////                    clevertapDefaultInstance.pushFcmRegistrationId(token, true);
//                });

        CleverTapAPI.createNotificationChannel(getApplicationContext(),
                "customclevertapid", "Custom Clevertap ID",
                "Test Channel Description",
                NotificationManager.IMPORTANCE_MAX, true);


    }

    public CleverTapAPI getClevertapDefaultInstance () {
        return clevertapDefaultInstance;
    }
}
