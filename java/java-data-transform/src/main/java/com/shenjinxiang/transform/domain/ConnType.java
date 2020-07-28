package com.shenjinxiang.transform.domain;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/7/28 8:33
 */
public enum ConnType {

    UDP, TCP;

    public static void main(String[] args) {
        ConnType connType1 = ConnType.valueOf("UDP");
        ConnType connType2 = ConnType.valueOf("TCP");
        System.out.println(connType1);
        System.out.println(connType2);
    }
}
