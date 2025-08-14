package io.grpc;

import com.clevertap.android.sdk.Constants;
import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import io.grpc.ClientStreamTracer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

@CheckReturnValue
/* loaded from: classes6.dex */
public final class CallOptions {
    public static final CallOptions DEFAULT;

    @Nullable
    private final String authority;

    @Nullable
    private final String compressorName;

    @Nullable
    private final CallCredentials credentials;
    private final Object[][] customOptions;

    @Nullable
    private final Deadline deadline;

    @Nullable
    private final Executor executor;

    @Nullable
    private final Integer maxInboundMessageSize;

    @Nullable
    private final Integer maxOutboundMessageSize;
    private final List<ClientStreamTracer.Factory> streamTracerFactories;

    @Nullable
    private final Boolean waitForReady;

    @Nullable
    public String getAuthority() {
        return this.authority;
    }

    @Nullable
    public String getCompressor() {
        return this.compressorName;
    }

    @Nullable
    public CallCredentials getCredentials() {
        return this.credentials;
    }

    @Nullable
    public Deadline getDeadline() {
        return this.deadline;
    }

    @Nullable
    public Executor getExecutor() {
        return this.executor;
    }

    @Nullable
    public Integer getMaxInboundMessageSize() {
        return this.maxInboundMessageSize;
    }

    @Nullable
    public Integer getMaxOutboundMessageSize() {
        return this.maxOutboundMessageSize;
    }

    public List<ClientStreamTracer.Factory> getStreamTracerFactories() {
        return this.streamTracerFactories;
    }

    Boolean getWaitForReady() {
        return this.waitForReady;
    }

    static {
        Builder builder = new Builder();
        builder.customOptions = (Object[][]) Array.newInstance((Class<?>) Object.class, 0, 2);
        builder.streamTracerFactories = Collections.emptyList();
        DEFAULT = builder.build();
    }

    private CallOptions(Builder builder) {
        this.deadline = builder.deadline;
        this.executor = builder.executor;
        this.authority = builder.authority;
        this.credentials = builder.credentials;
        this.compressorName = builder.compressorName;
        this.customOptions = builder.customOptions;
        this.streamTracerFactories = builder.streamTracerFactories;
        this.waitForReady = builder.waitForReady;
        this.maxInboundMessageSize = builder.maxInboundMessageSize;
        this.maxOutboundMessageSize = builder.maxOutboundMessageSize;
    }

    static class Builder {
        String authority;
        String compressorName;
        CallCredentials credentials;
        Object[][] customOptions;
        Deadline deadline;
        Executor executor;
        Integer maxInboundMessageSize;
        Integer maxOutboundMessageSize;
        List<ClientStreamTracer.Factory> streamTracerFactories;
        Boolean waitForReady;

        Builder() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public CallOptions build() {
            return new CallOptions(this);
        }
    }

    public CallOptions withAuthority(@Nullable String str) {
        Builder builder = toBuilder(this);
        builder.authority = str;
        return builder.build();
    }

    public CallOptions withCallCredentials(@Nullable CallCredentials callCredentials) {
        Builder builder = toBuilder(this);
        builder.credentials = callCredentials;
        return builder.build();
    }

    public CallOptions withCompression(@Nullable String str) {
        Builder builder = toBuilder(this);
        builder.compressorName = str;
        return builder.build();
    }

    public CallOptions withDeadline(@Nullable Deadline deadline) {
        Builder builder = toBuilder(this);
        builder.deadline = deadline;
        return builder.build();
    }

    public CallOptions withDeadlineAfter(long j, TimeUnit timeUnit) {
        return withDeadline(Deadline.after(j, timeUnit));
    }

    public CallOptions withWaitForReady() {
        Builder builder = toBuilder(this);
        builder.waitForReady = Boolean.TRUE;
        return builder.build();
    }

    public CallOptions withoutWaitForReady() {
        Builder builder = toBuilder(this);
        builder.waitForReady = Boolean.FALSE;
        return builder.build();
    }

    public CallOptions withExecutor(@Nullable Executor executor) {
        Builder builder = toBuilder(this);
        builder.executor = executor;
        return builder.build();
    }

    public CallOptions withStreamTracerFactory(ClientStreamTracer.Factory factory) {
        ArrayList arrayList = new ArrayList(this.streamTracerFactories.size() + 1);
        arrayList.addAll(this.streamTracerFactories);
        arrayList.add(factory);
        Builder builder = toBuilder(this);
        builder.streamTracerFactories = Collections.unmodifiableList(arrayList);
        return builder.build();
    }

    public static final class Key<T> {
        private final String debugString;
        private final T defaultValue;

        public T getDefault() {
            return this.defaultValue;
        }

        public String toString() {
            return this.debugString;
        }

        private Key(String str, T t) {
            this.debugString = str;
            this.defaultValue = t;
        }

        @Deprecated
        public static <T> Key<T> of(String str, T t) {
            Preconditions.checkNotNull(str, "debugString");
            return new Key<>(str, t);
        }

        public static <T> Key<T> create(String str) {
            Preconditions.checkNotNull(str, "debugString");
            return new Key<>(str, null);
        }

        public static <T> Key<T> createWithDefault(String str, T t) {
            Preconditions.checkNotNull(str, "debugString");
            return new Key<>(str, t);
        }
    }

    public <T> CallOptions withOption(Key<T> key, T t) {
        Preconditions.checkNotNull(key, Constants.KEY_KEY);
        Preconditions.checkNotNull(t, "value");
        Builder builder = toBuilder(this);
        int i = 0;
        while (true) {
            Object[][] objArr = this.customOptions;
            if (i >= objArr.length) {
                i = -1;
                break;
            }
            if (key.equals(objArr[i][0])) {
                break;
            }
            i++;
        }
        builder.customOptions = (Object[][]) Array.newInstance((Class<?>) Object.class, this.customOptions.length + (i == -1 ? 1 : 0), 2);
        System.arraycopy(this.customOptions, 0, builder.customOptions, 0, this.customOptions.length);
        if (i == -1) {
            builder.customOptions[this.customOptions.length] = new Object[]{key, t};
        } else {
            builder.customOptions[i] = new Object[]{key, t};
        }
        return builder.build();
    }

    public <T> T getOption(Key<T> key) {
        Preconditions.checkNotNull(key, Constants.KEY_KEY);
        int i = 0;
        while (true) {
            Object[][] objArr = this.customOptions;
            if (i >= objArr.length) {
                return (T) ((Key) key).defaultValue;
            }
            if (key.equals(objArr[i][0])) {
                return (T) this.customOptions[i][1];
            }
            i++;
        }
    }

    public boolean isWaitForReady() {
        return Boolean.TRUE.equals(this.waitForReady);
    }

    public CallOptions withMaxInboundMessageSize(int i) {
        Preconditions.checkArgument(i >= 0, "invalid maxsize %s", i);
        Builder builder = toBuilder(this);
        builder.maxInboundMessageSize = Integer.valueOf(i);
        return builder.build();
    }

    public CallOptions withMaxOutboundMessageSize(int i) {
        Preconditions.checkArgument(i >= 0, "invalid maxsize %s", i);
        Builder builder = toBuilder(this);
        builder.maxOutboundMessageSize = Integer.valueOf(i);
        return builder.build();
    }

    private static Builder toBuilder(CallOptions callOptions) {
        Builder builder = new Builder();
        builder.deadline = callOptions.deadline;
        builder.executor = callOptions.executor;
        builder.authority = callOptions.authority;
        builder.credentials = callOptions.credentials;
        builder.compressorName = callOptions.compressorName;
        builder.customOptions = callOptions.customOptions;
        builder.streamTracerFactories = callOptions.streamTracerFactories;
        builder.waitForReady = callOptions.waitForReady;
        builder.maxInboundMessageSize = callOptions.maxInboundMessageSize;
        builder.maxOutboundMessageSize = callOptions.maxOutboundMessageSize;
        return builder;
    }

    public String toString() {
        MoreObjects.ToStringHelper toStringHelperAdd = MoreObjects.toStringHelper(this).add("deadline", this.deadline).add("authority", this.authority).add("callCredentials", this.credentials);
        Executor executor = this.executor;
        return toStringHelperAdd.add("executor", executor != null ? executor.getClass() : null).add("compressorName", this.compressorName).add("customOptions", Arrays.deepToString(this.customOptions)).add("waitForReady", isWaitForReady()).add("maxInboundMessageSize", this.maxInboundMessageSize).add("maxOutboundMessageSize", this.maxOutboundMessageSize).add("streamTracerFactories", this.streamTracerFactories).toString();
    }
}
