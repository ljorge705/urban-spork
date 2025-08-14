package com.facebook.react.views.text.frescosupport;

import android.content.Context;
import android.net.Uri;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.facebook.common.logging.FLog;
import com.facebook.common.util.UriUtil;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableType;
import com.facebook.react.uimanager.ViewProps;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.text.internal.ReactTextInlineImageShadowNode;
import com.facebook.react.views.text.internal.span.TextInlineImageSpan;
import java.util.Locale;

/* loaded from: classes5.dex */
class FrescoBasedReactTextInlineImageShadowNode extends ReactTextInlineImageShadowNode {
    private final Object mCallerContext;
    private final AbstractDraweeControllerBuilder mDraweeControllerBuilder;
    private ReadableMap mHeaders;
    private String mResizeMode;
    private Uri mUri;
    private float mWidth = Float.NaN;
    private float mHeight = Float.NaN;
    private int mTintColor = 0;

    public Object getCallerContext() {
        return this.mCallerContext;
    }

    public AbstractDraweeControllerBuilder getDraweeControllerBuilder() {
        return this.mDraweeControllerBuilder;
    }

    public ReadableMap getHeaders() {
        return this.mHeaders;
    }

    public Uri getUri() {
        return this.mUri;
    }

    @Override // com.facebook.react.uimanager.ReactShadowNodeImpl, com.facebook.react.uimanager.ReactShadowNode
    public boolean isVirtual() {
        return true;
    }

    @ReactProp(name = "headers")
    public void setHeaders(ReadableMap readableMap) {
        this.mHeaders = readableMap;
    }

    @ReactProp(name = ViewProps.RESIZE_MODE)
    public void setResizeMode(String str) {
        this.mResizeMode = str;
    }

    @ReactProp(customType = "Color", name = "tintColor")
    public void setTintColor(int i) {
        this.mTintColor = i;
    }

    public FrescoBasedReactTextInlineImageShadowNode(AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, Object obj) {
        this.mDraweeControllerBuilder = abstractDraweeControllerBuilder;
        this.mCallerContext = obj;
    }

    @ReactProp(name = "src")
    public void setSource(ReadableArray readableArray) {
        Uri resourceDrawableUri = null;
        String string = (readableArray == null || readableArray.size() == 0) ? null : readableArray.getMap(0).getString(ReactNativeBlobUtilConst.DATA_ENCODE_URI);
        if (string != null) {
            try {
                Uri uri = Uri.parse(string);
                if (uri.getScheme() != null) {
                    resourceDrawableUri = uri;
                }
            } catch (Exception unused) {
            }
            if (resourceDrawableUri == null) {
                resourceDrawableUri = getResourceDrawableUri(getThemedContext(), string);
            }
        }
        if (resourceDrawableUri != this.mUri) {
            markUpdated();
        }
        this.mUri = resourceDrawableUri;
    }

    @Override // com.facebook.react.uimanager.LayoutShadowNode
    public void setWidth(Dynamic dynamic) {
        if (dynamic.getType() == ReadableType.Number) {
            this.mWidth = (float) dynamic.asDouble();
        } else {
            FLog.w("ReactNative", "Inline images must not have percentage based width");
            this.mWidth = Float.NaN;
        }
    }

    @Override // com.facebook.react.uimanager.LayoutShadowNode
    public void setHeight(Dynamic dynamic) {
        if (dynamic.getType() == ReadableType.Number) {
            this.mHeight = (float) dynamic.asDouble();
        } else {
            FLog.w("ReactNative", "Inline images must not have percentage based height");
            this.mHeight = Float.NaN;
        }
    }

    private static Uri getResourceDrawableUri(Context context, String str) {
        if (str == null || str.isEmpty()) {
            return null;
        }
        return new Uri.Builder().scheme(UriUtil.LOCAL_RESOURCE_SCHEME).path(String.valueOf(context.getResources().getIdentifier(str.toLowerCase(Locale.getDefault()).replace("-", "_"), "drawable", context.getPackageName()))).build();
    }

    @Override // com.facebook.react.views.text.internal.ReactTextInlineImageShadowNode
    public TextInlineImageSpan buildInlineImageSpan() {
        return new FrescoBasedReactTextInlineImageSpan(getThemedContext().getResources(), (int) Math.ceil(this.mHeight), (int) Math.ceil(this.mWidth), this.mTintColor, getUri(), getHeaders(), getDraweeControllerBuilder(), getCallerContext(), this.mResizeMode);
    }
}
