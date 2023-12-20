package com.swun.constellation.luckfrag;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.swun.constellation.R;

import java.util.List;

public class LuckAnalysisAdapter extends BaseAdapter {
    Context context;
    List<LuckItemBean> mDatas;

    public LuckAnalysisAdapter(Context context, List<LuckItemBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_luckanalysis_lv,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        LuckItemBean itemBean = mDatas.get(position);
        holder.titleTv.setText(itemBean.getTitle());
        holder.contentTv.setText(itemBean.getContent());
//        改变TextView的背景颜色
        GradientDrawable drawable = (GradientDrawable) holder.titleTv.getBackground();
        drawable.setColor(itemBean.getColorId());
        return convertView;
    }

    class ViewHolder{
        TextView titleTv,contentTv;
        public ViewHolder(View view){
            titleTv = view.findViewById(R.id.item_luckanalysis_tv_title);
            contentTv = view.findViewById(R.id.item_luckanalysis_tv_content);
        }
    }
}
