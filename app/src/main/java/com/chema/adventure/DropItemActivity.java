package com.chema.adventure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.chema.adventure.model.Inventory;
import com.chema.adventure.model.Room;
import com.chema.adventure.util.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DropItemActivity extends AppCompatActivity {

    @BindView(R.id.activity_drop_item_item_list)
    ListView itemList;
    Inventory inventory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();                           // ocultar barra titulo arriba (Paso 2)
        setContentView(R.layout.activity_drop_item);
        ButterKnife.bind(this);

        Intent i = getIntent();
        inventory = (Inventory) i.getSerializableExtra(Constants.KEY_INTENT_INVENTORY);
        Room room = (Room)i.getSerializableExtra(Constants.KEY_INTENT_TAKE_FROM_ROOM);
        List<String> rowNames = null);
        if (inventory == null) {
            rowNames = room.getItemNames();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, rowNames);
        itemList.setAdapter(adapter);

        itemList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View row, int position, long id) {


                Intent returnIntent = new Intent();
                returnIntent.putExtra(Constants.KEY_INTENT_DROP_ITEM_POSITION, position);
                setResult(RESULT_OK, returnIntent);
                finish();

            }
        });

    }
}
