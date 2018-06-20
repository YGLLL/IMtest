package com.example.minkin.imdemo.adapter.holder;

import android.os.Handler;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.minkin.imdemo.R;
import com.example.minkin.imdemo.adapter.ChatAdapter;
import com.example.minkin.imdemo.enity.MessageInfo;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConversationTimeViewHolder extends BaseViewHolder<MessageInfo> {

    @BindView(R.id.tv_time)
    TextView mTvTime;

    public ConversationTimeViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_converstion_time);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void setData(MessageInfo data) {
        mTvTime.setText(data.getTime() != null ? data.getTime() : "");
    }
}
