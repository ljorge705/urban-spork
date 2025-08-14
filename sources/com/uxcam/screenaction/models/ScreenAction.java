package com.uxcam.screenaction.models;

import android.graphics.Rect;
import com.clevertap.android.sdk.Constants;
import com.clevertap.android.sdk.product_config.CTProductConfigConstants;
import com.facebook.react.uimanager.ViewProps;
import io.reactivex.rxjava3.annotations.SchedulerSupport;
import io.sentry.protocol.ViewHierarchyNode;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0005\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010J\u0018\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\u00052\b\u0010#\u001a\u0004\u0018\u00010\u0001J\u0010\u0010*\u001a\u00020(2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0005J\u000e\u0010+\u001a\u00020(2\u0006\u0010\u001b\u001a\u00020\u0005J\u0010\u0010,\u001a\u00020(2\b\u0010#\u001a\u0004\u0018\u00010\u0005J\"\u0010-\u001a\u00020(2\u001a\u0010$\u001a\u0016\u0012\u0004\u0012\u00020\r\u0018\u00010%j\n\u0012\u0004\u0012\u00020\r\u0018\u0001`&R\u000e\u0010\u000b\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\"\u0010\u001b\u001a\u0004\u0018\u00010\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\u0005@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0016R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"R\u0012\u0010\f\u001a\u0004\u0018\u00010\r8\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010$\u001a\u0016\u0012\u0004\u0012\u00020\r\u0018\u00010%j\n\u0012\u0004\u0012\u00020\r\u0018\u0001`&X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/uxcam/screenaction/models/ScreenAction;", "", ViewHierarchyNode.JsonKeys.IDENTIFIER, "", "identifierString", "", "rect", "Landroid/graphics/Rect;", "time", "", ViewProps.POSITION, "actualClass", "uxCamView", "Lcom/uxcam/screenaction/models/UXCamView;", "parentClasses", "Lorg/json/JSONArray;", "(ILjava/lang/String;Landroid/graphics/Rect;FILjava/lang/String;Lcom/uxcam/screenaction/models/UXCamView;Lorg/json/JSONArray;)V", "customObject", "Lorg/json/JSONObject;", "detectorType", "event", "getIdentifierString", "()Ljava/lang/String;", "jsonObject", "getJsonObject", "()Lorg/json/JSONObject;", "<set-?>", "name", "getName", "getRect", "()Landroid/graphics/Rect;", "setRect", "(Landroid/graphics/Rect;)V", "getTime", "()F", "value", "viewsInGroupView", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "addToCustomData", "", Constants.KEY_KEY, "setDetectorType", "setName", "setValue", "setViewsInGroupView", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ScreenAction {
    private final String actualClass;
    private final JSONObject customObject;
    private String detectorType;
    public int event;
    private final int identifier;
    private final String identifierString;
    private String name;
    private final JSONArray parentClasses;
    private final int position;
    private Rect rect;
    private final float time;
    public final UXCamView uxCamView;
    private String value;
    private ArrayList<UXCamView> viewsInGroupView;

    public ScreenAction(int i, String str, Rect rect, float f, int i2, String actualClass, UXCamView uXCamView, JSONArray jSONArray) {
        Intrinsics.checkNotNullParameter(rect, "rect");
        Intrinsics.checkNotNullParameter(actualClass, "actualClass");
        this.identifier = i;
        this.identifierString = str;
        this.rect = rect;
        this.time = f;
        this.position = i2;
        this.actualClass = actualClass;
        this.uxCamView = uXCamView;
        this.parentClasses = jSONArray;
        this.customObject = new JSONObject();
    }

    public final void addToCustomData(String key, Object value) throws JSONException {
        Intrinsics.checkNotNullParameter(key, "key");
        try {
            this.customObject.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public final String getIdentifierString() {
        return this.identifierString;
    }

    public final JSONObject getJsonObject() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(KeyConstant.KEY_VIEW_TIME, this.event);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(this.rect.left);
            jSONArray.put(this.rect.top);
            jSONArray.put(this.rect.width());
            jSONArray.put(this.rect.height());
            jSONObject.put("rec", jSONArray);
            int i = this.identifier;
            if (i > 0) {
                jSONObject.put("i", i);
            }
            String str = this.identifierString;
            if (str != null && str.length() != 0) {
                jSONObject.put("is", this.identifierString);
            }
            jSONObject.putOpt("n", this.name);
            jSONObject.put(CTProductConfigConstants.PRODUCT_CONFIG_JSON_KEY_FOR_VALUE, this.value);
            jSONObject.put("p", this.position);
            jSONObject.put("c", this.actualClass);
            UXCamView uXCamView = this.uxCamView;
            jSONObject.put("isViewGroup", uXCamView != null ? Boolean.valueOf(uXCamView.isViewGroup()) : null);
            UXCamView uXCamView2 = this.uxCamView;
            jSONObject.put("isEnabled", uXCamView2 != null ? Boolean.valueOf(uXCamView2.isEnabled()) : null);
            UXCamView uXCamView3 = this.uxCamView;
            jSONObject.put("isClickable", uXCamView3 != null ? Boolean.valueOf(uXCamView3.isClickable()) : null);
            UXCamView uXCamView4 = this.uxCamView;
            jSONObject.put("hasOnClickListeners", uXCamView4 != null ? Boolean.valueOf(uXCamView4.hasOnClickListeners()) : null);
            UXCamView uXCamView5 = this.uxCamView;
            jSONObject.put("isScrollable", uXCamView5 != null ? Boolean.valueOf(uXCamView5.isScrollable()) : null);
            UXCamView uXCamView6 = this.uxCamView;
            jSONObject.put("isScrollContainer", uXCamView6 != null ? Boolean.valueOf(uXCamView6.isScrollContainer()) : null);
            jSONObject.put("detectorType", this.detectorType);
            jSONObject.put("parentClasses", this.parentClasses);
            JSONArray jSONArray2 = this.parentClasses;
            jSONObject.put("parentClassesCount", jSONArray2 != null ? Integer.valueOf(jSONArray2.length()) : null);
            jSONObject.put(SchedulerSupport.CUSTOM, this.customObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public final String getName() {
        return this.name;
    }

    public final Rect getRect() {
        return this.rect;
    }

    public final float getTime() {
        return this.time;
    }

    public final void setDetectorType(String detectorType) {
        this.detectorType = detectorType;
    }

    public final void setName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        if (name.length() < 128) {
            this.name = name;
            return;
        }
        StringBuilder sb = new StringBuilder();
        String strSubstring = name.substring(0, 128);
        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        this.name = sb.append(strSubstring).append("...").toString();
    }

    public final void setRect(Rect rect) {
        Intrinsics.checkNotNullParameter(rect, "<set-?>");
        this.rect = rect;
    }

    public final void setValue(String value) {
        this.value = value;
    }

    public final void setViewsInGroupView(ArrayList<UXCamView> viewsInGroupView) {
        this.viewsInGroupView = viewsInGroupView;
    }
}
