package org.github.hoorf.arch.framework.common;

import lombok.Data;
import lombok.experimental.Accessors;


@Data
@Accessors(chain = true)
public class RestResult<T> {

    public static Integer SUCCESS = 200200;
    public static Integer ERROR = 500500;

    private Integer code;

    private String msg;

    private T data;


    public RestResult() {
    }

    public RestResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RestResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static RestResult success() {
        return new RestResult(SUCCESS, "success");
    }

    public static RestResult success(Object data) {
        return new RestResult(SUCCESS, "success", data);
    }

    public static RestResult fail() {
        return new RestResult(ERROR, "fail");
    }
}
