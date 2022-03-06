package com.wwinfo.constant;

/**
 * Created with IntelliJ IDEA.
 * Description: 常量定义
 *
 * @Author: lk
 * DateTime: 2022-02-21 20:04
 */
public class SysConstant {

    public static final String LOCKUP = "1";   //锁住

    /**-----------------用户类型--------------**/
    public static final Integer USER_TYPE_ORG = 2;  //部门管理员

    /**-----------------销毁状态--------------**/
    public static final Integer DELSTATUS_NOT = 0; //未销毁
    public static final Integer DELSTATUS_ED = 1;  //销毁

    /**----------------出库登记状态-----------**/
    public static final Integer OUT_STATUS_NOT_DELIVE = 0;   //还未出库
    public static final Integer OUT_STATUS_DELIVEED = 1;   //已出库
    public static final Integer OUT_STATUS_RETURNED = 2;   //已归还


    /**----------------黑名单设置类型----------**/
    public static final Integer BLACK_TYPE_SET = 1;   //设置黑名单


    /**----------------是否是黑名单------------**/
    public static final Integer IS_NOT_BLACK = 0;    //不是黑名单
    public static final Integer IS_BLACK = 1;    //是黑名单

    /**----------------rfid_asset状态---------**/
    public static final Integer RFID_ASSET_STATUS = 1;  //已废弃

    /**----------------资产出库状态-----------**/
    public static final Integer ASSET_OUTREG_STATUS = 0;  //还未出库

    /**----------------盘点任务状态-----------**/
    public static final Integer INVETORY_TASK_STATUS_PROCESS = 0;  //盘点中
    public static final Integer INVETORY_TASK_STATUS_END = 1;  //盘点结束

    /**----------------盘点结果确认-----------**/
    public static final Integer RESULTCHECK_CONFIRM = 1;  //确认

}
