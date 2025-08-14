package com.microsoft.codepush.react;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class CodePushUpdateManager {
    private String mDocumentsDirectory;

    private String getDocumentsDirectory() {
        return this.mDocumentsDirectory;
    }

    public CodePushUpdateManager(String str) {
        this.mDocumentsDirectory = str;
    }

    private String getDownloadFilePath() {
        return CodePushUtils.appendPathComponent(getCodePushPath(), CodePushConstants.DOWNLOAD_FILE_NAME);
    }

    private String getUnzippedFolderPath() {
        return CodePushUtils.appendPathComponent(getCodePushPath(), CodePushConstants.UNZIPPED_FOLDER_NAME);
    }

    private String getCodePushPath() {
        String strAppendPathComponent = CodePushUtils.appendPathComponent(getDocumentsDirectory(), "CodePush");
        return CodePush.isUsingTestConfiguration() ? CodePushUtils.appendPathComponent(strAppendPathComponent, "TestPackages") : strAppendPathComponent;
    }

    private String getStatusFilePath() {
        return CodePushUtils.appendPathComponent(getCodePushPath(), CodePushConstants.STATUS_FILE);
    }

    public JSONObject getCurrentPackageInfo() {
        String statusFilePath = getStatusFilePath();
        if (!FileUtils.fileAtPathExists(statusFilePath)) {
            return new JSONObject();
        }
        try {
            return CodePushUtils.getJsonObjectFromFile(statusFilePath);
        } catch (IOException e) {
            throw new CodePushUnknownException("Error getting current package info", e);
        }
    }

    public void updateCurrentPackageInfo(JSONObject jSONObject) throws Throwable {
        try {
            CodePushUtils.writeJsonToFile(jSONObject, getStatusFilePath());
        } catch (IOException e) {
            throw new CodePushUnknownException("Error updating current package info", e);
        }
    }

    public String getCurrentPackageFolderPath() {
        String strOptString = getCurrentPackageInfo().optString(CodePushConstants.CURRENT_PACKAGE_KEY, null);
        if (strOptString == null) {
            return null;
        }
        return getPackageFolderPath(strOptString);
    }

    public String getCurrentPackageBundlePath(String str) {
        JSONObject currentPackage;
        String currentPackageFolderPath = getCurrentPackageFolderPath();
        if (currentPackageFolderPath == null || (currentPackage = getCurrentPackage()) == null) {
            return null;
        }
        String strOptString = currentPackage.optString(CodePushConstants.RELATIVE_BUNDLE_PATH_KEY, null);
        if (strOptString == null) {
            return CodePushUtils.appendPathComponent(currentPackageFolderPath, str);
        }
        return CodePushUtils.appendPathComponent(currentPackageFolderPath, strOptString);
    }

    public String getPackageFolderPath(String str) {
        return CodePushUtils.appendPathComponent(getCodePushPath(), str);
    }

    public String getCurrentPackageHash() {
        return getCurrentPackageInfo().optString(CodePushConstants.CURRENT_PACKAGE_KEY, null);
    }

    public String getPreviousPackageHash() {
        return getCurrentPackageInfo().optString(CodePushConstants.PREVIOUS_PACKAGE_KEY, null);
    }

    public JSONObject getCurrentPackage() {
        String currentPackageHash = getCurrentPackageHash();
        if (currentPackageHash == null) {
            return null;
        }
        return getPackage(currentPackageHash);
    }

    public JSONObject getPreviousPackage() {
        String previousPackageHash = getPreviousPackageHash();
        if (previousPackageHash == null) {
            return null;
        }
        return getPackage(previousPackageHash);
    }

    public JSONObject getPackage(String str) {
        try {
            return CodePushUtils.getJsonObjectFromFile(CodePushUtils.appendPathComponent(getPackageFolderPath(str), CodePushConstants.PACKAGE_FILE_NAME));
        } catch (IOException unused) {
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:125:0x023d A[Catch: IOException -> 0x0239, TryCatch #0 {IOException -> 0x0239, blocks: (B:121:0x0235, B:125:0x023d, B:127:0x0242, B:129:0x0247), top: B:134:0x0235 }] */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0242 A[Catch: IOException -> 0x0239, TryCatch #0 {IOException -> 0x0239, blocks: (B:121:0x0235, B:125:0x023d, B:127:0x0242, B:129:0x0247), top: B:134:0x0235 }] */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0247 A[Catch: IOException -> 0x0239, TRY_LEAVE, TryCatch #0 {IOException -> 0x0239, blocks: (B:121:0x0235, B:125:0x023d, B:127:0x0242, B:129:0x0247), top: B:134:0x0235 }] */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0235 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void downloadPackage(org.json.JSONObject r26, java.lang.String r27, com.microsoft.codepush.react.DownloadProgressCallback r28, java.lang.String r29) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 594
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.microsoft.codepush.react.CodePushUpdateManager.downloadPackage(org.json.JSONObject, java.lang.String, com.microsoft.codepush.react.DownloadProgressCallback, java.lang.String):void");
    }

    public void installPackage(JSONObject jSONObject, boolean z) {
        String strOptString = jSONObject.optString("packageHash", null);
        JSONObject currentPackageInfo = getCurrentPackageInfo();
        String strOptString2 = currentPackageInfo.optString(CodePushConstants.CURRENT_PACKAGE_KEY, null);
        if (strOptString == null || !strOptString.equals(strOptString2)) {
            if (z) {
                String currentPackageFolderPath = getCurrentPackageFolderPath();
                if (currentPackageFolderPath != null) {
                    FileUtils.deleteDirectoryAtPath(currentPackageFolderPath);
                }
            } else {
                String previousPackageHash = getPreviousPackageHash();
                if (previousPackageHash != null && !previousPackageHash.equals(strOptString)) {
                    FileUtils.deleteDirectoryAtPath(getPackageFolderPath(previousPackageHash));
                }
                CodePushUtils.setJSONValueForKey(currentPackageInfo, CodePushConstants.PREVIOUS_PACKAGE_KEY, currentPackageInfo.optString(CodePushConstants.CURRENT_PACKAGE_KEY, null));
            }
            CodePushUtils.setJSONValueForKey(currentPackageInfo, CodePushConstants.CURRENT_PACKAGE_KEY, strOptString);
            updateCurrentPackageInfo(currentPackageInfo);
        }
    }

    public void rollbackPackage() throws Throwable {
        JSONObject currentPackageInfo = getCurrentPackageInfo();
        FileUtils.deleteDirectoryAtPath(getCurrentPackageFolderPath());
        CodePushUtils.setJSONValueForKey(currentPackageInfo, CodePushConstants.CURRENT_PACKAGE_KEY, currentPackageInfo.optString(CodePushConstants.PREVIOUS_PACKAGE_KEY, null));
        CodePushUtils.setJSONValueForKey(currentPackageInfo, CodePushConstants.PREVIOUS_PACKAGE_KEY, null);
        updateCurrentPackageInfo(currentPackageInfo);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 4, insn: 0x007a: MOVE (r1 I:??[OBJECT, ARRAY]) = (r4 I:??[OBJECT, ARRAY]), block:B:42:0x007a */
    /* JADX WARN: Type inference failed for: r11v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v13, types: [java.io.FileOutputStream, java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r11v6, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r11v8 */
    public void downloadAndReplaceCurrentBundle(String str, String str2) throws IOException {
        HttpURLConnection httpURLConnection;
        BufferedInputStream bufferedInputStream;
        Object obj;
        BufferedOutputStream bufferedOutputStream;
        Object obj2;
        MalformedURLException e;
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                try {
                    bufferedInputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                } catch (MalformedURLException e2) {
                    e = e2;
                    obj = null;
                    obj2 = obj;
                    e = e;
                    throw new CodePushMalformedDataException(str, e);
                } catch (Throwable th) {
                    th = th;
                    str2 = 0;
                    bufferedInputStream = null;
                }
            } catch (Throwable th2) {
                th = th2;
                bufferedOutputStream2 = bufferedOutputStream;
            }
        } catch (MalformedURLException e3) {
            e = e3;
            obj = null;
        } catch (Throwable th3) {
            th = th3;
            str2 = 0;
            httpURLConnection = null;
            bufferedInputStream = null;
        }
        try {
            File file = new File(getCurrentPackageBundlePath(str2));
            file.delete();
            str2 = new FileOutputStream(file);
            try {
                BufferedOutputStream bufferedOutputStream3 = new BufferedOutputStream(str2, 262144);
                try {
                    byte[] bArr = new byte[262144];
                    while (true) {
                        int i = bufferedInputStream.read(bArr, 0, 262144);
                        if (i >= 0) {
                            bufferedOutputStream3.write(bArr, 0, i);
                        } else {
                            try {
                                break;
                            } catch (IOException e4) {
                                throw new CodePushUnknownException("Error closing IO resources.", e4);
                            }
                        }
                    }
                    bufferedOutputStream3.close();
                    str2.close();
                    bufferedInputStream.close();
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                } catch (MalformedURLException e5) {
                    e = e5;
                    throw new CodePushMalformedDataException(str, e);
                }
            } catch (MalformedURLException e6) {
                e = e6;
            } catch (Throwable th4) {
                th = th4;
                if (bufferedOutputStream2 != null) {
                    try {
                        bufferedOutputStream2.close();
                    } catch (IOException e7) {
                        throw new CodePushUnknownException("Error closing IO resources.", e7);
                    }
                }
                if (str2 != 0) {
                    str2.close();
                }
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (MalformedURLException e8) {
            e = e8;
            obj2 = null;
            e = e;
            throw new CodePushMalformedDataException(str, e);
        } catch (Throwable th5) {
            th = th5;
            str2 = 0;
        }
    }

    public void clearUpdates() {
        FileUtils.deleteDirectoryAtPath(getCodePushPath());
    }
}
