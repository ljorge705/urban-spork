package com.reactcommunity.rndatetimepicker;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.DatePicker;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.clevertap.android.sdk.Constants;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.module.annotations.ReactModule;
import java.util.Calendar;

@ReactModule(name = DatePickerModule.NAME)
/* loaded from: classes6.dex */
public class DatePickerModule extends NativeModuleDatePickerSpec {

    @VisibleForTesting
    public static final String NAME = "RNCDatePicker";

    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return NAME;
    }

    public DatePickerModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    private class DatePickerDialogListener implements DatePickerDialog.OnDateSetListener, DialogInterface.OnDismissListener, DialogInterface.OnClickListener {
        private final Bundle mArgs;
        private final Promise mPromise;
        private boolean mPromiseResolved = false;

        public DatePickerDialogListener(Promise promise, Bundle bundle) {
            this.mPromise = promise;
            this.mArgs = bundle;
        }

        @Override // android.app.DatePickerDialog.OnDateSetListener
        public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
            if (this.mPromiseResolved || !DatePickerModule.this.getReactApplicationContext().hasActiveReactInstance()) {
                return;
            }
            RNDate rNDate = new RNDate(this.mArgs);
            Calendar calendar = Calendar.getInstance(Common.getTimeZone(this.mArgs));
            calendar.set(i, i2, i3, rNDate.hour(), rNDate.minute(), 0);
            calendar.set(14, 0);
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString(Constants.KEY_ACTION, RNConstants.ACTION_DATE_SET);
            writableNativeMap.putDouble("timestamp", calendar.getTimeInMillis());
            writableNativeMap.putDouble("utcOffset", (calendar.getTimeZone().getOffset(calendar.getTimeInMillis()) / 1000) / 60);
            this.mPromise.resolve(writableNativeMap);
            this.mPromiseResolved = true;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            if (this.mPromiseResolved || !DatePickerModule.this.getReactApplicationContext().hasActiveReactInstance()) {
                return;
            }
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString(Constants.KEY_ACTION, RNConstants.ACTION_DISMISSED);
            this.mPromise.resolve(writableNativeMap);
            this.mPromiseResolved = true;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            if (this.mPromiseResolved || !DatePickerModule.this.getReactApplicationContext().hasActiveReactInstance()) {
                return;
            }
            WritableNativeMap writableNativeMap = new WritableNativeMap();
            writableNativeMap.putString(Constants.KEY_ACTION, RNConstants.ACTION_NEUTRAL_BUTTON);
            this.mPromise.resolve(writableNativeMap);
            this.mPromiseResolved = true;
        }
    }

    @Override // com.reactcommunity.rndatetimepicker.NativeModuleDatePickerSpec
    @ReactMethod
    public void dismiss(Promise promise) {
        Common.dismissDialog((FragmentActivity) getCurrentActivity(), NAME, promise);
    }

    @Override // com.reactcommunity.rndatetimepicker.NativeModuleDatePickerSpec
    @ReactMethod
    public void open(final ReadableMap readableMap, final Promise promise) {
        FragmentActivity fragmentActivity = (FragmentActivity) getCurrentActivity();
        if (fragmentActivity == null) {
            promise.reject(RNConstants.ERROR_NO_ACTIVITY, "Tried to open a DatePicker dialog while not attached to an Activity");
        } else {
            final FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
            UiThreadUtil.runOnUiThread(new Runnable() { // from class: com.reactcommunity.rndatetimepicker.DatePickerModule$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$open$0(supportFragmentManager, readableMap, promise);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$open$0(FragmentManager fragmentManager, ReadableMap readableMap, Promise promise) {
        RNDatePickerDialogFragment rNDatePickerDialogFragment = (RNDatePickerDialogFragment) fragmentManager.findFragmentByTag(NAME);
        Bundle bundleCreateFragmentArguments = createFragmentArguments(readableMap);
        if (rNDatePickerDialogFragment != null) {
            rNDatePickerDialogFragment.update(bundleCreateFragmentArguments);
            return;
        }
        RNDatePickerDialogFragment rNDatePickerDialogFragment2 = new RNDatePickerDialogFragment();
        rNDatePickerDialogFragment2.setArguments(bundleCreateFragmentArguments);
        DatePickerDialogListener datePickerDialogListener = new DatePickerDialogListener(promise, bundleCreateFragmentArguments);
        rNDatePickerDialogFragment2.setOnDismissListener(datePickerDialogListener);
        rNDatePickerDialogFragment2.setOnDateSetListener(datePickerDialogListener);
        rNDatePickerDialogFragment2.setOnNeutralButtonActionListener(datePickerDialogListener);
        rNDatePickerDialogFragment2.show(fragmentManager, NAME);
    }

    private Bundle createFragmentArguments(ReadableMap readableMap) {
        Bundle bundleCreateFragmentArguments = Common.createFragmentArguments(readableMap);
        if (readableMap.hasKey("minimumDate") && !readableMap.isNull("minimumDate")) {
            bundleCreateFragmentArguments.putLong("minimumDate", (long) readableMap.getDouble("minimumDate"));
        }
        if (readableMap.hasKey("maximumDate") && !readableMap.isNull("maximumDate")) {
            bundleCreateFragmentArguments.putLong("maximumDate", (long) readableMap.getDouble("maximumDate"));
        }
        if (readableMap.hasKey("display") && !readableMap.isNull("display")) {
            bundleCreateFragmentArguments.putString("display", readableMap.getString("display"));
        }
        if (readableMap.hasKey(RNConstants.ARG_DIALOG_BUTTONS) && !readableMap.isNull(RNConstants.ARG_DIALOG_BUTTONS)) {
            bundleCreateFragmentArguments.putBundle(RNConstants.ARG_DIALOG_BUTTONS, Arguments.toBundle(readableMap.getMap(RNConstants.ARG_DIALOG_BUTTONS)));
        }
        if (readableMap.hasKey(RNConstants.ARG_TZOFFSET_MINS) && !readableMap.isNull(RNConstants.ARG_TZOFFSET_MINS)) {
            bundleCreateFragmentArguments.putLong(RNConstants.ARG_TZOFFSET_MINS, (long) readableMap.getDouble(RNConstants.ARG_TZOFFSET_MINS));
        }
        if (readableMap.hasKey("testID") && !readableMap.isNull("testID")) {
            bundleCreateFragmentArguments.putString("testID", readableMap.getString("testID"));
        }
        return bundleCreateFragmentArguments;
    }
}
