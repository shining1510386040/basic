package com.demo.springboot.springlucence.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description mongo 工单记录
 * @date 2021/2/24 17:01
 * @see
 */
@Data
public class WorkOrderRecord {

    private String id;

    /**
     * 工单编号
     */
    private String workOrderNo;

    /**
     * 组织id
     */
    private String orgId;
    /**
     * 组织名称
     */
    private String orgName;
    /**
     * 版本
     */
    private String versionType;

    /**
     * 许可数
     */
    private String licenseNum;

    /**
     * 问题内容
     */
    private String content;

//    /**
//     * 问题分类
//     */
//    private WorkOrderCategory category;

    /**
     * 问题分类-名称:不持久化
     */
    private String categoryName;

    /**
     * 所属产品
     */
//    private WorkOrderProduct belongProduct;

    /**
     * 提交账号
     */
    private String submitAccount;

    /**
     * 提交时间
     */
    private Date submitTime;

    /**
     * 工单状态: 已创建、处理中、已关闭、待评价、已评价、待反馈、已反馈
     */
    private int workOrderStatus;

    /**
     * 工单类型：0普通工单、1重要工单
     */
    private int workOrderType;

    /**
     * 工单评价
     */
    private String evaluation;

    /**
     * 工单解决方案：
     */
//    private WorkOrderSolution solution;

    /**
     * 工单反馈
     */
    private List<Map> feedbackList;
    /**
     * 工单回复
     */
    private List<Map> replayList;


    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 当前工单的处理人Id
     */
    private String currentHandler;

    /**
     * 当前工单的处理人-显示label
     */
    private String currentHandlerLabel;

    /**
     * 工单的分配历史记录
     */
    private List<Map> assignList;

    /**
     * 工单处理人头像：不持久化
     */
    private String currentHandlerAvatar;

}
