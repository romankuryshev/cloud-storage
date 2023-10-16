module cloud.storage.command {
    requires io.netty.buffer;
    requires io.netty.codec;
    requires io.netty.common;
    requires io.netty.transport;
    requires static lombok;
    exports cloud.storage.common.codec;
    exports cloud.storage.common.command;
    exports cloud.storage.common.enums;
}