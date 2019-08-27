package com.snn.bildim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static int unlockTo = 4;
    private ArrayList<Card> cards;
    private RecyclerView recyclerView;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        createCards();

        ImageView imageViewList = findViewById(R.id.imageViewList);
        imageViewList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setListDisplay("List");
            }
        });

        ImageView imageViewGrid = findViewById(R.id.imageViewGrid);
        imageViewGrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setListDisplay("Grid");
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        customAdapter = new CustomAdapter(this, cards);
        recyclerView.setAdapter(customAdapter);

        setListDisplay("List");
    }

    private void createCards() {
        String[] titles = getResources().getStringArray(R.array.titles);
        String[] answers = getResources().getStringArray(R.array.answers);
        String[] questions = getResources().getStringArray(R.array.questions);

        cards = new ArrayList<>();

        for (int i = 0; i < titles.length && i < answers.length && i < questions.length; ++i) {
            cards.add(new Card(titles[i], answers[i], questions[i]));
        }
    }

    private void setListDisplay(String type) {
        if (type.equals("List")) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
        } else if (type.equals("Grid")) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            int value = data.getIntExtra("Value", 0);
            int position = data.getIntExtra("Position", 0);

            if (value == 2 && cards.get(position).getValue() != 2) {
                unlockTo += 2;
                customAdapter.notifyItemChanged(unlockTo - 1);
                customAdapter.notifyItemChanged(unlockTo - 2);
            } else if (value == 3 && cards.get(position).getValue() != 3) {
                unlockTo += 1;
                customAdapter.notifyItemChanged(unlockTo - 1);
            }

            cards.get(position).setValue(value);
            customAdapter.notifyItemChanged(position);
        }
    }
}
