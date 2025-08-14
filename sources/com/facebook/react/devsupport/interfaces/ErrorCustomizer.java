package com.facebook.react.devsupport.interfaces;

import android.util.Pair;

/* loaded from: classes5.dex */
public interface ErrorCustomizer {
    Pair<String, StackFrame[]> customizeErrorInfo(Pair<String, StackFrame[]> pair);
}
