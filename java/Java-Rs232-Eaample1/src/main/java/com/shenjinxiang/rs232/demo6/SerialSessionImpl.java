package com.shenjinxiang.rs232.demo6;

import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.filterchain.DefaultIoFilterChain;
import org.apache.mina.core.filterchain.IoFilterChain;
import org.apache.mina.core.service.*;
import org.apache.mina.core.session.AbstractIoSession;
import org.apache.mina.core.write.WriteRequest;
import org.apache.mina.transport.serial.SerialAddress;
import org.apache.mina.transport.serial.SerialConnector;
import org.apache.mina.transport.serial.SerialSession;
import org.apache.mina.transport.serial.SerialSessionConfig;
import org.apache.mina.util.ExceptionMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.TooManyListenersException;

public class SerialSessionImpl /*extends AbstractIoSession implements SerialSession, SerialPortEventListener */{
//    static final TransportMetadata METADATA = new DefaultTransportMetadata("rxtx", "serial", false, true,
//            SerialAddress.class, SerialSessionConfig.class, IoBuffer.class);
//    private final IoProcessor<SerialSessionImpl> processor = new SerialIoProcessor();
//    private final IoFilterChain filterChain;
//    private final IoServiceListenerSupport serviceListeners;
//    private final SerialAddress address;
//    private final SerialPort port;
//    private final Logger log;
//    private InputStream inputStream;
//    private OutputStream outputStream;
//    SerialSessionImpl(SerialConnector service, IoServiceListenerSupport serviceListeners, SerialAddress address,
//                      SerialPort port) {
//        super(service);
////        config = new DefaultSerialSessionConfig();
//        this.serviceListeners = serviceListeners;
//        filterChain = new DefaultIoFilterChain(this);
//        this.port = port;
//        this.address = address;
//        log = LoggerFactory.getLogger(SerialSessionImpl.class);
//    }
//    public SerialSessionConfig getConfig() {
//        return (SerialSessionConfig) config;
//    }
//    public IoFilterChain getFilterChain() {
//        return filterChain;
//    }
//    public TransportMetadata getTransportMetadata() {
//        return METADATA;
//    }
//    public SerialAddress getLocalAddress() {
//        return null; // not applicable
//    }
//    public SerialAddress getRemoteAddress() {
//        return address;
//    }
//    @Override
//    public SerialAddress getServiceAddress() {
//        return (SerialAddress) super.getServiceAddress();
//    }
//    public void setDTR(boolean dtr) {
//        port.setDTR(dtr);
//    }
//    public boolean isDTR() {
//        return port.isDTR();
//    }
//    public void setRTS(boolean rts) {
//        port.setRTS(rts);
//    }
//    public boolean isRTS() {
//        return port.isRTS();
//    }
//
//    void start() throws IOException, TooManyListenersException {
//        inputStream = port.getInputStream();
//        outputStream = port.getOutputStream();
//        ReadWorker w = new ReadWorker();
//        w.start();
//        port.addEventListener(this);
//        ((SerialConnector) getService()).getIdleStatusChecker0().addSession(this);
//        try {
//            getService().getFilterChainBuilder().buildFilterChain(getFilterChain());
//            serviceListeners.fireSessionCreated(this);
//        } catch (Throwable e) {
//            getFilterChain().fireExceptionCaught(e);
//            processor.remove(this);
//        }
//    }
//    private final Object writeMonitor = new Object();
//    private WriteWorker writeWorker;
//    private class WriteWorker extends Thread {
//        @Override
//        public void run() {
//            while (isConnected() && !isClosing()) {
//                flushWrites();
//                // wait for more data
//                synchronized (writeMonitor) {
//                    try {
//                        writeMonitor.wait();
//                    } catch (InterruptedException e) {
//                        log.error("InterruptedException", e);
//                    }
//                }
//            }
//        }
//    }
//    private void flushWrites() {
//        for (;;) {
//            WriteRequest req = getCurrentWriteRequest();
//            if (req == null) {
//                req = getWriteRequestQueue().poll(this);
//                if (req == null) {
//                    break;
//                }
//            }
//            IoBuffer buf = (IoBuffer) req.getMessage();
//            if (buf.remaining() == 0) {
//                setCurrentWriteRequest(null);
//                buf.reset();
//                this.getFilterChain().fireMessageSent(req);
//                continue;
//            }
//            int writtenBytes = buf.remaining();
//            try {
//                outputStream.write(buf.array(), buf.position(), writtenBytes);
//                buf.position(buf.position() + writtenBytes);
//                // increase written bytes
//                increaseWrittenBytes(writtenBytes, System.currentTimeMillis());
//                setCurrentWriteRequest(null);
//                buf.reset();
//                // fire the message sent event
//                getFilterChain().fireMessageSent(req);
//            } catch (IOException e) {
//                this.getFilterChain().fireExceptionCaught(e);
//            }
//        }
//    }
//    private final Object readReadyMonitor = new Object();
//    int dataSize;
//    //   long l1=0;
//    private class ReadWorker extends Thread {
//        @Override
//        public void run() {
//
//
//            while (isConnected() && !isClosing()) {
//                synchronized (readReadyMonitor) {
//                    try {
//                        readReadyMonitor.wait();
//                    } catch (InterruptedException e) {
//                        log.error("InterruptedException", e);
//                    }
//                    if (isClosing() || !isConnected()) {
//                        break;
//                    }
//                    try {
//                     /*if(l1==0){
//                      l1=System.currentTimeMillis();
//                     }else{
//                      long l2=System.currentTimeMillis();
//                      System.out.println(l2-l1);
//                      l1=l2;
//                     }*/
//                        while(inputStream.available()>dataSize){
//                            dataSize = inputStream.available();
//                            Thread.sleep(address.getWaitMs());//参数一般是200毫秒，这个参数也可以自己输入我的电脑每次读数据的间隔是200毫秒左右
//                        }
//                        byte[] data = new byte[dataSize];
//                        int readBytes = inputStream.read(data);
//                        if (readBytes > 0) {
//                            IoBuffer buf = IoBuffer.wrap(data, 0, readBytes);
//                            buf.put(data, 0, readBytes);
//                            buf.flip();
//                            getFilterChain().fireMessageReceived(buf);
//                        }
//                        dataSize=0;
//                    } catch (IOException e) {
//                        getFilterChain().fireExceptionCaught(e);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }
//    }
//    public void serialEvent(SerialPortEvent evt) {
//        if (evt.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
//            synchronized (readReadyMonitor) {
//                readReadyMonitor.notifyAll();
//            }
//        }
//    }
//    @Override
//    public IoProcessor<SerialSessionImpl> getProcessor() {
//        return processor;
//    }
//    private class SerialIoProcessor implements IoProcessor<SerialSessionImpl> {
//        public void add(SerialSessionImpl session) {
//            // It's already added when the session is constructed.
//        }
//        public void flush(SerialSessionImpl session) {
//            if (writeWorker == null) {
//                writeWorker = new WriteWorker();
//                writeWorker.start();
//            } else {
//                synchronized (writeMonitor) {
//                    writeMonitor.notifyAll();
//                }
//            }
//        }
//
//        @Override
//        public void write(SerialSessionImpl serialSession, WriteRequest writeRequest) {
//
//        }
//
//        public void remove(SerialSessionImpl session) {
//            try {
//                inputStream.close();
//            } catch (IOException e) {
//                ExceptionMonitor.getInstance().exceptionCaught(e);
//            }
//            try {
//                outputStream.close();
//            } catch (IOException e) {
//                ExceptionMonitor.getInstance().exceptionCaught(e);
//            }
//            try { // Turn flow control off right before close to avoid deadlock
//                port.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
//            } catch (UnsupportedCommOperationException e) {
//                ExceptionMonitor.getInstance().exceptionCaught(e);
//            }
//            port.close();
//            flush(session);
//            synchronized (readReadyMonitor) {
//                readReadyMonitor.notifyAll();
//            }
//            serviceListeners.fireSessionDestroyed(SerialSessionImpl.this);
//        }
//        public void updateTrafficControl(SerialSessionImpl session) {
//            throw new UnsupportedOperationException();
//        }
//        public void dispose() {
//            // Nothing to dispose
//        }
//        public boolean isDisposed() {
//            return false;
//        }
//        public boolean isDisposing() {
//            return false;
//        }
//    }
}
