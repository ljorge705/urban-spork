package io.invertase.firebase.firestore;

import com.facebook.react.bridge.ReadableArray;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Transaction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* loaded from: classes6.dex */
class ReactNativeFirebaseFirestoreTransactionHandler {
    private String appName;
    private ReadableArray commandBuffer;
    private final Condition condition;
    private Transaction firestoreTransaction;
    private final ReentrantLock lock;
    private long timeoutAt;
    private int transactionId;
    boolean aborted = false;
    boolean timeout = false;

    String getAppName() {
        return this.appName;
    }

    ReadableArray getCommandBuffer() {
        return this.commandBuffer;
    }

    int getTransactionId() {
        return this.transactionId;
    }

    void resetState(Transaction transaction) {
        this.commandBuffer = null;
        this.firestoreTransaction = transaction;
    }

    ReactNativeFirebaseFirestoreTransactionHandler(String str, int i) {
        this.appName = str;
        this.transactionId = i;
        updateInternalTimeout();
        ReentrantLock reentrantLock = new ReentrantLock();
        this.lock = reentrantLock;
        this.condition = reentrantLock.newCondition();
    }

    void abort() {
        this.aborted = true;
        safeUnlock();
    }

    void signalBufferReceived(ReadableArray readableArray) {
        this.lock.lock();
        try {
            this.commandBuffer = readableArray;
            this.condition.signalAll();
        } finally {
            safeUnlock();
        }
    }

    void await() {
        this.lock.lock();
        updateInternalTimeout();
        while (!this.aborted && !this.timeout && !this.condition.await(10L, TimeUnit.MILLISECONDS)) {
            try {
                if (System.currentTimeMillis() > this.timeoutAt) {
                    this.timeout = true;
                }
            } catch (InterruptedException unused) {
            } catch (Throwable th) {
                safeUnlock();
                throw th;
            }
        }
        safeUnlock();
    }

    DocumentSnapshot getDocument(DocumentReference documentReference) throws FirebaseFirestoreException {
        updateInternalTimeout();
        return this.firestoreTransaction.get(documentReference);
    }

    private void safeUnlock() {
        if (this.lock.isHeldByCurrentThread()) {
            this.lock.unlock();
        }
    }

    private void updateInternalTimeout() {
        this.timeoutAt = System.currentTimeMillis() + 15000;
    }
}
