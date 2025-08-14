package com.google.maps.android.geometry;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* loaded from: classes2.dex */
public class Point {
    public final double x;
    public final double y;

    public Point(double d, double d2) {
        this.x = d;
        this.y = d2;
    }

    public String toString() {
        return "Point{x=" + this.x + ", y=" + this.y + AbstractJsonLexerKt.END_OBJ;
    }
}
