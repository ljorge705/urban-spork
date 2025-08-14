package com.sdkmobilereactnative.deviceInfo;

import com.facebook.hermes.intl.Constants;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.module.annotations.ReactModule;
import com.fivvy.profiler.application.UseCaseService;
import com.fivvy.profiler.domain.models.DeviceInfo;
import io.sentry.SentryEvent;
import io.sentry.protocol.Device;

@ReactModule(name = DeviceInfoModule.NAME)
/* loaded from: classes6.dex */
public class DeviceInfoModule extends ReactContextBaseJavaModule {
    public static final String NAME = "DeviceInfoModule";
    public UseCaseService useCaseService;

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public DeviceInfoModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.useCaseService = new UseCaseService(reactApplicationContext);
    }

    @ReactMethod
    public void getDeviceInformation(Promise promise) {
        DeviceInfo deviceInfoCreateGetDeviceInfoUseCase = this.useCaseService.createGetDeviceInfoUseCase();
        WritableMap writableMapCreateMap = Arguments.createMap();
        writableMapCreateMap.putInt("battery_status", deviceInfoCreateGetDeviceInfoUseCase.getBatteryStatus());
        writableMapCreateMap.putString("api_level", deviceInfoCreateGetDeviceInfoUseCase.getApiLevel());
        writableMapCreateMap.putString("product", deviceInfoCreateGetDeviceInfoUseCase.getProduct());
        writableMapCreateMap.putString("device_id", deviceInfoCreateGetDeviceInfoUseCase.getDeviceId());
        writableMapCreateMap.putString("incremental_version", deviceInfoCreateGetDeviceInfoUseCase.getIncrementalVersion());
        writableMapCreateMap.putString("display", deviceInfoCreateGetDeviceInfoUseCase.getDisplay());
        writableMapCreateMap.putString("type", deviceInfoCreateGetDeviceInfoUseCase.getType());
        writableMapCreateMap.putString(Device.JsonKeys.MANUFACTURER, deviceInfoCreateGetDeviceInfoUseCase.getManufacturer());
        writableMapCreateMap.putString("tags", deviceInfoCreateGetDeviceInfoUseCase.getTags());
        writableMapCreateMap.putString("host", deviceInfoCreateGetDeviceInfoUseCase.getHost());
        writableMapCreateMap.putString(SentryEvent.JsonKeys.FINGERPRINT, deviceInfoCreateGetDeviceInfoUseCase.getFingerprint());
        writableMapCreateMap.putString(Device.JsonKeys.MODEL, deviceInfoCreateGetDeviceInfoUseCase.getModel());
        writableMapCreateMap.putString("id", deviceInfoCreateGetDeviceInfoUseCase.getId());
        writableMapCreateMap.putString("release_version", deviceInfoCreateGetDeviceInfoUseCase.getReleaseVersion());
        writableMapCreateMap.putString(Device.TYPE, deviceInfoCreateGetDeviceInfoUseCase.getDevice());
        writableMapCreateMap.putString(Device.JsonKeys.BRAND, deviceInfoCreateGetDeviceInfoUseCase.getBrand());
        writableMapCreateMap.putString("base_os", deviceInfoCreateGetDeviceInfoUseCase.getBaseOs());
        writableMapCreateMap.putString("hardware", deviceInfoCreateGetDeviceInfoUseCase.getHardware());
        writableMapCreateMap.putString(Constants.SENSITIVITY_BASE, deviceInfoCreateGetDeviceInfoUseCase.getBase());
        promise.resolve(writableMapCreateMap);
    }
}
