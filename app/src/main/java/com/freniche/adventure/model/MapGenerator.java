package com.freniche.adventure.model;

import android.content.Context;

import com.freniche.adventure.R;

import java.util.LinkedList;

public class MapGenerator {

    public static Room initialRoom;

    public static void generate(Context context) {
        Room room1 = new Room();
        room1.setDescription(context.getString(R.string.room_desc1));
        // room1.setImageUrl(context.getString(R.string.room_img1));
        room1.setImageUrl("");

        Room room2 = new Room();
        room2.setDescription(context.getString(R.string.room_desc2));
        room2.setImageUrl(context.getString(R.string.room_img2));

        Room room3 = new Room();
        room3.setDescription(context.getString(R.string.room_desc3));
        room3.setImageUrl(context.getString(R.string.room_img3));

        // link rooms
        room1.setRoomSouth(room2);
        room2.setRoomNorth(room1);

        room2.setRoomEast(room3);
        room3.setRoomWest(room2);

        LinkedList<Item> itemsRoom3 = new LinkedList<>();
        Item i1 = new Item("Botella", "Botella de Moskovskaya");
        itemsRoom3.add(i1);
        itemsRoom3.add(new Item("Cuchillo", "Cuchillo jamonero"));
        itemsRoom3.add(new Item("Billete 500 Eur", "Unicornio hecho papel moneda"));
        room3.setItems(itemsRoom3);

        Monster carmen = new Monster();
        carmen.setName("Carmen de Mairena");
        carmen.setDescription("Si entrara a esas habitaciones me tiraba hasta a el botones...");
        carmen.setImageUrl("http://albertriba.com/wp-content/uploads/2013/09/carme-mairena.jpg");

        room3.setMonster(carmen);

        initialRoom = room1;

    }











}
