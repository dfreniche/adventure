package com.freniche.adventure.model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Inventory implements Serializable {

    private LinkedList<Item> inventory = new LinkedList<>();

    public Inventory() {

    }

    public List<String> getItemNames() {
        List<String> names = new ArrayList<>();

        for (Item item: inventory) {
            names.add(item.getName());
        }

        return names;
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

    public Item getItem(int itemPosition) {
        return inventory.get(itemPosition);
    }

    public void deleteItem(int itemPosition) {
        inventory.remove(itemPosition);
    }
}
