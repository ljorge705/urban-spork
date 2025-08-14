package com.google.maps.android.data;

import com.google.android.gms.maps.model.LatLng;

/* loaded from: classes2.dex */
public class Point implements Geometry {
    private static final String GEOMETRY_TYPE = "Point";
    private final LatLng mCoordinates;

    @Override // com.google.maps.android.data.Geometry
    public LatLng getGeometryObject() {
        return this.mCoordinates;
    }

    @Override // com.google.maps.android.data.Geometry
    public String getGeometryType() {
        return GEOMETRY_TYPE;
    }

    public Point(LatLng latLng) {
        if (latLng == null) {
            throw new IllegalArgumentException("Coordinates cannot be null");
        }
        this.mCoordinates = latLng;
    }

    public String toString() {
        return "Point{\n coordinates=" + this.mCoordinates + "\n}\n";
    }
}
