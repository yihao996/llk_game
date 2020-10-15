package com.example.llkgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameFragment extends Fragment {
    private View view;
    public static RecyclerView recyclerView;
    private ArrayList<Games> gamesList = new ArrayList<Games>();
    private GameAdapter gameAdapter;
    private int[] imageId = {R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,
            R.drawable.five,R.drawable.six,R.drawable.seven,R.drawable.eight,R.drawable.nine};
    private String[] imageName = {"a","b","c","d","e","f","g","h","i"};
    public static Chronometer chronometer;

    public GameFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_game, container, false);
        chronometer = (Chronometer) view.findViewById((R.id.chronometer1));
        initGameView();
        initData();
        return view;
    }

    //设置图片
    private void initData(){
        Integer[] id = {0,1,2,3,4,5,6,7,8,
                0,1,2,3,4,5,6,7,8,
                0,1,2,3,4,5,6,7,8,
                0,1,2,3,4,5,6,7,8};
        List<Integer> list=new ArrayList<Integer>(Arrays.asList(id));
        Collections.shuffle(list);//随机生成图片输出顺序
        for (int i=0;i<36;i++){
            Games games = new Games();
            games.setGameImageId(imageId[Integer.parseInt(list.get(i).toString())]);
            games.setGameName(imageName[Integer.parseInt(list.get(i).toString())]);
            gamesList.add(games);
        }
    }

    //设置recyclerView的布局
    private void initGameView(){
        recyclerView = (RecyclerView)view.findViewById(R.id.game_recyclerview);
        gameAdapter = new GameAdapter(getActivity(),gamesList);
        recyclerView.setAdapter(gameAdapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(6,
                StaggeredGridLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
    }


    private Button button2;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button button1;
        button1 = getView().findViewById(R.id.button5);
        button2 = getView().findViewById(R.id.button9);
        //记录得分、用户名及头像
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences score =getActivity().
                        getSharedPreferences("GameScore",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = score.edit();
                int num = score.getInt("SCORE_NUM",0);
                String scoreName = "SCORE"+Integer.toString(num+1);
                String scoreImage = "IMAGE"+Integer.toString(num+1);
                String userNameId = "USER"+Integer.toString(num+1);
                //直接将得分储存为想要输出的字符串格式
                editor.putString(scoreName,"得分记录"+ Integer.toString(num+1)+
                        ": "+GameAdapter.getgameTime());
                editor.putString(userNameId,"ID:"+ SignFragment.userName);
                editor.putInt("SCORE_NUM",num+1);
                editor.putInt(scoreImage,SignFragment.headId);
                editor.apply();
                button2.setClickable(false);//使按钮只能记录一次得分
            }
        });
        //返回主界面
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //离开界面时将计时器和得分按钮重置
                GameAdapter.images = 0;
                GameAdapter.timeStart = 0;
                GameAdapter.setGameTime("0");
                button2.setClickable(true);

                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_gameFragment_to_mainFragment);
            }
        });
    }
}
