package com.clevertap.android.sdk.inbox;

import androidx.room.FtsOptions;

/* loaded from: classes5.dex */
public enum CTInboxMessageType {
    SimpleMessage(FtsOptions.TOKENIZER_SIMPLE),
    IconMessage("message-icon"),
    CarouselMessage("carousel"),
    CarouselImageMessage("carousel-image");

    private final String inboxMessageType;

    @Override // java.lang.Enum
    public String toString() {
        return this.inboxMessageType;
    }

    CTInboxMessageType(String str) {
        this.inboxMessageType = str;
    }

    static CTInboxMessageType fromString(String str) {
        str.hashCode();
        switch (str) {
            case "carousel-image":
                return CarouselImageMessage;
            case "message-icon":
                return IconMessage;
            case "simple":
                return SimpleMessage;
            case "carousel":
                return CarouselMessage;
            default:
                return null;
        }
    }
}
