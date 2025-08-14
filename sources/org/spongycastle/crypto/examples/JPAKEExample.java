package org.spongycastle.crypto.examples;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.spongycastle.crypto.CryptoException;
import org.spongycastle.crypto.DataLengthException;
import org.spongycastle.crypto.agreement.jpake.JPAKEParticipant;
import org.spongycastle.crypto.agreement.jpake.JPAKEPrimeOrderGroup;
import org.spongycastle.crypto.agreement.jpake.JPAKEPrimeOrderGroups;
import org.spongycastle.crypto.agreement.jpake.JPAKERound1Payload;
import org.spongycastle.crypto.agreement.jpake.JPAKERound2Payload;
import org.spongycastle.crypto.agreement.jpake.JPAKERound3Payload;
import org.spongycastle.crypto.digests.SHA256Digest;

/* loaded from: classes4.dex */
public class JPAKEExample {
    public static void main(String[] strArr) throws IllegalStateException, DataLengthException, CryptoException {
        JPAKEPrimeOrderGroup jPAKEPrimeOrderGroup = JPAKEPrimeOrderGroups.NIST_3072;
        BigInteger p = jPAKEPrimeOrderGroup.getP();
        BigInteger q = jPAKEPrimeOrderGroup.getQ();
        BigInteger g = jPAKEPrimeOrderGroup.getG();
        System.out.println("********* Initialization **********");
        System.out.println("Public parameters for the cyclic group:");
        System.out.println("p (" + p.bitLength() + " bits): " + p.toString(16));
        System.out.println("q (" + q.bitLength() + " bits): " + q.toString(16));
        System.out.println("g (" + p.bitLength() + " bits): " + g.toString(16));
        System.out.println("p mod q = " + p.mod(q).toString(16));
        System.out.println("g^{q} mod p = " + g.modPow(q, p).toString(16));
        System.out.println("");
        System.out.println("(Secret passwords used by Alice and Bob: \"password\" and \"password\")\n");
        SHA256Digest sHA256Digest = new SHA256Digest();
        SecureRandom secureRandom = new SecureRandom();
        JPAKEParticipant jPAKEParticipant = new JPAKEParticipant("alice", "password".toCharArray(), jPAKEPrimeOrderGroup, sHA256Digest, secureRandom);
        JPAKEParticipant jPAKEParticipant2 = new JPAKEParticipant("bob", "password".toCharArray(), jPAKEPrimeOrderGroup, sHA256Digest, secureRandom);
        JPAKERound1Payload jPAKERound1PayloadCreateRound1PayloadToSend = jPAKEParticipant.createRound1PayloadToSend();
        JPAKERound1Payload jPAKERound1PayloadCreateRound1PayloadToSend2 = jPAKEParticipant2.createRound1PayloadToSend();
        System.out.println("************ Round 1 **************");
        System.out.println("Alice sends to Bob: ");
        System.out.println("g^{x1}=" + jPAKERound1PayloadCreateRound1PayloadToSend.getGx1().toString(16));
        System.out.println("g^{x2}=" + jPAKERound1PayloadCreateRound1PayloadToSend.getGx2().toString(16));
        System.out.println("KP{x1}={" + jPAKERound1PayloadCreateRound1PayloadToSend.getKnowledgeProofForX1()[0].toString(16) + "};{" + jPAKERound1PayloadCreateRound1PayloadToSend.getKnowledgeProofForX1()[1].toString(16) + "}");
        System.out.println("KP{x2}={" + jPAKERound1PayloadCreateRound1PayloadToSend.getKnowledgeProofForX2()[0].toString(16) + "};{" + jPAKERound1PayloadCreateRound1PayloadToSend.getKnowledgeProofForX2()[1].toString(16) + "}");
        System.out.println("");
        System.out.println("Bob sends to Alice: ");
        System.out.println("g^{x3}=" + jPAKERound1PayloadCreateRound1PayloadToSend2.getGx1().toString(16));
        System.out.println("g^{x4}=" + jPAKERound1PayloadCreateRound1PayloadToSend2.getGx2().toString(16));
        System.out.println("KP{x3}={" + jPAKERound1PayloadCreateRound1PayloadToSend2.getKnowledgeProofForX1()[0].toString(16) + "};{" + jPAKERound1PayloadCreateRound1PayloadToSend2.getKnowledgeProofForX1()[1].toString(16) + "}");
        System.out.println("KP{x4}={" + jPAKERound1PayloadCreateRound1PayloadToSend2.getKnowledgeProofForX2()[0].toString(16) + "};{" + jPAKERound1PayloadCreateRound1PayloadToSend2.getKnowledgeProofForX2()[1].toString(16) + "}");
        System.out.println("");
        jPAKEParticipant.validateRound1PayloadReceived(jPAKERound1PayloadCreateRound1PayloadToSend2);
        System.out.println("Alice checks g^{x4}!=1: OK");
        System.out.println("Alice checks KP{x3}: OK");
        System.out.println("Alice checks KP{x4}: OK");
        System.out.println("");
        jPAKEParticipant2.validateRound1PayloadReceived(jPAKERound1PayloadCreateRound1PayloadToSend);
        System.out.println("Bob checks g^{x2}!=1: OK");
        System.out.println("Bob checks KP{x1},: OK");
        System.out.println("Bob checks KP{x2},: OK");
        System.out.println("");
        JPAKERound2Payload jPAKERound2PayloadCreateRound2PayloadToSend = jPAKEParticipant.createRound2PayloadToSend();
        JPAKERound2Payload jPAKERound2PayloadCreateRound2PayloadToSend2 = jPAKEParticipant2.createRound2PayloadToSend();
        System.out.println("************ Round 2 **************");
        System.out.println("Alice sends to Bob: ");
        System.out.println("A=" + jPAKERound2PayloadCreateRound2PayloadToSend.getA().toString(16));
        System.out.println("KP{x2*s}={" + jPAKERound2PayloadCreateRound2PayloadToSend.getKnowledgeProofForX2s()[0].toString(16) + "},{" + jPAKERound2PayloadCreateRound2PayloadToSend.getKnowledgeProofForX2s()[1].toString(16) + "}");
        System.out.println("");
        System.out.println("Bob sends to Alice");
        System.out.println("B=" + jPAKERound2PayloadCreateRound2PayloadToSend2.getA().toString(16));
        System.out.println("KP{x4*s}={" + jPAKERound2PayloadCreateRound2PayloadToSend2.getKnowledgeProofForX2s()[0].toString(16) + "},{" + jPAKERound2PayloadCreateRound2PayloadToSend2.getKnowledgeProofForX2s()[1].toString(16) + "}");
        System.out.println("");
        jPAKEParticipant.validateRound2PayloadReceived(jPAKERound2PayloadCreateRound2PayloadToSend2);
        System.out.println("Alice checks KP{x4*s}: OK\n");
        jPAKEParticipant2.validateRound2PayloadReceived(jPAKERound2PayloadCreateRound2PayloadToSend);
        System.out.println("Bob checks KP{x2*s}: OK\n");
        BigInteger bigIntegerCalculateKeyingMaterial = jPAKEParticipant.calculateKeyingMaterial();
        BigInteger bigIntegerCalculateKeyingMaterial2 = jPAKEParticipant2.calculateKeyingMaterial();
        System.out.println("********* After round 2 ***********");
        System.out.println("Alice computes key material \t K=" + bigIntegerCalculateKeyingMaterial.toString(16));
        System.out.println("Bob computes key material \t K=" + bigIntegerCalculateKeyingMaterial2.toString(16));
        System.out.println();
        deriveSessionKey(bigIntegerCalculateKeyingMaterial);
        deriveSessionKey(bigIntegerCalculateKeyingMaterial2);
        JPAKERound3Payload jPAKERound3PayloadCreateRound3PayloadToSend = jPAKEParticipant.createRound3PayloadToSend(bigIntegerCalculateKeyingMaterial);
        JPAKERound3Payload jPAKERound3PayloadCreateRound3PayloadToSend2 = jPAKEParticipant2.createRound3PayloadToSend(bigIntegerCalculateKeyingMaterial2);
        System.out.println("************ Round 3 **************");
        System.out.println("Alice sends to Bob: ");
        System.out.println("MacTag=" + jPAKERound3PayloadCreateRound3PayloadToSend.getMacTag().toString(16));
        System.out.println("");
        System.out.println("Bob sends to Alice: ");
        System.out.println("MacTag=" + jPAKERound3PayloadCreateRound3PayloadToSend2.getMacTag().toString(16));
        System.out.println("");
        jPAKEParticipant.validateRound3PayloadReceived(jPAKERound3PayloadCreateRound3PayloadToSend2, bigIntegerCalculateKeyingMaterial);
        System.out.println("Alice checks MacTag: OK\n");
        jPAKEParticipant2.validateRound3PayloadReceived(jPAKERound3PayloadCreateRound3PayloadToSend, bigIntegerCalculateKeyingMaterial2);
        System.out.println("Bob checks MacTag: OK\n");
        System.out.println();
        System.out.println("MacTags validated, therefore the keying material matches.");
    }

    private static BigInteger deriveSessionKey(BigInteger bigInteger) {
        SHA256Digest sHA256Digest = new SHA256Digest();
        byte[] byteArray = bigInteger.toByteArray();
        byte[] bArr = new byte[sHA256Digest.getDigestSize()];
        sHA256Digest.update(byteArray, 0, byteArray.length);
        sHA256Digest.doFinal(bArr, 0);
        return new BigInteger(bArr);
    }
}
