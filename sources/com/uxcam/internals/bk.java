package com.uxcam.internals;

import com.clevertap.android.sdk.Constants;
import com.uxcam.RNUxcamModule;
import com.uxcam.internals.gb;
import com.uxcam.screenshot.di.ScreenshotModule;
import com.uxcam.screenshot.model.UXCamBlur;
import com.uxcam.screenshot.model.UXCamOccludeAllTextFields;
import com.uxcam.screenshot.model.UXCamOcclusion;
import com.uxcam.screenshot.model.UXCamOverlay;
import com.uxcam.screenshot.repository.OcclusionRepository;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public final class bk implements bj {

    /* renamed from: a, reason: collision with root package name */
    public final OcclusionRepository f100a;

    public bk(OcclusionRepository occlusionRepository) {
        this.f100a = occlusionRepository;
    }

    @Override // com.uxcam.internals.bj
    public final void a(JSONObject jSONObject) {
        try {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("textFieldPrivacy");
            if (jSONArrayOptJSONArray == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            JSONObject jSONObject2 = null;
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObject3 = new JSONObject(jSONArrayOptJSONArray.get(i).toString());
                if (jSONObject3.optBoolean("isDefault", false)) {
                    jSONObject2 = jSONObject3;
                } else {
                    String strOptString = jSONObject3.optString("rule", "");
                    if (strOptString.equals("record")) {
                        arrayList.add(jSONObject3);
                    } else if (strOptString.equals("occludeTextFields")) {
                        ArrayList arrayList2 = new ArrayList();
                        JSONArray jSONArrayOptJSONArray2 = jSONObject3.optJSONArray(RNUxcamModule.SCREENS);
                        if (jSONArrayOptJSONArray2 != null) {
                            for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                                try {
                                    arrayList2.add(jSONArrayOptJSONArray2.getString(i2));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        this.f100a.applyOcclusionFromBackend(new UXCamOccludeAllTextFields.Builder().screens(arrayList2).build());
                    }
                }
            }
            if (jSONObject2 != null) {
                if (!arrayList.isEmpty()) {
                    a(arrayList);
                    return;
                }
                ArrayList arrayList3 = new ArrayList();
                JSONArray jSONArrayOptJSONArray3 = jSONObject2.optJSONArray(RNUxcamModule.SCREENS);
                if (jSONArrayOptJSONArray3 != null) {
                    for (int i3 = 0; i3 < jSONArrayOptJSONArray3.length(); i3++) {
                        try {
                            arrayList3.add(jSONArrayOptJSONArray3.getString(i3));
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                this.f100a.applyOcclusionFromBackend(new UXCamOccludeAllTextFields.Builder().screens(arrayList3).build());
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    @Override // com.uxcam.internals.bj
    public final void b(JSONObject jSONObject) {
        try {
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("videoPrivacy");
            boolean zOptBoolean = jSONObject.optBoolean("recordGestureForOccludedScreen", false);
            if (jSONArrayOptJSONArray == null) {
                a(jSONObject, zOptBoolean);
            } else {
                a(jSONArrayOptJSONArray, zOptBoolean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(boolean z, String str) {
        this.f100a.applyOcclusionFromBackend(new UXCamOverlay.Builder().screens(Collections.singletonList(str)).withoutGesture(!z).build());
    }

    public final void b(JSONObject jSONObject, boolean z) {
        UXCamOcclusion uXCamOcclusionA;
        String strOptString = jSONObject.optString("rule", "occlude");
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(RNUxcamModule.SCREENS);
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                try {
                    arrayList.add(jSONArrayOptJSONArray.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (strOptString.equals("occlude")) {
            UXCamOverlay.Builder builderWithoutGesture = new UXCamOverlay.Builder().withoutGesture(!z);
            builderWithoutGesture.screens(arrayList);
            uXCamOcclusionA = builderWithoutGesture.build();
        } else {
            uXCamOcclusionA = strOptString.equals("blur") ? a(jSONObject, arrayList, false, z) : null;
        }
        if (uXCamOcclusionA != null) {
            this.f100a.applyOcclusionFromBackend(uXCamOcclusionA);
        }
    }

    public final void a(ArrayList arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            JSONArray jSONArrayOptJSONArray = ((JSONObject) it.next()).optJSONArray(RNUxcamModule.SCREENS);
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    try {
                        arrayList2.add(jSONArrayOptJSONArray.getString(i));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        this.f100a.applyOcclusionFromBackend(new UXCamOccludeAllTextFields.Builder().screens(arrayList2).excludeMentionedScreens(true).build());
    }

    public final void a(JSONObject jSONObject, final boolean z) {
        if (jSONObject.optBoolean("occludeAllTextFields", false)) {
            this.f100a.applyOcclusionFromBackend(new UXCamOccludeAllTextFields.Builder().build());
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("screensNotToOcclude");
        gb.aa aaVar = new gb.aa() { // from class: com.uxcam.internals.bk$$ExternalSyntheticLambda0
            @Override // com.uxcam.internals.gb.aa
            public final void a(String str) {
                ScreenshotModule.getInstance().getOcclusionRepository().applyOcclusionFromBackend(new UXCamOverlay.Builder().screens(Collections.singletonList(str)).withoutGesture(!z).excludeMentionedScreens(true).build());
            }
        };
        if (jSONArrayOptJSONArray != null) {
            int length = jSONArrayOptJSONArray.length();
            for (int i = 0; i < length; i++) {
                try {
                    aaVar.a(jSONArrayOptJSONArray.get(i).toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("screensToOcclude");
        gb.aa aaVar2 = new gb.aa() { // from class: com.uxcam.internals.bk$$ExternalSyntheticLambda1
            @Override // com.uxcam.internals.gb.aa
            public final void a(String str) {
                this.f$0.b(z, str);
            }
        };
        if (jSONArrayOptJSONArray2 != null) {
            int length2 = jSONArrayOptJSONArray2.length();
            for (int i2 = 0; i2 < length2; i2++) {
                try {
                    aaVar2.a(jSONArrayOptJSONArray2.get(i2).toString());
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public final void a(JSONArray jSONArray, boolean z) {
        ArrayList arrayList = new ArrayList();
        try {
            this.f100a.removeAllOcclusionsFromBackend();
            UXCamOcclusion uXCamOcclusionA = null;
            JSONObject jSONObject = null;
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = new JSONObject(jSONArray.get(i).toString());
                if (jSONObject2.optBoolean("isDefault", false)) {
                    jSONObject = jSONObject2;
                } else if ("record".equals(jSONObject2.optString("rule", ""))) {
                    arrayList.add(jSONObject2);
                } else {
                    b(jSONObject2, z);
                }
            }
            if (jSONObject != null) {
                if (arrayList.isEmpty()) {
                    String strOptString = jSONObject.optString("rule", "occlude");
                    try {
                        if (strOptString.equals("occlude")) {
                            uXCamOcclusionA = new UXCamOverlay.Builder().withoutGesture(!z).build();
                        } else if (strOptString.equals("blur")) {
                            uXCamOcclusionA = a(jSONObject, null, true, z);
                        }
                        if (uXCamOcclusionA != null) {
                            this.f100a.applyOcclusionFromBackend(uXCamOcclusionA);
                            return;
                        }
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                a(jSONObject, arrayList, z);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static UXCamBlur a(JSONObject jSONObject, ArrayList arrayList, boolean z, boolean z2) {
        UXCamBlur.Builder builderWithoutGesture = new UXCamBlur.Builder().blurRadius(jSONObject.getJSONObject("config").optInt(Constants.KEY_RADIUS, ga.E)).withoutGesture(!z2);
        if (arrayList != null && !arrayList.isEmpty()) {
            builderWithoutGesture.screens(arrayList);
        }
        if (z && arrayList != null && !arrayList.isEmpty()) {
            builderWithoutGesture.excludeMentionedScreens(true);
        }
        return builderWithoutGesture.build();
    }

    public final void a(JSONObject jSONObject, ArrayList arrayList, boolean z) {
        UXCamOcclusion uXCamOcclusionA;
        String strOptString = jSONObject.optString("rule", "occlude");
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            JSONArray jSONArrayOptJSONArray = ((JSONObject) it.next()).optJSONArray(RNUxcamModule.SCREENS);
            if (jSONArrayOptJSONArray != null) {
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    try {
                        arrayList2.add(jSONArrayOptJSONArray.getString(i));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        try {
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (strOptString.equals("occlude")) {
            UXCamOverlay.Builder builderWithoutGesture = new UXCamOverlay.Builder().withoutGesture(!z);
            builderWithoutGesture.screens(arrayList2);
            if (!arrayList2.isEmpty()) {
                builderWithoutGesture.excludeMentionedScreens(true);
            }
            uXCamOcclusionA = builderWithoutGesture.build();
        } else {
            uXCamOcclusionA = strOptString.equals("blur") ? a(jSONObject, arrayList2, true, z) : null;
        }
        if (uXCamOcclusionA != null) {
            this.f100a.applyOcclusionFromBackend(uXCamOcclusionA);
        }
    }
}
