package com.example.trouvaille;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomListHer extends ArrayAdapter<String> {
    private final Activity context;
    private String[] hotel;
    private Integer[] imageId;
    String[] hotel1;
    final Integer[] imageId1;
    public CustomListHer(Activity context, String[] hotel, Integer[] imageId) {
        super(context, R.layout.custom_list_her, hotel);
        this.context = context;
        this.hotel = hotel;
        this.imageId = imageId;
        hotel1=hotel;
        imageId1=imageId;
    }
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.custom_list_her, null, true);
        TextView txtTitle = rowView.findViewById(R.id.txt);
        ImageView imageView = rowView.findViewById(R.id.img);
        txtTitle.setText(hotel[position]); //hotel[0]
        imageView.setImageResource(imageId[position]); //imageId[0]
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, HerInfoActivity.class);
                intent.putExtra("key_her", hotel[position]);
                context.startActivity(intent);
            }
        });
        return rowView;
    }
    @Override
    public Filter getFilter()
    {
        return filter;
    }
    Filter filter = new Filter()
    {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence)
        {
            String[] filteredList = new String[hotel1.length];
            if (charSequence.toString().isEmpty()) {
                filteredList=hotel1;
            }
            else {
                int k=0;
                for (int i=0;i<hotel1.length;i++)
                {
                    if (hotel1[i].toLowerCase().contains(charSequence.toString().toLowerCase()))
                    {
                        filteredList[k]=hotel1[i];
                        k++;
                    }
                }
            }
            FilterResults filterResults=new FilterResults();
            filterResults.values=filteredList;
            return filterResults;
        }
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults)
        {
            hotel = (String[]) filterResults.values;
            if (charSequence.toString().isEmpty()) {
                imageId=imageId1;
            }
            else
            {
                int k=0;
                for (int i=0;i<hotel1.length;i++)
                {
                    if (hotel1[i].toLowerCase().contains(charSequence.toString().toLowerCase()))
                    {
                        imageId[k]=imageId1[i];
                        k++;
                    }
                }
            }
            notifyDataSetChanged();
        }
    };
}