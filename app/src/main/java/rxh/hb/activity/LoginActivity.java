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
import rxh.hb.presenter.LoginPresenter;
import rxh.hb.utils.CreatUrl;
import rxh.hb.view.LoginView;

/**
 * Created by Administrator on 2016/4/11.
 */
public class LoginActivity extends BaseActivity implements LoginView {

    private EditText user_name, password;
    private Button login;
    private TextView title, register;
    LoginPresenter loginPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPresenter=new LoginPresenter(this);
        setContentView(R.layout.activity_login);
        initview();
    }

    public void initview() {
        title = (TextView) findViewById(R.id.title);
        title.setText("登录");
        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);
        user_name = (EditText) findViewById(R.id.user_name);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.register:
                startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
                break;
            case R.id.login:
                if (user_name.getText().toString().length() < 1) {
                    Toast.makeText(getApplicationContext(), "请输入用户名", Toast.LENGTH_LONG).show();
                    return;
                }
                if (password.getText().toString().length() < 1) {
                    Toast.makeText(getApplicationContext(), "请输入密码", Toast.LENGTH_LONG).show();
                    return;
                }
                RequestParams params = new RequestParams(new CreatUrl().creaturl("login"));
                params.addBodyParameter("user_name",user_name.getText().toString());
                params.addBodyParameter("password",password.getText().toString());
                loginPresenter.login(params);
                break;
            default:
                break;
        }
    }


    @Override
    public void show() {
        loading("登录中...", "true");
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
    public void gomain() {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}
