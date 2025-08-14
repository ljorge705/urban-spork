package io.invertase.firebase.messaging;

/* loaded from: classes6.dex */
public class ReactNativeFirebaseMessagingStoreHelper {
    private static ReactNativeFirebaseMessagingStoreHelper _instance;
    private ReactNativeFirebaseMessagingStore messagingStore = new ReactNativeFirebaseMessagingStoreImpl();

    public ReactNativeFirebaseMessagingStore getMessagingStore() {
        return this.messagingStore;
    }

    private ReactNativeFirebaseMessagingStoreHelper() {
    }

    public static ReactNativeFirebaseMessagingStoreHelper getInstance() {
        if (_instance == null) {
            _instance = new ReactNativeFirebaseMessagingStoreHelper();
        }
        return _instance;
    }
}
