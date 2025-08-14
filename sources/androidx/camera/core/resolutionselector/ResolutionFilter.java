package androidx.camera.core.resolutionselector;

import android.util.Size;
import java.util.List;

/* loaded from: classes.dex */
public interface ResolutionFilter {
    List<Size> filter(List<Size> list, int i);
}
