package com.uxcam.internals;

import com.onfido.android.sdk.capture.analytics.OnfidoAnalyticsEventResultReceiver;
import com.uxcam.internals.gk;
import com.uxcam.screenaction.models.KeyConstant;
import com.uxcam.screenaction.utils.Util;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.ClassUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public final class cb implements ca {

    /* renamed from: a, reason: collision with root package name */
    public final gv f106a;

    public cb(gv timelineRepository) {
        Intrinsics.checkNotNullParameter(timelineRepository, "timelineRepository");
        this.f106a = timelineRepository;
    }

    public final void a(String str, Map<?, ?> map) {
        float currentUxcamTime;
        if (str == null) {
            return;
        }
        HashMap map2 = new HashMap();
        int[] iArr = ga.y;
        int i = 0;
        int i2 = iArr[0];
        int i3 = iArr[1];
        int i4 = iArr[2];
        if (bh.f97a) {
            currentUxcamTime = Util.getCurrentUxcamTime(fp.f157n);
        } else {
            int i5 = fp.o;
            currentUxcamTime = 0.0f;
        }
        if (currentUxcamTime <= 0.0f) {
            return;
        }
        float fB = currentUxcamTime - this.f106a.b();
        float f = fB >= 0.0f ? fB : 0.0f;
        if (this.f106a.l().size() < i2 && a(str) <= 255) {
            if (map != null && map.size() <= i3) {
                Iterator<Map.Entry<?, ?>> it = map.entrySet().iterator();
                while (it.hasNext()) {
                    Map.Entry<?, ?> next = it.next();
                    Intrinsics.checkNotNull(next, "null cannot be cast to non-null type kotlin.collections.Map.Entry<*, *>");
                    if (next.getKey() != null) {
                        Object value = next.getValue();
                        if (value == null) {
                            value = "";
                        }
                        if (i < i3 && a(String.valueOf(next.getKey())) <= i4 && a(value.toString()) <= i4) {
                            map2.put(String.valueOf(next.getKey()), value);
                        } else {
                            Objects.toString(next.getKey());
                            value.toString();
                        }
                        i++;
                    }
                }
            } else if (map != null) {
                String str2 = "Too many properties in this event: " + map.size() + ". Limit is " + i3 + ClassUtils.PACKAGE_SEPARATOR_CHAR;
                map2.put("_UXCam_Overload", str2);
                gk.a("UXCam").a(str2, new Object[0]);
            }
            gv gvVar = this.f106a;
            gvVar.a(new bz(str, f, gvVar.g(), map2));
        }
    }

    @Override // com.uxcam.internals.ca
    public final void a(String event, float f, HashMap params) {
        String strG;
        Intrinsics.checkNotNullParameter(event, "event");
        Intrinsics.checkNotNullParameter(params, "params");
        gk.aa aaVarA = gk.a("rageClickDetector");
        Objects.toString(params);
        aaVarA.getClass();
        if (Intrinsics.areEqual("rageTap", event)) {
            strG = (String) params.get("activity");
        } else {
            strG = this.f106a.g();
        }
        bz bzVar = new bz(event, f, strG, params);
        bzVar.e = true;
        this.f106a.b(bzVar);
    }

    @Override // com.uxcam.internals.ca
    public final void a(String str, JSONObject jSONObject, Map<?, ?> map) throws JSONException {
        float currentUxcamTime;
        if (str == null) {
            return;
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            int[] iArr = ga.y;
            int i = 0;
            int i2 = iArr[0];
            int i3 = iArr[1];
            int i4 = iArr[2];
            if (this.f106a.l().size() < i2 && a(str) <= 255) {
                if (map != null && map.size() <= i3) {
                    for (Map.Entry<?, ?> entry : map.entrySet()) {
                        Object key = entry.getKey();
                        Object value = entry.getValue();
                        if (i >= i3) {
                            Objects.toString(key);
                            Objects.toString(value);
                        } else if (a(String.valueOf(key)) > i4) {
                            Objects.toString(key);
                            Objects.toString(value);
                        } else if (a(String.valueOf(value)) > i4) {
                            Objects.toString(key);
                            Objects.toString(value);
                        } else {
                            jSONObject2.put(String.valueOf(key), String.valueOf(value));
                        }
                        i++;
                    }
                } else if (map != null) {
                    String str2 = "Too many properties in this event: " + map.size() + ". Limit is " + i3 + ClassUtils.PACKAGE_SEPARATOR_CHAR;
                    jSONObject2.put("_UXCam_Overload", str2);
                    gk.a("UXCam").a(str2, new Object[0]);
                }
            }
            if (str.length() > 0) {
                jSONObject.put("name", str);
            }
            jSONObject.put(OnfidoAnalyticsEventResultReceiver.KEY_PROPERTIES, jSONObject2);
            jSONObject.put(KeyConstant.KEY_SCREEN, this.f106a.g());
            jSONObject.put("timeStamp", System.currentTimeMillis());
            if (bh.f97a) {
                currentUxcamTime = Util.getCurrentUxcamTime(fp.f157n);
            } else {
                int i5 = fp.o;
                currentUxcamTime = 0.0f;
            }
            jSONObject.put("timeline", currentUxcamTime);
            this.f106a.b(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static int a(String str) {
        Charset UTF_8 = StandardCharsets.UTF_8;
        Intrinsics.checkNotNullExpressionValue(UTF_8, "UTF_8");
        byte[] bytes = str.getBytes(UTF_8);
        Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
        return bytes.length;
    }
}
