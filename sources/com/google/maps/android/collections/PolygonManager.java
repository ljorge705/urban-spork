package com.google.maps.android.collections;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.maps.android.collections.MapObjectManager;
import java.util.Iterator;

/* loaded from: classes2.dex */
public class PolygonManager extends MapObjectManager<Polygon, Collection> implements GoogleMap.OnPolygonClickListener {
    @Override // com.google.maps.android.collections.MapObjectManager
    public /* bridge */ /* synthetic */ MapObjectManager.Collection getCollection(String str) {
        return super.getCollection(str);
    }

    @Override // com.google.maps.android.collections.MapObjectManager
    public /* bridge */ /* synthetic */ MapObjectManager.Collection newCollection(String str) {
        return super.newCollection(str);
    }

    @Override // com.google.maps.android.collections.MapObjectManager
    public /* bridge */ /* synthetic */ boolean remove(Polygon polygon) {
        return super.remove(polygon);
    }

    public PolygonManager(GoogleMap googleMap) {
        super(googleMap);
    }

    @Override // com.google.maps.android.collections.MapObjectManager
    void setListenersOnUiThread() {
        if (this.mMap != null) {
            this.mMap.setOnPolygonClickListener(this);
        }
    }

    @Override // com.google.maps.android.collections.MapObjectManager
    public Collection newCollection() {
        return new Collection();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.google.maps.android.collections.MapObjectManager
    public void removeObjectFromMap(Polygon polygon) {
        polygon.remove();
    }

    @Override // com.google.android.gms.maps.GoogleMap.OnPolygonClickListener
    public void onPolygonClick(Polygon polygon) {
        Collection collection = (Collection) this.mAllObjects.get(polygon);
        if (collection == null || collection.mPolygonClickListener == null) {
            return;
        }
        collection.mPolygonClickListener.onPolygonClick(polygon);
    }

    public class Collection extends MapObjectManager.Collection {
        private GoogleMap.OnPolygonClickListener mPolygonClickListener;

        public void setOnPolygonClickListener(GoogleMap.OnPolygonClickListener onPolygonClickListener) {
            this.mPolygonClickListener = onPolygonClickListener;
        }

        public Collection() {
            super();
        }

        public Polygon addPolygon(PolygonOptions polygonOptions) {
            Polygon polygonAddPolygon = PolygonManager.this.mMap.addPolygon(polygonOptions);
            super.add(polygonAddPolygon);
            return polygonAddPolygon;
        }

        public void addAll(java.util.Collection<PolygonOptions> collection) {
            Iterator<PolygonOptions> it = collection.iterator();
            while (it.hasNext()) {
                addPolygon(it.next());
            }
        }

        public void addAll(java.util.Collection<PolygonOptions> collection, boolean z) {
            Iterator<PolygonOptions> it = collection.iterator();
            while (it.hasNext()) {
                addPolygon(it.next()).setVisible(z);
            }
        }

        public void showAll() {
            Iterator<Polygon> it = getPolygons().iterator();
            while (it.hasNext()) {
                it.next().setVisible(true);
            }
        }

        public void hideAll() {
            Iterator<Polygon> it = getPolygons().iterator();
            while (it.hasNext()) {
                it.next().setVisible(false);
            }
        }

        public boolean remove(Polygon polygon) {
            return super.remove((Collection) polygon);
        }

        public java.util.Collection<Polygon> getPolygons() {
            return getObjects();
        }
    }
}
