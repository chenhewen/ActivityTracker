package com.robust.devtool;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class ClipboardUtil {

    public static void copyText(Context context, CharSequence label, CharSequence text, boolean showToast) {
        if (TextUtils.isEmpty(text)) return;
        ClipboardManager clipboardManager = (ClipboardManager) context.getSystemService(
                Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText(label == null ? "" : label, text);
        if (clipboardManager != null) {
            clipboardManager.setPrimaryClip(clipData);
        }
        if (showToast) {
            Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
        }
    }
}
