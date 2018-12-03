package com.example.breakpaddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.libbreakpad.BreakPadManager;

import java.io.File;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onInit(View view) {
        File dumpDir = new File(getFilesDir(),"dump");
        if (!dumpDir.exists()){
            dumpDir.mkdir();
        }
        BreakPadManager.getInstance().initBrakPad(dumpDir.getAbsolutePath());
    }

    public void onTest(View view) {
        BreakPadManager.getInstance().testBreak();
    }
}
