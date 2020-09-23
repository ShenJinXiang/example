package com.shenjinxiang.rs232.demo5;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageEncoder extends ProtocolEncoderAdapter {

    private final static Logger log = LoggerFactory.getLogger(MessageEncoder.class);

    @Override
    public void encode(IoSession ioSession, Object message, ProtocolEncoderOutput protocolEncoderOutput) throws Exception {
//        if(message instanceof ...){
            IoBuffer buffer = IoBuffer.allocate(100).setAutoExpand(true);
//            ...
            byte[] send = new byte[10];
            buffer.put(send);
            buffer.flip();
            ioSession.write(buffer);
//        }
    }
}
