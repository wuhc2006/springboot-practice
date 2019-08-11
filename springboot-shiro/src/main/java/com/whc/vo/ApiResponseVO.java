package com.whc.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.whc.domain.exception.ExceptionCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by dengyaming on 08/06/2017.
 * @author whc
 * @date 2019
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel(value = "ApiResponseVO", description = "接口返回数据统一结构")
public class ApiResponseVO<T> implements Serializable {

    private static final long serialVersionUID = -8011033980394074300L;

    @ApiModelProperty(value = "接口状态码，通常是业务错误代码. 等于【0】时表示接口处理成功，没有任何错误", required = true)
    private Integer code = 0;

    @ApiModelProperty(value = "接口响应信息，通常是业务处理的错误信息", required = true)
    private String msg;

    @ApiModelProperty(value = "接口返回的具体数据，可以为单个记录、列表数组", required = true)
    private T data;

    @ApiModelProperty(value = "当接口返回分页列表数据组时，可同时返回记录的总数")
    private Integer total = 0;

    public ApiResponseVO() {
    }

    public ApiResponseVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ApiResponseVO(Integer code, String msg, T data, Integer total) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.total = total;
    }

    public static<T> ApiResponseVO<T> success(String msg, T data, Integer total){
        return new ApiResponseVO<>(200, "", data, total);
    }

    public static<T> ApiResponseVO<T> success(String msg){
        return new ApiResponseVO<>(200, msg, null);
    }

    public static<T> ApiResponseVO<T> success(String msg, T data){
        return new ApiResponseVO<>(200, msg, data);
    }

    public static<T> ApiResponseVO<T> success(T data){
        return new ApiResponseVO<>(200, "", data);
    }

    public static<T> ApiResponseVO<T> fail(Integer code, String msg){
        return new ApiResponseVO<>(code, msg, null);
    }

    public ApiResponseVO(ExceptionCode exceptionCode){
        this(exceptionCode.getCode(), exceptionCode.getMsg(),null);
    }

    public ApiResponseVO(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ApiResponseVO{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                ", total=" + total +
                '}';
    }
}

