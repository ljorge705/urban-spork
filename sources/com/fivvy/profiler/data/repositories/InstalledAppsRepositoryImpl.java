package com.fivvy.profiler.data.repositories;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.util.Base64;
import com.fivvy.profiler.domain.models.AppInstalledInfo;
import com.fivvy.profiler.domain.repositories.InstalledAppsRepository;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class InstalledAppsRepositoryImpl implements InstalledAppsRepository {
    private final Context context;

    private String getCategories(int i) {
        switch (i) {
            case 0:
                return "Game";
            case 1:
                return "Audio";
            case 2:
                return "Video";
            case 3:
                return "Image";
            case 4:
                return "Social";
            case 5:
                return "News";
            case 6:
                return "Maps";
            case 7:
                return "Productivity";
            case 8:
                return "Accessibility";
            default:
                return "Unknown";
        }
    }

    public InstalledAppsRepositoryImpl(Context context) {
        this.context = context;
    }

    @Override // com.fivvy.profiler.domain.repositories.InstalledAppsRepository
    public List<AppInstalledInfo> getInstalledApps() throws PackageManager.NameNotFoundException {
        Iterator<ResolveInfo> it;
        ArrayList arrayList = new ArrayList();
        String str = null;
        Intent intent = new Intent("android.intent.action.MAIN", (Uri) null);
        intent.addCategory("android.intent.category.LAUNCHER");
        PackageManager packageManager = this.context.getPackageManager();
        int i = 0;
        Iterator<ResolveInfo> it2 = packageManager.queryIntentActivities(intent, 0).iterator();
        while (it2.hasNext()) {
            ResolveInfo next = it2.next();
            String string = next.loadLabel(packageManager).toString();
            String str2 = next.activityInfo.packageName;
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(str2, i);
                ApplicationInfo applicationInfo = packageInfo.applicationInfo;
                String strValueOf = Build.VERSION.SDK_INT >= 28 ? String.valueOf(packageInfo.getLongVersionCode()) : str;
                String str3 = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault()).format(Long.valueOf(packageInfo.firstInstallTime));
                String str4 = packageInfo.versionName;
                String strBitmapToBase64 = bitmapToBase64(drawableToBitmap(next.loadIcon(packageManager)));
                String.valueOf(applicationInfo.icon);
                it = it2;
                try {
                    arrayList.add(new AppInstalledInfo(string, str2, strValueOf, str3, str4, strBitmapToBase64, new SimpleDateFormat("yyyy.MM.dd HH:mm:ss", Locale.getDefault()).format(Long.valueOf(packageInfo.lastUpdateTime)), Build.VERSION.SDK_INT >= 26 ? getCategories(applicationInfo.category) : null));
                } catch (PackageManager.NameNotFoundException e) {
                    e = e;
                    e.printStackTrace();
                    it2 = it;
                    str = null;
                    i = 0;
                }
            } catch (PackageManager.NameNotFoundException e2) {
                e = e2;
                it = it2;
            }
            it2 = it;
            str = null;
            i = 0;
        }
        return arrayList;
    }

    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        if (intrinsicWidth <= 0) {
            intrinsicWidth = 1;
        }
        int intrinsicHeight = drawable.getIntrinsicHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight > 0 ? intrinsicHeight : 1, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmapCreateBitmap;
    }

    private String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
    }
}
