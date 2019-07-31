package com.robust.devtool;

import android.content.Context;
import android.content.pm.PackageManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AppUtil {

    public static String getApkPath(String pkgName) {
        try {
            Process process = Runtime.getRuntime().exec("adb shell pm path " + pkgName);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            // Grab the results
            StringBuilder log = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                log.append(line).append("\n");
            }

            return log.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
