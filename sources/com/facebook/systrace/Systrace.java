package com.facebook.systrace;

import androidx.tracing.Trace;
import com.clevertap.android.sdk.Constants;
import java.lang.reflect.InvocationTargetException;

/* loaded from: classes5.dex */
public class Systrace {
    public static final long TRACE_TAG_REACT_APPS = 0;
    public static final long TRACE_TAG_REACT_FRESCO = 0;
    public static final long TRACE_TAG_REACT_JAVA_BRIDGE = 0;
    public static final long TRACE_TAG_REACT_JS_VM_CALLS = 0;
    public static final long TRACE_TAG_REACT_VIEW = 0;

    public static boolean isTracing(long j) {
        return false;
    }

    public static void registerListener(TraceListener traceListener) {
    }

    public static void stepAsyncFlow(long j, String str, int i) {
    }

    public static void traceInstant(long j, String str, EventScope eventScope) {
    }

    public static void unregisterListener(TraceListener traceListener) {
    }

    public enum EventScope {
        THREAD(Constants.INAPP_POSITION_TOP),
        PROCESS('p'),
        GLOBAL('g');

        private final char mCode;

        public char getCode() {
            return this.mCode;
        }

        EventScope(char c) {
            this.mCode = c;
        }
    }

    public static void beginSection(long j, String str) {
        Trace.beginSection(str);
    }

    public static void endSection(long j) {
        Trace.endSection();
    }

    public static void beginAsyncSection(long j, String str, int i) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Trace.beginAsyncSection(str, i);
    }

    public static void beginAsyncSection(long j, String str, int i, long j2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        beginAsyncSection(j, str, i);
    }

    public static void endAsyncSection(long j, String str, int i) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Trace.endAsyncSection(str, i);
    }

    public static void endAsyncSection(long j, String str, int i, long j2) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        endAsyncSection(j, str, i);
    }

    public static void traceCounter(long j, String str, int i) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Trace.setCounter(str, i);
    }

    public static void startAsyncFlow(long j, String str, int i) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        beginAsyncSection(j, str, i);
    }

    public static void endAsyncFlow(long j, String str, int i) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        endAsyncSection(j, str, i);
    }
}
