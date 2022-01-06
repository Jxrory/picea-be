package com.jxrory.picea.common.constants;

/**
 * @author Rory
 * @date 2022/1/3 下午10:43
 */
public class RegularConstants {

    /**
     * 数字
     */
    public static final String NUMBER = "^-?[0-9]+";

    /**
     * 邮箱
     */
    public static final String EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    /**
     * 用户名正则，4到16位（字母，数字，下划线，减号）
     */
    public static final String USER_NAME = "^[a-zA-Z0-9_-]{4,16}$";

    /**
     * 手机
     */
    public static final String PHONE = "^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$";

    /**
     * 验证用户密码， 正确格式为：以字母开头，长度在6~18之间，只能包含字符、数字和下划线
     */
    public static final String PASSWORD = "^[a-zA-Z]\\w{5,17}$";

    /**
     * 验证码， 正确格式为：4位数字验证码
     */
    public static final String SMS_VALIDATE_CODE_NUM = "^\\d{6}$";

    /**
     * 验证码， 正确格式为：6位数字验证码
     */
    public static final String VALIDATE_CODE_NUM_SIX = "^\\d{6}$";

    /**
     * 汉字
     */
    public static final String CHINESE = "^[\\u4e00-\\u9fa5]{0,}$";

    /**
     * url http开头
     */
    public static final String URL = "^http://([\\w-]+\\.)+[\\w-]+(/[\\w-./?%&=]*)?$";

    /**
     * 电话 正确格式为："XXX-XXXXXXX"、"XXXX-XXXXXXXX"、"XXX-XXXXXXX"、"XXX-XXXXXXXX"、"XXXXXXX"和"XXXXXXXX"
     */
    public static final String TEL = "^(\\(\\d{3,4}-)|\\d{3.4}-)?\\d{7,8}$";

    /**
     * 验证身份证号 （15位或18位数字）
     */
    public static final String ID_CARD = "^\\d{15}|\\d{18}$";

    /**
     * 验证一年的12个月 正确格式为："01"～"09"和"1"～"12"
     */
    public static final String MONTH = "^(0?[1-9]|1[0-2])$";

    /**
     * 验证一个月的31天 正确格式为；"01"～"09"和"1"～"31"
     */
    public static final String DAY = "^((0?[1-9])|((1|2)[0-9])|30|31)$";

    /**
     * 匹配空行的正则表达式
     */
    public static final String EMPTY_LINE = "\\n[\\s| ]*\\r";

    /**
     * 匹配html标签的正则表达式
     */
    public static final String HTML_TAG = "<(.*)>(.*)<\\/(.*)>|<(.*)\\/>";

    /**
     * 匹配首尾空格的正则表达式
     */
    public static final String TRIM = "(^\\s*)|(\\s*$)";
}
