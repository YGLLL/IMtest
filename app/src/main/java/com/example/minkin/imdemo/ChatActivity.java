package com.example.minkin.imdemo;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.minkin.imdemo.adapter.ChatAdapter;
import com.example.minkin.imdemo.adapter.ConversationAdapter;
import com.example.minkin.imdemo.enity.MessageInfo;
import com.example.minkin.imdemo.util.Constants;
import com.jude.easyrecyclerview.EasyRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.erv)
    EasyRecyclerView mErv;
    @BindView(R.id.et)
    EditText mEt;
    @BindView(R.id.btn)
    Button mBtn;

    private ChatAdapter mAdapter;
    private List<MessageInfo> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        mData=new ArrayList<>();
        initData();
        mAdapter=new ChatAdapter(this,mData);
        mAdapter.addItemClickListener(itemClickListener);
        mErv.setLayoutManager(new LinearLayoutManager(this));
        mErv.setAdapter(mAdapter);
    }

    private void initData() {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setContent("你好，欢迎使用Rance的聊天界面框架");
        messageInfo.setType(Constants.CHAT_ITEM_TYPE_LEFT);
        messageInfo.setHeader("http://tupian.enterdesk.com/2014/mxy/11/2/1/12.jpg");
        mData.add(messageInfo);

        MessageInfo messageInfo1 = new MessageInfo();
        messageInfo1.setFilepath("http://www.trueme.net/bb_midi/welcome.wav");
        messageInfo1.setVoiceTime(3000);
        messageInfo1.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
        messageInfo1.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
        messageInfo1.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
        mData.add(messageInfo1);

        MessageInfo messageInfo2 = new MessageInfo();
        messageInfo2.setImageUrl("http://img4.imgtn.bdimg.com/it/u=1800788429,176707229&fm=21&gp=0.jpg");
        messageInfo2.setType(Constants.CHAT_ITEM_TYPE_LEFT);
        messageInfo2.setHeader("http://tupian.enterdesk.com/2014/mxy/11/2/1/12.jpg");
        mData.add(messageInfo2);

        MessageInfo messageInfo3 = new MessageInfo();
        messageInfo3.setContent("[微笑][色][色][色]");
        messageInfo3.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
        messageInfo3.setSendState(Constants.CHAT_ITEM_SEND_ERROR);
        messageInfo3.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
        mData.add(messageInfo3);
    }

    @OnClick(R.id.btn)
    public void onViewClicked() {
    }

    ChatAdapter.onItemClickListener itemClickListener=new ChatAdapter.onItemClickListener() {
        @Override
        public void onHeaderClick(int position) {

        }

        @Override
        public void onImageClick(View view, int position) {

        }

        @Override
        public void onVoiceClick(ImageView imageView, int position) {

        }
    };
}
