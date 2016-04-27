package rxh.hb.presenter;



import org.xutils.common.Callback;
import org.xutils.http.RequestParams;

import rxh.hb.model.GetInfo;
import rxh.hb.model.Is_Networking;
import rxh.hb.model.Response;
import rxh.hb.utils.JsonUtils;
import rxh.hb.view.RegisterView;

/**
 * Created by Administrator on 2016/4/11.
 */
public class RegisterPresenter {


    private GetInfo getInfo;
    private RegisterView registerView;


    public RegisterPresenter(RegisterView registerView){
        getInfo=new Is_Networking();
        this.registerView=registerView;
    }

    public void register(RequestParams params){
        registerView.show();

        getInfo.getinfo(params, new Response() {
            @Override
            public void onSuccess(String result) {
                String response= JsonUtils.register(result);
                registerView.hide();
                if(response.equals("3")){
                    response="用户名已经被使用";
                    registerView.onSuccess(response);
                    return;
                }else if(response.equals("200")){
                    registerView.gologin();
                    return;
                }else if(response.equals("2")){
                    response="数据库操作失败";
                    registerView.onSuccess(response);
                    return;
                }
            }

            @Override
            public void onError(Throwable ex) {
                registerView.hide();
                registerView.onError(ex);
            }

            @Override
            public void onCancelled(Callback.CancelledException cex) {
                registerView.hide();
            }

            @Override
            public void onFinished() {
                registerView.hide();
            }
        });
    }



}
