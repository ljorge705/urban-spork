package com.henninghall.date_picker;

import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactPropGroup;
import com.henninghall.date_picker.props.DividerColorProp;
import com.henninghall.date_picker.props.Is24hourSourceProp;
import com.henninghall.date_picker.props.ModeProp;
import com.henninghall.date_picker.props.TimezoneOffsetInMinutesProp;
import java.util.Map;

/* loaded from: classes2.dex */
public class DatePickerManager extends SimpleViewManager<PickerView> {
    @Override // com.facebook.react.uimanager.ViewManager, com.facebook.react.bridge.NativeModule
    public String getName() {
        return DatePickerModuleImpl.NAME;
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public PickerView createViewInstance(ThemedReactContext themedReactContext) {
        return DatePickerManagerImpl.createViewInstance(themedReactContext);
    }

    @ReactPropGroup(names = {"date", ModeProp.name, "locale", "maximumDate", "minimumDate", "textColor", TimezoneOffsetInMinutesProp.name, "minuteInterval", Is24hourSourceProp.name, DividerColorProp.name})
    public void setProps(PickerView pickerView, int i, Dynamic dynamic) {
        DatePickerManagerImpl.setProps(pickerView, i, dynamic, getClass());
    }

    @ReactPropGroup(customType = "Style", names = {"height"})
    public void setStyle(PickerView pickerView, int i, Dynamic dynamic) {
        DatePickerManagerImpl.setStyle(pickerView, i, dynamic, getClass());
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public Map<String, Integer> getCommandsMap() {
        return DatePickerManagerImpl.getCommandsMap();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public void onAfterUpdateTransaction(PickerView pickerView) {
        super.onAfterUpdateTransaction((DatePickerManager) pickerView);
        DatePickerManagerImpl.onAfterUpdateTransaction(pickerView);
    }

    @Override // com.facebook.react.uimanager.ViewManager
    public void receiveCommand(PickerView pickerView, int i, ReadableArray readableArray) {
        DatePickerManagerImpl.receiveCommand(pickerView, i, readableArray);
    }

    @Override // com.facebook.react.uimanager.BaseViewManager, com.facebook.react.uimanager.ViewManager
    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder().put("dateChange", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onChange"))).put("spinnerStateChange", MapBuilder.of("phasedRegistrationNames", MapBuilder.of("bubbled", "onStateChange"))).build();
    }
}
