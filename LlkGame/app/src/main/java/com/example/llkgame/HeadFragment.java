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
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class HeadFragment extends Fragment {

    private View view;
    public static RecyclerView recyclerView;
    private ArrayList<Head> headsList = new ArrayList<Head>();
    private HeadAdapter headAdapter;
    private int[] headId = {R.drawable.blue,R.drawable.yellow,R.drawable.orange,R.drawable.purple,
            R.drawable.green,R.drawable.panda,R.drawable.tooth,R.drawable.fox,R.drawable.hen};

    public HeadFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_head, container, false);
        initHeadView();
        initHead();
        return view;
    }

    private void initHead(){
        for (int i=0;i<9;i++){
            Head head = new Head();
            head.setHeadImageId(headId[i]);
            headsList.add(head);
        }
    }

    //设置recyclerView的布局
    private void initHeadView(){
        recyclerView = (RecyclerView)view.findViewById(R.id.head_recyclerview);
        headAdapter = new HeadAdapter(getActivity(),headsList);
        recyclerView.setAdapter(headAdapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(3,
                StaggeredGridLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //登录，进入菜单页
        Button button1;
        button1 = getView().findViewById(R.id.button13);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController controller = Navigation.findNavController(v);
                controller.navigate(R.id.action_headFragment_to_signFragment);
            }
        });

        //确定图片，将图片id保存到本地文件中
        Button button2;
        button2 = getView().findViewById((R.id.button14));
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences score =getActivity().getSharedPreferences("GameScore",
                        Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = score.edit();
                editor.putInt("HEAD_ID",HeadAdapter.headId);
                editor.apply();
                SignFragment.headId=score.getInt("HEAD_ID",0);
            }
        });
    }
}
