package org.bouncycastle.crypto.generators;

import com.drew.metadata.exif.ExifDirectoryBase;
import com.drew.metadata.exif.makernotes.SanyoMakernoteDirectory;
import com.drew.metadata.iptc.IptcDirectory;
import com.onfido.android.sdk.capture.ui.OnfidoActivity;
import io.grpc.internal.GrpcUtil;
import java.io.PrintStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Vector;
import okhttp3.internal.http.StatusLine;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.CryptoServicePurpose;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.constraints.ConstraintUtils;
import org.bouncycastle.crypto.constraints.DefaultServiceProperties;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.NaccacheSternKeyGenerationParameters;
import org.bouncycastle.crypto.params.NaccacheSternKeyParameters;
import org.bouncycastle.crypto.params.NaccacheSternPrivateKeyParameters;
import org.bouncycastle.util.BigIntegers;
import org.jmrtd.PassportService;
import org.spongycastle.crypto.tls.CipherSuite;

/* loaded from: classes4.dex */
public class NaccacheSternKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private NaccacheSternKeyGenerationParameters param;
    private static int[] smallPrimes = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_256_CBC_SHA256, 199, 211, PassportService.DEFAULT_MAX_BLOCKSIZE, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313, ExifDirectoryBase.TAG_PREDICTOR, 331, 337, ExifDirectoryBase.TAG_JPEG_TABLES, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, StatusLine.HTTP_MISDIRECTED_REQUEST, 431, OnfidoActivity.ONFIDO_RECREATE, 439, GrpcUtil.DEFAULT_PORT_SSL, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, SanyoMakernoteDirectory.TAG_LIGHT_SOURCE_SPECIAL, 547, IptcDirectory.TAG_REFERENCE_SERVICE};
    private static final BigInteger ONE = BigInteger.valueOf(1);

    private static Vector findFirstPrimes(int i) {
        Vector vector = new Vector(i);
        for (int i2 = 0; i2 != i; i2++) {
            vector.addElement(BigInteger.valueOf(smallPrimes[i2]));
        }
        return vector;
    }

    private static BigInteger generatePrime(int i, int i2, SecureRandom secureRandom) throws IllegalArgumentException {
        BigInteger bigIntegerCreateRandomPrime;
        do {
            bigIntegerCreateRandomPrime = BigIntegers.createRandomPrime(i, i2, secureRandom);
        } while (bigIntegerCreateRandomPrime.bitLength() != i);
        return bigIntegerCreateRandomPrime;
    }

    private static int getInt(SecureRandom secureRandom, int i) {
        int iNextInt;
        int i2;
        if (((-i) & i) == i) {
            return (int) ((i * (secureRandom.nextInt() & Integer.MAX_VALUE)) >> 31);
        }
        do {
            iNextInt = secureRandom.nextInt() & Integer.MAX_VALUE;
            i2 = iNextInt % i;
        } while ((iNextInt - i2) + (i - 1) < 0);
        return i2;
    }

    private static Vector permuteList(Vector vector, SecureRandom secureRandom) {
        Vector vector2 = new Vector();
        Vector vector3 = new Vector();
        for (int i = 0; i < vector.size(); i++) {
            vector3.addElement(vector.elementAt(i));
        }
        vector2.addElement(vector3.elementAt(0));
        while (true) {
            vector3.removeElementAt(0);
            if (vector3.size() == 0) {
                return vector2;
            }
            vector2.insertElementAt(vector3.elementAt(0), getInt(secureRandom, vector2.size() + 1));
        }
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() throws IllegalArgumentException {
        long j;
        BigInteger bigIntegerGeneratePrime;
        BigInteger bigIntegerAdd;
        BigInteger bigIntegerGeneratePrime2;
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger bigIntegerAdd2;
        BigInteger bigInteger3;
        BigInteger bigIntegerMultiply;
        BigInteger bigInteger4;
        BigInteger bigInteger5;
        BigInteger bigInteger6;
        BigInteger bigInteger7;
        BigInteger bigIntegerMod;
        BigInteger bigInteger8;
        BigInteger bigInteger9;
        int i;
        BigInteger bigInteger10;
        BigInteger bigInteger11;
        BigInteger bigInteger12;
        PrintStream printStream;
        StringBuilder sb;
        long j2;
        BigInteger bigIntegerCreateRandomPrime;
        SecureRandom secureRandom;
        SecureRandom secureRandom2;
        int i2;
        BigInteger bigInteger13;
        BigInteger bigInteger14;
        int i3;
        int strength = this.param.getStrength();
        SecureRandom random = this.param.getRandom();
        int certainty = this.param.getCertainty();
        boolean zIsDebug = this.param.isDebug();
        if (zIsDebug) {
            System.out.println("Fetching first " + this.param.getCntSmallPrimes() + " primes.");
        }
        Vector vectorPermuteList = permuteList(findFirstPrimes(this.param.getCntSmallPrimes()), random);
        BigInteger bigIntegerMultiply2 = ONE;
        BigInteger bigIntegerMultiply3 = bigIntegerMultiply2;
        for (int i4 = 0; i4 < vectorPermuteList.size() / 2; i4++) {
            bigIntegerMultiply3 = bigIntegerMultiply3.multiply((BigInteger) vectorPermuteList.elementAt(i4));
        }
        for (int size = vectorPermuteList.size() / 2; size < vectorPermuteList.size(); size++) {
            bigIntegerMultiply2 = bigIntegerMultiply2.multiply((BigInteger) vectorPermuteList.elementAt(size));
        }
        BigInteger bigIntegerMultiply4 = bigIntegerMultiply3.multiply(bigIntegerMultiply2);
        int iBitLength = (((strength - bigIntegerMultiply4.bitLength()) - 48) / 2) + 1;
        BigInteger bigIntegerGeneratePrime3 = generatePrime(iBitLength, certainty, random);
        BigInteger bigIntegerGeneratePrime4 = generatePrime(iBitLength, certainty, random);
        if (zIsDebug) {
            System.out.println("generating p and q");
        }
        BigInteger bigIntegerShiftLeft = bigIntegerGeneratePrime3.multiply(bigIntegerMultiply3).shiftLeft(1);
        BigInteger bigIntegerShiftLeft2 = bigIntegerGeneratePrime4.multiply(bigIntegerMultiply2).shiftLeft(1);
        long j3 = 0;
        while (true) {
            j = j3 + 1;
            bigIntegerGeneratePrime = generatePrime(24, certainty, random);
            bigIntegerAdd = bigIntegerGeneratePrime.multiply(bigIntegerShiftLeft).add(ONE);
            if (bigIntegerAdd.isProbablePrime(certainty)) {
                while (true) {
                    do {
                        bigIntegerGeneratePrime2 = generatePrime(24, certainty, random);
                    } while (bigIntegerGeneratePrime.equals(bigIntegerGeneratePrime2));
                    BigInteger bigIntegerMultiply5 = bigIntegerGeneratePrime2.multiply(bigIntegerShiftLeft2);
                    bigInteger = bigIntegerShiftLeft2;
                    bigInteger2 = ONE;
                    bigIntegerAdd2 = bigIntegerMultiply5.add(bigInteger2);
                    if (bigIntegerAdd2.isProbablePrime(certainty)) {
                        break;
                    }
                    bigIntegerShiftLeft2 = bigInteger;
                    bigIntegerShiftLeft = bigIntegerShiftLeft;
                }
                bigInteger3 = bigIntegerShiftLeft;
                if (BigIntegers.modOddIsCoprime(bigIntegerGeneratePrime.multiply(bigIntegerGeneratePrime2), bigIntegerMultiply4)) {
                    bigIntegerMultiply = bigIntegerAdd.multiply(bigIntegerAdd2);
                    bigInteger4 = bigIntegerGeneratePrime4;
                    if (bigIntegerMultiply.bitLength() >= strength) {
                        break;
                    }
                    int i5 = strength;
                    secureRandom2 = random;
                    i2 = certainty;
                    bigInteger13 = bigIntegerGeneratePrime3;
                    bigInteger14 = bigInteger4;
                    if (zIsDebug) {
                        i3 = i5;
                        System.out.println("key size too small. Should be " + i3 + " but is actually " + bigIntegerAdd.multiply(bigIntegerAdd2).bitLength());
                    } else {
                        i3 = i5;
                    }
                } else {
                    i3 = strength;
                    secureRandom2 = random;
                    i2 = certainty;
                    bigInteger14 = bigIntegerGeneratePrime4;
                    bigInteger13 = bigIntegerGeneratePrime3;
                }
            } else {
                secureRandom2 = random;
                i2 = certainty;
                bigInteger = bigIntegerShiftLeft2;
                bigInteger3 = bigIntegerShiftLeft;
                bigInteger13 = bigIntegerGeneratePrime3;
                i3 = strength;
                bigInteger14 = bigIntegerGeneratePrime4;
            }
            bigIntegerGeneratePrime4 = bigInteger14;
            strength = i3;
            bigIntegerGeneratePrime3 = bigInteger13;
            j3 = j;
            bigIntegerShiftLeft2 = bigInteger;
            bigIntegerShiftLeft = bigInteger3;
            random = secureRandom2;
            certainty = i2;
        }
        if (zIsDebug) {
            bigInteger6 = bigIntegerGeneratePrime3;
            bigInteger5 = bigIntegerGeneratePrime2;
            System.out.println("needed " + j + " tries to generate p and q.");
        } else {
            bigInteger5 = bigIntegerGeneratePrime2;
            bigInteger6 = bigIntegerGeneratePrime3;
        }
        BigInteger bigIntegerMultiply6 = bigIntegerAdd.subtract(bigInteger2).multiply(bigIntegerAdd2.subtract(bigInteger2));
        if (zIsDebug) {
            System.out.println("generating g");
        }
        long j4 = 0;
        while (true) {
            Vector vector = new Vector();
            bigInteger7 = bigIntegerAdd2;
            int i6 = 0;
            while (i6 != vectorPermuteList.size()) {
                BigInteger bigIntegerDivide = bigIntegerMultiply6.divide((BigInteger) vectorPermuteList.elementAt(i6));
                while (true) {
                    j2 = j4 + 1;
                    bigIntegerCreateRandomPrime = BigIntegers.createRandomPrime(strength, certainty, random);
                    secureRandom = random;
                    if (bigIntegerCreateRandomPrime.modPow(bigIntegerDivide, bigIntegerMultiply).equals(ONE)) {
                        j4 = j2;
                        random = secureRandom;
                    }
                }
                vector.addElement(bigIntegerCreateRandomPrime);
                i6++;
                j4 = j2;
                random = secureRandom;
            }
            SecureRandom secureRandom3 = random;
            bigIntegerMod = ONE;
            int i7 = 0;
            while (i7 < vectorPermuteList.size()) {
                bigIntegerMod = bigIntegerMod.multiply(((BigInteger) vector.elementAt(i7)).modPow(bigIntegerMultiply4.divide((BigInteger) vectorPermuteList.elementAt(i7)), bigIntegerMultiply)).mod(bigIntegerMultiply);
                i7++;
                certainty = certainty;
            }
            int i8 = certainty;
            int i9 = 0;
            while (true) {
                if (i9 >= vectorPermuteList.size()) {
                    BigInteger bigIntegerModPow = bigIntegerMod.modPow(bigIntegerMultiply6.divide(BigInteger.valueOf(4L)), bigIntegerMultiply);
                    BigInteger bigInteger15 = ONE;
                    if (!bigIntegerModPow.equals(bigInteger15)) {
                        if (!bigIntegerMod.modPow(bigIntegerMultiply6.divide(bigIntegerGeneratePrime), bigIntegerMultiply).equals(bigInteger15)) {
                            bigInteger8 = bigInteger5;
                            if (!bigIntegerMod.modPow(bigIntegerMultiply6.divide(bigInteger8), bigIntegerMultiply).equals(bigInteger15)) {
                                bigInteger9 = bigInteger6;
                                if (!bigIntegerMod.modPow(bigIntegerMultiply6.divide(bigInteger9), bigIntegerMultiply).equals(bigInteger15)) {
                                    i = strength;
                                    bigInteger10 = bigInteger4;
                                    if (!bigIntegerMod.modPow(bigIntegerMultiply6.divide(bigInteger10), bigIntegerMultiply).equals(bigInteger15)) {
                                        break;
                                    }
                                    if (zIsDebug) {
                                        bigInteger12 = bigIntegerMultiply6;
                                        System.out.println("g has order phi(n)/b\n g: " + bigIntegerMod);
                                    } else {
                                        bigInteger12 = bigIntegerMultiply6;
                                    }
                                } else {
                                    if (zIsDebug) {
                                        i = strength;
                                        System.out.println("g has order phi(n)/a\n g: " + bigIntegerMod);
                                    } else {
                                        i = strength;
                                    }
                                    bigInteger12 = bigIntegerMultiply6;
                                }
                            } else {
                                if (zIsDebug) {
                                    System.out.println("g has order phi(n)/q'\n g: " + bigIntegerMod);
                                }
                                bigInteger12 = bigIntegerMultiply6;
                                bigInteger9 = bigInteger6;
                            }
                        } else if (zIsDebug) {
                            printStream = System.out;
                            sb = new StringBuilder("g has order phi(n)/p'\n g: ");
                            printStream.println(sb.append(bigIntegerMod).toString());
                        }
                    } else if (zIsDebug) {
                        printStream = System.out;
                        sb = new StringBuilder("g has order phi(n)/4\n g:");
                        printStream.println(sb.append(bigIntegerMod).toString());
                    }
                } else if (!bigIntegerMod.modPow(bigIntegerMultiply6.divide((BigInteger) vectorPermuteList.elementAt(i9)), bigIntegerMultiply).equals(ONE)) {
                    i9++;
                } else if (zIsDebug) {
                    System.out.println("g has order phi(n)/" + vectorPermuteList.elementAt(i9) + "\n g: " + bigIntegerMod);
                }
            }
            bigInteger12 = bigIntegerMultiply6;
            bigInteger9 = bigInteger6;
            bigInteger8 = bigInteger5;
            i = strength;
            bigInteger10 = bigInteger4;
            bigInteger4 = bigInteger10;
            bigInteger5 = bigInteger8;
            bigIntegerMultiply6 = bigInteger12;
            strength = i;
            random = secureRandom3;
            certainty = i8;
            bigInteger6 = bigInteger9;
            bigIntegerAdd2 = bigInteger7;
        }
        BigInteger bigInteger16 = bigIntegerMultiply6;
        if (zIsDebug) {
            System.out.println("needed " + j4 + " tries to generate g");
            System.out.println();
            System.out.println("found new NaccacheStern cipher variables:");
            System.out.println("smallPrimes: " + vectorPermuteList);
            System.out.println("sigma:...... " + bigIntegerMultiply4 + " (" + bigIntegerMultiply4.bitLength() + " bits)");
            System.out.println("a:.......... " + bigInteger9);
            System.out.println("b:.......... " + bigInteger10);
            System.out.println("p':......... " + bigIntegerGeneratePrime);
            System.out.println("q':......... " + bigInteger8);
            System.out.println("p:.......... " + bigIntegerAdd);
            System.out.println("q:.......... " + bigInteger7);
            System.out.println("n:.......... " + bigIntegerMultiply);
            bigInteger11 = bigInteger16;
            System.out.println("phi(n):..... " + bigInteger11);
            System.out.println("g:.......... " + bigIntegerMod);
            System.out.println();
        } else {
            bigInteger11 = bigInteger16;
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new NaccacheSternKeyParameters(false, bigIntegerMod, bigIntegerMultiply, bigIntegerMultiply4.bitLength()), (AsymmetricKeyParameter) new NaccacheSternPrivateKeyParameters(bigIntegerMod, bigIntegerMultiply, bigIntegerMultiply4.bitLength(), vectorPermuteList, bigInteger11));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.param = (NaccacheSternKeyGenerationParameters) keyGenerationParameters;
        CryptoServicesRegistrar.checkConstraints(new DefaultServiceProperties("NaccacheStern KeyGen", ConstraintUtils.bitsOfSecurityForFF(keyGenerationParameters.getStrength()), keyGenerationParameters, CryptoServicePurpose.KEYGEN));
    }
}
