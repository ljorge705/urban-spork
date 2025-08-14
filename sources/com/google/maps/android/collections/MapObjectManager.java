package com.google.maps.android.collections;

import android.os.Handler;
import android.os.Looper;
import com.google.android.gms.maps.GoogleMap;
import com.google.maps.android.collections.MapObjectManager.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes2.dex */
public abstract class MapObjectManager<O, C extends Collection> {
    protected final GoogleMap mMap;
    private final Map<String, C> mNamedCollections = new HashMap();
    protected final Map<O, C> mAllObjects = new HashMap();

    public abstract C newCollection();

    protected abstract void removeObjectFromMap(O o);

    abstract void setListenersOnUiThread();

    public MapObjectManager(GoogleMap googleMap) {
        this.mMap = googleMap;
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.google.maps.android.collections.MapObjectManager.1
            @Override // java.lang.Runnable
            public void run() {
                MapObjectManager.this.setListenersOnUiThread();
            }
        });
    }

    public C newCollection(String str) {
        if (this.mNamedCollections.get(str) != null) {
            throw new IllegalArgumentException("collection id is not unique: " + str);
        }
        C c = (C) newCollection();
        this.mNamedCollections.put(str, c);
        return c;
    }

    public C getCollection(String str) {
        return this.mNamedCollections.get(str);
    }

    public boolean remove(O o) {
        C c = this.mAllObjects.get(o);
        return c != null && c.remove(o);
    }

    public class Collection {
        private final Set<O> mObjects = new LinkedHashSet();

        public Collection() {
        }

        protected void add(O o) {
            this.mObjects.add(o);
            MapObjectManager.this.mAllObjects.put(o, this);
        }

        protected boolean remove(O o) {
            if (!this.mObjects.remove(o)) {
                return false;
            }
            MapObjectManager.this.mAllObjects.remove(o);
            MapObjectManager.this.removeObjectFromMap(o);
            return true;
        }

        public void clear() {
            for (O o : this.mObjects) {
                MapObjectManager.this.removeObjectFromMap(o);
                MapObjectManager.this.mAllObjects.remove(o);
            }
            this.mObjects.clear();
        }

        protected java.util.Collection<O> getObjects() {
            return Collections.unmodifiableCollection(this.mObjects);
        }
    }
}
