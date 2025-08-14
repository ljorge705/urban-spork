package com.drew.metadata.heif;

import com.drew.imaging.heif.HeifHandler;
import com.drew.metadata.Metadata;
import com.drew.metadata.heif.boxes.HandlerBox;

/* loaded from: classes5.dex */
public class HeifHandlerFactory {
    private static final String HANDLER_PICTURE = "pict";
    private HeifHandler caller;

    public HeifHandlerFactory(HeifHandler heifHandler) {
        this.caller = heifHandler;
    }

    public HeifHandler getHandler(HandlerBox handlerBox, Metadata metadata) {
        return handlerBox.getHandlerType().equals(HANDLER_PICTURE) ? new HeifPictureHandler(metadata) : this.caller;
    }
}
