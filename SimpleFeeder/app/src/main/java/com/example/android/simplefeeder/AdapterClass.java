package com.example.android.simplefeeder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lenovo on 4/8/2018.
 */

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder>
{
    List<Details> detailValues;
    Context context;

    public AdapterClass(List<Details> detailValues,Context context) {
        this.detailValues = detailValues;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Details details=detailValues.get(position);
        holder.title.setText(details.getTitle());
        holder.description.setText(details.getDescription());
        Picasso.with(context).load(details.getImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return detailValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title;
        private TextView description;
        private ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.heading);
            description=(TextView)itemView.findViewById(R.id.description);
            image=(ImageView)itemView.findViewById(R.id.imageView);
        }
    }
}

