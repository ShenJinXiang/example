package com.shenjinxiang.rs232.demo5;

import com.shenjinxiang.rs232.kit.ByteKit;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class MessageDecoder extends CumulativeProtocolDecoder {
    @Override
    protected boolean doDecode(IoSession ioSession, IoBuffer ioBuffer, ProtocolDecoderOutput protocolDecoderOutput) throws Exception {
        byte[] input = new byte[ioBuffer.limit()];
        ioBuffer.get(input, 0, input.length);
        System.out.println(ByteKit.byteArrayToHexStr(input));
        return true;
    }
}
