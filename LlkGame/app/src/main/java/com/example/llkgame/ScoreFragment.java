package com.example.llkgame;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ScoreFragment extends Fragment {
    private View view;
    private List<Score> scoreList = new ArrayList<Score>();

    public ScoreFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_score, container, false);
        initData();
        initScoreView();
        return view;
    }

    //获取存储在GameScore.xml的数据并将其作为ListView中输出的数据
    private void initData(){
        SharedPreferences score =getActivity().
                getSharedPreferences("GameScore", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = score.edit();
        //获取已存储的得分记录条数
        int num = score.getInt("SCORE_NUM",0);
        String scoreName;
        String scoreImage;
        String userNameID;
        for(int i=0;i<num;i++){
            Score scores = new Score();
            //循环获得提取数据要用到的KEY
            scoreName = "SCORE"+Integer.toString(i+1);
            scoreImage = "IMAGE"+Integer.toString(i+1);
            userNameID = "USER"+Integer.toString(i+1);
            scores.setScoreImage(score.getInt(scoreImage,0));
            scores.setScore(score.getString(scoreName,"0"));
            scores.setUserName(score.getString(userNameID,"0"));
            scores.setScoreId(i);
            scoreList.add(scores);
        }
    }

    //设置ListView
    private void initScoreView(){
        ScoreAdepter adepter = new ScoreAdepter(getActivity(),R.layout.score_item,scoreList);
        ListView listView=(ListView) view.findViewById(R.id.listview);
        listView.setAdapter(adepter);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //返回主界面
        Button button1;
        button1 = getView().findViewById(R.id.button7);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_scoreFragment_to_mainFragment);
            }
        });
        //清空得分记录
        Button button2;
        button2 = getView().findViewById(R.id.button10);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences score =getActivity().getSharedPreferences("GameScore",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = score.edit();
                editor.clear();
                editor.commit();
                editor.putInt("SCORE_NUM",0);
                editor.putInt("HEAD_ID",SignFragment.headId);//清空后把当前头像id存入
                editor.apply();
                //通过自身跳转刷新界面
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_scoreFragment_self);
            }
        });
    }
}
