package androidx.test.espresso;

/* loaded from: classes5.dex */
public interface IdlingResource {

    public interface ResourceCallback {
        void onTransitionToIdle();
    }

    String getName();

    boolean isIdleNow();

    void registerIdleTransitionCallback(ResourceCallback callback);
}
