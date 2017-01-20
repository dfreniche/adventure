package com.freniche.adventure;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.freniche.adventure.model.Inventory;
import com.freniche.adventure.model.Item;
import com.freniche.adventure.model.MapGenerator;
import com.freniche.adventure.model.Player;
import com.freniche.adventure.model.Room;
import com.freniche.adventure.util.Constants;
import com.squareup.picasso.Picasso;

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
    @BindView(R.id.activity_main_scene_image) ImageView sceneImage;

    Inventory inventory = new Inventory();
    Player player = new Player();
    Room currentRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Picasso.with(this).setIndicatorsEnabled(true);
        Picasso.with(this).setLoggingEnabled(true);

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

        dropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DropItemActivity.class);
                i.putExtra(Constants.KEY_INTENT_INVENTORY, inventory);

                startActivityForResult(i, 1);
            }
        });

        takeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, DropItemActivity.class);
                i.putExtra(Constants.KEY_INTENT_TAKE_ITEM_FROM_ROOM, currentRoom);
                startActivityForResult(i, 2);
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

        MapGenerator.generate(this);

        currentRoom = MapGenerator.initialRoom;
    }

    private void repaintScene() {
        // write room description on screen
        mainText.setText(currentRoom.getDescription());

        if (currentRoom.getImageUrl() != null && currentRoom.getImageUrl().length()>0) {
            Picasso.
                    with(this).
                    load(currentRoom.getImageUrl()).
                    into(sceneImage);
        }

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

        // check for monster

        if (currentRoom.getMonster() != null) {
            Intent i = new Intent(MainActivity.this, FightMonsterActivity.class);
            i.putExtra("monster", currentRoom.getMonster());
            i.putExtra("player", player);
            startActivity(i);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1){  // drop
            if (resultCode == RESULT_OK) {
                int itemPosition = data.getIntExtra(Constants.KEY_INTENT_DROP_ITEM_POSITION, -1);

                Item item = inventory.getItem(itemPosition);
                currentRoom.getItems().add(item);
                inventory.deleteItem(itemPosition);

                Snackbar.make(mainText, getString(R.string.dropped_item_text) + item.getName(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
            }
        } else if (requestCode == 2) { // take
            if (resultCode == RESULT_OK) {
                int itemPosition = data.getIntExtra(Constants.KEY_INTENT_DROP_ITEM_POSITION, -1);

                Item item = currentRoom.getItems().get(itemPosition);
                inventory.add(item);
                currentRoom.getItems().remove(item);

                Snackbar.make(mainText, "Taken " + item.getName(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
            }
        }

    }
}


