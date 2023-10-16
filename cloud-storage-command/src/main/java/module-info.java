module cloud.storage.command {
    requires io.netty.buffer;
    requires io.netty.codec;
    requires io.netty.common;
    requires io.netty.transport;
    requires static lombok;
    exports cloud.storage.command;
    exports cloud.storage.command.codec;
}