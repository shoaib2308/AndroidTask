package com.singularity.androidtask.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.singularity.androidtask.Model.Datum;
import com.singularity.androidtask.R;
import com.singularity.androidtask.View.LockDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {
    List<Datum> roomListResponse = new ArrayList<>();
    Context context;
    TextView tv_org, tv_property, tv_room;
    LinearLayout linearLayout;

    public Adapter(Context context, List<Datum> dataListResponse) {
        this.roomListResponse = dataListResponse;
        this.context = context;
    }

    @NonNull
    @Override
    public Adapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_layout, parent, false);
        Viewholder viewholder = new Viewholder(view); // pass the view to View Holder
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder v, int position) {
        final Datum roomslist = roomListResponse.get(position);
        tv_org.setText("Org " + roomslist.getOrg().getId());
        tv_property.setText("Property " + roomslist.getProperty().getId());
        tv_room.setText("Room " + roomslist.getRoom().getId());
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, LockDetailsActivity.class);
                i.putExtra("roomid", roomslist.getRoom().getId());
                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return roomListResponse.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tv_org = itemView.findViewById(R.id.tv_org);
            tv_property = itemView.findViewById(R.id.tv_property);
            tv_room = itemView.findViewById(R.id.tv_room);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }
}
