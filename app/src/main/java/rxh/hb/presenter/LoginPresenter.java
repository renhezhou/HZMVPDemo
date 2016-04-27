package rxh.hb.presenter;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import rxh.hb.model.GetInfo;
import rxh.hb.model.Is_Networking;
import rxh.hb.model.Response;
import rxh.hb.utils.JsonUtils;
import rxh.hb.view.LoginView;

/**
 * Created by Administrator on 2016/4/11.
 */
public class LoginPresenter {

    private GetInfo getInfo;
    private LoginView loginView;

    public LoginPresenter(LoginView loginView) {
        getInfo = new Is_Networking();
        this.loginView = loginView;
    }

    public void login(RequestParams params) {
        loginView.show();
        getInfo.getinfo(params, new Response() {
            @Override
            public void onSuccess(String result) {
                String response = JsonUtils.login(result);
                loginView.hide();
                if (response.equals("1")) {
                    response = "该用户还未注册";
                    loginView.onSuccess(response);
                    return;
                } else if (response.equals("200")) {
                    loginView.gomain();
                    return;
                } else if (response.equals("4")) {
                    response = "用户名和密码不匹配";
                    loginView.onSuccess(response);
                    return;
                }
            }

            @Override
            public void onError(Throwable ex) {
                loginView.hide();
                loginView.onError(ex);
            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {
                loginView.hide();
            }

            @Override
            public void onFinished() {
                loginView.hide();
            }
        });
    }

}
