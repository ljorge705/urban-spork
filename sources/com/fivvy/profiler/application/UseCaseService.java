package com.fivvy.profiler.application;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.work.Data;
import com.fivvy.profiler.R;
import com.fivvy.profiler.data.repositories.AppUsageRepositoryImpl;
import com.fivvy.profiler.data.repositories.DeviceInfoRepositoryImpl;
import com.fivvy.profiler.data.repositories.InstalledAppsRepositoryImpl;
import com.fivvy.profiler.data.repositories.SendDataRepositoryImpl;
import com.fivvy.profiler.domain.models.AppInstalledInfo;
import com.fivvy.profiler.domain.models.AppUsageInfo;
import com.fivvy.profiler.domain.models.DeviceInfo;
import com.fivvy.profiler.domain.usecases.GetAppUsage;
import com.fivvy.profiler.domain.usecases.GetDeviceInfo;
import com.fivvy.profiler.domain.usecases.GetInstalledApps;
import com.fivvy.profiler.domain.usecases.OpenUsageSettings;
import com.fivvy.profiler.domain.usecases.OpenUsageSettingsDirectly;
import com.fivvy.profiler.domain.usecases.OpenUsageSettingsNative;
import com.fivvy.profiler.domain.usecases.SendDataUseCase;
import com.fivvy.profiler.domain.usecases.interfaces.IGetAppUsageUseCase;
import com.fivvy.profiler.domain.usecases.interfaces.IGetDeviceInfoUseCase;
import com.fivvy.profiler.domain.usecases.interfaces.IGetInstalledAppsUseCase;
import com.fivvy.profiler.domain.usecases.interfaces.IOpenUsageSettingsDirectlyDirectly;
import com.fivvy.profiler.domain.usecases.interfaces.IOpenUsageSettingsUseCase;
import com.fivvy.profiler.domain.usecases.interfaces.ISendDataUseCase;
import com.google.common.net.HttpHeaders;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class UseCaseService {
    private final Context context;
    private final IGetAppUsageUseCase getAppUsageUseCase;
    private final IGetDeviceInfoUseCase getDeviceInfoUseCase;
    private final IGetInstalledAppsUseCase getInstalledAppsUseCase;
    private final IOpenUsageSettingsUseCase openUsageAccessSettings;
    private final IOpenUsageSettingsUseCase openUsageAccessSettingsNative;
    private final IOpenUsageSettingsDirectlyDirectly openUsageSettingsDirectly;
    private final ISendDataUseCase sendDataUseCase;

    public UseCaseService(Context context) {
        this.context = context;
        this.getAppUsageUseCase = new GetAppUsage(new AppUsageRepositoryImpl(context));
        this.getDeviceInfoUseCase = new GetDeviceInfo(new DeviceInfoRepositoryImpl(context));
        this.getInstalledAppsUseCase = new GetInstalledApps(new InstalledAppsRepositoryImpl(context));
        this.openUsageAccessSettings = new OpenUsageSettings(new AppUsageRepositoryImpl(context));
        this.openUsageAccessSettingsNative = new OpenUsageSettingsNative(new AppUsageRepositoryImpl(context));
        this.sendDataUseCase = new SendDataUseCase(new SendDataRepositoryImpl(context));
        this.openUsageSettingsDirectly = new OpenUsageSettingsDirectly(new AppUsageRepositoryImpl(context));
    }

    public Data createSendContextualInformation(Map<String, Object> map, String str, String str2, String str3, String str4) {
        return this.sendDataUseCase.execute(map, str, str2, str3, str4);
    }

    public List<AppUsageInfo> createGetAppUsageUseCase(int i) {
        return this.getAppUsageUseCase.execute(i);
    }

    public DeviceInfo createGetDeviceInfoUseCase() {
        return this.getDeviceInfoUseCase.execute();
    }

    public List<AppInstalledInfo> createGetInstalledAppsUseCase() {
        return this.getInstalledAppsUseCase.execute();
    }

    public void createOpenUsageSettingsUseCase(String str, String str2, String str3, byte[] bArr, String str4, String str5, String str6, String str7) {
        Context context = this.context;
        if (context instanceof Activity) {
            openUsageSettings((Activity) context, str, str2, str3, bArr, str4, str5, str6, str7);
        } else {
            Log.e("UseCaseService", "Unsupported context type for opening usage settings");
        }
    }

    public void createOpenUsageSettingsUseCaseNative(String str, String str2, String str3, byte[] bArr, String str4, String str5, String str6, String str7) {
        Context context = this.context;
        if (context instanceof Activity) {
            openUsageSettings((Activity) context, str, str2, str3, bArr, str4, str5, str6, str7);
        } else {
            Log.e("UseCaseService", "Unsupported context type for opening usage settings");
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void openUsageSettings(Activity activity, final String str, final String str2, final String str3, final byte[] bArr, final String str4, String str5, String str6, String str7) {
        View viewInflate = activity.getLayoutInflater().inflate(R.layout.dialog_confirm, (ViewGroup) null);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.dialog_image);
        TextView textView = (TextView) viewInflate.findViewById(R.id.dialog_title);
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.dialog_message_1);
        TextView textView3 = (TextView) viewInflate.findViewById(R.id.dialog_message_2);
        Button button = (Button) viewInflate.findViewById(R.id.dialog_cancel);
        Button button2 = (Button) viewInflate.findViewById(R.id.dialog_accept);
        try {
            imageView.setImageDrawable(Drawable.createFromStream(this.context.getAssets().open("iddle_modal_image.png"), null));
            final AlertDialog alertDialogCreate = new AlertDialog.Builder(activity).setView(viewInflate).create();
            str.hashCode();
            char c = 65535;
            switch (str.hashCode()) {
                case 2217:
                    if (str.equals("EN")) {
                        c = 0;
                        break;
                    }
                    break;
                case 2222:
                    if (str.equals("ES")) {
                        c = 1;
                        break;
                    }
                    break;
                case 2562:
                    if (str.equals("PR")) {
                        c = 2;
                        break;
                    }
                    break;
            }
            String str8 = "Cancelar";
            String str9 = HttpHeaders.ACCEPT;
            switch (c) {
                case 0:
                default:
                    str8 = "Cancel";
                    break;
                case 1:
                    str9 = "Aceptar";
                    break;
                case 2:
                    str9 = "Aceitar";
                    break;
            }
            button.setText(str8);
            button2.setText(str9);
            textView.setText(str5);
            textView2.setText(str6);
            textView3.setText(str7);
            button.setOnClickListener(new View.OnClickListener() { // from class: com.fivvy.profiler.application.UseCaseService$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    alertDialogCreate.dismiss();
                }
            });
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.fivvy.profiler.application.UseCaseService$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m5125x9170d7c2(alertDialogCreate, str, str2, str3, bArr, str4, view);
                }
            });
            alertDialogCreate.show();
        } catch (IOException unused) {
        }
    }

    /* renamed from: lambda$openUsageSettings$1$com-fivvy-profiler-application-UseCaseService, reason: not valid java name */
    /* synthetic */ void m5125x9170d7c2(AlertDialog alertDialog, String str, String str2, String str3, byte[] bArr, String str4, View view) {
        alertDialog.dismiss();
        IOpenUsageSettingsUseCase iOpenUsageSettingsUseCase = this.openUsageAccessSettings;
        if (iOpenUsageSettingsUseCase != null) {
            iOpenUsageSettingsUseCase.execute(str, str2, str3, bArr, str4);
            return;
        }
        IOpenUsageSettingsUseCase iOpenUsageSettingsUseCase2 = this.openUsageAccessSettingsNative;
        if (iOpenUsageSettingsUseCase2 != null) {
            iOpenUsageSettingsUseCase2.execute(str, str2, str3, bArr, str4);
        }
    }

    public void createOpenUsageSettingsDirectlyUseCase() {
        this.openUsageSettingsDirectly.execute();
    }
}
