package com.shenjinxiang.netty.entity;


import com.shenjinxiang.netty.kit.ByteArrayConveter;
import com.shenjinxiang.netty.kit.ByteKit;

public class T0 {

    private Pdxp pdxp;
    private int t0;

    public T0() { }

    public T0(Pdxp pdxp, int t0) {
        this.pdxp = pdxp;
        this.pdxp.setbIDData(1);
        this.pdxp.setDataLen((short) 4);
        this.t0 = t0;
    }

    public byte[] bytes() {
        return ByteKit.asBytes(
                this.pdxp.bytes(),
                ByteArrayConveter.getByteArray(this.t0)
        );
    }

    public Pdxp getPdxp() {
        return pdxp;
    }

    public void setPdxp(Pdxp pdxp) {
        this.pdxp = pdxp;
    }

    public int getT0() {
        return t0;
    }

    public void setT0(int t0) {
        this.t0 = t0;
    }
}
