package com.rnmaps.maps;

import android.content.Context;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.collections.CircleManager;

/* loaded from: classes6.dex */
public class MapCircle extends MapFeature {
    private LatLng center;
    private Circle circle;
    private CircleOptions circleOptions;
    private int fillColor;
    private double radius;
    private int strokeColor;
    private float strokeWidth;
    private float zIndex;

    @Override // com.rnmaps.maps.MapFeature
    public Object getFeature() {
        return this.circle;
    }

    public MapCircle(Context context) {
        super(context);
    }

    public void setCenter(LatLng latLng) {
        this.center = latLng;
        Circle circle = this.circle;
        if (circle != null) {
            circle.setCenter(latLng);
        }
    }

    public void setRadius(double d) {
        this.radius = d;
        Circle circle = this.circle;
        if (circle != null) {
            circle.setRadius(d);
        }
    }

    public void setFillColor(int i) {
        this.fillColor = i;
        Circle circle = this.circle;
        if (circle != null) {
            circle.setFillColor(i);
        }
    }

    public void setStrokeColor(int i) {
        this.strokeColor = i;
        Circle circle = this.circle;
        if (circle != null) {
            circle.setStrokeColor(i);
        }
    }

    public void setStrokeWidth(float f) {
        this.strokeWidth = f;
        Circle circle = this.circle;
        if (circle != null) {
            circle.setStrokeWidth(f);
        }
    }

    public void setZIndex(float f) {
        this.zIndex = f;
        Circle circle = this.circle;
        if (circle != null) {
            circle.setZIndex(f);
        }
    }

    public CircleOptions getCircleOptions() {
        if (this.circleOptions == null) {
            this.circleOptions = createCircleOptions();
        }
        return this.circleOptions;
    }

    private CircleOptions createCircleOptions() {
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center(this.center);
        circleOptions.radius(this.radius);
        circleOptions.fillColor(this.fillColor);
        circleOptions.strokeColor(this.strokeColor);
        circleOptions.strokeWidth(this.strokeWidth);
        circleOptions.zIndex(this.zIndex);
        return circleOptions;
    }

    @Override // com.rnmaps.maps.MapFeature
    public void addToMap(Object obj) {
        this.circle = ((CircleManager.Collection) obj).addCircle(getCircleOptions());
    }

    @Override // com.rnmaps.maps.MapFeature
    public void removeFromMap(Object obj) {
        ((CircleManager.Collection) obj).remove(this.circle);
    }
}
