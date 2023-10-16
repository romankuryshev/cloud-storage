package cloud.storage.client.handler;

import cloud.storage.client.callback.Callback;
import cloud.storage.command.AbstractCommand;
import cloud.storage.command.MessageCommand;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Platform;

import java.util.concurrent.CountDownLatch;

/**
 * Хендлер вызывает callback {@link Callback} при получении сообщений.
 * Используется для отображения входящий сообщений в графическом интерфейсе.
 */
public class MessageReceiver extends SimpleChannelInboundHandler<AbstractCommand> {
    private final Callback onMessageReceivedCallback;

    public MessageReceiver(Callback onMessageReceivedCallback) {
        this.onMessageReceivedCallback = onMessageReceivedCallback;
    }

    /**
     * Метод выводит сообщение, пришедшее от сервера.
     * @param ctx           the {@link ChannelHandlerContext} which this {@link SimpleChannelInboundHandler}
     *                      belongs to
     * @param command           the message to handle
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, AbstractCommand command) throws Exception {
        final CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                onMessageReceivedCallback.callback(((MessageCommand)command).getMessage());
            } finally {
                latch.countDown();
            }
        });
    }
}
