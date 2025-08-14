package com.clevertap.android.sdk.bitmap;

import com.clevertap.android.sdk.Constants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: HttpUrlConnectionParams.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u001c\b\u0086\b\u0018\u00002\u00020\u0001BE\b\u0007\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0006\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0002\u0010\u000bJ\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0006HÆ\u0003J\u0015\u0010 \u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tHÆ\u0003JG\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tHÆ\u0001J\u0013\u0010\"\u001a\u00020\u00062\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020\u0003HÖ\u0001J\t\u0010%\u001a\u00020\nHÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR&\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0011\"\u0004\b\u001b\u0010\u0013¨\u0006&"}, d2 = {"Lcom/clevertap/android/sdk/bitmap/HttpUrlConnectionParams;", "", "connectTimeout", "", "readTimeout", "useCaches", "", "doInput", "requestMap", "", "", "(IIZZLjava/util/Map;)V", "getConnectTimeout", "()I", "setConnectTimeout", "(I)V", "getDoInput", "()Z", "setDoInput", "(Z)V", "getReadTimeout", "setReadTimeout", "getRequestMap", "()Ljava/util/Map;", "setRequestMap", "(Ljava/util/Map;)V", "getUseCaches", "setUseCaches", "component1", "component2", "component3", "component4", "component5", Constants.COPY_TYPE, "equals", "other", "hashCode", "toString", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final /* data */ class HttpUrlConnectionParams {
    private int connectTimeout;
    private boolean doInput;
    private int readTimeout;
    private Map<String, String> requestMap;
    private boolean useCaches;

    public HttpUrlConnectionParams() {
        this(0, 0, false, false, null, 31, null);
    }

    public HttpUrlConnectionParams(int i) {
        this(i, 0, false, false, null, 30, null);
    }

    public HttpUrlConnectionParams(int i, int i2) {
        this(i, i2, false, false, null, 28, null);
    }

    public HttpUrlConnectionParams(int i, int i2, boolean z) {
        this(i, i2, z, false, null, 24, null);
    }

    public HttpUrlConnectionParams(int i, int i2, boolean z, boolean z2) {
        this(i, i2, z, z2, null, 16, null);
    }

    public static /* synthetic */ HttpUrlConnectionParams copy$default(HttpUrlConnectionParams httpUrlConnectionParams, int i, int i2, boolean z, boolean z2, Map map, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = httpUrlConnectionParams.connectTimeout;
        }
        if ((i3 & 2) != 0) {
            i2 = httpUrlConnectionParams.readTimeout;
        }
        int i4 = i2;
        if ((i3 & 4) != 0) {
            z = httpUrlConnectionParams.useCaches;
        }
        boolean z3 = z;
        if ((i3 & 8) != 0) {
            z2 = httpUrlConnectionParams.doInput;
        }
        boolean z4 = z2;
        if ((i3 & 16) != 0) {
            map = httpUrlConnectionParams.requestMap;
        }
        return httpUrlConnectionParams.copy(i, i4, z3, z4, map);
    }

    /* renamed from: component1, reason: from getter */
    public final int getConnectTimeout() {
        return this.connectTimeout;
    }

    /* renamed from: component2, reason: from getter */
    public final int getReadTimeout() {
        return this.readTimeout;
    }

    /* renamed from: component3, reason: from getter */
    public final boolean getUseCaches() {
        return this.useCaches;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getDoInput() {
        return this.doInput;
    }

    public final Map<String, String> component5() {
        return this.requestMap;
    }

    public final HttpUrlConnectionParams copy(int connectTimeout, int readTimeout, boolean useCaches, boolean doInput, Map<String, String> requestMap) {
        Intrinsics.checkNotNullParameter(requestMap, "requestMap");
        return new HttpUrlConnectionParams(connectTimeout, readTimeout, useCaches, doInput, requestMap);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof HttpUrlConnectionParams)) {
            return false;
        }
        HttpUrlConnectionParams httpUrlConnectionParams = (HttpUrlConnectionParams) other;
        return this.connectTimeout == httpUrlConnectionParams.connectTimeout && this.readTimeout == httpUrlConnectionParams.readTimeout && this.useCaches == httpUrlConnectionParams.useCaches && this.doInput == httpUrlConnectionParams.doInput && Intrinsics.areEqual(this.requestMap, httpUrlConnectionParams.requestMap);
    }

    public final int getConnectTimeout() {
        return this.connectTimeout;
    }

    public final boolean getDoInput() {
        return this.doInput;
    }

    public final int getReadTimeout() {
        return this.readTimeout;
    }

    public final Map<String, String> getRequestMap() {
        return this.requestMap;
    }

    public final boolean getUseCaches() {
        return this.useCaches;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        int iHashCode = ((Integer.hashCode(this.connectTimeout) * 31) + Integer.hashCode(this.readTimeout)) * 31;
        boolean z = this.useCaches;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (iHashCode + i) * 31;
        boolean z2 = this.doInput;
        return ((i2 + (z2 ? 1 : z2 ? 1 : 0)) * 31) + this.requestMap.hashCode();
    }

    public final void setConnectTimeout(int i) {
        this.connectTimeout = i;
    }

    public final void setDoInput(boolean z) {
        this.doInput = z;
    }

    public final void setReadTimeout(int i) {
        this.readTimeout = i;
    }

    public final void setRequestMap(Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.requestMap = map;
    }

    public final void setUseCaches(boolean z) {
        this.useCaches = z;
    }

    public String toString() {
        return "HttpUrlConnectionParams(connectTimeout=" + this.connectTimeout + ", readTimeout=" + this.readTimeout + ", useCaches=" + this.useCaches + ", doInput=" + this.doInput + ", requestMap=" + this.requestMap + ')';
    }

    public HttpUrlConnectionParams(int i, int i2, boolean z, boolean z2, Map<String, String> requestMap) {
        Intrinsics.checkNotNullParameter(requestMap, "requestMap");
        this.connectTimeout = i;
        this.readTimeout = i2;
        this.useCaches = z;
        this.doInput = z2;
        this.requestMap = requestMap;
    }

    public /* synthetic */ HttpUrlConnectionParams(int i, int i2, boolean z, boolean z2, Map map, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? 0 : i, (i3 & 2) != 0 ? 0 : i2, (i3 & 4) != 0 ? false : z, (i3 & 8) == 0 ? z2 : false, (i3 & 16) != 0 ? MapsKt.emptyMap() : map);
    }
}
