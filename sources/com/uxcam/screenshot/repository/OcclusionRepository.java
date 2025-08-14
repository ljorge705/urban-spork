package com.uxcam.screenshot.repository;

import com.uxcam.screenaction.models.KeyConstant;
import com.uxcam.screenshot.model.UXCamOccludeAllTextFields;
import com.uxcam.screenshot.model.UXCamOcclusion;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u0006\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH&J\u0014\u0010\u000b\u001a\u0004\u0018\u00010\u00052\b\u0010\t\u001a\u0004\u0018\u00010\nH&J\b\u0010\f\u001a\u00020\u0003H&J\u0012\u0010\r\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u000e\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\t\u001a\u0004\u0018\u00010\nH&¨\u0006\u0011"}, d2 = {"Lcom/uxcam/screenshot/repository/OcclusionRepository;", "", "applyOcclusionFromBackend", "", "occlusion", "Lcom/uxcam/screenshot/model/UXCamOcclusion;", "applyOcclusionFromSDK", "getOccludeAllTextFields", "Lcom/uxcam/screenshot/model/UXCamOccludeAllTextFields;", KeyConstant.KEY_SCREEN, "", "getOcclusion", "removeAllOcclusionsFromBackend", "removeOcclusionFromBackend", "removeOcclusionFromSDK", "shouldOcclude", "", "screenshot_littleRelease"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes6.dex */
public interface OcclusionRepository {
    void applyOcclusionFromBackend(UXCamOcclusion occlusion);

    void applyOcclusionFromSDK(UXCamOcclusion occlusion);

    UXCamOccludeAllTextFields getOccludeAllTextFields(String screen);

    UXCamOcclusion getOcclusion(String screen);

    void removeAllOcclusionsFromBackend();

    void removeOcclusionFromBackend(UXCamOcclusion occlusion);

    void removeOcclusionFromSDK(UXCamOcclusion occlusion);

    boolean shouldOcclude(String screen);
}
