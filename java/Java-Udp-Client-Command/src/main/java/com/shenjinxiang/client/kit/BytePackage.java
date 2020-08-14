package com.shenjinxiang.client.kit;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/8/12 21:19
 */
public class BytePackage {

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

    public byte[] bytes() {
        return ByteKit.asBytes(
                new byte[]{this.getVer()},
                ByteArrayConveter.getByteArray(this.getmId()),
                ByteArrayConveter.getByteArray(this.getsId()),
                ByteArrayConveter.getByteArray(this.getdId()),
                ByteArrayConveter.getByteArray(this.getbId()),
                ByteArrayConveter.getByteArray(this.getNo()),
                new byte[]{this.getFlag()},
                ByteArrayConveter.getByteArray(this.getReser()),
                ByteArrayConveter.getByteArray(this.getDate()),
                ByteArrayConveter.getByteArray(this.getTime()),
                ByteArrayConveter.getByteArray(this.getDataLen()),
                this.getData()
        );
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        byte ver = (byte) 0x80;
        short mId = 0;
        int sId = 0;
        int dId = 0;
        int bId = 0;
        int no = 0;
        byte flag = 0;
        int reser = 0;
        short date = 0;
        int time = 0;
        short dataLen = 0;
        byte[] data = new byte[0];

        public Builder() {
        }

        Builder mId(short val) {
            this.mId = val;
            return this;
        }

        Builder sId(int val) {
            this.sId = val;
            return this;
        }

        Builder dId(int val) {
            this.dId = val;
            return this;
        }

        Builder bId(int val) {
            this.bId = val;
            return this;
        }

        Builder no(int val) {
            this.no = val;
            return this;
        }

        Builder flag(byte val) {
            this.flag = val;
            return this;
        }

        Builder reser(int val) {
            this.reser = val;
            return this;
        }

        Builder date(short val) {
            this.date = val;
            return this;
        }

        Builder time(int val) {
            this.time = val;
            return this;
        }

        Builder dataLen(short val) {
            this.reser = val;
            return this;
        }

        Builder data(byte[] val) {
            this.data = val;
            return this;
        }

        public BytePackage build() {
            BytePackage bytePackage = new BytePackage();
            bytePackage.setmId(this.mId);
            bytePackage.setsId(this.sId);
            bytePackage.setdId(this.dId);
            bytePackage.setbId(this.bId);
            bytePackage.setNo(this.no);
            bytePackage.setFlag(this.flag);
            bytePackage.setReser(this.reser);
            bytePackage.setDate(this.date);
            bytePackage.setTime(this.time);
            bytePackage.setData(this.data);
            return bytePackage;
        }


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
