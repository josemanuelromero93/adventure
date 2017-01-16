package com.chema.adventure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    Button startButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);  // ocultar barra titulo arriba (Paso 1)
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();                           // ocultar barra titulo arriba (Paso 2)
        setContentView(R.layout.activity_menu);

        startButton = (Button) findViewById(R.id.activity_menu_button_go);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}
