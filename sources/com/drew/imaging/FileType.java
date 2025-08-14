package com.drew.imaging;

import androidx.media3.common.MimeTypes;
import com.clevertap.android.sdk.Constants;
import com.reactnativecommunity.clipboard.ClipboardModule;
import io.sentry.rrweb.RRWebVideoEvent;

/* loaded from: classes5.dex */
public enum FileType {
    Unknown("Unknown", "Unknown", null, new String[0]),
    Jpeg("JPEG", "Joint Photographic Experts Group", "image/jpeg", "jpg", "jpeg", "jpe"),
    Tiff("TIFF", "Tagged Image File Format", "image/tiff", "tiff", "tif"),
    Psd("PSD", "Photoshop Document", "image/vnd.adobe.photoshop", "psd"),
    Png("PNG", "Portable Network Graphics", ClipboardModule.MIMETYPE_PNG, "png"),
    Bmp("BMP", "Device Independent Bitmap", "image/bmp", "bmp"),
    Gif("GIF", "Graphics Interchange Format", "image/gif", "gif"),
    Ico("ICO", "Windows Icon", "image/x-icon", Constants.NOTIF_ICON),
    Pcx("PCX", "PiCture eXchange", "image/x-pcx", "pcx"),
    Riff("RIFF", "Resource Interchange File Format", null, new String[0]),
    Wav("WAV", "Waveform Audio File Format", "audio/vnd.wave", "wav", "wave"),
    Avi("AVI", "Audio Video Interleaved", "video/vnd.avi", "avi"),
    WebP("WebP", "WebP", ClipboardModule.MIMETYPE_WEBP, "webp"),
    Mov("MOV", "QuickTime Movie", "video/quicktime", "mov", "qt"),
    Mp4("MP4", "MPEG-4 Part 14", "video/mp4", RRWebVideoEvent.REPLAY_CONTAINER, "m4a", "m4p", "m4b", "m4r", "m4v"),
    Heif("HEIF", "High Efficiency Image File Format", ClipboardModule.MIMETYPE_HEIF, "heif", "heic"),
    Eps("EPS", "Encapsulated PostScript", "application/postscript", "eps", "epsf", "epsi"),
    Mp3("MP3", "MP3", MimeTypes.AUDIO_MPEG, "mp3"),
    Arw("ARW", "Sony Camera Raw", null, "arw"),
    Crw("CRW", "Canon Camera Raw", null, "crw"),
    Cr2("CR2", "Canon Camera Raw", null, "cr2"),
    Nef("NEF", "Nikon Camera Raw", null, "nef"),
    Orf("ORF", "Olympus Camera Raw", null, "orf"),
    Raf("RAF", "FujiFilm Camera Raw", null, "raf"),
    Rw2("RW2", "Panasonic Camera Raw", null, "rw2"),
    Aac("AAC", "Advanced Audio Coding", "audio/aac", "m4a"),
    Asf("ASF", "Advanced Systems Format", "video/x-ms-asf", "asf", "wma", "wmv"),
    Cfbf("CFBF", "Compound File Binary Format", null, null),
    Flv("FLV", "Flash Video", MimeTypes.VIDEO_FLV, ".flv", ".f4v,"),
    Indd("INDD", "INDesign Document", "application/octet-stream", ".indd"),
    Mxf("MXF", "Material Exchange Format", "application/mxf", "mxf"),
    Pdf("PDF", "Portable Document Format", "application/pdf", "pdf"),
    Qxp("QXP", "Quark XPress Document", null, "qzp", "qxd"),
    Ram("RAM", "RealAudio", "audio/vnd.rn-realaudio", "aac", "ra"),
    Rtf("RTF", "Rich Text Format", "application/rtf", "rtf"),
    Sit("SIT", "Stuffit Archive", "application/x-stuffit", "sit"),
    Sitx("SITX", "Stuffit X Archive", "application/x-stuffitx", "sitx"),
    Swf("SWF", "Small Web Format", "application/vnd.adobe.flash-movie", "swf"),
    Vob("VOB", "Video Object", "video/dvd", ".vob"),
    Zip("ZIP", "ZIP Archive", "application/zip", ".zip", ".zipx");

    private final String[] _extensions;
    private final String _longName;
    private final String _mimeType;
    private final String _name;

    public String[] getAllExtensions() {
        return this._extensions;
    }

    public String getLongName() {
        return this._longName;
    }

    public String getMimeType() {
        return this._mimeType;
    }

    public String getName() {
        return this._name;
    }

    static {
    }

    FileType(String str, String str2, String str3, String... strArr) {
        this._name = str;
        this._longName = str2;
        this._mimeType = str3;
        this._extensions = strArr;
    }

    public String getCommonExtension() {
        String[] strArr = this._extensions;
        if (strArr == null || strArr.length == 0) {
            return null;
        }
        return strArr[0];
    }
}
