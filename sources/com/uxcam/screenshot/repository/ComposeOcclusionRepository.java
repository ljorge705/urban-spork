package com.uxcam.screenshot.repository;

import com.uxcam.screenaction.models.OccludeComposable;
import java.util.List;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u000e\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H&¨\u0006\b"}, d2 = {"Lcom/uxcam/screenshot/repository/ComposeOcclusionRepository;", "", "addComposable", "", "composable", "Lcom/uxcam/screenaction/models/OccludeComposable;", "getComposablesToHide", "", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface ComposeOcclusionRepository {
    void addComposable(OccludeComposable composable);

    List<OccludeComposable> getComposablesToHide();
}
