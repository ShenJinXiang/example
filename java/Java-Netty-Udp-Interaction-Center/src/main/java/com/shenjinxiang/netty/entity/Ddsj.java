package com.shenjinxiang.netty.entity;


import com.shenjinxiang.netty.kit.ByteArrayConveter;
import com.shenjinxiang.netty.kit.ByteKit;

public class Ddsj {

    private Pdxp pdxp;
    private int t;
    private float x;
    private float y;
    private float z;
    private float vx;
    private float vy;
    private float vz;

    public Ddsj() { }

    public Ddsj(Pdxp pdxp, int t, float x, float y, float z, float vx, float vy, float vz) {
        this.pdxp = pdxp;
        this.pdxp.setbIDData(3);
        this.pdxp.setDataLen((short) 28);
        this.t = t;
        this.x = x;
        this.y = y;
        this.z = z;
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
    }

    public byte[] bytes() {
        return ByteKit.asBytes(
                this.pdxp.bytes(),
                ByteArrayConveter.getByteArray(this.t),
                ByteArrayConveter.getByteArray(this.x),
                ByteArrayConveter.getByteArray(this.y),
                ByteArrayConveter.getByteArray(this.z),
                ByteArrayConveter.getByteArray(this.vx),
                ByteArrayConveter.getByteArray(this.vy),
                ByteArrayConveter.getByteArray(this.vz)
        );
    }

    public Pdxp getPdxp() {
        return pdxp;
    }

    public void setPdxp(Pdxp pdxp) {
        this.pdxp = pdxp;
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
