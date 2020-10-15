package com.example.llkgame;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class InstructionFragment extends Fragment {

    public InstructionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_instruction, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button button;
        button = getView().findViewById(R.id.button8);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_instructionFragment_to_mainFragment);
            }
        });

        TextView textView;
        textView = getView().findViewById(R.id.textView2);
        textView.setText("本游戏的玩法是：将视图中的两个相同图片点击便可将其消除，所有图片都被消除后游戏结束。在点击第一张图片开始计时，游戏结束可以选择点击记录分数按钮将分数记录，也可以选择直接退出不记录分数。");
    }
}
