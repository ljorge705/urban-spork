package com.google.common.base;

@ElementTypesAreNonnullByDefault
/* loaded from: classes3.dex */
interface PatternCompiler {
    CommonPattern compile(String str);

    boolean isPcreLike();
}
