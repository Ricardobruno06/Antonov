package com.example.ricardo.antonov;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button enviar = (Button) findViewById(R.id.button);
        //final EditText ip = (EditText) findViewById(R.id.editText);
        final EditText msg = (EditText) findViewById(R.id.editText);
        final EditText chave = (EditText) findViewById(R.id.editText3);

        enviar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String chaves = chave.getText().toString();
                String msgs = msg.getText().toString();
                //String ips = ip.getText().toString();
                EmitLogTopic.emit(msgs, chaves);


            }
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

    }
}
