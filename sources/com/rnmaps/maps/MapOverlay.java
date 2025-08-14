package com.rnmaps.maps;

import android.content.Context;
import android.graphics.Bitmap;
import com.facebook.react.bridge.ReadableArray;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.maps.android.collections.GroundOverlayManager;

/* loaded from: classes6.dex */
public class MapOverlay extends MapFeature implements ImageReadable {
    private float bearing;
    private LatLngBounds bounds;
    private GroundOverlay groundOverlay;
    private GroundOverlayManager.Collection groundOverlayCollection;
    private GroundOverlayOptions groundOverlayOptions;
    private BitmapDescriptor iconBitmapDescriptor;
    private final ImageReader mImageReader;
    private boolean tappable;
    private float transparency;
    private float zIndex;

    @Override // com.rnmaps.maps.MapFeature
    public Object getFeature() {
        return this.groundOverlay;
    }

    @Override // com.rnmaps.maps.ImageReadable
    public void setIconBitmap(Bitmap bitmap) {
    }

    @Override // com.rnmaps.maps.ImageReadable
    public void setIconBitmapDescriptor(BitmapDescriptor bitmapDescriptor) {
        this.iconBitmapDescriptor = bitmapDescriptor;
    }

    public MapOverlay(Context context) {
        super(context);
        this.mImageReader = new ImageReader(context, getResources(), this);
    }

    public void setBounds(ReadableArray readableArray) {
        LatLngBounds latLngBounds = new LatLngBounds(new LatLng(readableArray.getArray(0).getDouble(0), readableArray.getArray(0).getDouble(1)), new LatLng(readableArray.getArray(1).getDouble(0), readableArray.getArray(1).getDouble(1)));
        this.bounds = latLngBounds;
        GroundOverlay groundOverlay = this.groundOverlay;
        if (groundOverlay != null) {
            groundOverlay.setPositionFromBounds(latLngBounds);
        }
    }

    public void setBearing(float f) {
        this.bearing = f;
        GroundOverlay groundOverlay = this.groundOverlay;
        if (groundOverlay != null) {
            groundOverlay.setBearing(f);
        }
    }

    public void setZIndex(float f) {
        this.zIndex = f;
        GroundOverlay groundOverlay = this.groundOverlay;
        if (groundOverlay != null) {
            groundOverlay.setZIndex(f);
        }
    }

    public void setTransparency(float f) {
        this.transparency = f;
        GroundOverlay groundOverlay = this.groundOverlay;
        if (groundOverlay != null) {
            groundOverlay.setTransparency(f);
        }
    }

    public void setImage(String str) throws NumberFormatException {
        this.mImageReader.setImage(str);
    }

    public void setTappable(boolean z) {
        this.tappable = z;
        GroundOverlay groundOverlay = this.groundOverlay;
        if (groundOverlay != null) {
            groundOverlay.setClickable(z);
        }
    }

    public GroundOverlayOptions getGroundOverlayOptions() {
        if (this.groundOverlayOptions == null) {
            this.groundOverlayOptions = createGroundOverlayOptions();
        }
        return this.groundOverlayOptions;
    }

    private GroundOverlayOptions createGroundOverlayOptions() {
        GroundOverlayOptions groundOverlayOptions = this.groundOverlayOptions;
        if (groundOverlayOptions != null) {
            return groundOverlayOptions;
        }
        GroundOverlayOptions groundOverlayOptions2 = new GroundOverlayOptions();
        BitmapDescriptor bitmapDescriptor = this.iconBitmapDescriptor;
        if (bitmapDescriptor != null) {
            groundOverlayOptions2.image(bitmapDescriptor);
        } else {
            groundOverlayOptions2.image(BitmapDescriptorFactory.defaultMarker());
            groundOverlayOptions2.visible(false);
        }
        groundOverlayOptions2.positionFromBounds(this.bounds);
        groundOverlayOptions2.zIndex(this.zIndex);
        groundOverlayOptions2.bearing(this.bearing);
        groundOverlayOptions2.transparency(this.transparency);
        return groundOverlayOptions2;
    }

    @Override // com.rnmaps.maps.MapFeature
    public void addToMap(Object obj) {
        GroundOverlayManager.Collection collection = (GroundOverlayManager.Collection) obj;
        GroundOverlayOptions groundOverlayOptions = getGroundOverlayOptions();
        if (groundOverlayOptions == null) {
            this.groundOverlayCollection = collection;
            return;
        }
        GroundOverlay groundOverlayAddGroundOverlay = collection.addGroundOverlay(groundOverlayOptions);
        this.groundOverlay = groundOverlayAddGroundOverlay;
        groundOverlayAddGroundOverlay.setClickable(this.tappable);
    }

    @Override // com.rnmaps.maps.MapFeature
    public void removeFromMap(Object obj) {
        GroundOverlay groundOverlay = this.groundOverlay;
        if (groundOverlay != null) {
            ((GroundOverlayManager.Collection) obj).remove(groundOverlay);
            this.groundOverlay = null;
            this.groundOverlayOptions = null;
        }
        this.groundOverlayCollection = null;
    }

    @Override // com.rnmaps.maps.ImageReadable
    public void update() {
        GroundOverlay groundOverlay = getGroundOverlay();
        this.groundOverlay = groundOverlay;
        if (groundOverlay != null) {
            groundOverlay.setVisible(true);
            this.groundOverlay.setImage(this.iconBitmapDescriptor);
            this.groundOverlay.setTransparency(this.transparency);
            this.groundOverlay.setClickable(this.tappable);
        }
    }

    private GroundOverlay getGroundOverlay() {
        GroundOverlayOptions groundOverlayOptions;
        GroundOverlay groundOverlay = this.groundOverlay;
        if (groundOverlay != null) {
            return groundOverlay;
        }
        if (this.groundOverlayCollection == null || (groundOverlayOptions = getGroundOverlayOptions()) == null) {
            return null;
        }
        return this.groundOverlayCollection.addGroundOverlay(groundOverlayOptions);
    }
}
