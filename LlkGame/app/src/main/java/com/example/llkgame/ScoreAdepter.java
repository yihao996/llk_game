package com.example.llkgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ScoreAdepter extends ArrayAdapter<Score> {
    private int resourceId;
//    private Context context;

    public ScoreAdepter(Context context,int textViewResourceId,
                        List<Score> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    public View getView(int position, View scoreView, ViewGroup parent){
        Score score = (Score) getItem(position);
        View view;
        ViewHolder viewHolder;
        if(scoreView == null){
            view = LayoutInflater.from(
                    getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.score = (TextView)view.findViewById(R.id.textView3);
            viewHolder.thisHead = (ImageView)view.findViewById(R.id.imageView);
            viewHolder.userName = (TextView)view.findViewById(R.id.textView6);
            view.setTag(viewHolder);
        }else{
            view = scoreView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.score.setText(score.getScore());
        viewHolder.thisHead.setImageResource(score.getScoreImage());
        viewHolder.userName.setText(score.getUserName());
        return view;
    }

    class ViewHolder{
        TextView score;
        ImageView thisHead;
        TextView userName;
    }

}
