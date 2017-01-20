package com.freniche.adventure;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.freniche.adventure.model.Monster;
import com.freniche.adventure.model.Player;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FightMonsterActivity extends AppCompatActivity {

    @BindView(R.id.activity_fight_monster_button_fight) Button fightButton;
    @BindView(R.id.activity_fight_monster_monster_name) TextView monsterNameText;
    @BindView(R.id.activity_fight_monster_monster_description) TextView monsterDescriptionText;
    @BindView(R.id.activity_fight_monster_monster_life) TextView monsterLifeMeterText;
    @BindView(R.id.activity_fight_monster_player_life) TextView playerLifeMeterText;
    @BindView(R.id.activity_fight_monster_monster_image) ImageView monsterImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fight_monster);
        ButterKnife.bind(this);

        Intent i = getIntent();
        final Monster monster = (Monster) i.getSerializableExtra("monster");
        final Player player = (Player) i.getSerializableExtra("player");

        monsterNameText.setText(monster.getName());
        monsterDescriptionText.setText(monster.getDescription());
        Picasso.with(this).load(monster.getImageUrl()).into(monsterImage);

        fightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int monsterAttack = monster.attack();
                int playerAttack = player.attack();
                player.setHealthPoints(player.getHealthPoints() - monsterAttack);
                monster.setHealthPoints(monster.getHealthPoints() - playerAttack);

                playerLifeMeterText.setText("HP " + player.getHealthPoints());
                monsterLifeMeterText.setText("HP " + monster.getHealthPoints());
            }
        });
    }
}
