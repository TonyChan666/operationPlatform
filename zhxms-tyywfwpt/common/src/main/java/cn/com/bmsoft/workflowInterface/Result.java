package cn.com.bmsoft.workflowInterface;

import org.apache.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class Result extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public Result() {

    }
    public static Result error() {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, "未知异常，请联系管理员");
    }

    public static Result error(String msg) {
        return error(HttpStatus.SC_INTERNAL_SERVER_ERROR, msg);
    }
    public static Result error(int code, String msg) {
        Result r = new Result();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static Result ok(Map<String, Object> map) {
        Result r = new Result();
        r.putAll(map);
        return r;
    }
}
