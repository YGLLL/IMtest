package com.example.minkin.imdemo;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.minkin.imdemo.adapter.ChatAdapter;
import com.example.minkin.imdemo.enity.MessageInfo;
import com.example.minkin.imdemo.util.Constants;
import com.example.minkin.imdemo.weiget.StateButton;
import com.example.minkin.imdemo.weiget.refreshlayout.RefreshLayout;
import com.example.minkin.imdemo.weiget.refreshlayout.RefreshLayoutDirection;
import com.jude.easyrecyclerview.EasyRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.erv)
    EasyRecyclerView mErv;
    @BindView(R.id.reply_et)
    EditText mReplyEt;
    @BindView(R.id.iv_photo)
    ImageView mIvPhoto;
    @BindView(R.id.emotion_send)
    StateButton mEmotionSend;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;

    private ChatAdapter mAdapter;
    private List<MessageInfo> mData;

    private int mPageNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        mReplyEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    mIvPhoto.setVisibility(View.GONE);
                    mEmotionSend.setVisibility(View.VISIBLE);
                } else {
                    mIvPhoto.setVisibility(View.VISIBLE);
                    mEmotionSend.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        mEmotionSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIvPhoto.setVisibility(View.VISIBLE);
                mEmotionSend.setVisibility(View.GONE);
                MessageInfo messageInfo = new MessageInfo();
                messageInfo.setContent(mReplyEt.getText().toString());
                mReplyEt.setText("");
                sendMessage(messageInfo);
            }
        });

        //可以下拉
        mRefreshLayout.setDirection(RefreshLayoutDirection.TOP);
        mRefreshLayout.setOnRefreshListener(new RefreshLayout.OnRefreshListener() {
            @Override
            public void onPullDownToRefresh() {
                //下拉加载消息
            }

            @Override
            public void onPullUpToRefresh() {

            }
        });

        mData = new ArrayList<>();
        initData();
        mAdapter = new ChatAdapter(this, mData);
        mAdapter.addItemClickListener(itemClickListener);
        mErv.setLayoutManager(new LinearLayoutManager(this));
        mErv.setAdapter(mAdapter);
    }

    private void initData() {
        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setContent("你好，欢迎使用Rance的聊天界面框架");
        messageInfo.setType(Constants.CHAT_ITEM_TYPE_LEFT);
        messageInfo.setHeader("http://img5.imgtn.bdimg.com/it/u=3272996903,1322252932&fm=27&gp=0.jpg");
        mData.add(messageInfo);

        MessageInfo messageInfo1 = new MessageInfo();
        messageInfo1.setFilepath("http://www.trueme.net/bb_midi/welcome.wav");
        messageInfo1.setVoiceTime(3000);
        messageInfo1.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
        messageInfo1.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
        messageInfo1.setHeader("http://img1.imgtn.bdimg.com/it/u=2990245782,3572280809&fm=27&gp=0.jpg");
        mData.add(messageInfo1);

        MessageInfo messageInfo2 = new MessageInfo();
        messageInfo2.setImageUrl("http://img4.imgtn.bdimg.com/it/u=1800788429,176707229&fm=21&gp=0.jpg");
        messageInfo2.setType(Constants.CHAT_ITEM_TYPE_LEFT);
        messageInfo2.setHeader("http://img5.imgtn.bdimg.com/it/u=3272996903,1322252932&fm=27&gp=0.jpg");
        mData.add(messageInfo2);

        MessageInfo messageInfo3 = new MessageInfo();
        messageInfo3.setContent("[微笑][色][色][色]");
        messageInfo3.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
        messageInfo3.setSendState(Constants.CHAT_ITEM_SEND_ERROR);
        messageInfo3.setHeader("http://img1.imgtn.bdimg.com/it/u=2990245782,3572280809&fm=27&gp=0.jpg");
        mData.add(messageInfo3);
    }

    ChatAdapter.onItemClickListener itemClickListener = new ChatAdapter.onItemClickListener() {
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

    @OnClick({R.id.iv_photo, R.id.emotion_send})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_photo:
                break;
            case R.id.emotion_send:
                break;
        }
    }

    private void sendMessage(final MessageInfo messageInfo) {
        messageInfo.setHeader("http://img.dongqiudi.com/uploads/avatar/2014/10/20/8MCTb0WBFG_thumb_1413805282863.jpg");
        messageInfo.setType(Constants.CHAT_ITEM_TYPE_RIGHT);
        messageInfo.setSendState(Constants.CHAT_ITEM_SENDING);
        mData.add(messageInfo);
        mAdapter.add(messageInfo);
        mErv.scrollToPosition(mAdapter.getCount() - 1);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                messageInfo.setSendState(Constants.CHAT_ITEM_SEND_SUCCESS);
                mAdapter.notifyDataSetChanged();
            }
        }, 2000);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                MessageInfo message = new MessageInfo();
                message.setContent("这是模拟消息回复");
                message.setType(Constants.CHAT_ITEM_TYPE_LEFT);
                message.setHeader("http://tupian.enterdesk.com/2014/mxy/11/2/1/12.jpg");
                mData.add(message);
                mAdapter.add(message);
                mErv.scrollToPosition(mAdapter.getCount() - 1);
            }
        }, 3000);
    }

    private void onLoad(int size) {
        if (mRefreshLayout == null) {
            return;
        }
        if (mRefreshLayout.isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
        if (size < Constants.DEFAULT_PAGE_SIZE) {
            //关闭下拉加载
        }
    }
}
