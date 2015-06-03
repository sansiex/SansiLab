package com.dianping.sansi.sansilab.nettytest;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;
import java.nio.channels.Channels;
import java.util.concurrent.Executors;

/**
 * Created by zuhai.jiang on 2015/6/1.
 */
public class NettyClient {
//    private static final Logger logger = Logger.getLogger(TcpClient.class);
    public static String HOST = "127.0.0.1";
    public static int PORT = 9999;

    public static Bootstrap bootstrap = getBootstrap();
    public static Channel channel = getChannel(HOST,PORT);
    /**
     * 初始化Bootstrap
     * @return
     */
    public static final Bootstrap getBootstrap(){
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        b.group(group).channel(NioSocketChannel.class);
        b.handler(new ChannelInitializer<Channel>() {
            @Override
            protected void initChannel(Channel ch) throws Exception {
                ChannelPipeline pipeline = ch.pipeline();
                pipeline.addLast("frameDecoder", new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
                pipeline.addLast("frameEncoder", new LengthFieldPrepender(4));
                pipeline.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
                pipeline.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
                pipeline.addLast("handler", new TcpClientHandler());
            }
        });
        b.option(ChannelOption.SO_KEEPALIVE, true);
        return b;
    }

    public static final Channel getChannel(String host,int port){
        Channel channel = null;
        try {
            channel = bootstrap.connect(host, port).sync().channel();
        } catch (Exception e) {
//            logger.error(String.format("连接Server(IP[%s],PORT[%s])失败", host,port),e);
            System.out.println(String.format("连接Server(IP[%s],PORT[%s])失败", host,port));
            return null;
        }
        return channel;
    }

    public static void sendMsg(String msg) throws Exception {
        if(channel!=null){
            channel.writeAndFlush(msg).sync();
        }else{
//            logger.warn("消息发送失败,连接尚未建立!");
        }
    }

    public static void main(String[] args) throws Exception {
        try {
            long t0 = System.nanoTime();
            for (int i = 0; i < 100000; i++) {
                NettyClient.sendMsg(i+"你好1");
                Thread.sleep(1000);
            }
            long t1 = System.nanoTime();
            System.out.println((t1-t0)/1000000.0);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static class TcpClientHandler extends SimpleChannelInboundHandler<Object> {
//        private static final Logger logger = Logger.getLogger(TcpClientHandler.class);

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object msg)
                throws Exception {
            //messageReceived方法,名称很别扭，像是一个内部方法.
//            logger.info("client接收到服务器返回的消息:"+msg);
            System.out.println("client接收到服务器返回的消息:"+msg);

        }


    }
}
