package com.swun.constellation.mefrag;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import de.hdodenhof.circleimageview.CircleImageView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.swun.constellation.R;
import com.swun.constellation.bean.StarBean;
import com.swun.constellation.luckfrag.LuckBaseAdapter;
import com.swun.constellation.utils.AssetsUtils;

import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends Fragment implements View.OnClickListener {
    CircleImageView iconIv;
    TextView nameTv;
    private Map<String, Bitmap> imgMap;
    private List<StarBean.StarinfoBean> mDatas;
    private SharedPreferences star_pref;
    //  保存选择的星座位置
    int selectPos = 0;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        获取activity传递的数据
        Bundle bundle = getArguments();
        StarBean infoBean = (StarBean) bundle.getSerializable("info");
        mDatas = infoBean.getStarinfo();
        star_pref = getContext().getSharedPreferences("star_pref", Context.MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_me, container, false);
        iconIv = view.findViewById(R.id.mefrag_iv);
        nameTv = view.findViewById(R.id.mefrag_tv_name);
        iconIv.setOnClickListener(this);
//        进行初始化设置
        imgMap = AssetsUtils.getContentlogoImgMap();
//        读取共享参数当中保存的星座名称和logo名称
        String name = star_pref.getString("name", "白羊座");
        String logoname = star_pref.getString("logoname", "baiyang");
        Bitmap bitmap = imgMap.get(logoname);
        iconIv.setImageBitmap(bitmap);
        nameTv.setText(name);
        return view;
    }

    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.mefrag_iv:
//                showDialog();
//                break;
//        }
//    }

    public void onClick(View v) {
        if (v.getId() == R.id.mefrag_iv) {
            showDialog();
        }
    }
    private void showDialog() {
        final Dialog dialog = new Dialog(getContext());
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.me_dialog,null);
        dialog.setContentView(dialogView);
        dialog.setTitle("请选择您的星座:");
        GridView dialogGv = dialogView.findViewById(R.id.mefrag_dialog_gv);
//        设置适配器
        LuckBaseAdapter adapter = new LuckBaseAdapter(getContext(),mDatas);
        dialogGv.setAdapter(adapter);
//        设置能否被取消
        dialog.setCancelable(true);
//        设置点击弹出框的外部，可以取消弹出框
        dialog.setCanceledOnTouchOutside(true);

//        设置gridview每一项的点击事件
        dialogGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                StarBean.StarinfoBean bean = mDatas.get(position);
                String name = bean.getName();
                String logoname = bean.getLogoname();
                nameTv.setText(name);
                Bitmap bitmap = imgMap.get(logoname);
                iconIv.setImageBitmap(bitmap);
                selectPos = position;   //保存选择的位置
                dialog.cancel();
            }
        });
        dialog.show();
    }

    @Override
    public void onPause() {
        super.onPause();
        StarBean.StarinfoBean bean = mDatas.get(selectPos);
        String name = bean.getName();
        String logoname = bean.getLogoname();
        SharedPreferences.Editor editor = star_pref.edit();   // 获取向共享参数中写入数据的对象
        editor.putString("name",name);
        editor.putString("logoname",logoname);
        editor.commit();
    }
}
