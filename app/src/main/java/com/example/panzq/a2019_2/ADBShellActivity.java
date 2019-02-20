package com.example.panzq.a2019_2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class ADBShellActivity extends AppCompatActivity {

    TextView tv_content;
    EditText et_command;
    Button btn_startShell;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adbshell);
        findViews();
        setListener();
    }
    private void findViews()
    {
        tv_content = findViewById(R.id.tv_content);
        et_command =findViewById(R.id.et_command);
        btn_startShell = findViewById(R.id.btn_startShell);
        et_command.setText("pm list package");
    }
    private void setListener()
    {
        btn_startShell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String command = et_command.getText().toString();
                if (!TextUtils.isEmpty(command))
                {
                    tv_content.setText(command+":");
                    runAdbShellCommand(command);
                }
            }
        });
    }
    private void runAdbShellCommand(String command)
    {
        Log.d("panzqww",command);
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while((line = reader.readLine())!=null)
            {
                Log.d("panzqww","line = "+line);
                output.append(line).append("\n");
            }
            reader.close();
            int status = process.waitFor();
            Log.d("panzqww","status" +status);
            tv_content.setText(output.toString());
        } catch (Exception e) {
            e.printStackTrace();
            tv_content.setText(command+":"+"权限不足");
        }finally {
            if (process != null)
            {
                process.destroy();
                process = null;
            }
        }

    }
}
