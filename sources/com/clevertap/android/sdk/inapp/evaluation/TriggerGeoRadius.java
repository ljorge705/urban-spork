package com.clevertap.android.sdk.inapp.evaluation;

import com.clevertap.android.sdk.Constants;
import kotlin.Metadata;

/* compiled from: TriggerAdapter.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\b\"\u0004\b\f\u0010\nR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\b\"\u0004\b\u000e\u0010\n¨\u0006\u001a"}, d2 = {"Lcom/clevertap/android/sdk/inapp/evaluation/TriggerGeoRadius;", "", "latitude", "", "longitude", Constants.KEY_RADIUS, "(DDD)V", "getLatitude", "()D", "setLatitude", "(D)V", "getLongitude", "setLongitude", "getRadius", "setRadius", "component1", "component2", "component3", Constants.COPY_TYPE, "equals", "", "other", "hashCode", "", "toString", "", "clevertap-core_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes5.dex */
public final /* data */ class TriggerGeoRadius {
    private double latitude;
    private double longitude;
    private double radius;

    public static /* synthetic */ TriggerGeoRadius copy$default(TriggerGeoRadius triggerGeoRadius, double d, double d2, double d3, int i, Object obj) {
        if ((i & 1) != 0) {
            d = triggerGeoRadius.latitude;
        }
        double d4 = d;
        if ((i & 2) != 0) {
            d2 = triggerGeoRadius.longitude;
        }
        double d5 = d2;
        if ((i & 4) != 0) {
            d3 = triggerGeoRadius.radius;
        }
        return triggerGeoRadius.copy(d4, d5, d3);
    }

    /* renamed from: component1, reason: from getter */
    public final double getLatitude() {
        return this.latitude;
    }

    /* renamed from: component2, reason: from getter */
    public final double getLongitude() {
        return this.longitude;
    }

    /* renamed from: component3, reason: from getter */
    public final double getRadius() {
        return this.radius;
    }

    public final TriggerGeoRadius copy(double latitude, double longitude, double radius) {
        return new TriggerGeoRadius(latitude, longitude, radius);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TriggerGeoRadius)) {
            return false;
        }
        TriggerGeoRadius triggerGeoRadius = (TriggerGeoRadius) other;
        return Double.compare(this.latitude, triggerGeoRadius.latitude) == 0 && Double.compare(this.longitude, triggerGeoRadius.longitude) == 0 && Double.compare(this.radius, triggerGeoRadius.radius) == 0;
    }

    public final double getLatitude() {
        return this.latitude;
    }

    public final double getLongitude() {
        return this.longitude;
    }

    public final double getRadius() {
        return this.radius;
    }

    public int hashCode() {
        return (((Double.hashCode(this.latitude) * 31) + Double.hashCode(this.longitude)) * 31) + Double.hashCode(this.radius);
    }

    public final void setLatitude(double d) {
        this.latitude = d;
    }

    public final void setLongitude(double d) {
        this.longitude = d;
    }

    public final void setRadius(double d) {
        this.radius = d;
    }

    public String toString() {
        return "TriggerGeoRadius(latitude=" + this.latitude + ", longitude=" + this.longitude + ", radius=" + this.radius + ')';
    }

    public TriggerGeoRadius(double d, double d2, double d3) {
        this.latitude = d;
        this.longitude = d2;
        this.radius = d3;
    }
}
