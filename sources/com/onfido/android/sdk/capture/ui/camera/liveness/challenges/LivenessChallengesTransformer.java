package com.onfido.android.sdk.capture.ui.camera.liveness.challenges;

import com.facebook.react.uimanager.ViewProps;
import com.onfido.api.client.data.LiveVideoChallenges;
import com.onfido.javax.inject.Inject;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesTransformer;", "", "()V", ViewProps.TRANSFORM, "Lcom/onfido/android/sdk/capture/ui/camera/liveness/challenges/LivenessChallengesViewModel;", "liveVideoChallenges", "Lcom/onfido/api/client/data/LiveVideoChallenges;", "onfido-capture-sdk-core_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* loaded from: classes2.dex */
public class LivenessChallengesTransformer {

    @Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LivenessChallengeType.values().length];
            try {
                iArr[LivenessChallengeType.RECITE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LivenessChallengeType.MOVEMENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Inject
    public LivenessChallengesTransformer() {
    }

    public LivenessChallengesViewModel transform(LiveVideoChallenges liveVideoChallenges) throws UnknownLivenessChallengeException {
        List listEmptyList;
        List<LiveVideoChallenges.LiveVideoChallenge> challenge;
        Object reciteLivenessChallenge;
        Intrinsics.checkNotNullParameter(liveVideoChallenges, "liveVideoChallenges");
        LiveVideoChallenges.LiveVideoChallengesData data = liveVideoChallenges.getData();
        String id = data != null ? data.getId() : null;
        LiveVideoChallenges.LiveVideoChallengesData data2 = liveVideoChallenges.getData();
        if (data2 == null || (challenge = data2.getChallenge()) == null) {
            listEmptyList = CollectionsKt.emptyList();
        } else {
            listEmptyList = new ArrayList(CollectionsKt.collectionSizeOrDefault(challenge, 10));
            for (LiveVideoChallenges.LiveVideoChallenge liveVideoChallenge : challenge) {
                try {
                    LivenessChallengeType[] livenessChallengeTypeArrValues = LivenessChallengeType.values();
                    LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(livenessChallengeTypeArrValues.length), 16));
                    for (LivenessChallengeType livenessChallengeType : livenessChallengeTypeArrValues) {
                        linkedHashMap.put(livenessChallengeType.getId(), livenessChallengeType);
                    }
                    int i = WhenMappings.$EnumSwitchMapping$0[((LivenessChallengeType) MapsKt.getValue(linkedHashMap, liveVideoChallenge.getType())).ordinal()];
                    if (i == 1) {
                        Object query = liveVideoChallenge.getQuery();
                        Intrinsics.checkNotNull(query, "null cannot be cast to non-null type kotlin.collections.List<kotlin.Int>");
                        reciteLivenessChallenge = new ReciteLivenessChallenge(CollectionsKt.toIntArray((List) query));
                    } else {
                        if (i != 2) {
                            throw new NoWhenBranchMatchedException();
                        }
                        MovementType[] movementTypeArrValues = MovementType.values();
                        LinkedHashMap linkedHashMap2 = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(movementTypeArrValues.length), 16));
                        for (MovementType movementType : movementTypeArrValues) {
                            linkedHashMap2.put(movementType.getId(), movementType);
                        }
                        Object query2 = liveVideoChallenge.getQuery();
                        Intrinsics.checkNotNull(query2, "null cannot be cast to non-null type kotlin.String");
                        reciteLivenessChallenge = new MovementLivenessChallenge((MovementType) MapsKt.getValue(linkedHashMap2, (String) query2));
                    }
                    listEmptyList.add(reciteLivenessChallenge);
                } catch (NoSuchElementException unused) {
                    throw new UnknownLivenessChallengeException("Type=" + liveVideoChallenge.getType() + ", Query=" + liveVideoChallenge.getQuery());
                }
            }
        }
        Intrinsics.checkNotNull(id);
        return new LivenessChallengesViewModel(id, listEmptyList);
    }
}
