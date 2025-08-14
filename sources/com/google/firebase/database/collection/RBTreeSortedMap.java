package com.google.firebase.database.collection;

import com.google.firebase.database.collection.ImmutableSortedMap;
import com.google.firebase.database.collection.LLRBNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class RBTreeSortedMap<K, V> extends ImmutableSortedMap<K, V> {
    private Comparator<K> comparator;
    private LLRBNode<K, V> root;

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public Comparator<K> getComparator() {
        return this.comparator;
    }

    LLRBNode<K, V> getRoot() {
        return this.root;
    }

    RBTreeSortedMap(Comparator<K> comparator) {
        this.root = LLRBEmptyNode.getInstance();
        this.comparator = comparator;
    }

    private RBTreeSortedMap(LLRBNode<K, V> lLRBNode, Comparator<K> comparator) {
        this.root = lLRBNode;
        this.comparator = comparator;
    }

    private LLRBNode<K, V> getNode(K k) {
        LLRBNode<K, V> left = this.root;
        while (!left.isEmpty()) {
            int iCompare = this.comparator.compare(k, left.getKey());
            if (iCompare < 0) {
                left = left.getLeft();
            } else {
                if (iCompare == 0) {
                    return left;
                }
                left = left.getRight();
            }
        }
        return null;
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public boolean containsKey(K k) {
        return getNode(k) != null;
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public V get(K k) {
        LLRBNode<K, V> node = getNode(k);
        if (node != null) {
            return node.getValue();
        }
        return null;
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public ImmutableSortedMap<K, V> remove(K k) {
        return !containsKey(k) ? this : new RBTreeSortedMap(this.root.remove(k, this.comparator).copy(null, null, LLRBNode.Color.BLACK, null, null), this.comparator);
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public ImmutableSortedMap<K, V> insert(K k, V v) {
        return new RBTreeSortedMap(this.root.insert(k, v, this.comparator).copy(null, null, LLRBNode.Color.BLACK, null, null), this.comparator);
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public K getMinKey() {
        return this.root.getMin().getKey();
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public K getMaxKey() {
        return this.root.getMax().getKey();
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public int size() {
        return this.root.size();
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public boolean isEmpty() {
        return this.root.isEmpty();
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public void inOrderTraversal(LLRBNode.NodeVisitor<K, V> nodeVisitor) {
        this.root.inOrderTraversal(nodeVisitor);
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap, java.lang.Iterable
    public Iterator<Map.Entry<K, V>> iterator() {
        return new ImmutableSortedMapIterator(this.root, null, this.comparator, false);
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public Iterator<Map.Entry<K, V>> iteratorFrom(K k) {
        return new ImmutableSortedMapIterator(this.root, k, this.comparator, false);
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public Iterator<Map.Entry<K, V>> reverseIteratorFrom(K k) {
        return new ImmutableSortedMapIterator(this.root, k, this.comparator, true);
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public Iterator<Map.Entry<K, V>> reverseIterator() {
        return new ImmutableSortedMapIterator(this.root, null, this.comparator, true);
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public K getPredecessorKey(K k) {
        LLRBNode<K, V> left = this.root;
        LLRBNode<K, V> lLRBNode = null;
        while (!left.isEmpty()) {
            int iCompare = this.comparator.compare(k, left.getKey());
            if (iCompare == 0) {
                if (left.getLeft().isEmpty()) {
                    if (lLRBNode != null) {
                        return lLRBNode.getKey();
                    }
                    return null;
                }
                LLRBNode<K, V> left2 = left.getLeft();
                while (!left2.getRight().isEmpty()) {
                    left2 = left2.getRight();
                }
                return left2.getKey();
            }
            if (iCompare < 0) {
                left = left.getLeft();
            } else {
                lLRBNode = left;
                left = left.getRight();
            }
        }
        throw new IllegalArgumentException("Couldn't find predecessor key of non-present key: " + k);
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public K getSuccessorKey(K k) {
        LLRBNode<K, V> right = this.root;
        LLRBNode<K, V> lLRBNode = null;
        while (!right.isEmpty()) {
            int iCompare = this.comparator.compare(right.getKey(), k);
            if (iCompare == 0) {
                if (right.getRight().isEmpty()) {
                    if (lLRBNode != null) {
                        return lLRBNode.getKey();
                    }
                    return null;
                }
                LLRBNode<K, V> right2 = right.getRight();
                while (!right2.getLeft().isEmpty()) {
                    right2 = right2.getLeft();
                }
                return right2.getKey();
            }
            if (iCompare < 0) {
                right = right.getRight();
            } else {
                lLRBNode = right;
                right = right.getLeft();
            }
        }
        throw new IllegalArgumentException("Couldn't find successor key of non-present key: " + k);
    }

    @Override // com.google.firebase.database.collection.ImmutableSortedMap
    public int indexOf(K k) {
        LLRBNode<K, V> left = this.root;
        int size = 0;
        while (!left.isEmpty()) {
            int iCompare = this.comparator.compare(k, left.getKey());
            if (iCompare == 0) {
                return size + left.getLeft().size();
            }
            if (iCompare < 0) {
                left = left.getLeft();
            } else {
                size += left.getLeft().size() + 1;
                left = left.getRight();
            }
        }
        return -1;
    }

    public static <A, B, C> RBTreeSortedMap<A, C> buildFrom(List<A> list, Map<B, C> map, ImmutableSortedMap.Builder.KeyTranslator<A, B> keyTranslator, Comparator<A> comparator) {
        return Builder.buildFrom(list, map, keyTranslator, comparator);
    }

    public static <A, B> RBTreeSortedMap<A, B> fromMap(Map<A, B> map, Comparator<A> comparator) {
        return Builder.buildFrom(new ArrayList(map.keySet()), map, ImmutableSortedMap.Builder.identityTranslator(), comparator);
    }

    private static class Builder<A, B, C> {
        private final ImmutableSortedMap.Builder.KeyTranslator<A, B> keyTranslator;
        private final List<A> keys;
        private LLRBValueNode<A, C> leaf;
        private LLRBValueNode<A, C> root;
        private final Map<B, C> values;

        static class BooleanChunk {
            public int chunkSize;
            public boolean isOne;

            BooleanChunk() {
            }
        }

        static class Base1_2 implements Iterable<BooleanChunk> {
            private final int length;
            private long value;

            public Base1_2(int i) {
                int i2 = i + 1;
                int iFloor = (int) Math.floor(Math.log(i2) / Math.log(2.0d));
                this.length = iFloor;
                this.value = (((long) Math.pow(2.0d, iFloor)) - 1) & i2;
            }

            @Override // java.lang.Iterable
            public Iterator<BooleanChunk> iterator() {
                return new Iterator<BooleanChunk>() { // from class: com.google.firebase.database.collection.RBTreeSortedMap.Builder.Base1_2.1
                    private int current;

                    @Override // java.util.Iterator
                    public boolean hasNext() {
                        return this.current >= 0;
                    }

                    @Override // java.util.Iterator
                    public void remove() {
                    }

                    {
                        this.current = Base1_2.this.length - 1;
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // java.util.Iterator
                    public BooleanChunk next() {
                        long j = Base1_2.this.value & (1 << this.current);
                        BooleanChunk booleanChunk = new BooleanChunk();
                        booleanChunk.isOne = j == 0;
                        booleanChunk.chunkSize = (int) Math.pow(2.0d, this.current);
                        this.current--;
                        return booleanChunk;
                    }
                };
            }
        }

        private Builder(List<A> list, Map<B, C> map, ImmutableSortedMap.Builder.KeyTranslator<A, B> keyTranslator) {
            this.keys = list;
            this.values = map;
            this.keyTranslator = keyTranslator;
        }

        private C getValue(A a2) {
            return this.values.get(this.keyTranslator.translate(a2));
        }

        private LLRBNode<A, C> buildBalancedTree(int i, int i2) {
            if (i2 == 0) {
                return LLRBEmptyNode.getInstance();
            }
            if (i2 == 1) {
                A a2 = this.keys.get(i);
                return new LLRBBlackValueNode(a2, getValue(a2), null, null);
            }
            int i3 = i2 / 2;
            int i4 = i + i3;
            LLRBNode<A, C> lLRBNodeBuildBalancedTree = buildBalancedTree(i, i3);
            LLRBNode<A, C> lLRBNodeBuildBalancedTree2 = buildBalancedTree(i4 + 1, i3);
            A a3 = this.keys.get(i4);
            return new LLRBBlackValueNode(a3, getValue(a3), lLRBNodeBuildBalancedTree, lLRBNodeBuildBalancedTree2);
        }

        private void buildPennant(LLRBNode.Color color, int i, int i2) {
            LLRBValueNode<A, C> lLRBBlackValueNode;
            LLRBNode<A, C> lLRBNodeBuildBalancedTree = buildBalancedTree(i2 + 1, i - 1);
            A a2 = this.keys.get(i2);
            if (color == LLRBNode.Color.RED) {
                lLRBBlackValueNode = new LLRBRedValueNode<>(a2, getValue(a2), null, lLRBNodeBuildBalancedTree);
            } else {
                lLRBBlackValueNode = new LLRBBlackValueNode<>(a2, getValue(a2), null, lLRBNodeBuildBalancedTree);
            }
            if (this.root == null) {
                this.root = lLRBBlackValueNode;
                this.leaf = lLRBBlackValueNode;
            } else {
                this.leaf.setLeft(lLRBBlackValueNode);
                this.leaf = lLRBBlackValueNode;
            }
        }

        public static <A, B, C> RBTreeSortedMap<A, C> buildFrom(List<A> list, Map<B, C> map, ImmutableSortedMap.Builder.KeyTranslator<A, B> keyTranslator, Comparator<A> comparator) {
            Builder builder = new Builder(list, map, keyTranslator);
            Collections.sort(list, comparator);
            Iterator<BooleanChunk> it = new Base1_2(list.size()).iterator();
            int size = list.size();
            while (it.hasNext()) {
                BooleanChunk next = it.next();
                size -= next.chunkSize;
                if (next.isOne) {
                    builder.buildPennant(LLRBNode.Color.BLACK, next.chunkSize, size);
                } else {
                    builder.buildPennant(LLRBNode.Color.BLACK, next.chunkSize, size);
                    size -= next.chunkSize;
                    builder.buildPennant(LLRBNode.Color.RED, next.chunkSize, size);
                }
            }
            LLRBNode lLRBEmptyNode = builder.root;
            if (lLRBEmptyNode == null) {
                lLRBEmptyNode = LLRBEmptyNode.getInstance();
            }
            return new RBTreeSortedMap<>(lLRBEmptyNode, comparator);
        }
    }
}
