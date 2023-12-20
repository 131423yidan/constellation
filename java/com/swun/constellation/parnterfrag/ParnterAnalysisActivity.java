package com.swun.constellation.parnterfrag;

import androidx.appcompat.app.AppCompatActivity;
import de.hdodenhof.circleimageview.CircleImageView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.swun.constellation.R;
import com.swun.constellation.utils.AssetsUtils;
import com.swun.constellation.utils.LoadDataAsyncTask;
import com.swun.constellation.utils.URLContent;

import java.util.Map;

public class ParnterAnalysisActivity extends AppCompatActivity implements LoadDataAsyncTask.OnGetNetDataListener, View.OnClickListener {
    TextView manTv,womanTv,pdTv,vsTv,pfTv,bzTv,jxTv,zyTv,titleTv;
    CircleImageView manIv,womanIv;
    ImageView backIv;
    private String man_name;
    private String man_logoname;
    private String woman_name;
    private String woman_logoname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parnter_analysis);
        initView();
//        接收上一个页面传递的数值
        getLastData();
//        获取网络路径地址
        String parnterURL = URLContent.getParnterURL(man_name, woman_name);
//        加载网络数据
//        1.创造自定义的异步任务的对象
        LoadDataAsyncTask task = new LoadDataAsyncTask(this, this, true);
//        2.执行异步任务
        task.execute(parnterURL);
    }
    @Override
    public void onSuccess(String json) {
//        解析json数据
        if (!TextUtils.isEmpty(json)) {
            ParnterAnalysisBean analysisBean = new Gson().fromJson(json, ParnterAnalysisBean.class);
            ParnterAnalysisBean.ResultBean resultBean = analysisBean.getResult();

            pfTv.setText("配对评分: "+resultBean.getZhishu()+"  "+resultBean.getJieguo());
            bzTv.setText("星座比重: "+resultBean.getBizhong());
            jxTv.setText("解析:\n\n"+resultBean.getLianai());
            zyTv.setText("注意事项:\n\n"+resultBean.getZhuyi());
        }

    }
    private void getLastData() {
        Intent intent = getIntent();
        man_name = intent.getStringExtra("man_name");
        man_logoname = intent.getStringExtra("man_logoname");
        woman_name = intent.getStringExtra("woman_name");
        woman_logoname = intent.getStringExtra("woman_logoname");
//        设置能够显示的控件的信息
        Map<String, Bitmap> contentlogoImgMap =
                AssetsUtils.getContentlogoImgMap();
        Bitmap manBitmap = contentlogoImgMap.get(man_logoname);
        manIv.setImageBitmap(manBitmap);
        Bitmap womanBitmap = contentlogoImgMap.get(woman_logoname);
        womanIv.setImageBitmap(womanBitmap);

        manTv.setText(man_name);
        womanTv.setText(woman_name);
        pdTv.setText("星座配对-"+man_name+"和"+woman_name+"配对");
        vsTv.setText(man_name+" vs "+woman_name);
    }

    private void initView() {
        manIv = findViewById(R.id.parnteranalysis_iv_man);
        womanIv = findViewById(R.id.parnteranalysis_iv_woman);
        backIv = findViewById(R.id.title_iv_back);
        manTv = findViewById(R.id.parnteranalysis_tv_man);
        womanTv = findViewById(R.id.parnteranalysis_tv_woman);
        pdTv = findViewById(R.id.parnteranalysis_tv_pd);
        vsTv = findViewById(R.id.parnteranalysis_tv_vs);
        pfTv = findViewById(R.id.parnteranalysis_tv_pf);
        bzTv = findViewById(R.id.parnteranalysis_tv_bz);
        jxTv= findViewById(R.id.parnteranalysis_tv_jx);
        zyTv = findViewById(R.id.parnteranalysis_tv_zy);
        titleTv = findViewById(R.id.title_tv);
        backIv.setOnClickListener(this);
        titleTv.setText("配对详情");
    }


    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.title_iv_back:
//                finish();
//                break;
//        }
//    }
    public void onClick(View v) {
        if (v.getId() == R.id.title_iv_back) {
            finish();
        }
    }
}
