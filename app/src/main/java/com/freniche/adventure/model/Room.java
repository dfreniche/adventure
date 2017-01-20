package com.freniche.adventure.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Room implements Serializable {
    private String description;

    private LinkedList<Item> items;

    private Room roomNorth;
    private Room roomEast;
    private Room roomWest;
    private Room roomSouth;

    private String imageUrl;

    private Monster monster;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // lazy getter
    public LinkedList<Item> getItems() {
        if (items == null) {
            items = new LinkedList<>();
        }
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

    public List<String> getItemNames() {
        ArrayList<String> result = new ArrayList<>();

        for (Item item: items) {
            result.add(item.getName());
        }

        return result;
    }


    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }
}
