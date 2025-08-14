package com.uxcam.internals;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: classes6.dex */
public final class eg {

    /* renamed from: a, reason: collision with root package name */
    public final SharedPreferences f135a;

    public eg(Context context) {
        if (context != null) {
            this.f135a = context.getSharedPreferences("UXCamPreferences", 0);
        }
    }

    public final int b() {
        SharedPreferences sharedPreferences = this.f135a;
        if (sharedPreferences == null) {
            return 0;
        }
        return sharedPreferences.getInt("recorded_video_count", 0);
    }

    public final void a(long j) {
        SharedPreferences sharedPreferences = this.f135a;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putLong("mobile_data_used_size", j).apply();
        }
    }

    public final void a(String str, String str2) {
        SharedPreferences sharedPreferences = this.f135a;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString(str, str2).apply();
        }
    }

    public final void a(String str, boolean z) {
        SharedPreferences sharedPreferences = this.f135a;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putBoolean(str, z).apply();
        }
    }

    public final void a(String str) {
        SharedPreferences sharedPreferences = this.f135a;
        if (sharedPreferences != null) {
            sharedPreferences.edit().remove(str).apply();
        }
    }

    public final void a(String str, int i) {
        SharedPreferences sharedPreferences = this.f135a;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt(str, i).apply();
        }
    }

    public final int a() {
        SharedPreferences sharedPreferences = this.f135a;
        if (sharedPreferences == null) {
            return 0;
        }
        return sharedPreferences.getInt("recorded_session_count", 0);
    }
}
