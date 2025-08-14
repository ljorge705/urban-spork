package com.uxcam.screenaction.utils;

import android.os.Environment;
import com.clevertap.android.sdk.Constants;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConstants;
import com.uxcam.screenaction.models.KeyConstant;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* loaded from: classes6.dex */
public class FilePath {
    public static File getAlbumStorageDir(String str) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES), str);
        file.mkdirs();
        return file;
    }

    public static String getAppRootUrl() {
        try {
            return Util.getCurrentApplicationContext().getFilesDir().getPath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getExtension(String str) {
        if (str == null) {
            return null;
        }
        int iIndexOfExtension = indexOfExtension(str);
        return iIndexOfExtension == 1 ? "" : str.substring(iIndexOfExtension + 1);
    }

    public static String getIconFileName() {
        return "icon.png";
    }

    public static String getRootUrl(boolean z) {
        return z ? getAppRootUrl() + "/UXCam" : getAlbumStorageDir("UXBrowser").getAbsolutePath();
    }

    public static String getScreenFileName(Boolean bool) {
        if (bool.booleanValue()) {
            return "video.mp4";
        }
        return new SimpleDateFormat("yyyy-MM-dd_HH-mm", Locale.ENGLISH).format(new Date()) + LivenessConstants.VIDEO_RECORDING_FILE_FORMAT;
    }

    public static String getScreenVideoImageUrl(String str, Boolean bool) {
        return getSessionRootUrl(str, bool);
    }

    public static String getSessionRootUrl(String str, Boolean bool) {
        return getRootUrl(bool.booleanValue()) + "/" + str + "/";
    }

    public static String getTextFileName(Boolean bool) {
        return bool.booleanValue() ? "data.zip" : "data.txt";
    }

    private static int indexOfExtension(String str) {
        int iLastIndexOf;
        if (str != null && str.lastIndexOf("/") <= (iLastIndexOf = str.lastIndexOf("."))) {
            return iLastIndexOf;
        }
        return 1;
    }

    public static boolean isDataFile(String str) {
        return str.startsWith("data");
    }

    public static boolean isIconFile(String str) {
        return str.startsWith(Constants.KEY_ICON);
    }

    public static boolean isUxcamRootFolderEmpty(Boolean bool) {
        File[] fileArrListFiles = new File(getRootUrl(bool.booleanValue())).listFiles();
        return fileArrListFiles != null && fileArrListFiles.length == 0;
    }

    public static boolean isVideoFile(String str) {
        return str.startsWith("video") || str.startsWith(KeyConstant.KEY_SCREEN);
    }

    private static String removeExtension(String str) {
        if (str == null) {
            return null;
        }
        int iIndexOfExtension = indexOfExtension(str);
        return iIndexOfExtension == 1 ? str : str.substring(0, iIndexOfExtension);
    }

    public static String replaceExtensioin(String str, String str2) {
        return removeExtension(str) + "." + str2;
    }
}
