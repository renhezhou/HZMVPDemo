package rxh.hb.base;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;

import rxh.hb.activity.R;

/**
 * Created by Administrator on 2016/4/10.
 */
public class BaseActivity extends FragmentActivity implements View.OnClickListener{

    LoadingFragment loadingFragment;
    TextView no_net_working, no_response;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // 加载view,flag==0表示没网，flag==404表示请求失败
    public void initactivity(int flag) {
        if (flag == 0) {
            setContentView(R.layout.activity_base_no_net_working);
            no_net_working = (TextView) findViewById(R.id.no_net_working);
            no_net_working.setOnClickListener(this);
        } else if (flag == 404) {
            setContentView(R.layout.activity_base_no_response);
            no_response = (TextView) findViewById(R.id.no_response);
            no_response.setOnClickListener(this);
        }

    }



    // 显示进度条
    public void loading(String title, String flag) {
        if (loadingFragment == null) {
            loadingFragment = new LoadingFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", title);
            bundle.putString("flag", flag);
            loadingFragment.setArguments(bundle);
        }
        loadingFragment.show(getSupportFragmentManager(), "loadingFragment");
    }

    // 隐藏进度条
    public void dismiss() {
        loadingFragment.dismiss();
    }


    // 判断网络是否可用
    public Boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onClick(View v) {

    }
}
