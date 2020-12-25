package com.example.pkutoolman;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ChatAdapter extends BaseAdapter {
    private Context context;
    private List<ChatData> lists;

    public ChatAdapter(Context context, List<ChatData> lists) {
        super();
        this.context = context;
        this.lists = lists;
    }

    /**
     * 是否是自己发送的消息
     *
     * @author cyf
     *
     */
    public static interface IMsgViewType {
        int IMVT_COM_MSG = 0;// 收到对方的消息
        int IMVT_TO_MSG = 1;// 自己发送出去的消息
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return lists.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return lists.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    /**
     * 得到Item的类型，是对方发过来的消息，还是自己发送出去的
     */
    public int getItemViewType(int position) {
        ChatData entity = lists.get(position);

        if (entity.isMeSend()) {// 收到的消息
            return IMsgViewType.IMVT_COM_MSG;
        } else {// 自己发送的消息
            return IMsgViewType.IMVT_TO_MSG;
        }
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        // TODO Auto-generated method stub
        HolderView holderView = null;
        ChatData entity = lists.get(arg0);
        boolean isMeSend = entity.isMeSend();
        if (holderView == null) {
            holderView = new HolderView();
            if (isMeSend) {
                arg1 = View.inflate(context, R.layout.chat_sender, null);
                holderView.tv_chat_me_message = (TextView) arg1.findViewById(R.id.tv_chat_me_message);
                holderView.tv_chat_me_message.setText(entity.getChatMessage());
                holderView.my_message_time = (TextView) arg1.findViewById(R.id.my_message_time);
                holderView.my_message_time.setText(entity.getTime());
            } else {
                arg1 = View.inflate(context, R.layout.chat_other, null);
                holderView.tvname = (TextView) arg1.findViewById(R.id.tvname);
                holderView.tvname.setText(entity.getChatMessage());
                holderView.other_message_time = (TextView) arg1.findViewById(R.id.other_message_time);
                holderView.other_message_time.setText(entity.getTime());
            }
            arg1.setTag(holderView);
        } else {
            holderView = (HolderView) arg1.getTag();
        }

        return arg1;
    }

    class HolderView {
        TextView tv_chat_me_message;
        TextView my_message_time;
        TextView tvname;
        TextView other_message_time;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}