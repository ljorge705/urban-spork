package com.drew.metadata.mov;

import com.drew.imaging.quicktime.QuickTimeHandler;
import com.drew.metadata.Metadata;
import com.drew.metadata.mov.media.QuickTimeMusicHandler;
import com.drew.metadata.mov.media.QuickTimeSoundHandler;
import com.drew.metadata.mov.media.QuickTimeSubtitleHandler;
import com.drew.metadata.mov.media.QuickTimeTextHandler;
import com.drew.metadata.mov.media.QuickTimeTimecodeHandler;
import com.drew.metadata.mov.media.QuickTimeVideoHandler;
import com.drew.metadata.mov.metadata.QuickTimeDataHandler;
import com.drew.metadata.mov.metadata.QuickTimeDirectoryHandler;

/* loaded from: classes5.dex */
public class QuickTimeHandlerFactory {
    private static final String HANDLER_METADATA_DATA = "mdta";
    private static final String HANDLER_METADATA_DIRECTORY = "mdir";
    private static final String HANDLER_MUSIC_MEDIA = "musi";
    public static Long HANDLER_PARAM_CREATION_TIME = null;
    public static Long HANDLER_PARAM_DURATION = null;
    public static Long HANDLER_PARAM_MODIFICATION_TIME = null;
    public static Long HANDLER_PARAM_TIME_SCALE = null;
    private static final String HANDLER_SOUND_MEDIA = "soun";
    private static final String HANDLER_SUBTITLE_MEDIA = "sbtl";
    private static final String HANDLER_TEXT_MEDIA = "text";
    private static final String HANDLER_TIMECODE_MEDIA = "tmcd";
    private static final String HANDLER_VIDEO_MEDIA = "vide";
    private QuickTimeHandler caller;

    public QuickTimeHandlerFactory(QuickTimeHandler quickTimeHandler) {
        this.caller = quickTimeHandler;
    }

    public QuickTimeHandler getHandler(String str, Metadata metadata) {
        if (str.equals(HANDLER_METADATA_DIRECTORY)) {
            return new QuickTimeDirectoryHandler(metadata);
        }
        if (str.equals(HANDLER_METADATA_DATA)) {
            return new QuickTimeDataHandler(metadata);
        }
        if (str.equals(HANDLER_SOUND_MEDIA)) {
            return new QuickTimeSoundHandler(metadata);
        }
        if (str.equals(HANDLER_VIDEO_MEDIA)) {
            return new QuickTimeVideoHandler(metadata);
        }
        if (str.equals(HANDLER_TIMECODE_MEDIA)) {
            return new QuickTimeTimecodeHandler(metadata);
        }
        if (str.equals("text")) {
            return new QuickTimeTextHandler(metadata);
        }
        if (str.equals("sbtl")) {
            return new QuickTimeSubtitleHandler(metadata);
        }
        return str.equals(HANDLER_MUSIC_MEDIA) ? new QuickTimeMusicHandler(metadata) : this.caller;
    }
}
