package com.henninghall.date_picker.ui;

import android.view.View;
import com.henninghall.date_picker.State;
import com.henninghall.date_picker.wheelFunctions.AddOnChangeListener;
import com.henninghall.date_picker.wheelFunctions.AnimateToDate;
import com.henninghall.date_picker.wheelFunctions.Refresh;
import com.henninghall.date_picker.wheelFunctions.SetDate;
import com.henninghall.date_picker.wheelFunctions.SetDividerColor;
import com.henninghall.date_picker.wheelFunctions.TextColor;
import com.henninghall.date_picker.wheelFunctions.UpdateVisibility;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/* loaded from: classes2.dex */
public class UIManager {
    private WheelChangeListenerImpl onWheelChangeListener;
    private final View rootView;
    private final State state;
    private WheelScroller wheelScroller = new WheelScroller();
    private Wheels wheels;

    public UIManager(State state, View view) {
        this.state = state;
        this.rootView = view;
        this.wheels = new Wheels(state, view);
        addOnChangeListener();
    }

    public void updateWheelVisibility() {
        this.wheels.applyOnAll(new UpdateVisibility());
    }

    public void updateTextColor() {
        this.wheels.applyOnAll(new TextColor(this.state.getTextColor()));
    }

    public void updateWheelOrder() {
        this.wheels.updateWheelOrder();
    }

    public void updateDisplayValues() {
        this.wheels.applyOnAll(new Refresh());
    }

    public void setWheelsToDate() {
        this.wheels.applyOnAll(new SetDate(this.state.getPickerDate()));
    }

    public void scroll(int i, int i2) {
        this.wheelScroller.scroll(this.wheels.getWheel(this.state.derived.getOrderedVisibleWheels().get(i)), i2);
    }

    SimpleDateFormat getDateFormat() {
        return new SimpleDateFormat(this.wheels.getFormatPattern(), this.state.getLocale());
    }

    String getDisplayValueString() {
        return this.wheels.getDisplayValue();
    }

    void animateToDate(Calendar calendar) {
        this.wheels.applyOnInVisible(new SetDate(calendar));
        this.wheels.applyOnVisible(new AnimateToDate(calendar));
    }

    private void addOnChangeListener() {
        this.onWheelChangeListener = new WheelChangeListenerImpl(this.wheels, this.state, this, this.rootView);
        this.wheels.applyOnAll(new AddOnChangeListener(this.onWheelChangeListener));
    }

    public void addStateListener(SpinnerStateListener spinnerStateListener) {
        this.onWheelChangeListener.addStateListener(spinnerStateListener);
    }

    public void updateLastSelectedDate(Calendar calendar) {
        this.state.setLastSelectedDate(calendar);
    }

    public void setDividerColor(String str) {
        this.wheels.applyOnAll(new SetDividerColor(str));
    }
}
