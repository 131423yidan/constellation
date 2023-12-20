package com.swun.constellation.starfrag;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.swun.constellation.R;

import java.util.List;

public class AnalysisBaseAdapter extends BaseAdapter {
    Context context;
    List<StarAnalysisBean> mDatas;

    public AnalysisBaseAdapter(Context context, List<StarAnalysisBean> mDatas) {
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_star_analysis,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        StarAnalysisBean bean = mDatas.get(position);
        holder.titleTv.setText(bean.getTitle());
        holder.contentTv.setText(bean.getContent());
        holder.contentTv.setBackgroundResource(bean.getColor());
        return convertView;
    }

    class ViewHolder{
        TextView titleTv,contentTv;
        public ViewHolder(View view){
            titleTv = view.findViewById(R.id.itemstar_tv_title);
            contentTv = view.findViewById(R.id.itemstar_tv_content);
        }
    }
}
