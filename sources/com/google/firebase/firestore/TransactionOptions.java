package com.google.firebase.firestore;

import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* loaded from: classes2.dex */
public final class TransactionOptions {
    static final TransactionOptions DEFAULT = new Builder().build();
    static final int DEFAULT_MAX_ATTEMPTS_COUNT = 5;
    private final int maxAttempts;

    public int getMaxAttempts() {
        return this.maxAttempts;
    }

    public int hashCode() {
        return this.maxAttempts;
    }

    private TransactionOptions(int i) {
        this.maxAttempts = i;
    }

    public static final class Builder {
        private int maxAttempts;

        public Builder() {
            this.maxAttempts = 5;
        }

        public Builder(TransactionOptions transactionOptions) {
            this.maxAttempts = 5;
            this.maxAttempts = transactionOptions.maxAttempts;
        }

        public Builder setMaxAttempts(int i) {
            if (i < 1) {
                throw new IllegalArgumentException("Max attempts must be at least 1");
            }
            this.maxAttempts = i;
            return this;
        }

        public TransactionOptions build() {
            return new TransactionOptions(this.maxAttempts);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.maxAttempts == ((TransactionOptions) obj).maxAttempts;
    }

    public String toString() {
        return "TransactionOptions{maxAttempts=" + this.maxAttempts + AbstractJsonLexerKt.END_OBJ;
    }
}
