package com.uxcam.screenaction.views;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.uxcam.screenaction.models.ScreenAction;
import com.uxcam.screenaction.models.UXCamView;
import com.uxcam.screenaction.utils.Util;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONArray;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/uxcam/screenaction/views/ViewSystemScreenActionProviderImpl;", "Lcom/uxcam/screenaction/views/ViewSystemScreenActionProvider;", "<init>", "()V", "Companion", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0})
/* loaded from: classes6.dex */
public final class ViewSystemScreenActionProviderImpl implements ViewSystemScreenActionProvider {

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/uxcam/screenaction/views/ViewSystemScreenActionProviderImpl$Companion;", "", "()V", "EVENT_BUTTON", "", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(int i) {
            this();
        }
    }

    static {
        new Companion(0);
    }

    @Override // com.uxcam.screenaction.views.ViewSystemScreenActionProvider
    public final ScreenAction a(UXCamView uxCamView, float f, boolean z) {
        Intrinsics.checkNotNullParameter(uxCamView, "uxCamView");
        View view = uxCamView.getView().get();
        try {
            Intrinsics.checkNotNull(view);
            String actualClass = view.getClass().getName();
            JSONArray jSONArrayA = a(view.getClass());
            int id = view.getId();
            String resourceName = Util.getResourceName(view);
            Rect rect = uxCamView.getRect();
            Intrinsics.checkNotNullExpressionValue(rect, "uxCamView.rect");
            int position = uxCamView.getPosition();
            Intrinsics.checkNotNullExpressionValue(actualClass, "actualClass");
            ScreenAction screenAction = new ScreenAction(id, resourceName, rect, f, position, actualClass, uxCamView, jSONArrayA);
            a(view, screenAction, z);
            screenAction.setDetectorType("onTouchEvent");
            return screenAction;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray a(Class cls) {
        ArrayList arrayList = new ArrayList();
        while (cls.getSuperclass() != null) {
            cls = cls.getSuperclass();
            Intrinsics.checkNotNullExpressionValue(cls, "superClass.superclass");
            arrayList.add(cls);
        }
        int i = 0;
        arrayList.remove(0);
        if (arrayList.size() > 0) {
            arrayList.remove(arrayList.size() - 1);
        }
        JSONArray jSONArray = new JSONArray();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Class cls2 = (Class) it.next();
            if (i >= 4) {
                break;
            }
            jSONArray.put(cls2.getName());
            i++;
        }
        return jSONArray;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x004c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(android.view.View r8, final com.uxcam.screenaction.models.ScreenAction r9, boolean r10) throws org.json.JSONException {
        /*
            Method dump skipped, instructions count: 384
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.uxcam.screenaction.views.ViewSystemScreenActionProviderImpl.a(android.view.View, com.uxcam.screenaction.models.ScreenAction, boolean):void");
    }

    public static void a(ViewGroup viewGroup, ScreenAction screenAction, int i) {
        if (i == 3) {
            return;
        }
        int childCount = viewGroup.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = viewGroup.getChildAt(i2);
            if ((childAt instanceof TextView) && (screenAction.getName() == null || Intrinsics.areEqual(screenAction.getName(), ""))) {
                screenAction.setName(((TextView) childAt).getText().toString());
                return;
            }
            if (childAt instanceof ViewGroup) {
                a((ViewGroup) childAt, screenAction, i + 1);
            }
        }
    }

    public static void a(ViewGroup viewGroup, ArrayList arrayList) {
        int childCount = viewGroup.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt instanceof ViewGroup) {
                a((ViewGroup) childAt, arrayList);
            } else {
                arrayList.add(new UXCamView(childAt, new Rect()));
            }
        }
    }
}
