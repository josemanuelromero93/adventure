package com.chema.adventure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chema.adventure.model.Inventory;
import com.chema.adventure.model.Item;
import com.chema.adventure.model.MapGenerator;
import com.chema.adventure.model.Room;

public class MainActivity extends AppCompatActivity {

    ImageButton helpbutton;
    ImageButton northbutton;
    ImageButton westbutton;
    ImageButton eastbutton;
    ImageButton southbutton;
    ImageButton inventorybutton;
    ImageButton dropbutton;
    ImageButton takebutton;
    ImageButton lookbutton;
    TextView mainText; // Paso 1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = (TextView) findViewById(R.id.activity_main_scene_text); // Paso 2

        northbutton = (ImageButton) findViewById(R.id.activity_main_north_button);
        westbutton = (ImageButton) findViewById(R.id.activity_main_west_button);
        eastbutton = (ImageButton) findViewById(R.id.activity_main_east_button);
        southbutton = (ImageButton) findViewById(R.id.activity_main_south_button);
        lookbutton = (ImageButton) findViewById(R.id.activity_main_look_button);
        inventorybutton = (ImageButton) findViewById(R.id.activity_main_inventory);
        dropbutton = (ImageButton) findViewById(R.id.activity_main_drop);
        takebutton = (ImageButton) findViewById(R.id.activity_main_take);
        helpbutton = (ImageButton) findViewById(R.id.activity_main_help);


        helpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(i);
            }
        });

        northbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentRoom = currentRoom.getRoomNorth();
                repaintScene();

            }
        });

        westbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentRoom = currentRoom.getRoomWest();
                repaintScene();


            }
        });

        eastbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentRoom = currentRoom.getRoomEast();
                repaintScene();


            }
        });

        southbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentRoom = currentRoom.getRoomSouth();
                repaintScene();


            }
        });

        takebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(i);
            }
        });

        inventorybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(i);
            }
        });

        lookbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(i);
            }
        });

        dropbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(i);
            }
        });


        initGame();
        repaintScene();

        mainText.setText(currentRoom.getDescription()); // Paso 3
    }

    Inventory inventory = new Inventory();
    Room currentRoom;


    private void initGame() {
        Item sword = new Item("Sword", "A sharp blade");
        Item pieceOfPaper = new Item("Piece of Paper", "A blank piece of paper");
        Item rubberChicken = new Item("Rubber Chicken", "A rubber chicken");


        inventory.add(sword);
        inventory.add(pieceOfPaper);
        inventory.add(rubberChicken);

        MapGenerator.generate();

        currentRoom = MapGenerator.initialRoom;

    }


    private void repaintScene() {
        mainText.setText(currentRoom.getDescription());

        if (currentRoom.getRoomNorth() != null) {
            // hay habitación al norte
            northbutton.setVisibility(View.VISIBLE);
        } else {
            // no hay habitación al norte
            northbutton.setVisibility(View.INVISIBLE);
        }

        if (currentRoom.getRoomEast() != null) {
            // hay habitación al norte
            eastbutton.setVisibility(View.VISIBLE);
        } else {
            // no hay habitación al norte
            eastbutton.setVisibility(View.INVISIBLE);
        }

        if (currentRoom.getRoomWest() != null) {
            // hay habitación al norte
            westbutton.setVisibility(View.VISIBLE);
        } else {
            // no hay habitación al norte
            westbutton.setVisibility(View.INVISIBLE);
        }

        if (currentRoom.getRoomSouth() != null) {
            // hay habitación al norte
            southbutton.setVisibility(View.VISIBLE);
        } else {
            // no hay habitación al norte
            southbutton.setVisibility(View.INVISIBLE);
        }
    }
}
