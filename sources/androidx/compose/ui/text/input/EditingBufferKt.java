package androidx.compose.ui.text.input;

import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextRangeKt;
import com.facebook.react.uimanager.events.TouchesHelper;
import kotlin.Metadata;

/* compiled from: EditingBuffer.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a%\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0001H\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0004\u0010\u0005\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0006"}, d2 = {"updateRangeAfterDelete", "Landroidx/compose/ui/text/TextRange;", TouchesHelper.TARGET_KEY, "deleted", "updateRangeAfterDelete-pWDy79M", "(JJ)J", "ui-text_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class EditingBufferKt {
    /* renamed from: updateRangeAfterDelete-pWDy79M, reason: not valid java name */
    public static final long m4037updateRangeAfterDeletepWDy79M(long j, long j2) {
        int iM3901getLengthimpl;
        int iM3903getMinimpl = TextRange.m3903getMinimpl(j);
        int iM3902getMaximpl = TextRange.m3902getMaximpl(j);
        if (TextRange.m3907intersects5zctL8(j2, j)) {
            if (TextRange.m3895contains5zctL8(j2, j)) {
                iM3903getMinimpl = TextRange.m3903getMinimpl(j2);
                iM3902getMaximpl = iM3903getMinimpl;
            } else {
                if (TextRange.m3895contains5zctL8(j, j2)) {
                    iM3901getLengthimpl = TextRange.m3901getLengthimpl(j2);
                } else if (TextRange.m3896containsimpl(j2, iM3903getMinimpl)) {
                    iM3903getMinimpl = TextRange.m3903getMinimpl(j2);
                    iM3901getLengthimpl = TextRange.m3901getLengthimpl(j2);
                } else {
                    iM3902getMaximpl = TextRange.m3903getMinimpl(j2);
                }
                iM3902getMaximpl -= iM3901getLengthimpl;
            }
        } else if (iM3902getMaximpl > TextRange.m3903getMinimpl(j2)) {
            iM3903getMinimpl -= TextRange.m3901getLengthimpl(j2);
            iM3901getLengthimpl = TextRange.m3901getLengthimpl(j2);
            iM3902getMaximpl -= iM3901getLengthimpl;
        }
        return TextRangeKt.TextRange(iM3903getMinimpl, iM3902getMaximpl);
    }
}
