package com.nimbusds.jose.shaded.gson;

import com.nimbusds.jose.shaded.gson.internal.ReflectionAccessFilterHelper;

/* loaded from: classes2.dex */
public interface ReflectionAccessFilter {
    public static final ReflectionAccessFilter BLOCK_INACCESSIBLE_JAVA = new ReflectionAccessFilter() { // from class: com.nimbusds.jose.shaded.gson.ReflectionAccessFilter.1
        @Override // com.nimbusds.jose.shaded.gson.ReflectionAccessFilter
        public FilterResult check(Class<?> cls) {
            if (ReflectionAccessFilterHelper.isJavaType(cls)) {
                return FilterResult.BLOCK_INACCESSIBLE;
            }
            return FilterResult.INDECISIVE;
        }
    };
    public static final ReflectionAccessFilter BLOCK_ALL_JAVA = new ReflectionAccessFilter() { // from class: com.nimbusds.jose.shaded.gson.ReflectionAccessFilter.2
        @Override // com.nimbusds.jose.shaded.gson.ReflectionAccessFilter
        public FilterResult check(Class<?> cls) {
            if (ReflectionAccessFilterHelper.isJavaType(cls)) {
                return FilterResult.BLOCK_ALL;
            }
            return FilterResult.INDECISIVE;
        }
    };
    public static final ReflectionAccessFilter BLOCK_ALL_ANDROID = new ReflectionAccessFilter() { // from class: com.nimbusds.jose.shaded.gson.ReflectionAccessFilter.3
        @Override // com.nimbusds.jose.shaded.gson.ReflectionAccessFilter
        public FilterResult check(Class<?> cls) {
            if (ReflectionAccessFilterHelper.isAndroidType(cls)) {
                return FilterResult.BLOCK_ALL;
            }
            return FilterResult.INDECISIVE;
        }
    };
    public static final ReflectionAccessFilter BLOCK_ALL_PLATFORM = new ReflectionAccessFilter() { // from class: com.nimbusds.jose.shaded.gson.ReflectionAccessFilter.4
        @Override // com.nimbusds.jose.shaded.gson.ReflectionAccessFilter
        public FilterResult check(Class<?> cls) {
            if (ReflectionAccessFilterHelper.isAnyPlatformType(cls)) {
                return FilterResult.BLOCK_ALL;
            }
            return FilterResult.INDECISIVE;
        }
    };

    public enum FilterResult {
        ALLOW,
        INDECISIVE,
        BLOCK_INACCESSIBLE,
        BLOCK_ALL
    }

    FilterResult check(Class<?> cls);
}
