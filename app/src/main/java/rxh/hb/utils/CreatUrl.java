package rxh.hb.utils;

/**
 * Created by Administrator on 2016/4/12.
 */
public class CreatUrl {

    public static String base_url = "http://192.168.3.106:8080/MyInterfaceDemo/";

    public String creaturl(String model) {
        String url = null;
        url = base_url + model;
        return url;
    }

}
