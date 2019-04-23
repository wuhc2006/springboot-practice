package com.whc.domain;

/**
 * @ClassName SeckillStatus
 * @Description 秒杀状态
 * @Author Administrator
 * @Date 2019/4/22 22:18
 * @Version 1.0
 */
public enum SeckillStatus {
    SUCESS(1, "成功秒杀"),
    SOLD_OUT(-1, "库存不足"),
    REPEAT_SEC(-2, "重复秒杀");

    private int status;
    private String message;

    SeckillStatus(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
