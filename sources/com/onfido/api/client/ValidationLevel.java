package com.onfido.api.client;

import app.notifee.core.event.LogEvent;

/* loaded from: classes6.dex */
public enum ValidationLevel {
    ERROR("error"),
    WARNING(LogEvent.LEVEL_WARN);

    private final String id;

    public String getId() {
        return this.id;
    }

    ValidationLevel(String str) {
        this.id = str;
    }

    public static ValidationLevel fromId(String str) {
        for (ValidationLevel validationLevel : values()) {
            if (validationLevel.id.equalsIgnoreCase(str)) {
                return validationLevel;
            }
        }
        return null;
    }
}
