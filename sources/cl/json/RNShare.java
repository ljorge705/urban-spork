package cl.json;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import java.util.Map;
import javax.annotation.Nullable;

/* loaded from: classes5.dex */
public class RNShare extends ReactContextBaseJavaModule {
    private final RNShareImpl delegate;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return RNShareImpl.NAME;
    }

    public RNShare(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.delegate = new RNShareImpl(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    @Nullable
    public Map<String, Object> getConstants() {
        return this.delegate.getConstants();
    }

    @ReactMethod
    public void open(ReadableMap readableMap, Promise promise) {
        this.delegate.open(readableMap, promise);
    }

    @ReactMethod
    public void shareSingle(ReadableMap readableMap, Promise promise) {
        this.delegate.shareSingle(readableMap, promise);
    }

    @ReactMethod
    public void isPackageInstalled(String str, Promise promise) {
        this.delegate.isPackageInstalled(str, promise);
    }

    @ReactMethod
    public void isBase64File(String str, Promise promise) {
        this.delegate.isBase64File(str, promise);
    }
}
