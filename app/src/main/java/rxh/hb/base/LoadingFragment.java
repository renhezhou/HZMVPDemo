package rxh.hb.base;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import rxh.hb.activity.R;

/**
 * Created by Administrator on 2016/4/10.
 */
public class LoadingFragment extends DialogFragment {

    View view;
    TextView loading;
    String title, flag;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        Bundle bundle = getArguments();
        title = bundle.getString("title");
        flag = bundle.getString("flag");
        if (flag.equals("true")) {
            getDialog().setCanceledOnTouchOutside(false);
        } else if (flag.equals("false")) {
            getDialog().setCanceledOnTouchOutside(true);
        }
        view = inflater.inflate(R.layout.fragment_loading, container);
        loading = (TextView) view.findViewById(R.id.loading);
        loading.setText(title);
        return view;
    }

}
