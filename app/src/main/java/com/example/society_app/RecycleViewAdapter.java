package com.example.society_app;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder>{
    private Context mContext;
    private List<items> mData;

    public RecycleViewAdapter(Context mContext, List<items> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater=LayoutInflater.from(mContext);
        view=mInflater.inflate(R.layout.feautres,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {

        final String name = mData.get(position).getTitle();
        int res = mData.get(position).getThumbnail();

        holder.feature_name.setText(name);
        holder.feature_img.setImageResource(res);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name=="MEMBER DETAILS"){
                    Intent intent=new Intent(mContext,memberdetails.class);
                    mContext.startActivity(intent);
                }
               else if(name=="MAINTENANCE DETAILS"){
                    Intent intent=new Intent(mContext,maintenancedetails.class);
                    mContext.startActivity(intent);
                }
                else if(name=="EXPENSES"){
                    Intent intent=new Intent(mContext,expenses.class);
                    mContext.startActivity(intent);
                }
                else if(name=="MEETING DETAILS"){
                    Intent intent=new Intent(mContext,meetingdetails.class);
                    mContext.startActivity(intent);
                }
                else if(name=="NOTICE"){
                    Intent intent=new Intent(mContext,notice.class);
                    mContext.startActivity(intent);
                }
                else if(name=="NOTIFICATION"){
                    Intent intent=new Intent(mContext,notification.class);
                    mContext.startActivity(intent);
                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder{


        TextView feature_name;
        ImageView feature_img;
        CardView card;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            feature_name=(TextView) itemView.findViewById(R.id.feture);
            feature_img=(ImageView) itemView.findViewById(R.id.feature_img_id);
            card=(CardView) itemView.findViewById(R.id.cardview_id);
        }
    }


}
