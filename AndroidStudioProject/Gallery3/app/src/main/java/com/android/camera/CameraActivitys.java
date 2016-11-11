package com.android.camera;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.gallery3.utils.IntentHelper;

public class CameraActivitys extends Activity {

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        Log.e("CameraActivity2", "<onCreate> CameraActivity2 on create!");
        Intent intent = IntentHelper.getCameraIntent(CameraActivitys.this);
        // Since this is being launched from a homescreen shorcut,
        // it's already in a new task. Start Camera activity and
        // reset the task to its initial state if needed.
        intent.setFlags(Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        startActivity(intent);
        finish();
    }
}
