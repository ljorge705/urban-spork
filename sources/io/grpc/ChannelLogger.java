package io.grpc;

/* loaded from: classes6.dex */
public abstract class ChannelLogger {

    public enum ChannelLogLevel {
        DEBUG,
        INFO,
        WARNING,
        ERROR
    }

    public abstract void log(ChannelLogLevel channelLogLevel, String str);

    public abstract void log(ChannelLogLevel channelLogLevel, String str, Object... objArr);
}
