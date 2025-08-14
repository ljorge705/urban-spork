package org.junit.rules;

import java.util.concurrent.TimeUnit;
import org.junit.internal.runners.statements.FailOnTimeout;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/* loaded from: classes4.dex */
public class Timeout implements TestRule {
    private final boolean lookForStuckThread;
    private final TimeUnit timeUnit;
    private final long timeout;

    protected final boolean getLookingForStuckThread() {
        return this.lookForStuckThread;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Deprecated
    public Timeout(int i) {
        this(i, TimeUnit.MILLISECONDS);
    }

    public Timeout(long j, TimeUnit timeUnit) {
        this.timeout = j;
        this.timeUnit = timeUnit;
        this.lookForStuckThread = false;
    }

    protected Timeout(Builder builder) {
        this.timeout = builder.getTimeout();
        this.timeUnit = builder.getTimeUnit();
        this.lookForStuckThread = builder.getLookingForStuckThread();
    }

    public static Timeout millis(long j) {
        return new Timeout(j, TimeUnit.MILLISECONDS);
    }

    public static Timeout seconds(long j) {
        return new Timeout(j, TimeUnit.SECONDS);
    }

    protected final long getTimeout(TimeUnit timeUnit) {
        return timeUnit.convert(this.timeout, this.timeUnit);
    }

    protected Statement createFailOnTimeoutStatement(Statement statement) throws Exception {
        return FailOnTimeout.builder().withTimeout(this.timeout, this.timeUnit).withLookingForStuckThread(this.lookForStuckThread).build(statement);
    }

    @Override // org.junit.rules.TestRule
    public Statement apply(Statement statement, Description description) {
        try {
            return createFailOnTimeoutStatement(statement);
        } catch (Exception e) {
            return new Statement() { // from class: org.junit.rules.Timeout.1
                @Override // org.junit.runners.model.Statement
                public void evaluate() throws Throwable {
                    throw new RuntimeException("Invalid parameters for Timeout", e);
                }
            };
        }
    }

    public static class Builder {
        private boolean lookForStuckThread = false;
        private long timeout = 0;
        private TimeUnit timeUnit = TimeUnit.SECONDS;

        protected boolean getLookingForStuckThread() {
            return this.lookForStuckThread;
        }

        protected TimeUnit getTimeUnit() {
            return this.timeUnit;
        }

        protected long getTimeout() {
            return this.timeout;
        }

        public Builder withLookingForStuckThread(boolean z) {
            this.lookForStuckThread = z;
            return this;
        }

        public Builder withTimeout(long j, TimeUnit timeUnit) {
            this.timeout = j;
            this.timeUnit = timeUnit;
            return this;
        }

        protected Builder() {
        }

        public Timeout build() {
            return new Timeout(this);
        }
    }
}
