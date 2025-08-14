package com.google.maps.android.clustering.algo;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.projection.SphericalMercatorProjection;
import com.google.maps.android.quadtree.PointQuadTree;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public class NonHierarchicalDistanceBasedAlgorithm<T extends ClusterItem> extends AbstractAlgorithm<T> {
    private static final int DEFAULT_MAX_DISTANCE_AT_ZOOM = 100;
    private static final SphericalMercatorProjection PROJECTION = new SphericalMercatorProjection(1.0d);
    private int mMaxDistance = 100;
    private final Collection<QuadItem<T>> mItems = new LinkedHashSet();
    private final PointQuadTree<QuadItem<T>> mQuadTree = new PointQuadTree<>(0.0d, 1.0d, 0.0d, 1.0d);

    protected Collection<QuadItem<T>> getClusteringItems(PointQuadTree<QuadItem<T>> pointQuadTree, float f) {
        return this.mItems;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public int getMaxDistanceBetweenClusteredItems() {
        return this.mMaxDistance;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public void setMaxDistanceBetweenClusteredItems(int i) {
        this.mMaxDistance = i;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean addItem(T t) {
        boolean zAdd;
        QuadItem<T> quadItem = new QuadItem<>(t);
        synchronized (this.mQuadTree) {
            zAdd = this.mItems.add(quadItem);
            if (zAdd) {
                this.mQuadTree.add(quadItem);
            }
        }
        return zAdd;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean addItems(Collection<T> collection) {
        Iterator<T> it = collection.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (addItem(it.next())) {
                z = true;
            }
        }
        return z;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public void clearItems() {
        synchronized (this.mQuadTree) {
            this.mItems.clear();
            this.mQuadTree.clear();
        }
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean removeItem(T t) {
        boolean zRemove;
        QuadItem quadItem = new QuadItem(t);
        synchronized (this.mQuadTree) {
            zRemove = this.mItems.remove(quadItem);
            if (zRemove) {
                this.mQuadTree.remove(quadItem);
            }
        }
        return zRemove;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean removeItems(Collection<T> collection) {
        boolean z;
        synchronized (this.mQuadTree) {
            Iterator<T> it = collection.iterator();
            z = false;
            while (it.hasNext()) {
                QuadItem quadItem = new QuadItem(it.next());
                if (this.mItems.remove(quadItem)) {
                    this.mQuadTree.remove(quadItem);
                    z = true;
                }
            }
        }
        return z;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public boolean updateItem(T t) {
        boolean zRemoveItem;
        synchronized (this.mQuadTree) {
            zRemoveItem = removeItem(t);
            if (zRemoveItem) {
                zRemoveItem = addItem(t);
            }
        }
        return zRemoveItem;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.maps.android.clustering.algo.Algorithm
    public Set<? extends Cluster<T>> getClusters(float f) {
        double dPow = (this.mMaxDistance / Math.pow(2.0d, (int) f)) / 256.0d;
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        synchronized (this.mQuadTree) {
            Iterator<QuadItem<T>> it = getClusteringItems(this.mQuadTree, f).iterator();
            while (it.hasNext()) {
                QuadItem<T> next = it.next();
                if (!hashSet.contains(next)) {
                    Collection<T> collectionSearch = this.mQuadTree.search(createBoundsFromSpan(next.getPoint(), dPow));
                    if (collectionSearch.size() == 1) {
                        hashSet2.add(next);
                        hashSet.add(next);
                        map.put(next, Double.valueOf(0.0d));
                    } else {
                        StaticCluster staticCluster = new StaticCluster(((QuadItem) next).mClusterItem.getPosition());
                        hashSet2.add(staticCluster);
                        for (T t : collectionSearch) {
                            Double d = (Double) map.get(t);
                            Iterator<QuadItem<T>> it2 = it;
                            double dDistanceSquared = distanceSquared(t.getPoint(), next.getPoint());
                            if (d == null) {
                                map.put(t, Double.valueOf(dDistanceSquared));
                                staticCluster.add(t.mClusterItem);
                                map2.put(t, staticCluster);
                            } else if (d.doubleValue() >= dDistanceSquared) {
                                ((StaticCluster) map2.get(t)).remove(t.mClusterItem);
                                map.put(t, Double.valueOf(dDistanceSquared));
                                staticCluster.add(t.mClusterItem);
                                map2.put(t, staticCluster);
                            }
                            it = it2;
                        }
                        hashSet.addAll(collectionSearch);
                        it = it;
                    }
                }
            }
        }
        return hashSet2;
    }

    @Override // com.google.maps.android.clustering.algo.Algorithm
    public Collection<T> getItems() {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        synchronized (this.mQuadTree) {
            Iterator<QuadItem<T>> it = this.mItems.iterator();
            while (it.hasNext()) {
                linkedHashSet.add(((QuadItem) it.next()).mClusterItem);
            }
        }
        return linkedHashSet;
    }

    private double distanceSquared(Point point, Point point2) {
        return ((point.x - point2.x) * (point.x - point2.x)) + ((point.y - point2.y) * (point.y - point2.y));
    }

    private Bounds createBoundsFromSpan(Point point, double d) {
        double d2 = d / 2.0d;
        return new Bounds(point.x - d2, point.x + d2, point.y - d2, point.y + d2);
    }

    protected static class QuadItem<T extends ClusterItem> implements PointQuadTree.Item, Cluster<T> {
        private final T mClusterItem;
        private final Point mPoint;
        private final LatLng mPosition;
        private Set<T> singletonSet;

        @Override // com.google.maps.android.clustering.Cluster
        public Set<T> getItems() {
            return this.singletonSet;
        }

        @Override // com.google.maps.android.quadtree.PointQuadTree.Item
        public Point getPoint() {
            return this.mPoint;
        }

        @Override // com.google.maps.android.clustering.Cluster
        public LatLng getPosition() {
            return this.mPosition;
        }

        @Override // com.google.maps.android.clustering.Cluster
        public int getSize() {
            return 1;
        }

        private QuadItem(T t) {
            this.mClusterItem = t;
            LatLng position = t.getPosition();
            this.mPosition = position;
            this.mPoint = NonHierarchicalDistanceBasedAlgorithm.PROJECTION.toPoint(position);
            this.singletonSet = Collections.singleton(t);
        }

        public int hashCode() {
            return this.mClusterItem.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj instanceof QuadItem) {
                return ((QuadItem) obj).mClusterItem.equals(this.mClusterItem);
            }
            return false;
        }
    }
}
