package com.google.firebase.firestore.remote;

import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.util.Preconditions;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes2.dex */
final class TestingHooks {
    private static final TestingHooks instance = new TestingHooks();
    private final CopyOnWriteArrayList<AtomicReference<ExistenceFilterMismatchListener>> existenceFilterMismatchListeners = new CopyOnWriteArrayList<>();

    interface ExistenceFilterMismatchListener {
        void onExistenceFilterMismatch(ExistenceFilterMismatchInfo existenceFilterMismatchInfo);
    }

    static TestingHooks getInstance() {
        return instance;
    }

    private TestingHooks() {
    }

    void notifyOnExistenceFilterMismatch(ExistenceFilterMismatchInfo existenceFilterMismatchInfo) {
        Iterator<AtomicReference<ExistenceFilterMismatchListener>> it = this.existenceFilterMismatchListeners.iterator();
        while (it.hasNext()) {
            ExistenceFilterMismatchListener existenceFilterMismatchListener = it.next().get();
            if (existenceFilterMismatchListener != null) {
                existenceFilterMismatchListener.onExistenceFilterMismatch(existenceFilterMismatchInfo);
            }
        }
    }

    ListenerRegistration addExistenceFilterMismatchListener(ExistenceFilterMismatchListener existenceFilterMismatchListener) {
        Preconditions.checkNotNull(existenceFilterMismatchListener, "a null listener is not allowed");
        final AtomicReference<ExistenceFilterMismatchListener> atomicReference = new AtomicReference<>(existenceFilterMismatchListener);
        this.existenceFilterMismatchListeners.add(atomicReference);
        return new ListenerRegistration() { // from class: com.google.firebase.firestore.remote.TestingHooks$$ExternalSyntheticLambda0
            @Override // com.google.firebase.firestore.ListenerRegistration
            public final void remove() {
                this.f$0.m5354xc9d8f707(atomicReference);
            }
        };
    }

    /* renamed from: lambda$addExistenceFilterMismatchListener$0$com-google-firebase-firestore-remote-TestingHooks, reason: not valid java name */
    /* synthetic */ void m5354xc9d8f707(AtomicReference atomicReference) {
        atomicReference.set(null);
        this.existenceFilterMismatchListeners.remove(atomicReference);
    }

    static abstract class ExistenceFilterMismatchInfo {
        abstract ExistenceFilterBloomFilterInfo bloomFilter();

        abstract int existenceFilterCount();

        abstract int localCacheCount();

        ExistenceFilterMismatchInfo() {
        }

        static ExistenceFilterMismatchInfo create(int i, int i2, ExistenceFilterBloomFilterInfo existenceFilterBloomFilterInfo) {
            return new AutoValue_TestingHooks_ExistenceFilterMismatchInfo(i, i2, existenceFilterBloomFilterInfo);
        }

        static ExistenceFilterMismatchInfo from(boolean z, int i, ExistenceFilter existenceFilter) {
            return create(i, existenceFilter.getCount(), ExistenceFilterBloomFilterInfo.from(z, existenceFilter));
        }
    }

    static abstract class ExistenceFilterBloomFilterInfo {
        abstract boolean applied();

        abstract int bitmapLength();

        abstract int hashCount();

        abstract int padding();

        ExistenceFilterBloomFilterInfo() {
        }

        static ExistenceFilterBloomFilterInfo create(boolean z, int i, int i2, int i3) {
            return new AutoValue_TestingHooks_ExistenceFilterBloomFilterInfo(z, i, i2, i3);
        }

        static ExistenceFilterBloomFilterInfo from(boolean z, ExistenceFilter existenceFilter) {
            com.google.firestore.v1.BloomFilter unchangedNames = existenceFilter.getUnchangedNames();
            if (unchangedNames == null) {
                return null;
            }
            return create(z, unchangedNames.getHashCount(), unchangedNames.getBits().getBitmap().size(), unchangedNames.getBits().getPadding());
        }
    }
}
