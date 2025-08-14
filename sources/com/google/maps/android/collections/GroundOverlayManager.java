package com.google.maps.android.collections;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.maps.android.collections.MapObjectManager;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class GroundOverlayManager extends MapObjectManager<GroundOverlay, Collection> implements GoogleMap.OnGroundOverlayClickListener {
    @Override // com.google.maps.android.collections.MapObjectManager
    public /* bridge */ /* synthetic */ MapObjectManager.Collection getCollection(String str) {
        return super.getCollection(str);
    }

    @Override // com.google.maps.android.collections.MapObjectManager
    public /* bridge */ /* synthetic */ MapObjectManager.Collection newCollection(String str) {
        return super.newCollection(str);
    }

    @Override // com.google.maps.android.collections.MapObjectManager
    public /* bridge */ /* synthetic */ boolean remove(GroundOverlay groundOverlay) {
        return super.remove(groundOverlay);
    }

    public GroundOverlayManager(GoogleMap googleMap) {
        super(googleMap);
    }

    @Override // com.google.maps.android.collections.MapObjectManager
    void setListenersOnUiThread() {
        if (this.mMap != null) {
            this.mMap.setOnGroundOverlayClickListener(this);
        }
    }

    @Override // com.google.maps.android.collections.MapObjectManager
    public Collection newCollection() {
        return new Collection();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.maps.android.collections.MapObjectManager
    public void removeObjectFromMap(GroundOverlay groundOverlay) {
        groundOverlay.remove();
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnGroundOverlayClickListener
    public void onGroundOverlayClick(GroundOverlay groundOverlay) {
        Collection collection = (Collection) this.mAllObjects.get(groundOverlay);
        if (collection == null || collection.mGroundOverlayClickListener == null) {
            return;
        }
        collection.mGroundOverlayClickListener.onGroundOverlayClick(groundOverlay);
    }

    public class Collection extends MapObjectManager.Collection {
        private GoogleMap.OnGroundOverlayClickListener mGroundOverlayClickListener;

        public void setOnGroundOverlayClickListener(GoogleMap.OnGroundOverlayClickListener onGroundOverlayClickListener) {
            this.mGroundOverlayClickListener = onGroundOverlayClickListener;
        }

        public Collection() {
            super();
        }

        public GroundOverlay addGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
            GroundOverlay groundOverlayAddGroundOverlay = GroundOverlayManager.this.mMap.addGroundOverlay(groundOverlayOptions);
            super.add(groundOverlayAddGroundOverlay);
            return groundOverlayAddGroundOverlay;
        }

        public void addAll(java.util.Collection<GroundOverlayOptions> collection) {
            Iterator<GroundOverlayOptions> it = collection.iterator();
            while (it.hasNext()) {
                addGroundOverlay(it.next());
            }
        }

        public void addAll(java.util.Collection<GroundOverlayOptions> collection, boolean z) {
            Iterator<GroundOverlayOptions> it = collection.iterator();
            while (it.hasNext()) {
                addGroundOverlay(it.next()).setVisible(z);
            }
        }

        public void showAll() {
            Iterator<GroundOverlay> it = getGroundOverlays().iterator();
            while (it.hasNext()) {
                it.next().setVisible(true);
            }
        }

        public void hideAll() {
            Iterator<GroundOverlay> it = getGroundOverlays().iterator();
            while (it.hasNext()) {
                it.next().setVisible(false);
            }
        }

        public boolean remove(GroundOverlay groundOverlay) {
            return super.remove((Collection) groundOverlay);
        }

        public java.util.Collection<GroundOverlay> getGroundOverlays() {
            return getObjects();
        }
    }
}
