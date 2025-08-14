package com.uxcam.internals;

import android.content.Context;
import com.onfido.android.sdk.capture.ui.camera.liveness.LivenessConstants;
import com.uxcam.internals.gk;
import com.uxcam.screenaction.di.ScreenActionModule;
import com.uxcam.screenaction.utils.Connectivity;
import com.uxcam.screenaction.utils.FilePath;
import com.uxcam.screenaction.utils.Util;
import com.uxcam.screenshot.di.ScreenshotModule;
import com.uxcam.service.HttpPostService;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import kotlin.jvm.internal.Intrinsics;

/* loaded from: classes6.dex */
public final class fm {

    /* renamed from: a, reason: collision with root package name */
    public final Context f154a;

    public class aa implements Comparator {
        @Override // java.util.Comparator
        public final int compare(Object obj, Object obj2) {
            File file = (File) obj;
            File file2 = (File) obj2;
            if (file.lastModified() > file2.lastModified()) {
                return -1;
            }
            return file.lastModified() < file2.lastModified() ? 1 : 0;
        }
    }

    public fm(Context context) {
        this.f154a = context;
    }

    public final void a() {
        int i = ga.f167a;
        try {
            a(true);
        } catch (Exception e) {
            gk.c.getClass();
            HashMap map = new HashMap();
            String strReplace = "[ #event# ]".replace("#event#", "Exception");
            map.put("site_of_error", "SendOfflineData::deleteOfflineData()");
            map.put("reason", e.getMessage());
            ht.a(strReplace, (Map<String, String>) map);
        }
    }

    public final void b() {
        int i = ga.f167a;
        try {
            a(false);
        } catch (Exception e) {
            gk.c.getClass();
            HashMap map = new HashMap();
            String strReplace = "[ #event# ]".replace("#event#", "Exception");
            map.put("site_of_error", "SendOfflineData::uploadOfflineData()");
            map.put("reason", e.getMessage());
            ht.a(strReplace, (Map<String, String>) map);
        }
    }

    public final void a(boolean z) {
        File[] fileArrListFiles;
        try {
            int i = ga.f167a;
            boolean z2 = true;
            File[] fileArrListFiles2 = new File(FilePath.getRootUrl(true)).listFiles();
            if (fileArrListFiles2 == null) {
                HashMap map = new HashMap();
                String strReplace = "[ #event# ]".replace("#event#", "Process offline files on session");
                map.put("site_of_error", "SendOfflineData::ProcessFilesInSDCard()");
                map.put("reason", "Folder is empty");
                ht.b(strReplace, map);
                return;
            }
            Arrays.sort(fileArrListFiles2, new aa());
            HashMap map2 = new HashMap();
            String strReplace2 = "[ #event# ]".replace("#event#", "Processing Previous Session");
            map2.put("site_of_error", "SendOfflineData::ProcessFilesInSDCard()");
            map2.put("sessionCount", "" + (fileArrListFiles2.length - 1));
            ht.b(strReplace2, map2);
            int length = fileArrListFiles2.length;
            int i2 = 0;
            while (i2 < length) {
                File file = fileArrListFiles2[i2];
                if (!file.getName().equals(ga.b) && (fileArrListFiles = file.listFiles()) != null) {
                    if (fileArrListFiles.length == 0) {
                        file.delete();
                    } else if (z) {
                        if (!Util.dataFileExist(file)) {
                            Util.deleteRecursive(file);
                        } else {
                            Util.deleteDataAndVideoFile(file);
                            String str = FilePath.getRootUrl(z2) + "/" + file.getName() + "/";
                            if (bi.D == null) {
                                bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
                            }
                            bi biVar = bi.D;
                            Intrinsics.checkNotNull(biVar);
                            if (biVar.g == null) {
                                biVar.g = new bp();
                            }
                            bl blVar = new bl(biVar.g);
                            blVar.c = 1;
                            blVar.a("", (fr) null, str);
                            ht.a("createdCancelledSessionFile", (HashMap) null);
                        }
                    } else if (Connectivity.isConnected(this.f154a, true)) {
                        if (bi.D == null) {
                            bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
                        }
                        bi biVar2 = bi.D;
                        Intrinsics.checkNotNull(biVar2);
                        hr hrVarL = biVar2.l();
                        String name = file.getName();
                        String str2 = ((hs) hrVarL).a().b;
                        Iterator it = ah.a().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            String str3 = (String) it.next();
                            if (name.endsWith(String.valueOf(str3.hashCode()))) {
                                str2 = str3;
                                break;
                            }
                        }
                        b(file, str2);
                    }
                }
                i2++;
                z2 = true;
            }
        } catch (Exception e) {
            gk.c.getClass();
            HashMap map3 = new HashMap();
            String strReplace3 = "[ #event# ]".replace("#event#", "Exception");
            map3.put("site_of_error", "SendOfflineData::ProcessFilesInSDCard()");
            map3.put("reason", e.getMessage());
            ht.a(strReplace3, (Map<String, String>) map3);
        }
    }

    public final void b(File file, String str) {
        File[] fileArrListFiles = file.listFiles(new FilenameFilter() { // from class: com.uxcam.internals.fm$$ExternalSyntheticLambda0
            @Override // java.io.FilenameFilter
            public final boolean accept(File file2, String str2) {
                return fm.a(file2, str2);
            }
        });
        if (fileArrListFiles == null) {
            return;
        }
        int length = fileArrListFiles.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            File file2 = fileArrListFiles[i];
            if (file2.length() == 0 && !FilePath.isIconFile(file2.getName())) {
                StringBuilder sb = new StringBuilder();
                int i2 = ga.f167a;
                String string = sb.append(FilePath.getRootUrl(true)).append("/").append(file2.getParentFile().getName()).append("/").toString();
                Util.deleteRecursive(file2.getParentFile());
                HashMap map = new HashMap();
                String strReplace = "[ #event# ]".replace("#event#", "Unexpected Session End");
                map.put("site_of_error", "SendOfflineData::deleteSessionIfUnexpectedlyClosed");
                map.put("directory", file2.getParentFile().getName());
                map.put("event_happened", "" + System.currentTimeMillis());
                ht.a(strReplace, (Map<String, String>) map);
                if (bi.D == null) {
                    bi.D = new bi(ScreenshotModule.INSTANCE.getInstance(), ScreenActionModule.INSTANCE.getInstance());
                }
                bi biVar = bi.D;
                Intrinsics.checkNotNull(biVar);
                if (biVar.g == null) {
                    biVar.g = new bp();
                }
                bl blVar = new bl(biVar.g);
                blVar.c = 3;
                blVar.a("", (fr) null, string);
                ht.a("createdCancelledSessionFile", (HashMap) null);
            } else {
                if (file2.getName().contains(".aes")) {
                    Util.deleteRecursive(file2);
                    HashMap map2 = new HashMap();
                    String strReplace2 = "[ #event# ]".replace("#event#", "Unexpected File found");
                    map2.put("site_of_error", "SendOfflineData::deleteSessionIfUnexpectedlyClosed");
                    map2.put("desc", "delete .aes file");
                    map2.put("directory", file2.getPath());
                    map2.put("event_happened", "" + System.currentTimeMillis());
                    ht.a(strReplace2, map2);
                } else if (file2.getName().contains(LivenessConstants.VIDEO_RECORDING_FILE_FORMAT)) {
                    try {
                        ih ihVar = new ih(file2);
                        ihVar.a();
                        Util.deleteRecursive(file2);
                        Util.deleteRecursive(ihVar.b);
                    } catch (Exception unused) {
                        HashMap map3 = new HashMap();
                        String strReplace3 = "[ #event# ]".replace("#event#", "Unexpected File found");
                        map3.put("site_of_error", "SendOfflineData::deleteSessionIfUnexpectedlyClosed");
                        map3.put("desc", "re-encrypt video file");
                        map3.put("directory", file2.getPath());
                        map3.put("event_happened", "" + System.currentTimeMillis());
                        ht.a(strReplace3, map3);
                    }
                }
                i++;
            }
        }
        if (fileArrListFiles.length > 0) {
            if (HttpPostService.a(file)) {
                ag agVar = new ag();
                agVar.d = true;
                Context context = this.f154a;
                agVar.f78a = context;
                agVar.b = file;
                if (file.exists()) {
                    new Cif(context).a(agVar, str);
                    return;
                }
                return;
            }
            gk.aa aaVarA = gk.a("fm");
            file.toString();
            aaVarA.getClass();
        }
    }

    public static /* synthetic */ boolean a(File file, String str) {
        return FilePath.isVideoFile(str) || FilePath.isDataFile(str) || FilePath.isIconFile(str);
    }
}
