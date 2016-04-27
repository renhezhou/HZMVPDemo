package rxh.hb.utils;


/**
 * Created by Administrator on 2016/4/11.
 */
public class JsonUtils {

    //解析注册时服务器返回的数据
    public static String register(String data){
        String result=null;
        com.alibaba.fastjson.JSONObject object=com.alibaba.fastjson.JSONObject.parseObject(data);
        result=object.getString("code");
        return result;
    }

    //解析登录时服务器返回的数据
    public static String login(String data){
        String result=null;
        com.alibaba.fastjson.JSONObject object=com.alibaba.fastjson.JSONObject.parseObject(data);
        result=object.getString("code");
        return result;
    }

}
