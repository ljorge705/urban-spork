package com.onfido.android.sdk.capture.ui.camera;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/Frame;", "", "width", "", "height", "left", "top", "(IIII)V", "getHeight", "()I", "getLeft", "getTop", "getWidth", "component1", "component2", "component3", "component4", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "toString", "", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public final /* data */ class Frame {
    private final int height;
    private final int left;
    private final int top;
    private final int width;

    public Frame(int i, int i2, int i3, int i4) {
        this.width = i;
        this.height = i2;
        this.left = i3;
        this.top = i4;
    }

    public static /* synthetic */ Frame copy$default(Frame frame, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = frame.width;
        }
        if ((i5 & 2) != 0) {
            i2 = frame.height;
        }
        if ((i5 & 4) != 0) {
            i3 = frame.left;
        }
        if ((i5 & 8) != 0) {
            i4 = frame.top;
        }
        return frame.copy(i, i2, i3, i4);
    }

    /* renamed from: component1, reason: from getter */
    public final int getWidth() {
        return this.width;
    }

    /* renamed from: component2, reason: from getter */
    public final int getHeight() {
        return this.height;
    }

    /* renamed from: component3, reason: from getter */
    public final int getLeft() {
        return this.left;
    }

    /* renamed from: component4, reason: from getter */
    public final int getTop() {
        return this.top;
    }

    public final Frame copy(int width, int height, int left, int top) {
        return new Frame(width, height, left, top);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Frame)) {
            return false;
        }
        Frame frame = (Frame) other;
        return this.width == frame.width && this.height == frame.height && this.left == frame.left && this.top == frame.top;
    }

    public final int getHeight() {
        return this.height;
    }

    public final int getLeft() {
        return this.left;
    }

    public final int getTop() {
        return this.top;
    }

    public final int getWidth() {
        return this.width;
    }

    public int hashCode() {
        return (((((Integer.hashCode(this.width) * 31) + Integer.hashCode(this.height)) * 31) + Integer.hashCode(this.left)) * 31) + Integer.hashCode(this.top);
    }

    public String toString() {
        return "Frame(width=" + this.width + ", height=" + this.height + ", left=" + this.left + ", top=" + this.top + ')';
    }
}
