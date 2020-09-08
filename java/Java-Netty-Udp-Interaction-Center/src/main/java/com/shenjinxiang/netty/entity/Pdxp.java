package com.shenjinxiang.netty.entity;


import com.shenjinxiang.netty.kit.ByteArrayConveter;
import com.shenjinxiang.netty.kit.ByteKit;

public class Pdxp {

    private byte verData;
    private short mIDData;
    private int sIData;
    private int dIDData;
    private int bIDData;
    private int noData;
    private byte flagData;
    private int reserData;
    private short dateData;
    private int timeData;
    private short dataLen;

    public Pdxp() {
        this.verData = (byte)0x80;
    }

    public Pdxp(int noData) {
        this.verData = (byte)0x80;
        this.noData = noData;
    }

    public Pdxp(int noData, short dataLen) {
        this.verData = (byte)0x80;
        this.noData = noData;
        this.dataLen = dataLen;
    }

    public byte[] bytes() {
        return ByteKit.asBytes(
                new byte[]{this.verData},
                ByteArrayConveter.getByteArray(this.mIDData),
                ByteArrayConveter.getByteArray(this.sIData),
                ByteArrayConveter.getByteArray(this.dIDData),
                ByteArrayConveter.getByteArray(this.bIDData),
                ByteArrayConveter.getByteArray(this.noData),
                new byte[]{this.flagData},
                ByteArrayConveter.getByteArray(this.reserData),
                ByteArrayConveter.getByteArray(this.dateData),
                ByteArrayConveter.getByteArray(this.timeData),
                ByteArrayConveter.getByteArray(this.dataLen)
        );
    }

    public byte getVerData() {
        return verData;
    }

    public void setVerData(byte verData) {
        this.verData = verData;
    }

    public short getmIDData() {
        return mIDData;
    }

    public void setmIDData(short mIDData) {
        this.mIDData = mIDData;
    }

    public int getsIData() {
        return sIData;
    }

    public void setsIData(int sIData) {
        this.sIData = sIData;
    }

    public int getdIDData() {
        return dIDData;
    }

    public void setdIDData(int dIDData) {
        this.dIDData = dIDData;
    }

    public int getbIDData() {
        return bIDData;
    }

    public void setbIDData(int bIDData) {
        this.bIDData = bIDData;
    }

    public int getNoData() {
        return noData;
    }

    public void setNoData(int noData) {
        this.noData = noData;
    }

    public byte getFlagData() {
        return flagData;
    }

    public void setFlagData(byte flagData) {
        this.flagData = flagData;
    }

    public int getReserData() {
        return reserData;
    }

    public void setReserData(int reserData) {
        this.reserData = reserData;
    }

    public short getDateData() {
        return dateData;
    }

    public void setDateData(short dateData) {
        this.dateData = dateData;
    }

    public int getTimeData() {
        return timeData;
    }

    public void setTimeData(int timeData) {
        this.timeData = timeData;
    }

    public short getDataLen() {
        return dataLen;
    }

    public void setDataLen(short dataLen) {
        this.dataLen = dataLen;
    }
}
