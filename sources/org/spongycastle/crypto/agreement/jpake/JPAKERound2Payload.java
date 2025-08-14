package org.spongycastle.crypto.agreement.jpake;

import java.math.BigInteger;
import org.spongycastle.util.Arrays;

/* loaded from: classes3.dex */
public class JPAKERound2Payload {

    /* renamed from: a, reason: collision with root package name */
    private final BigInteger f382a;
    private final BigInteger[] knowledgeProofForX2s;
    private final String participantId;

    public BigInteger getA() {
        return this.f382a;
    }

    public String getParticipantId() {
        return this.participantId;
    }

    public JPAKERound2Payload(String str, BigInteger bigInteger, BigInteger[] bigIntegerArr) {
        JPAKEUtil.validateNotNull(str, "participantId");
        JPAKEUtil.validateNotNull(bigInteger, "a");
        JPAKEUtil.validateNotNull(bigIntegerArr, "knowledgeProofForX2s");
        this.participantId = str;
        this.f382a = bigInteger;
        this.knowledgeProofForX2s = Arrays.copyOf(bigIntegerArr, bigIntegerArr.length);
    }

    public BigInteger[] getKnowledgeProofForX2s() {
        BigInteger[] bigIntegerArr = this.knowledgeProofForX2s;
        return Arrays.copyOf(bigIntegerArr, bigIntegerArr.length);
    }
}
