package com.shenjinxiang.interaction.entity;

/**
 * 运行状态
 * @Author: ShenJinXiang
 * @Date: 2020/8/3 23:34
 */
public enum RunStatus {

    /**
     * 默认，暂无任务
     */
    NONE,
    /**
     * 开始工作准备中，和算法中心建立tcp链接
     */
//    WORK_PREPARATION,
    /**
     * 可以开始考核，接受波数据
     */
    READY_FOR_WORK,
    /**
     * 工作中，此时需要从算法中心获取数据
     */
    WORK,
    /**
     * 播放记录，此时，从文件中获取数据
     */
    PLAY_RECORD;
}
