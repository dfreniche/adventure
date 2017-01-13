package com.freniche.adventure.model;


import java.util.LinkedList;

public class Inventory {

    private LinkedList<Item> inventory = new LinkedList<>();

    public Inventory() {

    }

    public String print() {
        String result = "";

        for (Item item: inventory) {
            result = result + item.getName() + "\n";
        }

        return result;
    }

    public void add(Item item) {
        this.inventory.add(item);
    }
}
