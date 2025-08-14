package com.facebook.react.modules.i18nmanager;

import com.facebook.fbreact.specs.NativeI18nManagerSpec;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.module.annotations.ReactModule;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ReactModule(name = NativeI18nManagerSpec.NAME)
/* loaded from: classes5.dex */
public class I18nManagerModule extends NativeI18nManagerSpec {
    private final I18nUtil sharedI18nUtilInstance;

    public I18nManagerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
        this.sharedI18nUtilInstance = I18nUtil.getInstance();
    }

    @Override // com.facebook.fbreact.specs.NativeI18nManagerSpec
    public Map<String, Object> getTypedExportedConstants() {
        ReactApplicationContext reactApplicationContext = getReactApplicationContext();
        Locale locale = reactApplicationContext.getResources().getConfiguration().getLocales().get(0);
        HashMap mapNewHashMap = MapBuilder.newHashMap();
        mapNewHashMap.put("isRTL", Boolean.valueOf(this.sharedI18nUtilInstance.isRTL(reactApplicationContext)));
        mapNewHashMap.put("doLeftAndRightSwapInRTL", Boolean.valueOf(this.sharedI18nUtilInstance.doLeftAndRightSwapInRTL(reactApplicationContext)));
        mapNewHashMap.put("localeIdentifier", locale.toString());
        return mapNewHashMap;
    }

    @Override // com.facebook.fbreact.specs.NativeI18nManagerSpec
    public void allowRTL(boolean z) {
        this.sharedI18nUtilInstance.allowRTL(getReactApplicationContext(), z);
    }

    @Override // com.facebook.fbreact.specs.NativeI18nManagerSpec
    public void forceRTL(boolean z) {
        this.sharedI18nUtilInstance.forceRTL(getReactApplicationContext(), z);
    }

    @Override // com.facebook.fbreact.specs.NativeI18nManagerSpec
    public void swapLeftAndRightInRTL(boolean z) {
        this.sharedI18nUtilInstance.swapLeftAndRightInRTL(getReactApplicationContext(), z);
    }
}
