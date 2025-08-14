package com.henninghall.date_picker.ui;

import android.view.View;
import com.henninghall.date_picker.Emitter;
import com.henninghall.date_picker.State;
import com.henninghall.date_picker.wheels.Wheel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TimeZone;

/* loaded from: classes2.dex */
public class WheelChangeListenerImpl implements WheelChangeListener {
    private SpinnerState lastEmittedSpinnerState;
    private Set<SpinnerStateListener> listeners = new HashSet();
    private final View rootView;
    private final State state;
    private final UIManager uiManager;
    private final Wheels wheels;

    WheelChangeListenerImpl(Wheels wheels, State state, UIManager uIManager, View view) {
        this.wheels = wheels;
        this.uiManager = uIManager;
        this.state = state;
        this.rootView = view;
    }

    private SimpleDateFormat getDateFormat() throws NumberFormatException {
        TimeZone timeZone = this.state.getTimeZone();
        SimpleDateFormat dateFormat = this.uiManager.getDateFormat();
        dateFormat.setTimeZone(timeZone);
        return dateFormat;
    }

    @Override // com.henninghall.date_picker.ui.WheelChangeListener
    public void onChange(Wheel wheel) throws NumberFormatException {
        if (this.wheels.hasSpinningWheel()) {
            return;
        }
        if (!dateExists()) {
            Calendar closestExistingDateInPast = getClosestExistingDateInPast();
            if (closestExistingDateInPast != null) {
                this.uiManager.animateToDate(closestExistingDateInPast);
                return;
            }
            return;
        }
        Calendar selectedDate = getSelectedDate();
        if (selectedDate == null) {
            return;
        }
        Calendar minimumDate = this.state.getMinimumDate();
        if (minimumDate != null && selectedDate.before(minimumDate)) {
            this.uiManager.animateToDate(minimumDate);
            return;
        }
        Calendar maximumDate = this.state.getMaximumDate();
        if (maximumDate != null && selectedDate.after(maximumDate)) {
            this.uiManager.animateToDate(maximumDate);
            return;
        }
        String displayValueString = this.uiManager.getDisplayValueString();
        this.uiManager.updateLastSelectedDate(selectedDate);
        Emitter.onDateChange(selectedDate, displayValueString, this.state.getId(), this.rootView);
    }

    @Override // com.henninghall.date_picker.ui.WheelChangeListener
    public void onStateChange(Wheel wheel) {
        SpinnerState spinnerState = this.wheels.hasSpinningWheel() ? SpinnerState.spinning : SpinnerState.idle;
        if (spinnerState.equals(this.lastEmittedSpinnerState)) {
            return;
        }
        this.lastEmittedSpinnerState = spinnerState;
        Emitter.onSpinnerStateChange(spinnerState, this.state.getId(), this.rootView);
        Iterator<SpinnerStateListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            it.next().onChange(spinnerState);
        }
    }

    private boolean dateExists() throws NumberFormatException {
        SimpleDateFormat dateFormat = getDateFormat();
        String dateTimeString = this.wheels.getDateTimeString();
        try {
            dateFormat.setLenient(false);
            dateFormat.parse(dateTimeString);
            return true;
        } catch (ParseException unused) {
            return false;
        }
    }

    private Calendar getSelectedDate() throws NumberFormatException {
        SimpleDateFormat dateFormat = getDateFormat();
        String dateTimeString = this.wheels.getDateTimeString();
        Calendar calendar = Calendar.getInstance(this.state.getTimeZone());
        try {
            dateFormat.setLenient(true);
            calendar.setTime(dateFormat.parse(dateTimeString));
            return calendar;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private Calendar getClosestExistingDateInPast() throws NumberFormatException {
        SimpleDateFormat dateFormat = getDateFormat();
        dateFormat.setLenient(false);
        for (int i = 0; i < 10; i++) {
            try {
                String dateTimeString = this.wheels.getDateTimeString(i);
                Calendar calendar = Calendar.getInstance(this.state.getTimeZone());
                calendar.setTime(dateFormat.parse(dateTimeString));
                return calendar;
            } catch (ParseException unused) {
            }
        }
        return null;
    }

    public void addStateListener(SpinnerStateListener spinnerStateListener) {
        this.listeners.add(spinnerStateListener);
    }
}
