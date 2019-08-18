package com.hjh.myspringboot.myspringboot.entity;

import com.fasterxml.jackson.annotation.JsonView;
import com.hjh.myspringboot.myspringboot.validate.MyConstraint;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @Description:
 * @Author: HJH
 * @Date: 2019-08-16 21:31
 */
@Data
public class User {
    @JsonView(View.Summary.class)
    private long id;

    @JsonView(View.Summary.class)
    @NotBlank(message = "用户名不能为空..")
    @MyConstraint(message = "自定义验证test")
    private String username;

    @JsonView(View.SummaryWithDetail.class)
    private String password;

    @JsonView(View.SummaryWithDetail.class)
    @Past(message = "不能为未来时间...")
    private Date createDate;
}
