package com.uxcam.screenaction;

import com.uxcam.screenaction.models.GestureData;
import com.uxcam.screenaction.models.OccludeComposable;
import com.uxcam.screenaction.models.ScreenAction;
import com.uxcam.screenaction.models.UXCamOccludeView;
import com.uxcam.screenaction.models.UXCamView;
import com.uxcam.screenaction.models.ViewRootData;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001Jl\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0010\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0010\u0010\r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0018\u00010\t2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\t2\u0014\u0010\u0011\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0013\u0012\u0004\u0012\u00020\u00030\u0012H&¨\u0006\u0014"}, d2 = {"Lcom/uxcam/screenaction/ScreenActionProvider;", "", "getScreenAction", "", "currentTime", "", "uxCamView", "Lcom/uxcam/screenaction/models/UXCamView;", "occludedViews", "", "Lcom/uxcam/screenaction/models/UXCamOccludeView;", "gestureData", "Lcom/uxcam/screenaction/models/GestureData;", "rootViews", "Lcom/uxcam/screenaction/models/ViewRootData;", "occludedComposables", "Lcom/uxcam/screenaction/models/OccludeComposable;", "onResult", "Lkotlin/Function1;", "Lcom/uxcam/screenaction/models/ScreenAction;", "screenaction_littleRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface ScreenActionProvider {
    void getScreenAction(float currentTime, UXCamView uxCamView, List<? extends UXCamOccludeView> occludedViews, GestureData gestureData, List<ViewRootData> rootViews, List<OccludeComposable> occludedComposables, Function1<? super ScreenAction, Unit> onResult);
}
