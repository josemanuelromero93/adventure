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

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.activity_main_help) ImageButton helpbutton;
    @BindView(R.id.activity_main_north_button) ImageButton northbutton;
    @BindView(R.id.activity_main_west_button) ImageButton westbutton;
    @BindView(R.id.activity_main_east_button) ImageButton eastbutton;
    @BindView(R.id.activity_main_south_button) ImageButton southbutton;
    @BindView(R.id.activity_main_inventory) ImageButton inventorybutton;
    @BindView(R.id.activity_main_drop) ImageButton dropbutton;
    @BindView(R.id.activity_main_take) ImageButton takebutton;
    @BindView(R.id.activity_main_look_button) ImageButton lookbutton;
    @BindView(R.id.activity_main_scene_text) TextView mainText; // Paso 1
    Inventory inventory = new Inventory();
    Room currentRoom;

   // @BindView(R.id.activity_main_inventory);
   // Button pruebabutterknife;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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

            }
        });

        inventorybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainText.setText(inventory.print());
            }
        });

        lookbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainText.setText(currentRoom.getDescription() + "\n");

            }
        });

        dropbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });


        initGame();
        repaintScene();

        mainText.setText(currentRoom.getDescription()); // Paso 3
    }




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
