package com.clevertap.android.sdk.validation;

import java.util.ArrayList;

/* loaded from: classes5.dex */
public class ValidationResultStack {
    private static final Object pendingValidationResultsLock = new Object();
    private ArrayList<ValidationResult> pendingValidationResults = new ArrayList<>();

    public ArrayList<ValidationResult> getPendingValidationResults() {
        return this.pendingValidationResults;
    }

    public void pushValidationResult(ValidationResult validationResult) {
        synchronized (pendingValidationResultsLock) {
            try {
                int size = this.pendingValidationResults.size();
                if (size > 50) {
                    ArrayList<ValidationResult> arrayList = new ArrayList<>();
                    for (int i = 10; i < size; i++) {
                        arrayList.add(this.pendingValidationResults.get(i));
                    }
                    arrayList.add(validationResult);
                    this.pendingValidationResults = arrayList;
                } else {
                    this.pendingValidationResults.add(validationResult);
                }
            } catch (Exception unused) {
            }
        }
    }

    public ValidationResult popValidationResult() {
        ValidationResult validationResultRemove;
        synchronized (pendingValidationResultsLock) {
            validationResultRemove = null;
            try {
                if (!this.pendingValidationResults.isEmpty()) {
                    validationResultRemove = this.pendingValidationResults.remove(0);
                }
            } catch (Exception unused) {
            }
        }
        return validationResultRemove;
    }
}
