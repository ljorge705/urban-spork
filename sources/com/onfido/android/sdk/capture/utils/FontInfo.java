package com.onfido.android.sdk.capture.utils;

import android.graphics.Typeface;
import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J!\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/onfido/android/sdk/capture/utils/FontInfo;", "", "typeface", "Landroid/graphics/Typeface;", "fontName", "", "(Landroid/graphics/Typeface;Ljava/lang/String;)V", "getFontName", "()Ljava/lang/String;", "getTypeface", "()Landroid/graphics/Typeface;", "component1", "component2", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class FontInfo {
    private final String fontName;
    private final Typeface typeface;

    public FontInfo(Typeface typeface, String str) {
        this.typeface = typeface;
        this.fontName = str;
    }

    public static /* synthetic */ FontInfo copy$default(FontInfo fontInfo, Typeface typeface, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            typeface = fontInfo.typeface;
        }
        if ((i & 2) != 0) {
            str = fontInfo.fontName;
        }
        return fontInfo.copy(typeface, str);
    }

    /* renamed from: component1, reason: from getter */
    public final Typeface getTypeface() {
        return this.typeface;
    }

    /* renamed from: component2, reason: from getter */
    public final String getFontName() {
        return this.fontName;
    }

    public final FontInfo copy(Typeface typeface, String fontName) {
        return new FontInfo(typeface, fontName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FontInfo)) {
            return false;
        }
        FontInfo fontInfo = (FontInfo) other;
        return Intrinsics.areEqual(this.typeface, fontInfo.typeface) && Intrinsics.areEqual(this.fontName, fontInfo.fontName);
    }

    public final String getFontName() {
        return this.fontName;
    }

    public final Typeface getTypeface() {
        return this.typeface;
    }

    public int hashCode() {
        Typeface typeface = this.typeface;
        int iHashCode = (typeface == null ? 0 : typeface.hashCode()) * 31;
        String str = this.fontName;
        return iHashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "FontInfo(typeface=" + this.typeface + ", fontName=" + this.fontName + ')';
    }
}
