package com.xiaoxin.common;

import com.baomidou.mybatisplus.extension.api.R;
import lombok.Data;

/**
 * @author:XIAOXIN
 * @date:2023/07/26
 * 封装统一的返回结果
 **/
@Data
public class Result<T> {
    private String code;
    private T data;
    private String msg;

    public static <T> Result <T> success(T data) {
        Result<T> res = new Result<>();
        res.setData(data);
        res.setCode("200");
        return res;
    }
    public static <T> Result <T> success() {
        Result<T> res = new Result<>();
        res.setCode("200");
        res.setMsg("登录成功");
        return res;
    }

    private static <T> Result<T> error(String code, String msg) {
        Result<T> res = new Result<>();
        res.setCode(code);
        res.setMsg(msg);
        return res;
    }
    public static <T> Result<T> error(String msg) {
        Result<T> res = new Result<>();
        res.setCode("-1");
        res.setMsg(msg);
        return res;
    }


}
