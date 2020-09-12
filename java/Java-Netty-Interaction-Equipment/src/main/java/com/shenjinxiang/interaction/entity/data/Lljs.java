package com.shenjinxiang.interaction.entity.data;


import com.shenjinxiang.interaction.kit.ByteArrayConveter;
import com.shenjinxiang.interaction.kit.ByteKit;

/**
 * 链路监视信息
 */
public class Lljs {

    private Pdxp pdxp;
    private int t;

    public Lljs() { }

    public Lljs(Pdxp pdxp, int t) {
        this.pdxp = pdxp;
        this.pdxp.setmIDData((short) 15);
        this.pdxp.setbIDData(2);
        this.pdxp.setDataLen((short) 4);
        this.t = t;
    }

    public byte[] bytes() {
        return ByteKit.asBytes(
                this.pdxp.bytes(),
                ByteArrayConveter.getByteArray(this.t)
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
}
