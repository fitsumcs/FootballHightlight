package com.example.footbalhighlight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecycleAdapter  extends RecyclerView.Adapter<RecycleAdapter.MyIdeaHandler> implements Filterable {

    Context context;
    ArrayList<FootballModel> highLightList;
    ArrayList<FootballModel> copy_highLightList;
    private  OnItemClickedListener m_onItemClickedListener;

    public RecycleAdapter(Context con, ArrayList<FootballModel> highLightList, OnItemClickedListener onItemClickedListener)
    {

        this.context = con;
        this.highLightList = highLightList;
        this.m_onItemClickedListener = onItemClickedListener;
        //for using it separately
        copy_highLightList = new ArrayList<>(highLightList);


    }

    @NonNull
    @Override
    public MyIdeaHandler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row,parent,false);
        MyIdeaHandler myIdeaHandler = new MyIdeaHandler(view,m_onItemClickedListener);
        return myIdeaHandler;

    }

    @Override
    public void onBindViewHolder(@NonNull MyIdeaHandler holder, int position) {

        FootballModel footballModel = highLightList.get(position);
        String teams = footballModel.getTitle();
        String catagory = footballModel.getCatagory();
        String date = footballModel.getDate();
        String imagUrl = footballModel.getThumbnail();

        //set the view
        holder.teams.setText(teams);
        holder.catagory.setText(catagory);
        holder.date.setText(date);

        Glide.with(context)
                .load(imagUrl)
                .into(holder.imgUrl);

    }


    @Override
    public int getItemCount() {
        return highLightList.size();
    }

    //the filter
    @Override
    public Filter getFilter() {
        return myHighlightFilter;
    }
    private  Filter myHighlightFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<FootballModel> filterList = new ArrayList<>();

            if(charSequence == null || charSequence.length()==0)
            {
                filterList.addAll(copy_highLightList);
            }
            else{
                String fillterPattern = charSequence.toString().toLowerCase().trim();
                for (FootballModel item: copy_highLightList)
                {
                       if(item.getTitle().toLowerCase().contains(fillterPattern))
                       {
                           filterList.add(item);
                       }
                }
                

            }

            FilterResults results = new FilterResults();
            results.values = filterList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            highLightList.clear();
            highLightList.addAll((List)filterResults.values);
            notifyDataSetChanged();


        }
    };

    public class MyIdeaHandler extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView teams;
        TextView catagory;
        TextView date;
        ImageView imgUrl;
        OnItemClickedListener onItemClickedListener;

        public MyIdeaHandler(@NonNull View myview , OnItemClickedListener onItemClickedListener) {
            super(myview);
            teams   =   myview.findViewById(R.id.textView_teams);
            catagory   =   myview.findViewById(R.id.textView_catagory);
            date =   myview.findViewById(R.id.textView_Date);
            imgUrl =   myview.findViewById(R.id.imageView3);

            this.onItemClickedListener = onItemClickedListener;

            myview.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

            onItemClickedListener.onItemClicked(getAdapterPosition());

        }
    }


}
