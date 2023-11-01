package cloud.storage.client.network;

import cloud.storage.client.callback.Callback;
import cloud.storage.client.handler.MessageReceiver;
import cloud.storage.common.command.AbstractCommand;
import cloud.storage.common.codec.CommandDecoder;
import cloud.storage.common.codec.CommandEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Network {
    private static final int PORT = 8189;
    private static final String HOST = "localhost";
    public SocketChannel channel;
    private final Logger logger = LoggerFactory.getLogger(Network.class);
    public Network(Callback onMessageReceivedCallback) {
        Thread t = new Thread(() -> {
            EventLoopGroup workerGroup = new NioEventLoopGroup();
            try {
                Bootstrap b = new Bootstrap();
                b.group(workerGroup)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                channel = ch;
                                ch.pipeline().addLast(new CommandDecoder(), new CommandEncoder(), new MessageReceiver(onMessageReceivedCallback));
                            }
                        });
                ChannelFuture future = b.connect(HOST, PORT).sync();
                logger.info("Client connected.");
                future.channel().closeFuture().sync();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                workerGroup.shutdownGracefully();
            }
        });
        t.start();
    }

    public void close() {
        channel.close();
        logger.info("Channel closed.");
    }

    /**
     * Метод отправляет команду на сервер.
     * @param command команда {@link AbstractCommand} для отправки.
     */
    public void sendCommand(AbstractCommand command) {
        channel.writeAndFlush(command);
        logger.info("Command sent.");
    }
}
