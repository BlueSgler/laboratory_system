package com.laboratory.util.result;

import lombok.experimental.Accessors;

/**
 * @author Pymjl
 * @date 2022/1/21 22:03
 * 规定:
 * #1表示成功
 * #1001～1999 区间表示参数错误
 * #2001～2999 区间表示用户错误
 * #3001～3999 区间表示接口异常
 * #10001~10004 订单支付状态
 * 统一错误枚举类
 */
@Accessors(chain = true)
public enum ResultCode {

    /* 成功 */
    SUCCESS(200, "成功"),

    /* 默认失败 */
    COMMON_FAIL(999, "失败"),

    CUSTOM_FAIL(9999, "自定义错误"),

    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),
    FAIL_COPY_PROPERTIES(1076, "创建对象失败或拷贝对象属性失败"),
    WEI_XIN_CODE_ERROR(1005,"微信code错误"),

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线"),

    /*Token错误*/
    TOKEN_EXPIRED(4001, "登陆信息过期"),
    TOKEN_ERROR(4002, "信息被更改,请重新登陆"),
    TOKEN_NULL(4003, "sessionId为空"),

    /* 业务错误 */
    NO_PERMISSION(3001, "没有权限"),

    /*支付状态码*/
    TRADE_SUCCESS(10001,"交易支付成功"),
    TRADE_CLOSED(10002,"交易超时未支付或已全部退款"),
    WAIT_BUYER_PAY(10003,"等待买家支付订单"),
    TRADE_FINISHED(10004,"交易结束不可退款");


    private Integer code;
    private String message;


    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public ResultCode setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultCode setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * 根据code获取message
     *
     * @param code 状态码
     * @return String
     */
    public static String getMessageByCode(Integer code) {
        for (ResultCode ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getMessage();
            }
        }
        return null;
    }
}
