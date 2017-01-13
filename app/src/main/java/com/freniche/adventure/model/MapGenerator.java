package com.freniche.adventure.model;

import java.util.LinkedList;

public class MapGenerator {

    public static Room initialRoom;

    public static void generate() {
        Room room1 = new Room();
        room1.setDescription("[Room 1] Te encuentras en un aula con las contraventanas semicerradas. Olor a ordenador encendido y cerebro frito impregna tus sentidos.");

        Room room2 = new Room();
        room2.setDescription("[Room 2] Te deslumbra la luz del Sol que se filtra por las ventanas del pasillo. Sientes un escalofrío al ver a un grajo arrastrándose.");

        Room room3 = new Room();
        room3.setDescription("[Room 3] Hay una barra de bar con tapicería roja pasada de moda. Huele a tabaco usado y lágrimas de tango.");

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


        initialRoom = room1;

    }











}
