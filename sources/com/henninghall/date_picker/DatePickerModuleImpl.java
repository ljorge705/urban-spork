package com.henninghall.date_picker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Dynamic;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.ReadableMapKeySetIterator;
import com.henninghall.date_picker.ui.SpinnerState;
import com.henninghall.date_picker.ui.SpinnerStateListener;
import com.onfido.android.sdk.capture.component.active.video.capture.presentation.capture.view.headturn.OnfidoAnimWebView;
import net.time4j.android.ApplicationStarter;

/* loaded from: classes2.dex */
public class DatePickerModuleImpl {
    public static final String NAME = "RNDatePicker";
    private AlertDialog dialog;

    DatePickerModuleImpl(Context context) {
        ApplicationStarter.initialize(context, false);
    }

    public void openPicker(ReadableMap readableMap) {
        final PickerView pickerViewCreatePicker = createPicker(readableMap);
        AlertDialog alertDialogCreateDialog = createDialog(readableMap, pickerViewCreatePicker, new Callback() { // from class: com.henninghall.date_picker.DatePickerModuleImpl.1
            @Override // com.facebook.react.bridge.Callback
            public void invoke(Object... objArr) {
                Emitter.onConfirm(pickerViewCreatePicker.getDate(), pickerViewCreatePicker.getPickerId());
            }
        }, new Callback() { // from class: com.henninghall.date_picker.DatePickerModuleImpl.2
            @Override // com.facebook.react.bridge.Callback
            public void invoke(Object... objArr) {
                Emitter.onCancel(pickerViewCreatePicker.getPickerId());
            }
        });
        this.dialog = alertDialogCreateDialog;
        alertDialogCreateDialog.show();
    }

    public void closePicker() {
        this.dialog.dismiss();
    }

    private AlertDialog createDialog(ReadableMap readableMap, final PickerView pickerView, final Callback callback, final Callback callback2) {
        String string = readableMap.getString("confirmText");
        String string2 = readableMap.getString("cancelText");
        final String string3 = readableMap.getString("buttonColor");
        final AlertDialog alertDialogCreate = new AlertDialogBuilder(DatePickerPackage.context.getCurrentActivity(), getTheme(readableMap)).setColoredTitle(readableMap).setCancelable(true).setView(withTopMargin(pickerView)).setPositiveButton(string, new DialogInterface.OnClickListener() { // from class: com.henninghall.date_picker.DatePickerModuleImpl.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                callback.invoke(pickerView.getDate());
                dialogInterface.dismiss();
            }
        }).setNegativeButton(string2, new DialogInterface.OnClickListener() { // from class: com.henninghall.date_picker.DatePickerModuleImpl.4
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                callback2.invoke(new Object[0]);
                dialogInterface.dismiss();
            }
        }).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.henninghall.date_picker.DatePickerModuleImpl.3
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                callback2.invoke(new Object[0]);
            }
        }).create();
        alertDialogCreate.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.henninghall.date_picker.DatePickerModuleImpl.6
            @Override // android.content.DialogInterface.OnShowListener
            public void onShow(DialogInterface dialogInterface) {
                String str = string3;
                if (str != null) {
                    int color = Color.parseColor(str);
                    alertDialogCreate.getButton(-1).setTextColor(color);
                    alertDialogCreate.getButton(-2).setTextColor(color);
                }
            }
        });
        return alertDialogCreate;
    }

    private int getTheme(ReadableMap readableMap) {
        String string = readableMap.getString("theme");
        if (string == null) {
            return 0;
        }
        string.hashCode();
        if (string.equals(OnfidoAnimWebView.THEME_DARK)) {
            return 4;
        }
        return !string.equals(OnfidoAnimWebView.THEME_LIGHT) ? 0 : 5;
    }

    private PickerView createPicker(ReadableMap readableMap) {
        PickerView pickerView = new PickerView(new LinearLayout.LayoutParams(-1, Utils.toDp(180)));
        ReadableMapKeySetIterator readableMapKeySetIteratorKeySetIterator = readableMap.keySetIterator();
        while (readableMapKeySetIteratorKeySetIterator.hasNextKey()) {
            String strNextKey = readableMapKeySetIteratorKeySetIterator.nextKey();
            Dynamic dynamic = readableMap.getDynamic(strNextKey);
            if (!strNextKey.equals("style")) {
                try {
                    pickerView.updateProp(strNextKey, dynamic);
                } catch (Exception unused) {
                }
            }
        }
        pickerView.update();
        pickerView.addSpinnerStateListener(new SpinnerStateListener() { // from class: com.henninghall.date_picker.DatePickerModuleImpl.7
            @Override // com.henninghall.date_picker.ui.SpinnerStateListener
            public void onChange(SpinnerState spinnerState) {
                DatePickerModuleImpl.this.setEnabledConfirmButton(spinnerState == SpinnerState.idle);
            }
        });
        return pickerView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setEnabledConfirmButton(boolean z) {
        this.dialog.getButton(-1).setEnabled(z);
    }

    private View withTopMargin(PickerView pickerView) {
        LinearLayout linearLayout = new LinearLayout(DatePickerPackage.context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        linearLayout.addView(pickerView);
        linearLayout.setPadding(0, Utils.toDp(20), 0, 0);
        return linearLayout;
    }

    static class AlertDialogBuilder extends AlertDialog.Builder {
        public AlertDialogBuilder(Context context, int i) {
            super(context, i);
        }

        public AlertDialogBuilder setColoredTitle(ReadableMap readableMap) {
            String string = readableMap.getString("textColor");
            CharSequence string2 = readableMap.getString("title");
            if (string == null) {
                setTitle(string2);
                return this;
            }
            TextView textView = new TextView(DatePickerPackage.context.getCurrentActivity());
            textView.setText(string2);
            TypedValue typedValue = new TypedValue();
            DatePickerPackage.context.getCurrentActivity().getTheme().resolveAttribute(android.R.attr.dialogPreferredPadding, typedValue, true);
            int iComplexToDimensionPixelSize = TypedValue.complexToDimensionPixelSize(typedValue.data, DatePickerPackage.context.getResources().getDisplayMetrics());
            textView.setPadding(iComplexToDimensionPixelSize, iComplexToDimensionPixelSize, iComplexToDimensionPixelSize, 0);
            textView.setTextSize(20.0f);
            textView.setTextColor(Color.parseColor(string));
            setCustomTitle(textView);
            return this;
        }
    }
}
