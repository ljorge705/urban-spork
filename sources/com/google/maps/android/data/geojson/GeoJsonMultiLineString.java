package com.google.maps.android.data.geojson;

import com.google.maps.android.data.Geometry;
import com.google.maps.android.data.MultiGeometry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class GeoJsonMultiLineString extends MultiGeometry {
    public GeoJsonMultiLineString(List<GeoJsonLineString> list) {
        super(list);
        setGeometryType("MultiLineString");
    }

    public String getType() {
        return getGeometryType();
    }

    public List<GeoJsonLineString> getLineStrings() {
        List geometryObject = getGeometryObject();
        ArrayList arrayList = new ArrayList();
        Iterator it = geometryObject.iterator();
        while (it.hasNext()) {
            arrayList.add((GeoJsonLineString) ((Geometry) it.next()));
        }
        return arrayList;
    }
}
