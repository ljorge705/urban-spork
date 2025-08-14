package com.google.maps.android.quadtree;

import com.google.maps.android.geometry.Bounds;
import com.google.maps.android.geometry.Point;
import com.google.maps.android.quadtree.PointQuadTree.Item;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* loaded from: classes2.dex */
public class PointQuadTree<T extends Item> {
    private static final int MAX_DEPTH = 40;
    private static final int MAX_ELEMENTS = 50;
    private final Bounds mBounds;
    private List<PointQuadTree<T>> mChildren;
    private final int mDepth;
    private Set<T> mItems;

    public interface Item {
        Point getPoint();
    }

    public PointQuadTree(double d, double d2, double d3, double d4) {
        this(new Bounds(d, d2, d3, d4));
    }

    public PointQuadTree(Bounds bounds) {
        this(bounds, 0);
    }

    private PointQuadTree(double d, double d2, double d3, double d4, int i) {
        this(new Bounds(d, d2, d3, d4), i);
    }

    private PointQuadTree(Bounds bounds, int i) {
        this.mChildren = null;
        this.mBounds = bounds;
        this.mDepth = i;
    }

    public void add(T t) {
        Point point = t.getPoint();
        if (this.mBounds.contains(point.x, point.y)) {
            insert(point.x, point.y, t);
        }
    }

    private void insert(double d, double d2, T t) {
        if (this.mChildren == null) {
            if (this.mItems == null) {
                this.mItems = new LinkedHashSet();
            }
            this.mItems.add(t);
            if (this.mItems.size() <= 50 || this.mDepth >= 40) {
                return;
            }
            split();
            return;
        }
        if (d2 < this.mBounds.midY) {
            if (d < this.mBounds.midX) {
                this.mChildren.get(0).insert(d, d2, t);
                return;
            } else {
                this.mChildren.get(1).insert(d, d2, t);
                return;
            }
        }
        if (d < this.mBounds.midX) {
            this.mChildren.get(2).insert(d, d2, t);
        } else {
            this.mChildren.get(3).insert(d, d2, t);
        }
    }

    private void split() {
        ArrayList arrayList = new ArrayList(4);
        this.mChildren = arrayList;
        arrayList.add(new PointQuadTree(this.mBounds.minX, this.mBounds.midX, this.mBounds.minY, this.mBounds.midY, this.mDepth + 1));
        this.mChildren.add(new PointQuadTree<>(this.mBounds.midX, this.mBounds.maxX, this.mBounds.minY, this.mBounds.midY, this.mDepth + 1));
        this.mChildren.add(new PointQuadTree<>(this.mBounds.minX, this.mBounds.midX, this.mBounds.midY, this.mBounds.maxY, this.mDepth + 1));
        this.mChildren.add(new PointQuadTree<>(this.mBounds.midX, this.mBounds.maxX, this.mBounds.midY, this.mBounds.maxY, this.mDepth + 1));
        Set<T> set = this.mItems;
        this.mItems = null;
        for (T t : set) {
            insert(t.getPoint().x, t.getPoint().y, t);
        }
    }

    public boolean remove(T t) {
        Point point = t.getPoint();
        if (this.mBounds.contains(point.x, point.y)) {
            return remove(point.x, point.y, t);
        }
        return false;
    }

    private boolean remove(double d, double d2, T t) {
        if (this.mChildren == null) {
            Set<T> set = this.mItems;
            if (set == null) {
                return false;
            }
            return set.remove(t);
        }
        if (d2 < this.mBounds.midY) {
            if (d < this.mBounds.midX) {
                return this.mChildren.get(0).remove(d, d2, t);
            }
            return this.mChildren.get(1).remove(d, d2, t);
        }
        if (d < this.mBounds.midX) {
            return this.mChildren.get(2).remove(d, d2, t);
        }
        return this.mChildren.get(3).remove(d, d2, t);
    }

    public void clear() {
        this.mChildren = null;
        Set<T> set = this.mItems;
        if (set != null) {
            set.clear();
        }
    }

    public Collection<T> search(Bounds bounds) {
        ArrayList arrayList = new ArrayList();
        search(bounds, arrayList);
        return arrayList;
    }

    private void search(Bounds bounds, Collection<T> collection) {
        if (this.mBounds.intersects(bounds)) {
            List<PointQuadTree<T>> list = this.mChildren;
            if (list != null) {
                Iterator<PointQuadTree<T>> it = list.iterator();
                while (it.hasNext()) {
                    it.next().search(bounds, collection);
                }
            } else if (this.mItems != null) {
                if (bounds.contains(this.mBounds)) {
                    collection.addAll(this.mItems);
                    return;
                }
                for (T t : this.mItems) {
                    if (bounds.contains(t.getPoint())) {
                        collection.add(t);
                    }
                }
            }
        }
    }
}
