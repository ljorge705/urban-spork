package com.rnmaps.maps;

import android.content.Context;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.google.android.gms.maps.model.Cap;
import com.google.android.gms.maps.model.Dash;
import com.google.android.gms.maps.model.Dot;
import com.google.android.gms.maps.model.Gap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;
import com.google.maps.android.collections.PolylineManager;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class MapPolyline extends MapFeature {
    private int color;
    private List<LatLng> coordinates;
    private boolean geodesic;
    private Cap lineCap;
    private List<PatternItem> pattern;
    private ReadableArray patternValues;
    private Polyline polyline;
    private PolylineOptions polylineOptions;
    private boolean tappable;
    private float width;
    private float zIndex;

    @Override // com.rnmaps.maps.MapFeature
    public Object getFeature() {
        return this.polyline;
    }

    public MapPolyline(Context context) {
        super(context);
        this.lineCap = new RoundCap();
    }

    public void setCoordinates(ReadableArray readableArray) {
        this.coordinates = new ArrayList(readableArray.size());
        for (int i = 0; i < readableArray.size(); i++) {
            ReadableMap map = readableArray.getMap(i);
            this.coordinates.add(i, new LatLng(map.getDouble("latitude"), map.getDouble("longitude")));
        }
        Polyline polyline = this.polyline;
        if (polyline != null) {
            polyline.setPoints(this.coordinates);
        }
    }

    public void setColor(int i) {
        this.color = i;
        Polyline polyline = this.polyline;
        if (polyline != null) {
            polyline.setColor(i);
        }
    }

    public void setWidth(float f) {
        this.width = f;
        Polyline polyline = this.polyline;
        if (polyline != null) {
            polyline.setWidth(f);
        }
    }

    public void setZIndex(float f) {
        this.zIndex = f;
        Polyline polyline = this.polyline;
        if (polyline != null) {
            polyline.setZIndex(f);
        }
    }

    public void setTappable(boolean z) {
        this.tappable = z;
        Polyline polyline = this.polyline;
        if (polyline != null) {
            polyline.setClickable(z);
        }
    }

    public void setGeodesic(boolean z) {
        this.geodesic = z;
        Polyline polyline = this.polyline;
        if (polyline != null) {
            polyline.setGeodesic(z);
        }
    }

    public void setLineCap(Cap cap) {
        this.lineCap = cap;
        Polyline polyline = this.polyline;
        if (polyline != null) {
            polyline.setStartCap(cap);
            this.polyline.setEndCap(cap);
        }
        applyPattern();
    }

    public void setLineDashPattern(ReadableArray readableArray) {
        this.patternValues = readableArray;
        applyPattern();
    }

    private void applyPattern() {
        PatternItem dash;
        if (this.patternValues == null) {
            return;
        }
        this.pattern = new ArrayList(this.patternValues.size());
        for (int i = 0; i < this.patternValues.size(); i++) {
            float f = (float) this.patternValues.getDouble(i);
            if (i % 2 != 0) {
                this.pattern.add(new Gap(f));
            } else {
                if (this.lineCap instanceof RoundCap) {
                    dash = new Dot();
                } else {
                    dash = new Dash(f);
                }
                this.pattern.add(dash);
            }
        }
        Polyline polyline = this.polyline;
        if (polyline != null) {
            polyline.setPattern(this.pattern);
        }
    }

    public PolylineOptions getPolylineOptions() {
        if (this.polylineOptions == null) {
            this.polylineOptions = createPolylineOptions();
        }
        return this.polylineOptions;
    }

    private PolylineOptions createPolylineOptions() {
        PolylineOptions polylineOptions = new PolylineOptions();
        polylineOptions.addAll(this.coordinates);
        polylineOptions.color(this.color);
        polylineOptions.width(this.width);
        polylineOptions.geodesic(this.geodesic);
        polylineOptions.zIndex(this.zIndex);
        polylineOptions.startCap(this.lineCap);
        polylineOptions.endCap(this.lineCap);
        polylineOptions.pattern(this.pattern);
        return polylineOptions;
    }

    @Override // com.rnmaps.maps.MapFeature
    public void addToMap(Object obj) {
        Polyline polylineAddPolyline = ((PolylineManager.Collection) obj).addPolyline(getPolylineOptions());
        this.polyline = polylineAddPolyline;
        polylineAddPolyline.setClickable(this.tappable);
    }

    @Override // com.rnmaps.maps.MapFeature
    public void removeFromMap(Object obj) {
        ((PolylineManager.Collection) obj).remove(this.polyline);
    }
}
