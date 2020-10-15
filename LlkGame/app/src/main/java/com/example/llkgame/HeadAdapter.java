package com.example.llkgame;

import android.content.Context;
import android.media.Image;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HeadAdapter extends RecyclerView.Adapter<HeadAdapter.ViewHolder> {

private Context context;
private static ArrayList<Head> headsList;
public static int headId;

public HeadAdapter(Context context,ArrayList<Head> headsList){
        this.context = context;
        this.headsList = headsList;
        }

static class ViewHolder extends RecyclerView.ViewHolder {
    private ImageButton mHeadImage;
    private static int clickPosition = 0;
    private static int clickImageId = 0;
    public ViewHolder(View itemView){
        super(itemView);
        mHeadImage = (ImageButton) itemView.findViewById(R.id.imageButton3);
        mHeadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置点击选中效果，如果和上次点击的是不同图片，则取消上次点击的选中效果
                mHeadImage.setAlpha(100);
                if (clickPosition != getAdapterPosition()) {
                    ImageButton button2 = HeadFragment.recyclerView.getLayoutManager().
                            findViewByPosition(clickPosition).findViewById(R.id.imageButton3);
                    button2.setAlpha(255);
                }
                //如果点击的图片与上次点击不同，获取点击的位置和图片信息
                if (clickImageId != headsList.get(getAdapterPosition()).headImageId) {
                    clickPosition = getAdapterPosition();
                    clickImageId = headsList.get(clickPosition).headImageId;
                }
                //获取选中头像id
                headId = headsList.get(getAdapterPosition()).headImageId;
            }
        });
    }
}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(context,R.layout.head_item,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HeadAdapter.ViewHolder holder, int position) {
        Head data = headsList.get(position);
        holder.mHeadImage.setImageResource(data.headImageId);
    }

    @Override
    public int getItemCount() {
        return headsList.size();
    }

}
