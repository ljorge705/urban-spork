package com.google.maps.android.data.geojson;

import com.google.maps.android.data.Geometry;
import com.google.maps.android.data.MultiGeometry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class GeoJsonMultiPolygon extends MultiGeometry {
    public GeoJsonMultiPolygon(List<GeoJsonPolygon> list) {
        super(list);
        setGeometryType("MultiPolygon");
    }

    public String getType() {
        return getGeometryType();
    }

    public List<GeoJsonPolygon> getPolygons() {
        List geometryObject = getGeometryObject();
        ArrayList arrayList = new ArrayList();
        Iterator it = geometryObject.iterator();
        while (it.hasNext()) {
            arrayList.add((GeoJsonPolygon) ((Geometry) it.next()));
        }
        return arrayList;
    }
}
