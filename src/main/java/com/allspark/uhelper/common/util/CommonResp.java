package com.allspark.uhelper.common.util;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * @ClassName CommonResp
 * @Description TODO
 * @Author 86159
 * @Date 2022/10/12 19:16
 * @Version 1.0
 **/
@Schema(description = "统一返回类")
public class CommonResp<T> {

    /**
     * 业务上的成功或失败
     */
    @Schema(description = "业务上的成功或失败")
    private boolean success = true;

    /**
     * 返回信息
     */
    @Schema(description = "返回信息")
    private String message;

    /**
     * 返回泛型数据，自定义类型
     */
    @Schema(description = "返回泛型数据，自定义类型,真实数据")
    private T content;

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResponseDto{");
        sb.append("success=").append(success);
        sb.append(", message='").append(message).append('\'');
        sb.append(", content=").append(content);
        sb.append('}');
        return sb.toString();
    }
}


