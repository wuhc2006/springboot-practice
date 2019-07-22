package com.whc.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @ClassName User
 * @Description TODO 用户
 * @Author Administrator
 * @Date 2018/12/22 22:29
 * @Version 1.0
 */
@ApiModel(value = "User", description = "用户")
public class User {

    @ApiModelProperty("id")
    @NotNull
    private Long id;

    @ApiModelProperty("用户名")
    @NotEmpty
    private String username;

    @ApiModelProperty("密码")
    @NotEmpty
    private String password;

    @JsonFormat()
    @ApiModelProperty("创建时间")
    private Date addTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    public User() {
    }

    public User(String username, String password, Date addTime) {
        this.username = username;
        this.password = password;
        this.addTime = addTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
