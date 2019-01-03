package com.whc.domain.exception;

public enum ExceptionCode {
    SiteAlert(601, "温馨提示"),
    NotSignIn(401, "请先登录"),
    NotLogerError(402, "账号密码错误"),
    NOT_FOUND(404, "记录不存在"),
    PermissionDenied(403, "没有权限进行此操作"),
    Error(500, "不可预料的错误"),
    MissingArgument(501, "缺少参数"),
    FormInvalid(600, "表单数据校验错误"),
    RegDisabled(603, "很抱歉，暂时不开放注册"),
    SmsOverflow(10101, "验证码发送太频繁，请稍后重试"),
    SmsSendError(10102, "验证码发送太频繁，请稍后重试"),
    MobileInValid(10103, "手机号格式不争取"),
    SmsCodeNotMatch(10104, "短信验证码校验错误，请重新输入"),
    WeixinAccessTokenError(10105, "访问微信Token接口出现错误，请稍后重试"),
    WeixinUserInfoError(10106, "访问微信用户接口出现错误，请稍后重试"),
    VoiceSmsFailed(10107, "语音验证码发送失败，请稍后重试"),
    SmsCodeTimeout(10108, "短信验证码已失效，请重新获取"),
    AccountMobileExist(20600, "该手机号已被别人使用，请更换"),
    AccountMobileNotFound(20604, "该手机不存在，请更换"),
    AccountLocked(20612, "该帐号暂不允许使用"),
    SessionUserNotFound(20605, "该用户不存在，请重新登录"),
    PersonNotFound(20606, "该用户个人资料不存在，请录入"),
    UpdateMobileError(20607, "修改手机号报错，请稍后重试"),
    UpdatePasswordError(20608, "修改密码报错，请稍后重试"),
    PasswordNotMatch(20609, "登录密码错误，请稍后重试"),
    WeixinBinded(20611, "该微信号已绑定到其他人帐号"),
    TokenInvalid(3000, "令牌无效"),
    TokenTimeout(3001, "令牌已过期"),
    AuthCodeTimeOut(3001, "授权码有效时间已过期"),
    AccountUserIdExist(20612, "该客户编号已被别人使用，请更换"),
    RegCodeNotExists(20613, "该注册推荐码不存在, 请重新输入"),
    ORG_NotExists(7001,"抱歉该企业不在服务范围"),
    ORG_STAFFNotExiss(7002,"抱歉该员工存不存在"),
    ORG_KeyAndSecretError(7003,"企业授权秘钥校验出错"),
    SMS_MobileError(10001,"手机号码有误"),
    Message_ValiCodeError(7004,"校验值有误"),
    ;

    int code;
    String msg;

    ExceptionCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
