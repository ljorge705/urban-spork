package androidx.compose.ui.tooling.animation;

import androidx.compose.ui.tooling.PreviewUtilsKt;
import androidx.compose.ui.tooling.data.Group;
import androidx.exifinterface.media.ExifInterface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AnimationSearch.kt */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0010\u001e\n\u0000\u001a&\u0010\t\u001a\u0004\u0018\u0001H\n\"\u0006\b\u0000\u0010\n\u0018\u0001*\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\rH\u0082\b¢\u0006\u0002\u0010\u000e\u001a!\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\n0\u0010\"\u0006\b\u0000\u0010\n\u0018\u0001*\b\u0012\u0004\u0012\u00020\u000b0\u0011H\u0082\b\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"ANIMATED_CONTENT", "", "ANIMATED_VISIBILITY", "ANIMATE_VALUE_AS_STATE", "REMEMBER", "REMEMBER_INFINITE_TRANSITION", "REMEMBER_UPDATED_STATE", "SIZE_ANIMATION_MODIFIER", "UPDATE_TRANSITION", "findData", ExifInterface.GPS_DIRECTION_TRUE, "Landroidx/compose/ui/tooling/data/Group;", "includeGrandchildren", "", "(Landroidx/compose/ui/tooling/data/Group;Z)Ljava/lang/Object;", "findRememberedData", "", "", "ui-tooling_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AnimationSearchKt {
    private static final String ANIMATED_CONTENT = "AnimatedContent";
    private static final String ANIMATED_VISIBILITY = "AnimatedVisibility";
    private static final String ANIMATE_VALUE_AS_STATE = "animateValueAsState";
    private static final String REMEMBER = "remember";
    private static final String REMEMBER_INFINITE_TRANSITION = "rememberInfiniteTransition";
    private static final String REMEMBER_UPDATED_STATE = "rememberUpdatedState";
    private static final String SIZE_ANIMATION_MODIFIER = "androidx.compose.animation.SizeAnimationModifier";
    private static final String UPDATE_TRANSITION = "updateTransition";

    private static final /* synthetic */ <T> List<T> findRememberedData(Collection<? extends Group> collection) {
        T next;
        Collection<? extends Group> collection2 = collection;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = collection2.iterator();
        while (true) {
            T t = null;
            if (!it.hasNext()) {
                break;
            }
            Iterator<T> it2 = ((Group) it.next()).getData().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                T next2 = it2.next();
                Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                if (next2 instanceof Object) {
                    t = next2;
                    break;
                }
            }
            Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
            if (t != null) {
                arrayList.add(t);
            }
        }
        ArrayList arrayList2 = arrayList;
        ArrayList arrayList3 = new ArrayList();
        Iterator<T> it3 = collection2.iterator();
        while (it3.hasNext()) {
            Group groupFirstOrNull = PreviewUtilsKt.firstOrNull((Group) it3.next(), AnimationSearchKt$findRememberedData$rememberCalls$1$1.INSTANCE);
            if (groupFirstOrNull != null) {
                arrayList3.add(groupFirstOrNull);
            }
        }
        ArrayList arrayList4 = arrayList2;
        ArrayList arrayList5 = new ArrayList();
        Iterator<T> it4 = arrayList3.iterator();
        while (it4.hasNext()) {
            Iterator<T> it5 = ((Group) it4.next()).getData().iterator();
            while (true) {
                if (!it5.hasNext()) {
                    next = null;
                    break;
                }
                next = it5.next();
                Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
                if (next instanceof Object) {
                    break;
                }
            }
            Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
            if (next != null) {
                arrayList5.add(next);
            }
        }
        return CollectionsKt.plus((Collection) arrayList4, (Iterable) arrayList5);
    }

    private static final /* synthetic */ <T> T findData(Group group, boolean z) {
        T next;
        Collection<Object> data = group.getData();
        List children = group.getChildren();
        if (z) {
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = children.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(arrayList, ((Group) it.next()).getChildren());
            }
            children = CollectionsKt.plus((Collection) children, (Iterable) arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it2 = children.iterator();
        while (it2.hasNext()) {
            CollectionsKt.addAll(arrayList2, ((Group) it2.next()).getData());
        }
        Iterator<T> it3 = CollectionsKt.plus((Collection) data, (Iterable) arrayList2).iterator();
        while (true) {
            if (!it3.hasNext()) {
                next = null;
                break;
            }
            next = it3.next();
            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
            if (next instanceof Object) {
                break;
            }
        }
        Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
        return next;
    }

    static /* synthetic */ Object findData$default(Group group, boolean z, int i, Object obj) {
        Object next;
        if ((i & 1) != 0) {
            z = false;
        }
        Collection<Object> data = group.getData();
        List children = group.getChildren();
        if (z) {
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = children.iterator();
            while (it.hasNext()) {
                CollectionsKt.addAll(arrayList, ((Group) it.next()).getChildren());
            }
            children = CollectionsKt.plus((Collection) children, (Iterable) arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it2 = children.iterator();
        while (it2.hasNext()) {
            CollectionsKt.addAll(arrayList2, ((Group) it2.next()).getData());
        }
        Iterator it3 = CollectionsKt.plus((Collection) data, (Iterable) arrayList2).iterator();
        while (true) {
            if (!it3.hasNext()) {
                next = null;
                break;
            }
            next = it3.next();
            Intrinsics.reifiedOperationMarker(3, ExifInterface.GPS_DIRECTION_TRUE);
            if (next instanceof Object) {
                break;
            }
        }
        Intrinsics.reifiedOperationMarker(2, ExifInterface.GPS_DIRECTION_TRUE);
        return next;
    }
}
