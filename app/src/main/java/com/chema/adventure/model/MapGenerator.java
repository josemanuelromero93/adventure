package com.chema.adventure.model;

import java.util.LinkedList;

public class MapGenerator {
    public static Room initialRoom;

    public static void generate() {
        Room room1 = new Room();
        room1.setDescription("[Room 1]: Te encuentras en un aula con las contraventanas semicerradas. Olor a ordenador encendido y cerebro frito impregna tus sentidos");

        Room room2 = new Room();
        room2.setDescription("[Room 2]: Te deslumbra la luz del sol que se filtra por las ventanas del pasillo. Sientes un escalofrío al ver a un grajo arrastrándose.");
        Room room3 = new Room();
        room3.setDescription("[Room 3]: Hay un barra de bar con tapicería roja pasada de moda. Huele a tabaco usado y lágrimas de tango.");

        Room room4 = new Room();
        room4.setDescription("[Room 4]: Room 4.");

        // enlazo las habitaciones

        room1.setRoomSouth(room2);
        room1.setRoomEast(room4);
        room2.setRoomNorth(room1);
        room2.setRoomEast(room3);
        room3.setRoomWest(room2);
        room3.setRoomNorth (room4);
        room4.setRoomWest(room1);
        room4.setRoomSouth(room3);

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
