package com.snn.bildim;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList<Card> cards;
    private LayoutInflater layoutInflater;

    CustomAdapter(Context context, ArrayList<Card> cards) {
        this.cards = cards;
        this.context = context;
        this.activity = (Activity) context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MViewHolder(layoutInflater.inflate(R.layout.card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MViewHolder holder, int position) {
        holder.setData(position);
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }

    class MViewHolder extends RecyclerView.ViewHolder {

        private Toast toast;
        private int position;
        private TextView textView;

        private MViewHolder(@NonNull View itemView) {
            super(itemView);
            CardView cardView = itemView.findViewById(R.id.cardView);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (position >= MainActivity.unlockTo) {
                        showToast("Bu bilmece şu an kilitli! " +
                                "Kilidi açık soruları cevapladıktan sonra tekrar deneyin.");
                    } else {
                        Intent intent = new Intent(context, PuzzleActivity.class);
                        intent.putExtra("Position", position);
                        intent.putExtra("Card", cards.get(position));
                        activity.startActivityForResult(intent, 0);
                    }
                }
            });
            textView = itemView.findViewById(R.id.textViewCardTitle);
        }

        @SuppressLint("SetTextI18n")
        void setData(int position) {
            this.position = position;
            switch (cards.get(this.position).getValue()) {
                case 0:
                    itemView.setBackground(GradientColor.GRAY);
                    break;
                case 1:
                    itemView.setBackground(GradientColor.RED);
                    break;
                case 2:
                    itemView.setBackground(GradientColor.GREEN);
                    break;
                case 3:
                    itemView.setBackground(GradientColor.YELLOW);
                    break;
            }

            if (position < MainActivity.unlockTo) {
                textView.setText(cards.get(position).getTitle());
            } else {
                textView.setText("Kilitli Bilmece");
            }
        }

        private void showToast(String text) {
            if (this.toast != null) {
                this.toast.cancel();
            }
            this.toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
            this.toast.show();
        }
    }
}
