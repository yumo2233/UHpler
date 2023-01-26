package com.allspark.uhelper.common.form;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @ClassName UserUpdatePasswordForm
 * @Description TODO
 * @Author 86159
 * @Date 2023/1/26 19:57
 * @Version 1.0
 **/
@Schema(description = "修改密码表单")
@Data
public class UserUpdatePasswordForm {


    @NotBlank(message = "用户工号不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$", message = "用户工号不正确")
    @Schema(description = "用户工号")
    private String number;

    @NotBlank(message = "password不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$", message = "password内容不正确")
    @Schema(description = "用户密码")
    private String password;
}
