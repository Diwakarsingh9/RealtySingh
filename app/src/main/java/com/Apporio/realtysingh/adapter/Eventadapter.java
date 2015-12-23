package com.Apporio.realtysingh.adapter;


        import android.content.Context;
        import android.content.Intent;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.Apporio.realtysingh.Eventsfragment;
        import com.Apporio.realtysingh.ListInneractivity;
        import com.Apporio.realtysingh.R;
        import com.squareup.picasso.Picasso;

        import java.util.ArrayList;

/**
 * Created by gaurav on 11/21/2015.
 */
public class Eventadapter extends BaseAdapter {

    ArrayList<String> et= new ArrayList<String>();
    ArrayList<String> Title= new ArrayList<String>();
    ArrayList<String> Source= new ArrayList<String>();
    ArrayList<String> descp= new ArrayList<String>();
    ArrayList<String> Date= new ArrayList<String>();
    ArrayList<String> images= new ArrayList<String>();
    public  static ArrayList<String> src_link;
    Context ctc;

    LayoutInflater inflater;


    public Eventadapter(Context activity, ArrayList<String> moduleet, ArrayList<String> modulesrc, ArrayList<String> moduletilte, ArrayList<String> moduledate, ArrayList<String> moduledescp, ArrayList<String> moduleimg, ArrayList<String> modulesrc_link) {

        this.ctc=activity;
        this.et= moduleet;
        this.descp= moduledescp;
        this.Title=moduletilte;
        this.Source=modulesrc;
        this.Date= moduledate;
        this.images = moduleimg;
        this.src_link = modulesrc_link;
        inflater = LayoutInflater.from(this.ctc);
    }

    @Override
    public int getCount() {
        return Title.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public  class Holder{

        TextView tvTitle,tvSource,tvDate;
        ImageView image;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Holder holder;
        if(convertView== null) {
            convertView = inflater.inflate(R.layout.gridlistitems,null);
            holder = new Holder();
            convertView.setTag(holder);
        }
        else {
            holder = (Holder)convertView.getTag();
        }


        holder.tvTitle = (TextView)convertView.findViewById(R.id.title);
        holder.tvTitle.setText(Title.get(position));

        holder.tvSource = (TextView)convertView.findViewById(R.id.source);
        holder.tvSource.setText(Source.get(position));

        holder.tvDate = (TextView)convertView.findViewById(R.id.date);
        holder.tvDate.setText(Date.get(position));

        holder.image = (ImageView)convertView.findViewById(R.id.bannerimage);
        Picasso.with(ctc)
                .load("http://meetsingh.com/" + images.get(position))
                .placeholder(R.drawable.stub) // optional
                .error(R.drawable.stub)         // optional
                .into(holder.image);
        holder.image.setTag(position);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = (int) holder.image.getTag();
                Intent in = new Intent(ctc, ListInneractivity.class);

                in.putExtra("title", "EVENTS");
                in.putExtra("head", Title.get(pos));
                in.putExtra("date", Date.get(pos));
                in.putExtra("descp", descp.get(pos));
                in.putExtra("img", images.get(pos));
                in.putExtra("src", Source.get(pos));
                in.putExtra("plc", et.get(pos));
                in.putExtra("src_link",src_link.get(pos));
                in.putExtra("id", Eventsfragment.module_id.get(pos));
                ctc.startActivity(in);


            }
        });
        return convertView;
    }

}