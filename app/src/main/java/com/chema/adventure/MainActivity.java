package com.chema.adventure;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chema.adventure.model.Inventory;
import com.chema.adventure.model.Item;
import com.chema.adventure.model.MapGenerator;
import com.chema.adventure.model.Room;
import com.chema.adventure.util.Constants;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.activity_main_scene_image)
    ImageView sceneimage;
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
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();                           // ocultar barra titulo arriba (Paso 2)
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Picasso.with(this).setIndicatorsEnabled(true);
        Picasso.with(this).setLoggingEnabled(true);

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
                mainText.setText(currentRoom.getDescription() + "\n" + currentRoom.getRoomItems());


            }
        });

        dropbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Intent i = new Intent(MainActivity.this, DropItemActivity.class);
                i.putExtra(Constants.KEY_INTENT_INVENTORY, inventory);

                startActivityForResult(i, 1);


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

        MapGenerator.generate(this);

        currentRoom = MapGenerator.initialRoom;

    }


    private void repaintScene() {
        mainText.setText(currentRoom.getDescription());

        if (currentRoom.getImageUrl()!= null && currentRoom.getImageUrl().length()>0) {
            Picasso.with(this).load(currentRoom.getImageUrl()).into(sceneimage);
        }

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                int itemPosition = data.getIntExtra(Constants.KEY_INTENT_DROP_ITEM_POSITION, -1);
                Item item = inventory.getItems().get(itemPosition);
                currentRoom.getItems().add(item);
                inventory.delete(itemPosition);

                Snackbar.make(mainText, getString(R.string.dropped_item_text) + item.getName(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        }
    }
}
