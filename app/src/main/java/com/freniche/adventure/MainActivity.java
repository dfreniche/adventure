package com.freniche.adventure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.freniche.adventure.model.Inventory;
import com.freniche.adventure.model.Item;
import com.freniche.adventure.model.MapGenerator;
import com.freniche.adventure.model.Room;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.activity_main_help) ImageButton helpButton;
    @BindView(R.id.activity_main_scene_text) TextView mainText;
    @BindView(R.id.activity_main_north_button) ImageButton northButton;
    @BindView(R.id.activity_main_south_button) ImageButton southButton;
    @BindView(R.id.activity_main_east_button) ImageButton eastButton;
    @BindView(R.id.activity_main_west_button) ImageButton westButton;
    @BindView(R.id.activity_main_inventory) Button inventoryButton;
    @BindView(R.id.activity_main_drop) Button dropButton;
    @BindView(R.id.activity_main_take) Button takeButton;
    @BindView(R.id.activity_main_look_button) ImageButton lookButton;

    Inventory inventory = new Inventory();
    Room currentRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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

        lookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainText.setText(currentRoom.getDescription() +
                        "\n" + currentRoom.getRoomItems());
            }
        });

        inventoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainText.setText(inventory.print());
            }
        });


        initGame();
        repaintScene();
    }

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


