package com.example.llkgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NavigationRes;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class SignFragment extends Fragment {
    private static final int PHOTO_REQUEST_GALLERY = 2;
    public SignFragment() {
        // Required empty public constructor
    }

    public static int headId=R.drawable.panda;
    public static String userName="无";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign, container, false);
    }
    EditText editText;
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //登录，进入菜单页

        Button button1;
        button1 = getView().findViewById(R.id.button6);
        editText = getView().findViewById(R.id.editText);
        editText.setText("请输入用户名");
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName=editText.getText().toString();//获得当前用户名
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_signFragment_to_mainFragment);
            }
        });

        //进入头像选择页面
        ImageButton button2;
        button2 = getView().findViewById(R.id.imageButton);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_signFragment_to_headFragment);
            }
        });

        //头像设置为上次登录使用的头像
        ImageButton imageButton;
        imageButton = getView().findViewById(R.id.imageButton);
        SharedPreferences score =getActivity().getSharedPreferences("GameScore", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = score.edit();
        imageButton.setImageResource(score.getInt("HEAD_ID",0));
        headId = score.getInt("HEAD_ID",0);

        //结束所有活动
        Button button3;
        button3 = getView().findViewById(R.id.button11);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCollector.finishAll();
            }
        });
    }

    
}
