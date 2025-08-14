package agency.flexible.react.modules.email;

import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import androidx.core.net.MailTo;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public class EmailModule extends ReactContextBaseJavaModule {
    @Override // com.facebook.react.bridge.NativeModule
    public String getName() {
        return "Email";
    }

    public EmailModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public void open(String str, boolean z, Promise promise) {
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(MailTo.MAILTO_SCHEME));
        PackageManager packageManager = getCurrentActivity().getPackageManager();
        List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        if (listQueryIntentActivities.size() > 0) {
            Intent intentCreateLaunchIntent = createLaunchIntent(listQueryIntentActivities.get(0), z);
            if (intentCreateLaunchIntent != null) {
                Intent intentCreateChooser = Intent.createChooser(intentCreateLaunchIntent, str);
                ArrayList arrayList = new ArrayList();
                for (int i = 1; i < listQueryIntentActivities.size(); i++) {
                    ResolveInfo resolveInfo = listQueryIntentActivities.get(i);
                    String str2 = resolveInfo.activityInfo.packageName;
                    Intent intentCreateLaunchIntent2 = createLaunchIntent(resolveInfo, z);
                    if (intentCreateLaunchIntent2 != null) {
                        arrayList.add(new LabeledIntent(intentCreateLaunchIntent2, str2, resolveInfo.loadLabel(packageManager), resolveInfo.icon));
                    }
                }
                intentCreateChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (LabeledIntent[]) arrayList.toArray(new LabeledIntent[arrayList.size()]));
                setNewTaskFlag(intentCreateChooser, z);
                getCurrentActivity().startActivity(intentCreateChooser);
            }
            promise.resolve(true);
            return;
        }
        promise.reject("NoEmailAppsAvailable", "No email apps available");
    }

    @ReactMethod
    public void compose(String str, String str2, String str3, String str4) {
        Intent intent = new Intent("android.intent.action.SENDTO");
        intent.setData(Uri.parse(MailTo.MAILTO_SCHEME + Uri.encode(str2) + "?subject=" + Uri.encode(str3) + "&body=" + Uri.encode(str4)));
        Intent intentCreateChooser = Intent.createChooser(intent, str);
        intentCreateChooser.setFlags(268435456);
        getReactApplicationContext().startActivity(intentCreateChooser);
    }

    @Nullable
    private Intent createLaunchIntent(ResolveInfo resolveInfo, boolean z) {
        Intent launchIntentForPackage = getCurrentActivity().getPackageManager().getLaunchIntentForPackage(resolveInfo.activityInfo.packageName);
        if (launchIntentForPackage != null) {
            setNewTaskFlag(launchIntentForPackage, z);
        }
        return launchIntentForPackage;
    }

    private void setNewTaskFlag(Intent intent, boolean z) {
        if (z) {
            intent.addFlags(268435456);
        } else {
            intent.setFlags(intent.getFlags() & (-268435457));
        }
    }
}
