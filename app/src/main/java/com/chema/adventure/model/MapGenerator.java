package com.chema.adventure.model;

import android.content.Context;

import com.chema.adventure.R;

import java.util.LinkedList;

public class MapGenerator {
    public static Room initialRoom;

    public static void generate(Context context) {             // para leer ficheros siempre se necesita un contexto
        Room room1 = new Room();
        room1.setDescription(context.getString(R.string.rooms_desc1));
        room1.setImageUrl(context.getString(R.string.rooms_img1));
        Room room2 = new Room();
        room2.setDescription(context.getString(R.string.rooms_desc2));
        room2.setImageUrl(context.getString(R.string.rooms_img2));
        Room room3 = new Room();
        room3.setDescription(context.getString(R.string.rooms_desc3));
        room3.setImageUrl(context.getString(R.string.rooms_img3));
        Room room4 = new Room();
        room4.setDescription(context.getString(R.string.rooms_desc4));
        room4.setImageUrl(context.getString(R.string.rooms_img4));
        Room room5 = new Room();
        room5.setDescription(context.getString(R.string.rooms_desc5));
        room5.setImageUrl(context.getString(R.string.rooms_img5));

        // enlazo las habitaciones
        room1.setRoomSouth(room2);
        room1.setRoomEast(room4);
        room2.setRoomNorth(room1);
        room2.setRoomEast(room3);
        room3.setRoomWest(room2);
        room3.setRoomNorth (room4);
        room4.setRoomWest(room1);
        room4.setRoomSouth(room3);
        room4.setRoomEast(room5);
        room5.setRoomWest(room4);

        // lista de items
        LinkedList<Item> itemsRoom3 = new LinkedList<>();
        Item i1 = new Item("Botella", "Botella de vodka");
        itemsRoom3.add(i1);
        itemsRoom3.add(new Item("Cuchillo", "Cuchillo jamonero"));
        itemsRoom3.add(new Item("Billete 500€", "Unicornio hecho papel moneda"));
        room3.setItems(itemsRoom3);
        initialRoom = room1;

    }


}
