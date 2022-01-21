package com.devkh.mapdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PitchNearByAdapter extends RecyclerView.Adapter<PitchNearByAdapter.PitchCardViewHolder> {

    private Context mContext;
    private ArrayList<PitchCard> mPitchCards;

    public PitchNearByAdapter(Context context, ArrayList<PitchCard> pitchCards) {
        mContext = context;
        mPitchCards = pitchCards;
    }

    @NonNull
    @Override
    public PitchCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_pitch_card, parent, false);
        return new PitchCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PitchCardViewHolder holder, int position) {
        holder.image.setImageResource(mPitchCards.get(position).getImage());
        holder.name.setText(mPitchCards.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mPitchCards.size();
    }

    class PitchCardViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView name;

        public PitchCardViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_pitch);
            name = itemView.findViewById(R.id.name_pitch);
        }

    }

}
