package com.robust.devtool;

import android.accessibilityservice.AccessibilityService;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;

import de.greenrobot.event.EventBus;

public class TrackerService extends AccessibilityService {
    public static final String TAG = "TrackerService";
    public static final String COMMAND = "COMMAND";
    public static final String COMMAND_OPEN = "COMMAND_OPEN";
    public static final String COMMAND_CLOSE = "COMMAND_CLOSE";
    TrackerWindowManager mTrackerWindowManager;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        TrackerWindowManager.getInstance().showFloat();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Log.d(TAG, "onAccessibilityEvent: " + event.getPackageName());
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {

            CharSequence packageName = event.getPackageName();
            CharSequence className = event.getClassName();
            if (!TextUtils.isEmpty(packageName) && !TextUtils.isEmpty(className)) {
                EventBus.getDefault().post(new ActivityChangedEvent(
                        event.getPackageName().toString(),
                        event.getClassName().toString()
                ));
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        TrackerWindowManager.getInstance().hideFloat();
    }

    public static class ActivityChangedEvent {
        private final String mPackageName;
        private final String mClassName;

        public ActivityChangedEvent(String packageName, String className) {
            mPackageName = packageName;
            mClassName = className;
        }

        public String getPackageName() {
            return mPackageName;
        }

        public String getClassName() {
            return mClassName;
        }
    }
}
