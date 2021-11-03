package com.demo.springboot.shardingspherewithspringboot.common;

/**
 * @param
 * @author Wenyi Cao
 * @version 1.0
 * @description 响应码枚举：code、中文、英文
 * @date 2021/9/6 14:52
 * @return
 */
public enum ResultEnum {

    /**
     * @date 2021.5
     * <p>
     * 返回异常代码字典表，所有的服务返回参数returnCode需要注册到此字典表，
     * 所有应用共用此字典表，返回代码规则示例：10-000-OK 返回代码规则定义说明：
     * [服务代码]-[消息类型]-[消息代码]，如果未找到合适的匹配规则，可以进行扩展补充
     * <p>
     * *****第一段为两位服务名，定义如下:******
     * main-api:10(含主应用)
     * transform-svc:11
     * auth-svc:12
     * domain-svc:13
     * upgrade-svc:14
     * openaccount-svc:15
     * boss-svc:16
     * login-svc:17
     * platform-svc:18
     * store-svc:19
     * sysconfig:20
     * <p>
     * *****第二段为三位消息类型，定义如下:******
     * 000：代表正常执行
     * 100：代码类型异常
     * 200：数据库类型异常
     * 300：接口调用，网络等异常
     * 400：业务逻辑类型异常
     * 500：自定义代码错误，触发器，类等
     * <p>
     * *****第三段为三位消息编码，直接使用带含义的英文短语，定义如下:******
     * OK：正常执行
     * AuthenticationFailed：认证失败
     * InvalidParamater：参数错误
     * InvalidParameter.IsNull：参数错误null
     * MissingParameter：缺少参数
     * UnsupportedParameter：不支持的参数
     * SqlQueryTimeout：sql查询超时熔断
     * SqlSyntaxError：sql语法错误
     * UnknownError：未知的错误
     * NullPointerException：空指针错误
     * ConnectionTimeout：连接超时
     * ConnPoolExhausted：连接池满
     * IndexOutOfBoundsException：数组越界
     * APICallFailed：接口调用失败
     * AssetBOMPermission:资产BOM权限
     * @since CloudCC11
     */
    SUCCESS("20-000-OK", "操作成功", "SUCCESS"),
    RUN_TIME_EXCEPTION("LOCAL-SERVER-1", "发生系统运行时异常,请联系系统管理员。", "The system is abnormal, please contact the system administrator for help"),
    SQL_TIMEOUT_EXCEPTION("20-200-ConnectionTimeout", "发生系统运行时异常,请联系系统管理员。",
            "Due to the improper configuration of the system or unoptimized custom SQL queries, the system has exceeded the query capability allowed which cause the query failure. Please contact the system administrator for system tuning."),
    INVALID_PARAM_EXCEPTION("20-100-InvalidParamater", "发生系统运行时异常,请联系系统管理员。", "The system is abnormal, please contact the system administrator for help");

    ResultEnum(String code, String zhMsg, String enMsg) {
        this.code = code;
        this.zhMsg = zhMsg;
        this.enMsg = enMsg;
    }

    private final String code;

    private final String zhMsg;

    private final String enMsg;

    public String getCode() {
        return code;
    }

    private String getZhMsg() {
        return zhMsg;
    }

    private String getEnMsg() {
        return enMsg;
    }

    public String getMessage(String language) {
        if ("zh".equalsIgnoreCase(language.trim())) {
            return getZhMsg();
        } else {
            return getEnMsg();
        }
    }
}
