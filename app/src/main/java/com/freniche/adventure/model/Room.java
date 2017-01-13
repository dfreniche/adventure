package com.freniche.adventure.model;


import java.util.LinkedList;

public class Room {
    private String description;

    private LinkedList<Item> items;

    private Room roomNorth;
    private Room roomEast;
    private Room roomWest;
    private Room roomSouth;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LinkedList<Item> getItems() {
        return items;
    }

    public String getRoomItems() {
        if (this.items == null) {
            return "";
        }

        String result = "";

        for (Item item: this.items) {
            result = result + "<i>" + item.getName() + "</i>\n";
        }

        return result;
    }

    public void setItems(LinkedList<Item> items) {
        this.items = items;
    }

    public Room getRoomNorth() {
        return roomNorth;
    }

    public void setRoomNorth(Room roomNorth) {
        this.roomNorth = roomNorth;
    }

    public Room getRoomEast() {
        return roomEast;
    }

    public void setRoomEast(Room roomEast) {
        this.roomEast = roomEast;
    }

    public Room getRoomWest() {
        return roomWest;
    }

    public void setRoomWest(Room roomWest) {
        this.roomWest = roomWest;
    }

    public Room getRoomSouth() {
        return roomSouth;
    }

    public void setRoomSouth(Room roomSouth) {
        this.roomSouth = roomSouth;
    }
}
