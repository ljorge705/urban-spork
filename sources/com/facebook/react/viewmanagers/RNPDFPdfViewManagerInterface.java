package com.facebook.react.viewmanagers;

import android.view.View;

/* loaded from: classes5.dex */
public interface RNPDFPdfViewManagerInterface<T extends View> {
    void setEnableAnnotationRendering(T t, boolean z);

    void setEnableAntialiasing(T t, boolean z);

    void setEnablePaging(T t, boolean z);

    void setEnableRTL(T t, boolean z);

    void setFitPolicy(T t, int i);

    void setHorizontal(T t, boolean z);

    void setMaxScale(T t, float f);

    void setMinScale(T t, float f);

    void setNativePage(T t, int i);

    void setPage(T t, int i);

    void setPassword(T t, String str);

    void setPath(T t, String str);

    void setScale(T t, float f);

    void setShowsHorizontalScrollIndicator(T t, boolean z);

    void setShowsVerticalScrollIndicator(T t, boolean z);

    void setSinglePage(T t, boolean z);

    void setSpacing(T t, int i);
}
