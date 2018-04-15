package com.example.android.simplefeeder;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import static android.R.attr.start;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static java.security.AccessController.getContext;

/**
 * Created by lenovo on 4/8/2018.
 */

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.ViewHolder>
{
    private View.OnClickListener mClickListner;
    List<Details> detailValues;
    Context context;

    public AdapterClass(List<Details> detailValues,Context context) {
        this.detailValues = detailValues;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent,final int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.item_list,parent,false);

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

        public ViewHolder(final View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    Intent i=new Intent(context,WebActivity.class);
                    i.putExtra("WEB_ADDRESS",detailValues.get(pos).getLink());
                    context.startActivity(i);

                }
            });
            title=(TextView)itemView.findViewById(R.id.heading);
            description=(TextView)itemView.findViewById(R.id.description);
            image=(ImageView)itemView.findViewById(R.id.imageView);
        }
    }
}

