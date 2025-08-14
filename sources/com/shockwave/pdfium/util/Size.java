package com.shockwave.pdfium.util;

/* loaded from: classes6.dex */
public class Size {
    private final int height;
    private final int width;

    public int getHeight() {
        return this.height;
    }

    public int getWidth() {
        return this.width;
    }

    public int hashCode() {
        int i = this.height;
        int i2 = this.width;
        return i ^ ((i2 >>> 16) | (i2 << 16));
    }

    public Size(int i, int i2) {
        this.width = i;
        this.height = i2;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Size)) {
            return false;
        }
        Size size = (Size) obj;
        return this.width == size.width && this.height == size.height;
    }

    public String toString() {
        return this.width + "x" + this.height;
    }
}
