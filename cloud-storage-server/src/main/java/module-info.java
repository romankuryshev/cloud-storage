module cloud.storage.server{
    requires cloud.storage.command;
    requires io.netty.buffer;
    requires io.netty.codec;
    requires io.netty.common;
    requires io.netty.transport;
    requires org.slf4j;
    requires static lombok;
    exports cloud.storage.server;
}