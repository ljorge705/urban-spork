package org.spongycastle.jce.provider;

import com.clevertap.android.sdk.Constants;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.ClassUtils;
import org.spongycastle.asn1.ASN1OctetString;
import org.spongycastle.asn1.ASN1Sequence;
import org.spongycastle.asn1.DERIA5String;
import org.spongycastle.asn1.x509.GeneralName;
import org.spongycastle.asn1.x509.GeneralSubtree;
import org.spongycastle.util.Arrays;
import org.spongycastle.util.Integers;
import org.spongycastle.util.Strings;

/* loaded from: classes7.dex */
public class PKIXNameConstraintValidator {
    private Set permittedSubtreesDN;
    private Set permittedSubtreesDNS;
    private Set permittedSubtreesEmail;
    private Set permittedSubtreesIP;
    private Set permittedSubtreesURI;
    private Set excludedSubtreesDN = new HashSet();
    private Set excludedSubtreesDNS = new HashSet();
    private Set excludedSubtreesEmail = new HashSet();
    private Set excludedSubtreesURI = new HashSet();
    private Set excludedSubtreesIP = new HashSet();

    private static boolean withinDNSubtree(ASN1Sequence aSN1Sequence, ASN1Sequence aSN1Sequence2) {
        if (aSN1Sequence2.size() < 1 || aSN1Sequence2.size() > aSN1Sequence.size()) {
            return false;
        }
        for (int size = aSN1Sequence2.size() - 1; size >= 0; size--) {
            if (!aSN1Sequence2.getObjectAt(size).equals(aSN1Sequence.getObjectAt(size))) {
                return false;
            }
        }
        return true;
    }

    public void checkPermittedDN(ASN1Sequence aSN1Sequence) throws PKIXNameConstraintValidatorException {
        checkPermittedDN(this.permittedSubtreesDN, aSN1Sequence);
    }

    public void checkExcludedDN(ASN1Sequence aSN1Sequence) throws PKIXNameConstraintValidatorException {
        checkExcludedDN(this.excludedSubtreesDN, aSN1Sequence);
    }

    private void checkPermittedDN(Set set, ASN1Sequence aSN1Sequence) throws PKIXNameConstraintValidatorException {
        if (set == null) {
            return;
        }
        if (set.isEmpty() && aSN1Sequence.size() == 0) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (withinDNSubtree(aSN1Sequence, (ASN1Sequence) it.next())) {
                return;
            }
        }
        throw new PKIXNameConstraintValidatorException("Subject distinguished name is not from a permitted subtree");
    }

    private void checkExcludedDN(Set set, ASN1Sequence aSN1Sequence) throws PKIXNameConstraintValidatorException {
        if (set.isEmpty()) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (withinDNSubtree(aSN1Sequence, (ASN1Sequence) it.next())) {
                throw new PKIXNameConstraintValidatorException("Subject distinguished name is from an excluded subtree");
            }
        }
    }

    private Set intersectDN(Set set, Set set2) {
        HashSet hashSet = new HashSet();
        Iterator it = set2.iterator();
        while (it.hasNext()) {
            ASN1Sequence aSN1Sequence = ASN1Sequence.getInstance(((GeneralSubtree) it.next()).getBase().getName().toASN1Primitive());
            if (set != null) {
                Iterator it2 = set.iterator();
                while (it2.hasNext()) {
                    ASN1Sequence aSN1Sequence2 = (ASN1Sequence) it2.next();
                    if (withinDNSubtree(aSN1Sequence, aSN1Sequence2)) {
                        hashSet.add(aSN1Sequence);
                    } else if (withinDNSubtree(aSN1Sequence2, aSN1Sequence)) {
                        hashSet.add(aSN1Sequence2);
                    }
                }
            } else if (aSN1Sequence != null) {
                hashSet.add(aSN1Sequence);
            }
        }
        return hashSet;
    }

    private Set unionDN(Set set, ASN1Sequence aSN1Sequence) {
        if (set.isEmpty()) {
            if (aSN1Sequence == null) {
                return set;
            }
            set.add(aSN1Sequence);
            return set;
        }
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            ASN1Sequence aSN1Sequence2 = (ASN1Sequence) it.next();
            if (withinDNSubtree(aSN1Sequence, aSN1Sequence2)) {
                hashSet.add(aSN1Sequence2);
            } else if (withinDNSubtree(aSN1Sequence2, aSN1Sequence)) {
                hashSet.add(aSN1Sequence);
            } else {
                hashSet.add(aSN1Sequence2);
                hashSet.add(aSN1Sequence);
            }
        }
        return hashSet;
    }

    private Set intersectEmail(Set set, Set set2) {
        HashSet hashSet = new HashSet();
        Iterator it = set2.iterator();
        while (it.hasNext()) {
            String strExtractNameAsString = extractNameAsString(((GeneralSubtree) it.next()).getBase());
            if (set != null) {
                Iterator it2 = set.iterator();
                while (it2.hasNext()) {
                    intersectEmail(strExtractNameAsString, (String) it2.next(), hashSet);
                }
            } else if (strExtractNameAsString != null) {
                hashSet.add(strExtractNameAsString);
            }
        }
        return hashSet;
    }

    private Set unionEmail(Set set, String str) {
        if (set.isEmpty()) {
            if (str == null) {
                return set;
            }
            set.add(str);
            return set;
        }
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            unionEmail((String) it.next(), str, hashSet);
        }
        return hashSet;
    }

    private Set intersectIP(Set set, Set set2) {
        HashSet hashSet = new HashSet();
        Iterator it = set2.iterator();
        while (it.hasNext()) {
            byte[] octets = ASN1OctetString.getInstance(((GeneralSubtree) it.next()).getBase().getName()).getOctets();
            if (set != null) {
                Iterator it2 = set.iterator();
                while (it2.hasNext()) {
                    hashSet.addAll(intersectIPRange((byte[]) it2.next(), octets));
                }
            } else if (octets != null) {
                hashSet.add(octets);
            }
        }
        return hashSet;
    }

    private Set unionIP(Set set, byte[] bArr) {
        if (set.isEmpty()) {
            if (bArr == null) {
                return set;
            }
            set.add(bArr);
            return set;
        }
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            hashSet.addAll(unionIPRange((byte[]) it.next(), bArr));
        }
        return hashSet;
    }

    private Set unionIPRange(byte[] bArr, byte[] bArr2) {
        HashSet hashSet = new HashSet();
        if (Arrays.areEqual(bArr, bArr2)) {
            hashSet.add(bArr);
        } else {
            hashSet.add(bArr);
            hashSet.add(bArr2);
        }
        return hashSet;
    }

    private Set intersectIPRange(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return Collections.EMPTY_SET;
        }
        byte[][] bArrExtractIPsAndSubnetMasks = extractIPsAndSubnetMasks(bArr, bArr2);
        byte[] bArr3 = bArrExtractIPsAndSubnetMasks[0];
        byte[] bArr4 = bArrExtractIPsAndSubnetMasks[1];
        byte[] bArr5 = bArrExtractIPsAndSubnetMasks[2];
        byte[] bArr6 = bArrExtractIPsAndSubnetMasks[3];
        byte[][] bArrMinMaxIPs = minMaxIPs(bArr3, bArr4, bArr5, bArr6);
        if (compareTo(max(bArrMinMaxIPs[0], bArrMinMaxIPs[2]), min(bArrMinMaxIPs[1], bArrMinMaxIPs[3])) == 1) {
            return Collections.EMPTY_SET;
        }
        return Collections.singleton(ipWithSubnetMask(or(bArrMinMaxIPs[0], bArrMinMaxIPs[2]), or(bArr4, bArr6)));
    }

    private byte[] ipWithSubnetMask(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        byte[] bArr3 = new byte[length * 2];
        System.arraycopy(bArr, 0, bArr3, 0, length);
        System.arraycopy(bArr2, 0, bArr3, length, length);
        return bArr3;
    }

    private byte[][] extractIPsAndSubnetMasks(byte[] bArr, byte[] bArr2) {
        int length = bArr.length / 2;
        byte[] bArr3 = new byte[length];
        byte[] bArr4 = new byte[length];
        System.arraycopy(bArr, 0, bArr3, 0, length);
        System.arraycopy(bArr, length, bArr4, 0, length);
        byte[] bArr5 = new byte[length];
        byte[] bArr6 = new byte[length];
        System.arraycopy(bArr2, 0, bArr5, 0, length);
        System.arraycopy(bArr2, length, bArr6, 0, length);
        return new byte[][]{bArr3, bArr4, bArr5, bArr6};
    }

    private byte[][] minMaxIPs(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4) {
        int length = bArr.length;
        byte[] bArr5 = new byte[length];
        byte[] bArr6 = new byte[length];
        byte[] bArr7 = new byte[length];
        byte[] bArr8 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr5[i] = (byte) (bArr[i] & bArr2[i]);
            byte b = bArr[i];
            byte b2 = bArr2[i];
            bArr6[i] = (byte) ((b & b2) | (~b2));
            bArr7[i] = (byte) (bArr3[i] & bArr4[i]);
            byte b3 = bArr3[i];
            byte b4 = bArr4[i];
            bArr8[i] = (byte) ((b3 & b4) | (~b4));
        }
        return new byte[][]{bArr5, bArr6, bArr7, bArr8};
    }

    private void checkPermittedEmail(Set set, String str) throws PKIXNameConstraintValidatorException {
        if (set == null) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (emailIsConstrained(str, (String) it.next())) {
                return;
            }
        }
        if (str.length() != 0 || set.size() != 0) {
            throw new PKIXNameConstraintValidatorException("Subject email address is not from a permitted subtree.");
        }
    }

    private void checkExcludedEmail(Set set, String str) throws PKIXNameConstraintValidatorException {
        if (set.isEmpty()) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (emailIsConstrained(str, (String) it.next())) {
                throw new PKIXNameConstraintValidatorException("Email address is from an excluded subtree.");
            }
        }
    }

    private void checkPermittedIP(Set set, byte[] bArr) throws PKIXNameConstraintValidatorException {
        if (set == null) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (isIPConstrained(bArr, (byte[]) it.next())) {
                return;
            }
        }
        if (bArr.length != 0 || set.size() != 0) {
            throw new PKIXNameConstraintValidatorException("IP is not from a permitted subtree.");
        }
    }

    private void checkExcludedIP(Set set, byte[] bArr) throws PKIXNameConstraintValidatorException {
        if (set.isEmpty()) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (isIPConstrained(bArr, (byte[]) it.next())) {
                throw new PKIXNameConstraintValidatorException("IP is from an excluded subtree.");
            }
        }
    }

    private boolean isIPConstrained(byte[] bArr, byte[] bArr2) {
        int length = bArr.length;
        if (length != bArr2.length / 2) {
            return false;
        }
        byte[] bArr3 = new byte[length];
        System.arraycopy(bArr2, length, bArr3, 0, length);
        byte[] bArr4 = new byte[length];
        byte[] bArr5 = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr4[i] = (byte) (bArr2[i] & bArr3[i]);
            bArr5[i] = (byte) (bArr[i] & bArr3[i]);
        }
        return Arrays.areEqual(bArr4, bArr5);
    }

    private boolean emailIsConstrained(String str, String str2) {
        String strSubstring = str.substring(str.indexOf(64) + 1);
        if (str2.indexOf(64) != -1) {
            if (str.equalsIgnoreCase(str2) || strSubstring.equalsIgnoreCase(str2.substring(1))) {
                return true;
            }
        } else if (str2.charAt(0) != '.') {
            if (strSubstring.equalsIgnoreCase(str2)) {
                return true;
            }
        } else if (withinDomain(strSubstring, str2)) {
            return true;
        }
        return false;
    }

    private boolean withinDomain(String str, String str2) {
        if (str2.startsWith(".")) {
            str2 = str2.substring(1);
        }
        String[] strArrSplit = Strings.split(str2, ClassUtils.PACKAGE_SEPARATOR_CHAR);
        String[] strArrSplit2 = Strings.split(str, ClassUtils.PACKAGE_SEPARATOR_CHAR);
        if (strArrSplit2.length <= strArrSplit.length) {
            return false;
        }
        int length = strArrSplit2.length - strArrSplit.length;
        for (int i = -1; i < strArrSplit.length; i++) {
            if (i == -1) {
                if (strArrSplit2[i + length].equals("")) {
                    return false;
                }
            } else if (!strArrSplit[i].equalsIgnoreCase(strArrSplit2[i + length])) {
                return false;
            }
        }
        return true;
    }

    private void checkPermittedDNS(Set set, String str) throws PKIXNameConstraintValidatorException {
        if (set == null) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (withinDomain(str, str2) || str.equalsIgnoreCase(str2)) {
                return;
            }
        }
        if (str.length() != 0 || set.size() != 0) {
            throw new PKIXNameConstraintValidatorException("DNS is not from a permitted subtree.");
        }
    }

    private void checkExcludedDNS(Set set, String str) throws PKIXNameConstraintValidatorException {
        if (set.isEmpty()) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (withinDomain(str, str2) || str.equalsIgnoreCase(str2)) {
                throw new PKIXNameConstraintValidatorException("DNS is from an excluded subtree.");
            }
        }
    }

    private void unionEmail(String str, String str2, Set set) {
        if (str.indexOf(64) != -1) {
            String strSubstring = str.substring(str.indexOf(64) + 1);
            if (str2.indexOf(64) != -1) {
                if (str.equalsIgnoreCase(str2)) {
                    set.add(str);
                    return;
                } else {
                    set.add(str);
                    set.add(str2);
                    return;
                }
            }
            if (str2.startsWith(".")) {
                if (withinDomain(strSubstring, str2)) {
                    set.add(str2);
                    return;
                } else {
                    set.add(str);
                    set.add(str2);
                    return;
                }
            }
            if (strSubstring.equalsIgnoreCase(str2)) {
                set.add(str2);
                return;
            } else {
                set.add(str);
                set.add(str2);
                return;
            }
        }
        if (str.startsWith(".")) {
            if (str2.indexOf(64) != -1) {
                if (withinDomain(str2.substring(str.indexOf(64) + 1), str)) {
                    set.add(str);
                    return;
                } else {
                    set.add(str);
                    set.add(str2);
                    return;
                }
            }
            if (str2.startsWith(".")) {
                if (withinDomain(str, str2) || str.equalsIgnoreCase(str2)) {
                    set.add(str2);
                    return;
                } else if (withinDomain(str2, str)) {
                    set.add(str);
                    return;
                } else {
                    set.add(str);
                    set.add(str2);
                    return;
                }
            }
            if (withinDomain(str2, str)) {
                set.add(str);
                return;
            } else {
                set.add(str);
                set.add(str2);
                return;
            }
        }
        if (str2.indexOf(64) != -1) {
            if (str2.substring(str.indexOf(64) + 1).equalsIgnoreCase(str)) {
                set.add(str);
                return;
            } else {
                set.add(str);
                set.add(str2);
                return;
            }
        }
        if (str2.startsWith(".")) {
            if (withinDomain(str, str2)) {
                set.add(str2);
                return;
            } else {
                set.add(str);
                set.add(str2);
                return;
            }
        }
        if (str.equalsIgnoreCase(str2)) {
            set.add(str);
        } else {
            set.add(str);
            set.add(str2);
        }
    }

    private void unionURI(String str, String str2, Set set) {
        if (str.indexOf(64) != -1) {
            String strSubstring = str.substring(str.indexOf(64) + 1);
            if (str2.indexOf(64) != -1) {
                if (str.equalsIgnoreCase(str2)) {
                    set.add(str);
                    return;
                } else {
                    set.add(str);
                    set.add(str2);
                    return;
                }
            }
            if (str2.startsWith(".")) {
                if (withinDomain(strSubstring, str2)) {
                    set.add(str2);
                    return;
                } else {
                    set.add(str);
                    set.add(str2);
                    return;
                }
            }
            if (strSubstring.equalsIgnoreCase(str2)) {
                set.add(str2);
                return;
            } else {
                set.add(str);
                set.add(str2);
                return;
            }
        }
        if (str.startsWith(".")) {
            if (str2.indexOf(64) != -1) {
                if (withinDomain(str2.substring(str.indexOf(64) + 1), str)) {
                    set.add(str);
                    return;
                } else {
                    set.add(str);
                    set.add(str2);
                    return;
                }
            }
            if (str2.startsWith(".")) {
                if (withinDomain(str, str2) || str.equalsIgnoreCase(str2)) {
                    set.add(str2);
                    return;
                } else if (withinDomain(str2, str)) {
                    set.add(str);
                    return;
                } else {
                    set.add(str);
                    set.add(str2);
                    return;
                }
            }
            if (withinDomain(str2, str)) {
                set.add(str);
                return;
            } else {
                set.add(str);
                set.add(str2);
                return;
            }
        }
        if (str2.indexOf(64) != -1) {
            if (str2.substring(str.indexOf(64) + 1).equalsIgnoreCase(str)) {
                set.add(str);
                return;
            } else {
                set.add(str);
                set.add(str2);
                return;
            }
        }
        if (str2.startsWith(".")) {
            if (withinDomain(str, str2)) {
                set.add(str2);
                return;
            } else {
                set.add(str);
                set.add(str2);
                return;
            }
        }
        if (str.equalsIgnoreCase(str2)) {
            set.add(str);
        } else {
            set.add(str);
            set.add(str2);
        }
    }

    private Set intersectDNS(Set set, Set set2) {
        HashSet hashSet = new HashSet();
        Iterator it = set2.iterator();
        while (it.hasNext()) {
            String strExtractNameAsString = extractNameAsString(((GeneralSubtree) it.next()).getBase());
            if (set != null) {
                Iterator it2 = set.iterator();
                while (it2.hasNext()) {
                    String str = (String) it2.next();
                    if (withinDomain(str, strExtractNameAsString)) {
                        hashSet.add(str);
                    } else if (withinDomain(strExtractNameAsString, str)) {
                        hashSet.add(strExtractNameAsString);
                    }
                }
            } else if (strExtractNameAsString != null) {
                hashSet.add(strExtractNameAsString);
            }
        }
        return hashSet;
    }

    protected Set unionDNS(Set set, String str) {
        if (set.isEmpty()) {
            if (str == null) {
                return set;
            }
            set.add(str);
            return set;
        }
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            String str2 = (String) it.next();
            if (withinDomain(str2, str)) {
                hashSet.add(str);
            } else if (withinDomain(str, str2)) {
                hashSet.add(str2);
            } else {
                hashSet.add(str2);
                hashSet.add(str);
            }
        }
        return hashSet;
    }

    private void intersectEmail(String str, String str2, Set set) {
        if (str.indexOf(64) != -1) {
            String strSubstring = str.substring(str.indexOf(64) + 1);
            if (str2.indexOf(64) != -1) {
                if (str.equalsIgnoreCase(str2)) {
                    set.add(str);
                    return;
                }
                return;
            } else if (str2.startsWith(".")) {
                if (withinDomain(strSubstring, str2)) {
                    set.add(str);
                    return;
                }
                return;
            } else {
                if (strSubstring.equalsIgnoreCase(str2)) {
                    set.add(str);
                    return;
                }
                return;
            }
        }
        if (str.startsWith(".")) {
            if (str2.indexOf(64) != -1) {
                if (withinDomain(str2.substring(str.indexOf(64) + 1), str)) {
                    set.add(str2);
                    return;
                }
                return;
            } else {
                if (str2.startsWith(".")) {
                    if (withinDomain(str, str2) || str.equalsIgnoreCase(str2)) {
                        set.add(str);
                        return;
                    } else {
                        if (withinDomain(str2, str)) {
                            set.add(str2);
                            return;
                        }
                        return;
                    }
                }
                if (withinDomain(str2, str)) {
                    set.add(str2);
                    return;
                }
                return;
            }
        }
        if (str2.indexOf(64) != -1) {
            if (str2.substring(str2.indexOf(64) + 1).equalsIgnoreCase(str)) {
                set.add(str2);
            }
        } else if (str2.startsWith(".")) {
            if (withinDomain(str, str2)) {
                set.add(str);
            }
        } else if (str.equalsIgnoreCase(str2)) {
            set.add(str);
        }
    }

    private void checkExcludedURI(Set set, String str) throws PKIXNameConstraintValidatorException {
        if (set.isEmpty()) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (isUriConstrained(str, (String) it.next())) {
                throw new PKIXNameConstraintValidatorException("URI is from an excluded subtree.");
            }
        }
    }

    private Set intersectURI(Set set, Set set2) {
        HashSet hashSet = new HashSet();
        Iterator it = set2.iterator();
        while (it.hasNext()) {
            String strExtractNameAsString = extractNameAsString(((GeneralSubtree) it.next()).getBase());
            if (set != null) {
                Iterator it2 = set.iterator();
                while (it2.hasNext()) {
                    intersectURI((String) it2.next(), strExtractNameAsString, hashSet);
                }
            } else if (strExtractNameAsString != null) {
                hashSet.add(strExtractNameAsString);
            }
        }
        return hashSet;
    }

    private Set unionURI(Set set, String str) {
        if (set.isEmpty()) {
            if (str == null) {
                return set;
            }
            set.add(str);
            return set;
        }
        HashSet hashSet = new HashSet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            unionURI((String) it.next(), str, hashSet);
        }
        return hashSet;
    }

    private void intersectURI(String str, String str2, Set set) {
        if (str.indexOf(64) != -1) {
            String strSubstring = str.substring(str.indexOf(64) + 1);
            if (str2.indexOf(64) != -1) {
                if (str.equalsIgnoreCase(str2)) {
                    set.add(str);
                    return;
                }
                return;
            } else if (str2.startsWith(".")) {
                if (withinDomain(strSubstring, str2)) {
                    set.add(str);
                    return;
                }
                return;
            } else {
                if (strSubstring.equalsIgnoreCase(str2)) {
                    set.add(str);
                    return;
                }
                return;
            }
        }
        if (str.startsWith(".")) {
            if (str2.indexOf(64) != -1) {
                if (withinDomain(str2.substring(str.indexOf(64) + 1), str)) {
                    set.add(str2);
                    return;
                }
                return;
            } else {
                if (str2.startsWith(".")) {
                    if (withinDomain(str, str2) || str.equalsIgnoreCase(str2)) {
                        set.add(str);
                        return;
                    } else {
                        if (withinDomain(str2, str)) {
                            set.add(str2);
                            return;
                        }
                        return;
                    }
                }
                if (withinDomain(str2, str)) {
                    set.add(str2);
                    return;
                }
                return;
            }
        }
        if (str2.indexOf(64) != -1) {
            if (str2.substring(str2.indexOf(64) + 1).equalsIgnoreCase(str)) {
                set.add(str2);
            }
        } else if (str2.startsWith(".")) {
            if (withinDomain(str, str2)) {
                set.add(str);
            }
        } else if (str.equalsIgnoreCase(str2)) {
            set.add(str);
        }
    }

    private void checkPermittedURI(Set set, String str) throws PKIXNameConstraintValidatorException {
        if (set == null) {
            return;
        }
        Iterator it = set.iterator();
        while (it.hasNext()) {
            if (isUriConstrained(str, (String) it.next())) {
                return;
            }
        }
        if (str.length() != 0 || set.size() != 0) {
            throw new PKIXNameConstraintValidatorException("URI is not from a permitted subtree.");
        }
    }

    private boolean isUriConstrained(String str, String str2) {
        String strExtractHostFromURL = extractHostFromURL(str);
        return !str2.startsWith(".") ? strExtractHostFromURL.equalsIgnoreCase(str2) : withinDomain(strExtractHostFromURL, str2);
    }

    private static String extractHostFromURL(String str) {
        String strSubstring = str.substring(str.indexOf(58) + 1);
        if (strSubstring.indexOf("//") != -1) {
            strSubstring = strSubstring.substring(strSubstring.indexOf("//") + 2);
        }
        if (strSubstring.lastIndexOf(58) != -1) {
            strSubstring = strSubstring.substring(0, strSubstring.lastIndexOf(58));
        }
        String strSubstring2 = strSubstring.substring(strSubstring.indexOf(58) + 1);
        String strSubstring3 = strSubstring2.substring(strSubstring2.indexOf(64) + 1);
        return strSubstring3.indexOf(47) != -1 ? strSubstring3.substring(0, strSubstring3.indexOf(47)) : strSubstring3;
    }

    public void checkPermitted(GeneralName generalName) throws PKIXNameConstraintValidatorException {
        int tagNo = generalName.getTagNo();
        if (tagNo == 1) {
            checkPermittedEmail(this.permittedSubtreesEmail, extractNameAsString(generalName));
            return;
        }
        if (tagNo == 2) {
            checkPermittedDNS(this.permittedSubtreesDNS, DERIA5String.getInstance(generalName.getName()).getString());
            return;
        }
        if (tagNo == 4) {
            checkPermittedDN(ASN1Sequence.getInstance(generalName.getName().toASN1Primitive()));
            return;
        }
        if (tagNo == 6) {
            checkPermittedURI(this.permittedSubtreesURI, DERIA5String.getInstance(generalName.getName()).getString());
        } else {
            if (tagNo != 7) {
                return;
            }
            checkPermittedIP(this.permittedSubtreesIP, ASN1OctetString.getInstance(generalName.getName()).getOctets());
        }
    }

    public void checkExcluded(GeneralName generalName) throws PKIXNameConstraintValidatorException {
        int tagNo = generalName.getTagNo();
        if (tagNo == 1) {
            checkExcludedEmail(this.excludedSubtreesEmail, extractNameAsString(generalName));
            return;
        }
        if (tagNo == 2) {
            checkExcludedDNS(this.excludedSubtreesDNS, DERIA5String.getInstance(generalName.getName()).getString());
            return;
        }
        if (tagNo == 4) {
            checkExcludedDN(ASN1Sequence.getInstance(generalName.getName().toASN1Primitive()));
            return;
        }
        if (tagNo == 6) {
            checkExcludedURI(this.excludedSubtreesURI, DERIA5String.getInstance(generalName.getName()).getString());
        } else {
            if (tagNo != 7) {
                return;
            }
            checkExcludedIP(this.excludedSubtreesIP, ASN1OctetString.getInstance(generalName.getName()).getOctets());
        }
    }

    public void intersectPermittedSubtree(GeneralSubtree generalSubtree) {
        intersectPermittedSubtree(new GeneralSubtree[]{generalSubtree});
    }

    public void intersectPermittedSubtree(GeneralSubtree[] generalSubtreeArr) {
        HashMap map = new HashMap();
        for (int i = 0; i != generalSubtreeArr.length; i++) {
            GeneralSubtree generalSubtree = generalSubtreeArr[i];
            Integer numValueOf = Integers.valueOf(generalSubtree.getBase().getTagNo());
            if (map.get(numValueOf) == null) {
                map.put(numValueOf, new HashSet());
            }
            ((Set) map.get(numValueOf)).add(generalSubtree);
        }
        for (Map.Entry entry : map.entrySet()) {
            int iIntValue = ((Integer) entry.getKey()).intValue();
            if (iIntValue == 1) {
                this.permittedSubtreesEmail = intersectEmail(this.permittedSubtreesEmail, (Set) entry.getValue());
            } else if (iIntValue == 2) {
                this.permittedSubtreesDNS = intersectDNS(this.permittedSubtreesDNS, (Set) entry.getValue());
            } else if (iIntValue == 4) {
                this.permittedSubtreesDN = intersectDN(this.permittedSubtreesDN, (Set) entry.getValue());
            } else if (iIntValue == 6) {
                this.permittedSubtreesURI = intersectURI(this.permittedSubtreesURI, (Set) entry.getValue());
            } else if (iIntValue == 7) {
                this.permittedSubtreesIP = intersectIP(this.permittedSubtreesIP, (Set) entry.getValue());
            }
        }
    }

    private String extractNameAsString(GeneralName generalName) {
        return DERIA5String.getInstance(generalName.getName()).getString();
    }

    public void intersectEmptyPermittedSubtree(int i) {
        if (i == 1) {
            this.permittedSubtreesEmail = new HashSet();
            return;
        }
        if (i == 2) {
            this.permittedSubtreesDNS = new HashSet();
            return;
        }
        if (i == 4) {
            this.permittedSubtreesDN = new HashSet();
        } else if (i == 6) {
            this.permittedSubtreesURI = new HashSet();
        } else {
            if (i != 7) {
                return;
            }
            this.permittedSubtreesIP = new HashSet();
        }
    }

    public void addExcludedSubtree(GeneralSubtree generalSubtree) {
        GeneralName base = generalSubtree.getBase();
        int tagNo = base.getTagNo();
        if (tagNo == 1) {
            this.excludedSubtreesEmail = unionEmail(this.excludedSubtreesEmail, extractNameAsString(base));
            return;
        }
        if (tagNo == 2) {
            this.excludedSubtreesDNS = unionDNS(this.excludedSubtreesDNS, extractNameAsString(base));
            return;
        }
        if (tagNo == 4) {
            this.excludedSubtreesDN = unionDN(this.excludedSubtreesDN, (ASN1Sequence) base.getName().toASN1Primitive());
        } else if (tagNo == 6) {
            this.excludedSubtreesURI = unionURI(this.excludedSubtreesURI, extractNameAsString(base));
        } else {
            if (tagNo != 7) {
                return;
            }
            this.excludedSubtreesIP = unionIP(this.excludedSubtreesIP, ASN1OctetString.getInstance(base.getName()).getOctets());
        }
    }

    private static byte[] max(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr.length; i++) {
            if ((bArr[i] & 65535) > (65535 & bArr2[i])) {
                return bArr;
            }
        }
        return bArr2;
    }

    private static byte[] min(byte[] bArr, byte[] bArr2) {
        for (int i = 0; i < bArr.length; i++) {
            if ((bArr[i] & 65535) < (65535 & bArr2[i])) {
                return bArr;
            }
        }
        return bArr2;
    }

    private static int compareTo(byte[] bArr, byte[] bArr2) {
        if (Arrays.areEqual(bArr, bArr2)) {
            return 0;
        }
        return Arrays.areEqual(max(bArr, bArr2), bArr) ? 1 : -1;
    }

    private static byte[] or(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr.length];
        for (int i = 0; i < bArr.length; i++) {
            bArr3[i] = (byte) (bArr[i] | bArr2[i]);
        }
        return bArr3;
    }

    public int hashCode() {
        return hashCollection(this.excludedSubtreesDN) + hashCollection(this.excludedSubtreesDNS) + hashCollection(this.excludedSubtreesEmail) + hashCollection(this.excludedSubtreesIP) + hashCollection(this.excludedSubtreesURI) + hashCollection(this.permittedSubtreesDN) + hashCollection(this.permittedSubtreesDNS) + hashCollection(this.permittedSubtreesEmail) + hashCollection(this.permittedSubtreesIP) + hashCollection(this.permittedSubtreesURI);
    }

    private int hashCollection(Collection collection) {
        int iHashCode;
        int i = 0;
        if (collection == null) {
            return 0;
        }
        for (Object obj : collection) {
            if (obj instanceof byte[]) {
                iHashCode = Arrays.hashCode((byte[]) obj);
            } else {
                iHashCode = obj.hashCode();
            }
            i += iHashCode;
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PKIXNameConstraintValidator)) {
            return false;
        }
        PKIXNameConstraintValidator pKIXNameConstraintValidator = (PKIXNameConstraintValidator) obj;
        return collectionsAreEqual(pKIXNameConstraintValidator.excludedSubtreesDN, this.excludedSubtreesDN) && collectionsAreEqual(pKIXNameConstraintValidator.excludedSubtreesDNS, this.excludedSubtreesDNS) && collectionsAreEqual(pKIXNameConstraintValidator.excludedSubtreesEmail, this.excludedSubtreesEmail) && collectionsAreEqual(pKIXNameConstraintValidator.excludedSubtreesIP, this.excludedSubtreesIP) && collectionsAreEqual(pKIXNameConstraintValidator.excludedSubtreesURI, this.excludedSubtreesURI) && collectionsAreEqual(pKIXNameConstraintValidator.permittedSubtreesDN, this.permittedSubtreesDN) && collectionsAreEqual(pKIXNameConstraintValidator.permittedSubtreesDNS, this.permittedSubtreesDNS) && collectionsAreEqual(pKIXNameConstraintValidator.permittedSubtreesEmail, this.permittedSubtreesEmail) && collectionsAreEqual(pKIXNameConstraintValidator.permittedSubtreesIP, this.permittedSubtreesIP) && collectionsAreEqual(pKIXNameConstraintValidator.permittedSubtreesURI, this.permittedSubtreesURI);
    }

    private boolean collectionsAreEqual(Collection collection, Collection collection2) {
        if (collection == collection2) {
            return true;
        }
        if (collection == null || collection2 == null || collection.size() != collection2.size()) {
            return false;
        }
        for (Object obj : collection) {
            Iterator it = collection2.iterator();
            while (it.hasNext()) {
                if (equals(obj, it.next())) {
                    break;
                }
            }
            return false;
        }
        return true;
    }

    private boolean equals(Object obj, Object obj2) {
        if (obj == obj2) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        if ((obj instanceof byte[]) && (obj2 instanceof byte[])) {
            return Arrays.areEqual((byte[]) obj, (byte[]) obj2);
        }
        return obj.equals(obj2);
    }

    private String stringifyIP(byte[] bArr) {
        String str = "";
        for (int i = 0; i < bArr.length / 2; i++) {
            str = str + Integer.toString(bArr[i] & 255) + ".";
        }
        String str2 = str.substring(0, str.length() - 1) + "/";
        for (int length = bArr.length / 2; length < bArr.length; length++) {
            str2 = str2 + Integer.toString(bArr[length] & 255) + ".";
        }
        return str2.substring(0, str2.length() - 1);
    }

    private String stringifyIPCollection(Set set) {
        Iterator it = set.iterator();
        String strSubstring = "[";
        while (it.hasNext()) {
            strSubstring = strSubstring + stringifyIP((byte[]) it.next()) + Constants.SEPARATOR_COMMA;
        }
        if (strSubstring.length() > 1) {
            strSubstring = strSubstring.substring(0, strSubstring.length() - 1);
        }
        return strSubstring + "]";
    }

    public String toString() {
        String str = this.permittedSubtreesDN != null ? "permitted:\nDN:\n" + this.permittedSubtreesDN.toString() + "\n" : "permitted:\n";
        if (this.permittedSubtreesDNS != null) {
            str = (str + "DNS:\n") + this.permittedSubtreesDNS.toString() + "\n";
        }
        if (this.permittedSubtreesEmail != null) {
            str = (str + "Email:\n") + this.permittedSubtreesEmail.toString() + "\n";
        }
        if (this.permittedSubtreesURI != null) {
            str = (str + "URI:\n") + this.permittedSubtreesURI.toString() + "\n";
        }
        if (this.permittedSubtreesIP != null) {
            str = (str + "IP:\n") + stringifyIPCollection(this.permittedSubtreesIP) + "\n";
        }
        String str2 = str + "excluded:\n";
        if (!this.excludedSubtreesDN.isEmpty()) {
            str2 = (str2 + "DN:\n") + this.excludedSubtreesDN.toString() + "\n";
        }
        if (!this.excludedSubtreesDNS.isEmpty()) {
            str2 = (str2 + "DNS:\n") + this.excludedSubtreesDNS.toString() + "\n";
        }
        if (!this.excludedSubtreesEmail.isEmpty()) {
            str2 = (str2 + "Email:\n") + this.excludedSubtreesEmail.toString() + "\n";
        }
        if (!this.excludedSubtreesURI.isEmpty()) {
            str2 = (str2 + "URI:\n") + this.excludedSubtreesURI.toString() + "\n";
        }
        if (this.excludedSubtreesIP.isEmpty()) {
            return str2;
        }
        return (str2 + "IP:\n") + stringifyIPCollection(this.excludedSubtreesIP) + "\n";
    }
}
