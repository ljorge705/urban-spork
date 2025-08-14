package com.facebook.react.viewmanagers;

import android.view.View;
import com.ReactNativeBlobUtil.ReactNativeBlobUtilConst;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.BaseViewManagerDelegate;
import com.facebook.react.uimanager.BaseViewManagerInterface;
import com.facebook.react.viewmanagers.RNPDFPdfViewManagerInterface;
import org.apache.commons.lang3.CharUtils;

/* loaded from: classes5.dex */
public class RNPDFPdfViewManagerDelegate<T extends View, U extends BaseViewManagerInterface<T> & RNPDFPdfViewManagerInterface<T>> extends BaseViewManagerDelegate<T, U> {
    /* JADX WARN: Incorrect types in method signature: (TU;)V */
    public RNPDFPdfViewManagerDelegate(BaseViewManagerInterface baseViewManagerInterface) {
        super(baseViewManagerInterface);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void setProperty(T t, String str, Object obj) {
        str.hashCode();
        char c = 65535;
        switch (str.hashCode()) {
            case -2012158909:
                if (str.equals("spacing")) {
                    c = 0;
                    break;
                }
                break;
            case -1790919953:
                if (str.equals("enablePaging")) {
                    c = 1;
                    break;
                }
                break;
            case -1379690984:
                if (str.equals("minScale")) {
                    c = 2;
                    break;
                }
                break;
            case -922092170:
                if (str.equals("showsVerticalScrollIndicator")) {
                    c = 3;
                    break;
                }
                break;
            case -657951334:
                if (str.equals("enableAnnotationRendering")) {
                    c = 4;
                    break;
                }
                break;
            case -631667225:
                if (str.equals("enableRTL")) {
                    c = 5;
                    break;
                }
                break;
            case 3433103:
                if (str.equals("page")) {
                    c = 6;
                    break;
                }
                break;
            case 3433509:
                if (str.equals(ReactNativeBlobUtilConst.RNFB_RESPONSE_PATH)) {
                    c = 7;
                    break;
                }
                break;
            case 109250890:
                if (str.equals("scale")) {
                    c = '\b';
                    break;
                }
                break;
            case 396505670:
                if (str.equals("maxScale")) {
                    c = '\t';
                    break;
                }
                break;
            case 902106275:
                if (str.equals("fitPolicy")) {
                    c = '\n';
                    break;
                }
                break;
            case 913503991:
                if (str.equals("singlePage")) {
                    c = 11;
                    break;
                }
                break;
            case 1216985755:
                if (str.equals("password")) {
                    c = '\f';
                    break;
                }
                break;
            case 1308044823:
                if (str.equals("enableAntialiasing")) {
                    c = CharUtils.CR;
                    break;
                }
                break;
            case 1387629604:
                if (str.equals("horizontal")) {
                    c = 14;
                    break;
                }
                break;
            case 1915931784:
                if (str.equals("showsHorizontalScrollIndicator")) {
                    c = 15;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                ((RNPDFPdfViewManagerInterface) this.mViewManager).setSpacing(t, obj != null ? ((Double) obj).intValue() : 0);
                break;
            case 1:
                ((RNPDFPdfViewManagerInterface) this.mViewManager).setEnablePaging(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 2:
                ((RNPDFPdfViewManagerInterface) this.mViewManager).setMinScale(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                break;
            case 3:
                ((RNPDFPdfViewManagerInterface) this.mViewManager).setShowsVerticalScrollIndicator(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 4:
                ((RNPDFPdfViewManagerInterface) this.mViewManager).setEnableAnnotationRendering(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 5:
                ((RNPDFPdfViewManagerInterface) this.mViewManager).setEnableRTL(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 6:
                ((RNPDFPdfViewManagerInterface) this.mViewManager).setPage(t, obj != null ? ((Double) obj).intValue() : 0);
                break;
            case 7:
                ((RNPDFPdfViewManagerInterface) this.mViewManager).setPath(t, obj != null ? (String) obj : null);
                break;
            case '\b':
                ((RNPDFPdfViewManagerInterface) this.mViewManager).setScale(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                break;
            case '\t':
                ((RNPDFPdfViewManagerInterface) this.mViewManager).setMaxScale(t, obj != null ? ((Double) obj).floatValue() : 0.0f);
                break;
            case '\n':
                ((RNPDFPdfViewManagerInterface) this.mViewManager).setFitPolicy(t, obj != null ? ((Double) obj).intValue() : 0);
                break;
            case 11:
                ((RNPDFPdfViewManagerInterface) this.mViewManager).setSinglePage(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case '\f':
                ((RNPDFPdfViewManagerInterface) this.mViewManager).setPassword(t, obj != null ? (String) obj : null);
                break;
            case '\r':
                ((RNPDFPdfViewManagerInterface) this.mViewManager).setEnableAntialiasing(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 14:
                ((RNPDFPdfViewManagerInterface) this.mViewManager).setHorizontal(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            case 15:
                ((RNPDFPdfViewManagerInterface) this.mViewManager).setShowsHorizontalScrollIndicator(t, obj != null ? ((Boolean) obj).booleanValue() : false);
                break;
            default:
                super.setProperty(t, str, obj);
                break;
        }
    }

    @Override // com.facebook.react.uimanager.BaseViewManagerDelegate, com.facebook.react.uimanager.ViewManagerDelegate
    public void receiveCommand(T t, String str, ReadableArray readableArray) {
        str.hashCode();
        if (str.equals("setNativePage")) {
            ((RNPDFPdfViewManagerInterface) this.mViewManager).setNativePage(t, readableArray.getInt(0));
        }
    }
}
