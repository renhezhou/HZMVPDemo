package rxh.hb.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.xutils.http.RequestParams;

import rxh.hb.base.BaseActivity;
import rxh.hb.presenter.RegisterPresenter;
import rxh.hb.utils.CreatUrl;
import rxh.hb.view.RegisterView;

/**
 * Created by Administrator on 2016/4/10.
 */
public class RegisterActivity extends BaseActivity implements RegisterView {

    private EditText user_name, password;
    private TextView title;
    private Button register;
    RegisterPresenter registerPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        registerPresenter = new RegisterPresenter(this);
        initview();
    }

    public void initview() {
        title = (TextView) findViewById(R.id.title);
        title.setText("注册");
        user_name = (EditText) findViewById(R.id.user_name);
        password = (EditText) findViewById(R.id.password);
        register = (Button) findViewById(R.id.register);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                if (user_name.getText().toString().length() < 1) {
                    Toast.makeText(getApplicationContext(), "请输入用户名", Toast.LENGTH_LONG).show();
                    return;
                }
                if (password.getText().toString().length() < 1) {
                    Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_LONG).show();
                    return;
                }
                RequestParams params = new RequestParams(new CreatUrl().creaturl("register"));
                params.addBodyParameter("user_name", user_name.getText().toString());
                params.addBodyParameter("password", password.getText().toString());
                registerPresenter.register(params);
                break;
            default:
                break;

        }
    }

    @Override
    public void show() {
        loading("注册中...", "true");
    }

    @Override
    public void hide() {
        dismiss();
    }

    @Override
    public void onSuccess(String result) {
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(Throwable ex) {
        Toast.makeText(getApplicationContext(), ex.getMessage().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void gologin() {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }
}
