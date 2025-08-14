package io.sentry.util;

/* loaded from: classes6.dex */
public final class SentryRandom {
    private static final SentryRandomThreadLocal instance = new SentryRandomThreadLocal();

    public static Random current() {
        return instance.get();
    }

    private static class SentryRandomThreadLocal extends ThreadLocal<Random> {
        private SentryRandomThreadLocal() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.lang.ThreadLocal
        public Random initialValue() {
            return new Random();
        }
    }
}
