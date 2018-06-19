package com.example.minkin.imdemo.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.minkin.imdemo.R;
import com.example.minkin.imdemo.enity.ConversationInfo;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *创建人:yanggl
 *创建时间:2018-6-19  18:27
 *类描述:
 *备注:
 */
public class ConversationAdapter extends RecyclerArrayAdapter<ConversationInfo> {

    private OnItemClickListener onItemClickListener;

    public ConversationAdapter(Context context, List<ConversationInfo> objects) {
        super(context, objects);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder=new ViewHolder(parent);
        return viewHolder;
    }

    class ViewHolder extends BaseViewHolder<ConversationInfo> {

        @BindView(R.id.iv_head)
        ImageView mIvHead;
        @BindView(R.id.tv_nickname)
        TextView mTvNickname;
        @BindView(R.id.tv_time)
        TextView mTvTime;
        @BindView(R.id.tv_last_message)
        TextView mTvLastMessage;
        @BindView(R.id.tv_unread_message)
        TextView mTvUnreadMessage;
        @BindView(R.id.ll)
        LinearLayout mLL;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_rv_conversation);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void setData(ConversationInfo data) {
//            mIvHead
            mTvNickname.setText(data.getNickName());
            mTvTime.setText(data.getLastTime());
            mTvLastMessage.setText(data.getLastMessage());
            mTvUnreadMessage.setText(data.getUnReadMessage());
            mLL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(getDataPosition());
                }
            });
        }
    }

    public void addItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(int position);
    }
}
