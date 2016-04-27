package rxh.hb.view;

/**
 * Created by Administrator on 2016/4/11.
 */
public interface RegisterView {

    void show();

    void hide();

    void onSuccess(String result);

    void onError(Throwable ex);

    void gologin();

}
