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

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.ViewHolder> {

    private Context context;
    private static ArrayList<Games> gamesList;
    public static int images = 0;
    public static int timeStart = 0;
    private static int clickImageId = 0;
    private static int clickPosition = 0;
    public static String gameTime = "0";
    private static int a=0;

    public GameAdapter(Context context,ArrayList<Games> gamesList){
        this.context = context;
        this.gamesList = gamesList;
    }

    public static String getgameTime() {
        return gameTime;
    }

    public static void setGameTime(String gameTime) {
        GameAdapter.gameTime = gameTime;
    }

    //自定义ViewHolder并实现图片配对玩法
    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageButton mGameImage;
        private String ImageName;
        public ViewHolder(View itemView){
            super(itemView);
            mGameImage = (ImageButton) itemView.findViewById(R.id.imageButton2);
            mGameImage.setOnClickListener(new View.OnClickListener() {
                //设置连连看游戏
                @Override
                public void onClick(View v) {
                    //设置点击选中效果
                    mGameImage.setAlpha(100);
                    if (clickPosition != getAdapterPosition())
                    {//点击位置不等于上次点击位置时，将上次点击的图片恢复
                        ImageButton button2 = GameFragment.recyclerView.getLayoutManager().
                                findViewByPosition(clickPosition).findViewById(R.id.imageButton2);
                        button2.setAlpha(255);
                    }

                    //点击后开始计时和结束计时，以消除的图片对数作为判定条件
                    if (images == 0 && timeStart == 0) {
                        GameFragment.chronometer.start();
                        timeStart = 1;
                    }
                    if (images == 34) {
                        if(a==0){
                            a=a+1;
                        }else if(clickPosition != getAdapterPosition()){
                            GameFragment.chronometer.stop();
                            gameTime = GameFragment.chronometer.getText().toString();
                            a=0;
                        }
                    }

                    //点击的图片与之前图片不同时将图片id设置为当前点击图片id
                    if (clickImageId != gamesList.get(getAdapterPosition()).gameImageId) {
                        clickPosition = getAdapterPosition();
                        clickImageId = gamesList.get(clickPosition).gameImageId;
                    }
                    //点击图片相同且是处于不同位置时，将两个图片消除并取消可点击属性
                    else if (clickImageId == gamesList.get(getAdapterPosition()).gameImageId &&
                            clickPosition != getAdapterPosition()) {
                        mGameImage.setImageResource(R.drawable.ten);
                        mGameImage.setClickable(false);
                        //通过获取位置的函数获取到上次点击的图片
                        ImageButton button2 = GameFragment.recyclerView.getLayoutManager().
                                findViewByPosition(clickPosition).findViewById(R.id.imageButton2);
                        button2.setImageResource(R.drawable.ten);
                        button2.setClickable(false);
                        mGameImage.setAlpha(255);
                        //重置点击的图片和位置
                        clickImageId = 0;
                        clickPosition = 0;
                        //计算对数
                        images += 2;
                    }
                }
            });
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = View.inflate(context,R.layout.game_item,null);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull GameAdapter.ViewHolder holder, int position) {
        Games data = gamesList.get(position);
        holder.mGameImage.setImageResource(data.gameImageId);
        holder.ImageName = data.gameName;
    }

    @Override
    public int getItemCount() {
        return gamesList.size();
    }

}
