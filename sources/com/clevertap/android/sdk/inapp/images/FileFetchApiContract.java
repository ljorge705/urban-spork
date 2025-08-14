package com.clevertap.android.sdk.inapp.images;

import com.clevertap.android.sdk.inapp.data.CtCacheType;
import com.clevertap.android.sdk.network.DownloadedBitmap;
import kotlin.Metadata;
import kotlin.Pair;

/* compiled from: FileFetchApi.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005H&¨\u0006\b"}, d2 = {"Lcom/clevertap/android/sdk/inapp/images/FileFetchApiContract;", "", "makeApiCallForFile", "Lcom/clevertap/android/sdk/network/DownloadedBitmap;", "urlMeta", "Lkotlin/Pair;", "", "Lcom/clevertap/android/sdk/inapp/data/CtCacheType;", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public interface FileFetchApiContract {
    DownloadedBitmap makeApiCallForFile(Pair<String, ? extends CtCacheType> urlMeta);
}
