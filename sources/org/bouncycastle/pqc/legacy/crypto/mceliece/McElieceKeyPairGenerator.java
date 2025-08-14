package org.bouncycastle.pqc.legacy.crypto.mceliece;

import java.security.SecureRandom;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.pqc.legacy.math.linearalgebra.GF2Matrix;
import org.bouncycastle.pqc.legacy.math.linearalgebra.GF2mField;
import org.bouncycastle.pqc.legacy.math.linearalgebra.GoppaCode;
import org.bouncycastle.pqc.legacy.math.linearalgebra.Permutation;
import org.bouncycastle.pqc.legacy.math.linearalgebra.PolynomialGF2mSmallM;
import org.bouncycastle.pqc.legacy.math.linearalgebra.PolynomialRingGF2m;

/* loaded from: classes4.dex */
public class McElieceKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private static final String OID = "1.3.6.1.4.1.8301.3.1.3.4.1";
    private int fieldPoly;
    private boolean initialized = false;
    private int m;
    private McElieceKeyGenerationParameters mcElieceParams;

    /* renamed from: n, reason: collision with root package name */
    private int f368n;
    private SecureRandom random;
    private int t;

    private AsymmetricCipherKeyPair genKeyPair() {
        if (!this.initialized) {
            initializeDefault();
        }
        GF2mField gF2mField = new GF2mField(this.m, this.fieldPoly);
        PolynomialGF2mSmallM polynomialGF2mSmallM = new PolynomialGF2mSmallM(gF2mField, this.t, 'I', this.random);
        new PolynomialRingGF2m(gF2mField, polynomialGF2mSmallM).getSquareRootMatrix();
        GoppaCode.MaMaPe maMaPeComputeSystematicForm = GoppaCode.computeSystematicForm(GoppaCode.createCanonicalCheckMatrix(gF2mField, polynomialGF2mSmallM), this.random);
        GF2Matrix secondMatrix = maMaPeComputeSystematicForm.getSecondMatrix();
        Permutation permutation = maMaPeComputeSystematicForm.getPermutation();
        GF2Matrix gF2Matrix = (GF2Matrix) secondMatrix.computeTranspose();
        GF2Matrix gF2MatrixExtendLeftCompactForm = gF2Matrix.extendLeftCompactForm();
        int numRows = gF2Matrix.getNumRows();
        GF2Matrix[] gF2MatrixArrCreateRandomRegularMatrixAndItsInverse = GF2Matrix.createRandomRegularMatrixAndItsInverse(numRows, this.random);
        Permutation permutation2 = new Permutation(this.f368n, this.random);
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new McEliecePublicKeyParameters(this.f368n, this.t, (GF2Matrix) ((GF2Matrix) gF2MatrixArrCreateRandomRegularMatrixAndItsInverse[0].rightMultiply(gF2MatrixExtendLeftCompactForm)).rightMultiply(permutation2)), (AsymmetricKeyParameter) new McEliecePrivateKeyParameters(this.f368n, numRows, gF2mField, polynomialGF2mSmallM, permutation, permutation2, gF2MatrixArrCreateRandomRegularMatrixAndItsInverse[1]));
    }

    private void initialize(KeyGenerationParameters keyGenerationParameters) {
        this.mcElieceParams = (McElieceKeyGenerationParameters) keyGenerationParameters;
        this.random = keyGenerationParameters.getRandom();
        this.m = this.mcElieceParams.getParameters().getM();
        this.f368n = this.mcElieceParams.getParameters().getN();
        this.t = this.mcElieceParams.getParameters().getT();
        this.fieldPoly = this.mcElieceParams.getParameters().getFieldPoly();
        this.initialized = true;
    }

    private void initializeDefault() {
        initialize(new McElieceKeyGenerationParameters(null, new McElieceParameters()));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        return genKeyPair();
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        initialize(keyGenerationParameters);
    }
}
