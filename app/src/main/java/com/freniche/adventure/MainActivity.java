package com.freniche.adventure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.freniche.adventure.model.Inventory;
import com.freniche.adventure.model.Item;
import com.freniche.adventure.model.MapGenerator;
import com.freniche.adventure.model.Room;

public class MainActivity extends AppCompatActivity {

    ImageButton helpButton;
    TextView mainText;
    ImageButton northButton;
    ImageButton southButton;
    ImageButton eastButton;
    ImageButton westButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainText = (TextView) findViewById(R.id.activity_main_scene_text); // 2
        helpButton = (ImageButton) findViewById(R.id.activity_main_help);
        northButton = (ImageButton) findViewById(R.id.activity_main_north_button);
        southButton = (ImageButton) findViewById(R.id.activity_main_south_button);
        eastButton = (ImageButton) findViewById(R.id.activity_main_east_button);
        westButton = (ImageButton) findViewById(R.id.activity_main_west_button);

        // north button
        northButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentRoom = currentRoom.getRoomNorth();
                repaintScene();

            }
        });
        // south button
        southButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentRoom = currentRoom.getRoomSouth();
                repaintScene();

            }
        });
        // east button
        eastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentRoom = currentRoom.getRoomEast();
                repaintScene();

            }
        });
        // west button
        westButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentRoom = currentRoom.getRoomWest();
                repaintScene();
            }
        });


        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(i);
            }
        });


        initGame();
        repaintScene();
    }

    Inventory inventory = new Inventory();
    Room currentRoom;

    private void initGame() {
        Item sword = new Item("Sword", "A sharp blade");
        Item pieceOfPaper = new Item("Piece Of Paper", "A blank piece of paper");
        Item rubberChicken = new Item("Pollo de goma", "A blank piece of paper");

        inventory.add(sword);
        inventory.add(pieceOfPaper);
        inventory.add(rubberChicken);

        MapGenerator.generate();

        currentRoom = MapGenerator.initialRoom;
    }

    private void repaintScene() {
        // write room description on screen
        mainText.setText(currentRoom.getDescription());

        if (currentRoom.getRoomNorth() != null) {
            // there's a room pointing north
            northButton.setVisibility(View.VISIBLE);
        } else {
            // no room, I'm sad
            northButton.setVisibility(View.INVISIBLE);
        }

        if (currentRoom.getRoomSouth() != null) {
            // there's a room pointing north
            southButton.setVisibility(View.VISIBLE);
        } else {
            // no room, I'm sad
            southButton.setVisibility(View.INVISIBLE);
        }

        if (currentRoom.getRoomEast() != null) {
            // there's a room pointing north
            eastButton.setVisibility(View.VISIBLE);
        } else {
            // no room, I'm sad
            eastButton.setVisibility(View.INVISIBLE);
        }

        if (currentRoom.getRoomWest() != null) {
            // there's a room pointing north
            westButton.setVisibility(View.VISIBLE);
        } else {
            // no room, I'm sad
            westButton.setVisibility(View.INVISIBLE);
        }

    }















}


