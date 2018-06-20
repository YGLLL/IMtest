package com.example.minkin.imdemo.adapter;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.minkin.imdemo.adapter.holder.ChatAcceptViewHolder;
import com.example.minkin.imdemo.adapter.holder.ChatSendViewHolder;
import com.example.minkin.imdemo.adapter.holder.ConversationTimeViewHolder;
import com.example.minkin.imdemo.enity.MessageInfo;
import com.example.minkin.imdemo.util.Constants;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/*
 *创建人:yanggl
 *创建时间:2018-6-19  20:44
 *类描述:
 *备注:
 */
public class ChatAdapter extends RecyclerArrayAdapter<MessageInfo> {

    private onItemClickListener onItemClickListener;
    public Handler handler;

    public ChatAdapter(Context context, List<MessageInfo> mData) {
        super(context,mData);
        handler = new Handler();
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = null;
        switch (viewType) {
            case Constants.CHAT_ITEM_TYPE_LEFT:
                viewHolder = new ChatAcceptViewHolder(parent, onItemClickListener, handler);
                break;
            case Constants.CHAT_ITEM_TYPE_RIGHT:
                viewHolder = new ChatSendViewHolder(parent, onItemClickListener, handler);
                break;
            case Constants.NEW_CONVERSATION_TIME:
                viewHolder=new ConversationTimeViewHolder(parent);
                break;
        }
        return viewHolder;
    }

    @Override
    public int getViewType(int position) {
        return getAllData().get(position).getType();
    }

    public void addItemClickListener(onItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener {
        void onHeaderClick(int position);

        void onImageClick(View view, int position);

        void onVoiceClick(ImageView imageView, int position);
    }
}
