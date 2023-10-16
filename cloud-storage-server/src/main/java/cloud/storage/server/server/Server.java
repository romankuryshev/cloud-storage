package cloud.storage.server.server;

import cloud.storage.common.codec.CommandDecoder;
import cloud.storage.common.codec.CommandEncoder;
import cloud.storage.server.handler.AuthenticationHandler;
import cloud.storage.server.handler.CommandHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
    private static final int PORT = 8189;
    private static final int COUNT_CONNECTION_GROUP = 1;
    private final Logger logger = LoggerFactory.getLogger(Server.class);
    private final EventLoopGroup connectionGroup;
    private final EventLoopGroup workerGroup;

    public Server() {
        connectionGroup = new NioEventLoopGroup(COUNT_CONNECTION_GROUP);
        workerGroup = new NioEventLoopGroup();
    }

    /**
     * Метод запускает сервер.
     */
    public void start() {
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(connectionGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new CommandDecoder(), new CommandEncoder(), new AuthenticationHandler(), new CommandHandler());
                        }
                    });
            ChannelFuture future = b.bind(PORT).sync();
            logger.info("Server starts!");
            future.channel().closeFuture().sync();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            connectionGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}