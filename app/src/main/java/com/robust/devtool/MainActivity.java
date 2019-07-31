package com.robust.devtool;

import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    private View mFloatPermissionBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!hasFloatPermissionBtn()) {
                    grantFloatPermission();
                    return;
                }

                if(AccessibilityUtil.checkAccessibility(MainActivity.this)) {
                    TrackerWindowManager.getInstance().showFloat();
                    finish();
                }
            }
        });
    }

    private boolean hasFloatPermissionBtn() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(this);
        }

        return true;
    }

    private void grantFloatPermission() {
        startActivityForResult(
                new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())),
                REQUEST_CODE
        );
        Toast.makeText(MainActivity.this, R.string.permission_float, Toast.LENGTH_LONG).show();
    }

    private void grandAccessibilityPermission() {

    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

        }
    }
}
