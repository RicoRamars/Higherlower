package com.example.ricoramars.higher_lower;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.ricoramars.higher_lower.R.drawable.d1;
import static com.example.ricoramars.higher_lower.R.drawable.d2;
import static com.example.ricoramars.higher_lower.R.drawable.d3;
import static com.example.ricoramars.higher_lower.R.drawable.d4;
import static com.example.ricoramars.higher_lower.R.drawable.d5;
import static com.example.ricoramars.higher_lower.R.drawable.d6;

public class MainActivity extends AppCompatActivity {
    //Local variables
    private ImageView imageViewDice;
    private TextView tv_score;
    private TextView tv_highScore;
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;
    private List<String> dThrow;
    private int score = 0;
    private int numberHighScore = 0;
    private int theThrow;
    private Random random;
    private int number, numberNew;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dThrow = new ArrayList<>();
        mListView = findViewById(R.id.list_view_throws);

        imageViewDice = findViewById(R.id.image_view_dice);

        tv_score = findViewById(R.id.tv_score);
        tv_highScore = findViewById(R.id.tv_highScore);

        random = new Random();

        number = 1;

        // The fab to guess the outcome of the dice will be higher
        fab = findViewById(R.id.fab_higher);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberNew = random.nextInt(6)+1;
                if (numberNew > number) {
                    score++;
                    Toast.makeText(MainActivity.this, "You win!", Toast.LENGTH_SHORT).show();
                } else if (score > numberHighScore) {
                    numberHighScore = score;
                    tv_highScore.setText("Highscore: " + Integer.toString(numberHighScore));
                    score = 0;
                } else {
                    score = 0;
                    Toast.makeText(MainActivity.this, "You Lose!", Toast.LENGTH_SHORT).show();
                }
                tv_score.setText("score: " + Integer.toString(score));
                number = numberNew;
                rollDice();

            }
        });
        // The fab to guess the outcome of the dice will be lower
        fab = findViewById(R.id.fab_lower);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberNew = random.nextInt(6)+1;
                if (numberNew < number) {
                    score++;
                    Toast.makeText(MainActivity.this, "You win!", Toast.LENGTH_SHORT).show();
                } else if (score > numberHighScore) {
                    numberHighScore = score;
                    tv_highScore.setText("Highscore: " + Integer.toString(numberHighScore));
                    score = 0;
                } else {
                    score = 0;
                    Toast.makeText(MainActivity.this, "You Lose!", Toast.LENGTH_SHORT).show();
                }
                tv_score.setText("score: " + Integer.toString(score));
                number = numberNew;
                rollDice();
                }
            });
        }


    //
    private void rollDice() {

        switch (numberNew) {
            case 1:
                imageViewDice.setImageResource(d1);
                theThrow = 1;
                break;
            case 2:
                imageViewDice.setImageResource(d2);
                theThrow = 2;
                break;
            case 3:
                imageViewDice.setImageResource(d3);
                theThrow = 3;
                break;
            case 4:
                imageViewDice.setImageResource(d4);
                theThrow = 4;
                break;
            case 5:
                imageViewDice.setImageResource(d5);
                theThrow = 5;
                break;
            case 6:
                imageViewDice.setImageResource(d6);
                theThrow = 6;
                break;
        }
        addToList();
    }

    //Add the dice roll to the listView
    private void addToList() {
        String text = "You rolled " + Integer.toString(theThrow);
        dThrow.add(text);
        updateUI();
    }

    private void updateUI() {
        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dThrow);
            mListView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }
}

