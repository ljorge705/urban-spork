package com.drew.lang;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class ByteTrie<T> {
    private int _maxDepth;
    private final ByteTrieNode<T> _root = new ByteTrieNode<>();

    public int getMaxDepth() {
        return this._maxDepth;
    }

    static class ByteTrieNode<T> {
        private final Map<Byte, ByteTrieNode<T>> _children = new HashMap();
        private T _value = null;

        ByteTrieNode() {
        }

        public void setValue(T t) {
            if (this._value != null) {
                throw new RuntimeException("Value already set for this trie node");
            }
            this._value = t;
        }
    }

    public T find(byte[] bArr) {
        ByteTrieNode<T> byteTrieNode = this._root;
        T t = (T) ((ByteTrieNode) byteTrieNode)._value;
        for (byte b : bArr) {
            byteTrieNode = (ByteTrieNode) ((ByteTrieNode) byteTrieNode)._children.get(Byte.valueOf(b));
            if (byteTrieNode == null) {
                break;
            }
            if (((ByteTrieNode) byteTrieNode)._value != null) {
                t = (T) ((ByteTrieNode) byteTrieNode)._value;
            }
        }
        return t;
    }

    public void addPath(T t, byte[]... bArr) {
        ByteTrieNode<T> byteTrieNode = this._root;
        int i = 0;
        for (byte[] bArr2 : bArr) {
            for (byte b : bArr2) {
                ByteTrieNode<T> byteTrieNode2 = (ByteTrieNode) ((ByteTrieNode) byteTrieNode)._children.get(Byte.valueOf(b));
                if (byteTrieNode2 == null) {
                    byteTrieNode2 = new ByteTrieNode<>();
                    ((ByteTrieNode) byteTrieNode)._children.put(Byte.valueOf(b), byteTrieNode2);
                }
                byteTrieNode = byteTrieNode2;
                i++;
            }
        }
        if (i == 0) {
            throw new IllegalArgumentException("Parts must contain at least one byte.");
        }
        byteTrieNode.setValue(t);
        this._maxDepth = Math.max(this._maxDepth, i);
    }

    public void setDefaultValue(T t) {
        this._root.setValue(t);
    }
}
