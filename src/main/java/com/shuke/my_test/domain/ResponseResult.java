package com.shuke.my_test.domain;



import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 舒克、舒克
 * @date 2025/4/30 15:31
 * @description 响应结果类
 */
@Data
@NoArgsConstructor
public class ResponseResult<T> {
    private int code;
    private T data;
    private String message;

    // 带参数的构造方法（Lombok @AllArgsConstructor替代方案）
    private ResponseResult(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    /* 成功响应 */
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(200, data, "操作成功");
    }

    public static <T> ResponseResult<T> success(T data, String message) {
        return new ResponseResult<>(200, data, message);
    }

    /* 错误响应 */
    public static <T> ResponseResult<T> error(int code, String message) {
        return new ResponseResult<>(code, null, message);
    }

    public static <T> ResponseResult<T> error(int code, T data, String message) {
        return new ResponseResult<>(code, data, message);
    }

    /* 常用快捷方法 */
    public static ResponseResult<Void> unauthorized() {
        return new ResponseResult<>(401, null, "未授权访问");
    }

    public static ResponseResult<Void> forbidden() {
        return new ResponseResult<>(403, null, "禁止访问");
    }
}
