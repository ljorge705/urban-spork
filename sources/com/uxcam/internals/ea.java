package com.uxcam.internals;

import com.clevertap.android.sdk.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import kotlin.Pair;
import okhttp3.Headers;
import okhttp3.Request;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public final class ea {

    /* renamed from: a, reason: collision with root package name */
    public final aa f128a;

    public static class aa {

        /* renamed from: a, reason: collision with root package name */
        public final ArrayList f129a = new ArrayList();
        public final List<ab> b;

        /* renamed from: com.uxcam.internals.ea$aa$aa, reason: collision with other inner class name */
        public static class C0192aa {

            /* renamed from: a, reason: collision with root package name */
            public final ArrayList f130a = new ArrayList();
            public final ArrayList b = new ArrayList();
        }

        public aa(ArrayList arrayList, ArrayList arrayList2) {
            this.b = arrayList2;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                this.f129a.add(Pattern.compile((String) it.next(), 2));
            }
        }
    }

    public static class ab {
    }

    public ea(eb ebVar, aa aaVar) {
        this.f128a = aaVar;
    }

    public final JSONObject a(Request request) throws JSONException {
        String host = request.url().url().getHost();
        Iterator<ab> it = this.f128a.b.iterator();
        if (it.hasNext()) {
            it.next().getClass();
            throw null;
        }
        String strMethod = request.method();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("protocol", "");
            jSONObject.put("requestUrl", host);
            jSONObject.put("httpVerb", strMethod);
            jSONObject.put("httpStatusCode", "-1");
            jSONObject.put("callDurationMs", -1);
            jSONObject.put("responseSizeBytes", -1);
            jSONObject.put("sentRequestAt", -1);
            jSONObject.put("receivedResponseAt", -1);
            jSONObject.put("requestHeaders", a(request.headers()));
            jSONObject.put("throwableMessage", "");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public final JSONArray a(Headers headers) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        Iterator<Pair<? extends String, ? extends String>> it = headers.iterator();
        while (it.hasNext()) {
            Pair<? extends String, ? extends String> next = it.next();
            JSONObject jSONObject = new JSONObject();
            String first = next.getFirst();
            Locale locale = Locale.ENGLISH;
            String lowerCase = first.toLowerCase(locale);
            String lowerCase2 = next.getSecond().toLowerCase(locale);
            try {
                jSONObject.put(Constants.KEY_KEY, lowerCase);
                jSONObject.put("value", lowerCase2);
            } catch (JSONException unused) {
            }
            Iterator it2 = this.f128a.f129a.iterator();
            boolean z = false;
            while (it2.hasNext()) {
                Pattern pattern = (Pattern) it2.next();
                boolean zFind = pattern.matcher(lowerCase).find(0);
                pattern.toString();
                z = zFind;
            }
            if (!z) {
                jSONArray.put(jSONObject);
            }
        }
        return jSONArray;
    }
}
