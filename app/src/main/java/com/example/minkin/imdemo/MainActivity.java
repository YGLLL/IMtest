package com.example.minkin.imdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;

import com.example.minkin.imdemo.adapter.ConversationAdapter;
import com.example.minkin.imdemo.enity.ConversationInfo;
import com.jude.easyrecyclerview.EasyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.erv)
    EasyRecyclerView mErv;

    private ConversationAdapter mAdapter;
    private List<ConversationInfo> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mData=new ArrayList<>();
        initData();
        mAdapter=new ConversationAdapter(this,mData);
        mAdapter.addItemClickListener(onItemClickListener);
        mErv.setLayoutManager(new LinearLayoutManager(this));
        mErv.setAdapter(mAdapter);
    }

    private void initData() {
        for (int i=0;i<100;i++){
            ConversationInfo info=new ConversationInfo();
            info.setNickName(i+"");
            i++;
            info.setLastTime(i+"");
            i++;
            info.setLastMessage(i+"");
            i++;
            info.setUnReadMessage(i+"");
            mData.add(info);
        }
    }

    ConversationAdapter.OnItemClickListener onItemClickListener=new ConversationAdapter.OnItemClickListener() {
        @Override
        public void onClick(int position) {
            startActivity(new Intent(MainActivity.this,ChatActivity.class));
        }
    };
}
