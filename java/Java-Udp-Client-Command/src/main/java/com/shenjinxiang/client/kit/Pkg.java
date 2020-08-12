package com.shenjinxiang.client.kit;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/12 21:19
 */
public class Pkg {

    private byte ver = (byte) 0x80;
    private short mId;
    private int sId;
    private int dId;
    private int bId;
    private int no;
    private byte flag;
    private int reser;
    private short date;
    private int time;
    private short dataLen;
    private byte[] data;

    public static byte[] build(Pkg pkg) {
        return ByteKit.asBytes(
                new byte[]{pkg.getVer()},
                ByteArrayConveter.getByteArray(pkg.getmId()),
                ByteArrayConveter.getByteArray(pkg.getsId()),
                ByteArrayConveter.getByteArray(pkg.getdId()),
                ByteArrayConveter.getByteArray(pkg.getbId()),
                ByteArrayConveter.getByteArray(pkg.getNo()),
                new byte[]{pkg.getFlag()},
                ByteArrayConveter.getByteArray(pkg.getReser()),
                ByteArrayConveter.getByteArray(pkg.getDate()),
                ByteArrayConveter.getByteArray(pkg.getTime()),
                ByteArrayConveter.getByteArray(pkg.getDataLen()),
                pkg.getData()
        );
    }

    public byte getVer() {
        return ver;
    }

    public void setVer(byte ver) {
        this.ver = ver;
    }

    public short getmId() {
        return mId;
    }

    public void setmId(short mId) {
        this.mId = mId;
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    public int getbId() {
        return bId;
    }

    public void setbId(int bId) {
        this.bId = bId;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public byte getFlag() {
        return flag;
    }

    public void setFlag(byte flag) {
        this.flag = flag;
    }

    public int getReser() {
        return reser;
    }

    public void setReser(int reser) {
        this.reser = reser;
    }

    public short getDate() {
        return date;
    }

    public void setDate(short date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public short getDataLen() {
        return dataLen;
    }

    public void setDataLen(short dataLen) {
        this.dataLen = dataLen;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
