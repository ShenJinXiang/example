package com.shenjinxiang.client.domain;

import com.shenjinxiang.client.kit.ByteArrayConveter;
import com.shenjinxiang.client.kit.ByteKit;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/14 20:31
 */
public class Data514 {

    private int t;
    private float x;
    private float y;
    private float z;
    private float vx;
    private float vy;
    private float vz;

    public byte[] data() {
        return ByteKit.asBytes(
                ByteArrayConveter.getByteArray(t),
                ByteArrayConveter.getByteArray(x),
                ByteArrayConveter.getByteArray(y),
                ByteArrayConveter.getByteArray(z),
                ByteArrayConveter.getByteArray(vx),
                ByteArrayConveter.getByteArray(vy),
                ByteArrayConveter.getByteArray(vz)
        );
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getVx() {
        return vx;
    }

    public void setVx(float vx) {
        this.vx = vx;
    }

    public float getVy() {
        return vy;
    }

    public void setVy(float vy) {
        this.vy = vy;
    }

    public float getVz() {
        return vz;
    }

    public void setVz(float vz) {
        this.vz = vz;
    }
}
