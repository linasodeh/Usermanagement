package com.example.usermanagement;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    Context context;
    ArrayList<Movement> movementArrayList;

    public MyAdapter(Context context, ArrayList<Movement> movementArrayList) {
        this.context = context;
        this.movementArrayList = movementArrayList;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        Movement movement=movementArrayList.get(position);
        holder.type.setText(movement.getType());
        holder.time.setText(String.valueOf(movement.getTime()));
        holder.imageViewPlayMovement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return movementArrayList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView type, time;
        ImageView imageViewPlayMovement;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            type= itemView.findViewById(R.id.tvMovementTypeItem);
            time=itemView.findViewById(R.id.tvMovementTimeItem);
            imageViewPlayMovement=itemView.findViewById(R.id.ivMovementPlayItem);
        }
    }
}
