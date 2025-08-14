package com.onfido.android.sdk.capture.internal.util.logging;

import com.clevertap.android.sdk.Constants;
import com.drew.metadata.wav.WavDirectory;
import com.onfido.android.sdk.capture.internal.config.OnfidoRemoteConfig;
import com.onfido.android.sdk.capture.internal.util.logging.RemoteLoggerTree;
import com.onfido.android.sdk.capture.internal.util.logging.Timber;
import com.onfido.android.sdk.capture.utils.TimeProvider;
import com.onfido.javax.inject.Inject;
import com.onfido.javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.ExceptionsKt;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\t\b\u0007\u0018\u0000 -2\u00020\u0001:\u0003-./B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\r\u0010\u0014\u001a\u00020\u0015H\u0001¢\u0006\u0002\b\u0016J5\u0010\u0017\u001a(\u0012\f\u0012\n \u001a*\u0004\u0018\u00010\u00190\u0019 \u001a*\u0014\u0012\u000e\b\u0001\u0012\n \u001a*\u0004\u0018\u00010\u00190\u0019\u0018\u00010\u00180\u0018H\u0001¢\u0006\u0004\b\u001b\u0010\u001cJ\r\u0010\u001d\u001a\u00020\nH\u0001¢\u0006\u0002\b\u001eJ\u001a\u0010\u001f\u001a\u00020\n2\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\"\u001a\u00020\u0010H\u0016J,\u0010#\u001a\u00020$2\u0006\u0010\"\u001a\u00020\u00102\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010%\u001a\u00020!2\b\u0010&\u001a\u0004\u0018\u00010'H\u0016J\r\u0010(\u001a\u00020$H\u0000¢\u0006\u0002\b)J4\u0010*\u001a\u00020$2\u0006\u0010\"\u001a\u00020\u00102\b\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010%\u001a\u00020!2\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010+\u001a\u00020\u0015H\u0002J\u0010\u0010,\u001a\u00020\n2\u0006\u0010\"\u001a\u00020\u0010H\u0002R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\t\u0010\u000bR!\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\r\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00060"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/logging/RemoteLoggerTree;", "Lcom/onfido/android/sdk/capture/internal/util/logging/Timber$DebugTree;", "repository", "Lcom/onfido/android/sdk/capture/internal/util/logging/LoggerRepository;", "timeProvider", "Lcom/onfido/android/sdk/capture/utils/TimeProvider;", "remoteConfig", "Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;", "(Lcom/onfido/android/sdk/capture/internal/util/logging/LoggerRepository;Lcom/onfido/android/sdk/capture/utils/TimeProvider;Lcom/onfido/android/sdk/capture/internal/config/OnfidoRemoteConfig;)V", "isRemoteLoggingEnabled", "", "()Z", "isRemoteLoggingEnabled$delegate", "Lkotlin/Lazy;", "remoteEnabledLogPriorities", "", "", "getRemoteEnabledLogPriorities", "()Ljava/util/Set;", "remoteEnabledLogPriorities$delegate", "getLogInfo", "Lcom/onfido/android/sdk/capture/internal/util/logging/RemoteLoggerTree$LogInfo;", "getLogInfo$onfido_capture_sdk_core_release", "getStackTraces", "", "Ljava/lang/StackTraceElement;", "kotlin.jvm.PlatformType", "getStackTraces$onfido_capture_sdk_core_release", "()[Ljava/lang/StackTraceElement;", "isInDebug", "isInDebug$onfido_capture_sdk_core_release", "isLoggable", "tag", "", "priority", OnfidoLogMapper.LOG_EVENT_TYPE, "", "message", "t", "", "onUproot", "onUproot$onfido_capture_sdk_core_release", "sendToRemote", "logInfo", "shouldSendToRemote", "Companion", "LogInfo", "RemoteSupportedLevels", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@Singleton
/* loaded from: classes2.dex */
public final class RemoteLoggerTree extends Timber.DebugTree {
    private static final String FILE_FIELD_KEY = "file";
    private static final String LINE_FIELD_KEY = "line";
    private static final String MESSAGE_FIELD_KEY = "message";
    private static final String METHOD_FIELD_KEY = "method";
    private static final int PRIORITY_NOT_DEFINED = -1;
    private static final String THROWABLE_FIELD_KEY = "exception";

    /* renamed from: isRemoteLoggingEnabled$delegate, reason: from kotlin metadata */
    private final Lazy isRemoteLoggingEnabled;

    /* renamed from: remoteEnabledLogPriorities$delegate, reason: from kotlin metadata */
    private final Lazy remoteEnabledLogPriorities;
    private final LoggerRepository repository;
    private final TimeProvider timeProvider;
    private static final Companion Companion = new Companion(null);
    private static final List<String> ignoredClasses = CollectionsKt.listOf((Object[]) new String[]{RemoteLoggerTree.class.getName(), Timber.class.getName(), Timber.Tree.class.getName(), Timber.DebugTree.class.getName(), Timber.Companion.class.getName()});

    /* JADX INFO: Access modifiers changed from: private */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0004H\u0002J\u000e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\f\u0010\u0016\u001a\u00020\u0004*\u00020\tH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u001f\u0010\u000b\u001a\u0010\u0012\f\u0012\n \r*\u0004\u0018\u00010\u00040\u00040\f¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0017"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/logging/RemoteLoggerTree$Companion;", "", "()V", "FILE_FIELD_KEY", "", "LINE_FIELD_KEY", "MESSAGE_FIELD_KEY", "METHOD_FIELD_KEY", "PRIORITY_NOT_DEFINED", "", "THROWABLE_FIELD_KEY", "ignoredClasses", "", "kotlin.jvm.PlatformType", "getIgnoredClasses", "()Ljava/util/List;", "mapToPriority", "levelName", "toLogInfo", "Lcom/onfido/android/sdk/capture/internal/util/logging/RemoteLoggerTree$LogInfo;", "trace", "Ljava/lang/StackTraceElement;", "toPriorityName", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    static final class Companion {
        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0055 A[ORIG_RETURN, RETURN] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final int mapToPriority(java.lang.String r2) {
            /*
                r1 = this;
                java.util.Locale r0 = java.util.Locale.ROOT
                java.lang.String r2 = r2.toUpperCase(r0)
                java.lang.String r0 = "toUpperCase(...)"
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r0)
                int r0 = r2.hashCode()
                switch(r0) {
                    case 2251950: goto L4a;
                    case 2656902: goto L3f;
                    case 64921139: goto L34;
                    case 66247144: goto L29;
                    case 1069090146: goto L1e;
                    case 1940088646: goto L13;
                    default: goto L12;
                }
            L12:
                goto L55
            L13:
                java.lang.String r0 = "ASSERT"
                boolean r2 = r2.equals(r0)
                if (r2 != 0) goto L1c
                goto L55
            L1c:
                r2 = 7
                goto L56
            L1e:
                java.lang.String r0 = "VERBOSE"
                boolean r2 = r2.equals(r0)
                if (r2 != 0) goto L27
                goto L55
            L27:
                r2 = 2
                goto L56
            L29:
                java.lang.String r0 = "ERROR"
                boolean r2 = r2.equals(r0)
                if (r2 != 0) goto L32
                goto L55
            L32:
                r2 = 6
                goto L56
            L34:
                java.lang.String r0 = "DEBUG"
                boolean r2 = r2.equals(r0)
                if (r2 != 0) goto L3d
                goto L55
            L3d:
                r2 = 3
                goto L56
            L3f:
                java.lang.String r0 = "WARN"
                boolean r2 = r2.equals(r0)
                if (r2 != 0) goto L48
                goto L55
            L48:
                r2 = 5
                goto L56
            L4a:
                java.lang.String r0 = "INFO"
                boolean r2 = r2.equals(r0)
                if (r2 != 0) goto L53
                goto L55
            L53:
                r2 = 4
                goto L56
            L55:
                r2 = -1
            L56:
                return r2
            */
            throw new UnsupportedOperationException("Method not decompiled: com.onfido.android.sdk.capture.internal.util.logging.RemoteLoggerTree.Companion.mapToPriority(java.lang.String):int");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String toPriorityName(int i) {
            switch (i) {
                case 2:
                    return "VERBOSE";
                case 3:
                    return "DEBUG";
                case 4:
                    return WavDirectory.LIST_INFO;
                case 5:
                    return "WARN";
                case 6:
                    return "ERROR";
                case 7:
                    return "ASSERT";
                default:
                    return "NOT DEFINED";
            }
        }

        public final List<String> getIgnoredClasses() {
            return RemoteLoggerTree.ignoredClasses;
        }

        public final LogInfo toLogInfo(StackTraceElement trace) {
            Intrinsics.checkNotNullParameter(trace, "trace");
            String fileName = trace.getFileName();
            if (fileName == null) {
                fileName = "";
            }
            String methodName = trace.getMethodName();
            Intrinsics.checkNotNullExpressionValue(methodName, "getMethodName(...)");
            return new LogInfo(fileName, methodName, trace.getLineNumber());
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0081\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0006HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0016"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/logging/RemoteLoggerTree$LogInfo;", "", "file", "", "method", RemoteLoggerTree.LINE_FIELD_KEY, "", "(Ljava/lang/String;Ljava/lang/String;I)V", "getFile", "()Ljava/lang/String;", "getLine", "()I", "getMethod", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final /* data */ class LogInfo {
        private final String file;
        private final int line;
        private final String method;

        public LogInfo(String file, String method, int i) {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(method, "method");
            this.file = file;
            this.method = method;
            this.line = i;
        }

        public static /* synthetic */ LogInfo copy$default(LogInfo logInfo, String str, String str2, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = logInfo.file;
            }
            if ((i2 & 2) != 0) {
                str2 = logInfo.method;
            }
            if ((i2 & 4) != 0) {
                i = logInfo.line;
            }
            return logInfo.copy(str, str2, i);
        }

        /* renamed from: component1, reason: from getter */
        public final String getFile() {
            return this.file;
        }

        /* renamed from: component2, reason: from getter */
        public final String getMethod() {
            return this.method;
        }

        /* renamed from: component3, reason: from getter */
        public final int getLine() {
            return this.line;
        }

        public final LogInfo copy(String file, String method, int line) {
            Intrinsics.checkNotNullParameter(file, "file");
            Intrinsics.checkNotNullParameter(method, "method");
            return new LogInfo(file, method, line);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LogInfo)) {
                return false;
            }
            LogInfo logInfo = (LogInfo) other;
            return Intrinsics.areEqual(this.file, logInfo.file) && Intrinsics.areEqual(this.method, logInfo.method) && this.line == logInfo.line;
        }

        public final String getFile() {
            return this.file;
        }

        public final int getLine() {
            return this.line;
        }

        public final String getMethod() {
            return this.method;
        }

        public int hashCode() {
            return (((this.file.hashCode() * 31) + this.method.hashCode()) * 31) + Integer.hashCode(this.line);
        }

        public String toString() {
            return "LogInfo(file=" + this.file + ", method=" + this.method + ", line=" + this.line + ')';
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Lcom/onfido/android/sdk/capture/internal/util/logging/RemoteLoggerTree$RemoteSupportedLevels;", "", "(Ljava/lang/String;I)V", "ERROR", "WARN", "DEBUG", "VERBOSE", WavDirectory.LIST_INFO, "ASSERT", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    public static final class RemoteSupportedLevels {
        private static final /* synthetic */ EnumEntries $ENTRIES;
        private static final /* synthetic */ RemoteSupportedLevels[] $VALUES;
        public static final RemoteSupportedLevels ERROR = new RemoteSupportedLevels("ERROR", 0);
        public static final RemoteSupportedLevels WARN = new RemoteSupportedLevels("WARN", 1);
        public static final RemoteSupportedLevels DEBUG = new RemoteSupportedLevels("DEBUG", 2);
        public static final RemoteSupportedLevels VERBOSE = new RemoteSupportedLevels("VERBOSE", 3);
        public static final RemoteSupportedLevels INFO = new RemoteSupportedLevels(WavDirectory.LIST_INFO, 4);
        public static final RemoteSupportedLevels ASSERT = new RemoteSupportedLevels("ASSERT", 5);

        private static final /* synthetic */ RemoteSupportedLevels[] $values() {
            return new RemoteSupportedLevels[]{ERROR, WARN, DEBUG, VERBOSE, INFO, ASSERT};
        }

        static {
            RemoteSupportedLevels[] remoteSupportedLevelsArr$values = $values();
            $VALUES = remoteSupportedLevelsArr$values;
            $ENTRIES = EnumEntriesKt.enumEntries(remoteSupportedLevelsArr$values);
        }

        private RemoteSupportedLevels(String str, int i) {
        }

        public static EnumEntries<RemoteSupportedLevels> getEntries() {
            return $ENTRIES;
        }

        public static RemoteSupportedLevels valueOf(String str) {
            return (RemoteSupportedLevels) Enum.valueOf(RemoteSupportedLevels.class, str);
        }

        public static RemoteSupportedLevels[] values() {
            return (RemoteSupportedLevels[]) $VALUES.clone();
        }
    }

    @Inject
    public RemoteLoggerTree(LoggerRepository repository, TimeProvider timeProvider, final OnfidoRemoteConfig remoteConfig) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        Intrinsics.checkNotNullParameter(timeProvider, "timeProvider");
        Intrinsics.checkNotNullParameter(remoteConfig, "remoteConfig");
        this.repository = repository;
        this.timeProvider = timeProvider;
        this.isRemoteLoggingEnabled = LazyKt.lazy(new Function0<Boolean>() { // from class: com.onfido.android.sdk.capture.internal.util.logging.RemoteLoggerTree.isRemoteLoggingEnabled.2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(remoteConfig.getLoggerConfiguration().isEnabled());
            }
        });
        this.remoteEnabledLogPriorities = LazyKt.lazy(new Function0<Set<? extends Integer>>() { // from class: com.onfido.android.sdk.capture.internal.util.logging.RemoteLoggerTree$remoteEnabledLogPriorities$2
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final Set<? extends Integer> invoke() {
                List<String> levels = remoteConfig.getLoggerConfiguration().getLevels();
                RemoteLoggerTree.Companion companion = RemoteLoggerTree.Companion;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(levels, 10));
                Iterator<T> it = levels.iterator();
                while (it.hasNext()) {
                    arrayList.add(Integer.valueOf(companion.mapToPriority((String) it.next())));
                }
                return CollectionsKt.toSet(arrayList);
            }
        });
    }

    private final Set<Integer> getRemoteEnabledLogPriorities() {
        return (Set) this.remoteEnabledLogPriorities.getValue();
    }

    private final boolean isRemoteLoggingEnabled() {
        return ((Boolean) this.isRemoteLoggingEnabled.getValue()).booleanValue();
    }

    private final void sendToRemote(int priority, String tag, String message, Throwable t, LogInfo logInfo) {
        String priorityName = Companion.toPriorityName(priority);
        if (tag == null) {
            tag = "";
        }
        List listListOf = CollectionsKt.listOf(tag);
        String currentTimeIsoFormat = this.timeProvider.getCurrentTimeIsoFormat();
        Pair pair = TuplesKt.to("message", message);
        Pair pair2 = TuplesKt.to("method", logInfo.getMethod());
        Pair pair3 = TuplesKt.to(LINE_FIELD_KEY, String.valueOf(logInfo.getLine()));
        Pair pair4 = TuplesKt.to("file", logInfo.getFile());
        String strStackTraceToString = t != null ? ExceptionsKt.stackTraceToString(t) : null;
        this.repository.log$onfido_capture_sdk_core_release(new OnfidoRemoteLog(priorityName, listListOf, currentTimeIsoFormat, MapsKt.mapOf(pair, pair2, pair3, pair4, TuplesKt.to("exception", strStackTraceToString != null ? strStackTraceToString : ""))), priority == 6);
    }

    private final boolean shouldSendToRemote(int priority) {
        return isRemoteLoggingEnabled() && getRemoteEnabledLogPriorities().contains(Integer.valueOf(priority));
    }

    public final LogInfo getLogInfo$onfido_capture_sdk_core_release() {
        StackTraceElement[] stackTraces$onfido_capture_sdk_core_release = getStackTraces$onfido_capture_sdk_core_release();
        Intrinsics.checkNotNullExpressionValue(stackTraces$onfido_capture_sdk_core_release, "getStackTraces(...)");
        for (StackTraceElement stackTraceElement : stackTraces$onfido_capture_sdk_core_release) {
            if (!ignoredClasses.contains(stackTraceElement.getClassName())) {
                return Companion.toLogInfo(stackTraceElement);
            }
        }
        throw new NoSuchElementException("Array contains no element matching the predicate.");
    }

    public final StackTraceElement[] getStackTraces$onfido_capture_sdk_core_release() {
        return new Throwable().getStackTrace();
    }

    public final boolean isInDebug$onfido_capture_sdk_core_release() {
        return false;
    }

    @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
    public boolean isLoggable(String tag, int priority) {
        return isInDebug$onfido_capture_sdk_core_release() || getRemoteEnabledLogPriorities().contains(Integer.valueOf(priority));
    }

    @Override // com.onfido.android.sdk.capture.internal.util.logging.Timber.DebugTree, com.onfido.android.sdk.capture.internal.util.logging.Timber.Tree
    public void log(int priority, String tag, String message, Throwable t) {
        Intrinsics.checkNotNullParameter(message, "message");
        LogInfo logInfo$onfido_capture_sdk_core_release = getLogInfo$onfido_capture_sdk_core_release();
        if (shouldSendToRemote(priority)) {
            sendToRemote(priority, tag, message, t, logInfo$onfido_capture_sdk_core_release);
        }
        super.log(priority, tag, message + '\n' + logInfo$onfido_capture_sdk_core_release, t);
    }

    public final void onUproot$onfido_capture_sdk_core_release() {
        this.repository.submitLogs$onfido_capture_sdk_core_release();
    }
}
