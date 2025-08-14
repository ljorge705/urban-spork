package org.ejbca.cvc;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.ejbca.cvc.exception.ConstructionException;
import org.ejbca.cvc.util.BCECUtil;

/* loaded from: classes4.dex */
public final class CertificateGenerator {
    private CertificateGenerator() {
    }

    public static CVCertificate createTestCertificate(PublicKey publicKey, PrivateKey privateKey, CAReferenceField cAReferenceField, HolderReferenceField holderReferenceField, String str, AuthorizationRoleEnum authorizationRoleEnum) throws ConstructionException, NoSuchAlgorithmException, SignatureException, IOException, InvalidKeyException, NoSuchProviderException {
        Date time = Calendar.getInstance().getTime();
        Calendar calendar = Calendar.getInstance();
        calendar.add(2, 3);
        return createCertificate(publicKey, privateKey, str, cAReferenceField, holderReferenceField, authorizationRoleEnum, AccessRightEnum.READ_ACCESS_DG3_AND_DG4, time, calendar.getTime(), BouncyCastleProvider.PROVIDER_NAME);
    }

    public static CVCertificate createCertificate(PrivateKey privateKey, String str, CVCertificateBody cVCertificateBody, String str2) throws ConstructionException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, IOException, NoSuchProviderException {
        CVCertificate cVCertificate = new CVCertificate(cVCertificateBody);
        Signature signature = Signature.getInstance(AlgorithmUtil.convertAlgorithmNameToCVC(str), str2);
        signature.initSign(privateKey);
        signature.update(cVCertificate.getTBS());
        cVCertificate.setSignature(BCECUtil.convertX962SigToCVC(str, signature.sign()));
        return cVCertificate;
    }

    public static CVCertificate createCertificate(PublicKey publicKey, PrivateKey privateKey, String str, CAReferenceField cAReferenceField, HolderReferenceField holderReferenceField, AuthorizationRole authorizationRole, AccessRights accessRights, Date date, Date date2, Collection<CVCDiscretionaryDataTemplate> collection, String str2) throws ConstructionException, NoSuchAlgorithmException, SignatureException, IOException, InvalidKeyException, NoSuchProviderException {
        return createCertificate(privateKey, str, new CVCertificateBody(cAReferenceField, KeyFactory.createInstance(publicKey, str, authorizationRole), holderReferenceField, authorizationRole, accessRights, date, date2, collection), str2);
    }

    public static CVCertificate createCertificate(PublicKey publicKey, PrivateKey privateKey, String str, CAReferenceField cAReferenceField, HolderReferenceField holderReferenceField, AuthorizationRole authorizationRole, AccessRights accessRights, Date date, Date date2, String str2) throws ConstructionException, NoSuchAlgorithmException, SignatureException, IOException, InvalidKeyException, NoSuchProviderException {
        return createCertificate(publicKey, privateKey, str, cAReferenceField, holderReferenceField, authorizationRole, accessRights, date, date2, null, str2);
    }

    public static CVCertificate createCertificate(PublicKey publicKey, PrivateKey privateKey, String str, CAReferenceField cAReferenceField, HolderReferenceField holderReferenceField, AuthorizationRoleEnum authorizationRoleEnum, AccessRightEnum accessRightEnum, Date date, Date date2, String str2) throws ConstructionException, NoSuchAlgorithmException, SignatureException, IOException, InvalidKeyException, NoSuchProviderException {
        return createCertificate(publicKey, privateKey, str, cAReferenceField, holderReferenceField, (AuthorizationRole) authorizationRoleEnum, (AccessRights) accessRightEnum, date, date2, str2);
    }

    public static CVCertificate createRequest(KeyPair keyPair, String str, HolderReferenceField holderReferenceField) throws ConstructionException, NoSuchAlgorithmException, SignatureException, IOException, InvalidKeyException, NoSuchProviderException {
        return createRequest(keyPair, str, holderReferenceField, BouncyCastleProvider.PROVIDER_NAME);
    }

    public static CVCertificate createRequest(KeyPair keyPair, String str, HolderReferenceField holderReferenceField, String str2) throws ConstructionException, NoSuchAlgorithmException, SignatureException, IOException, InvalidKeyException, NoSuchProviderException {
        return createRequest(keyPair, str, null, holderReferenceField, str2);
    }

    public static CVCertificate createRequest(KeyPair keyPair, String str, CAReferenceField cAReferenceField, HolderReferenceField holderReferenceField) throws ConstructionException, NoSuchAlgorithmException, SignatureException, IOException, InvalidKeyException, NoSuchProviderException {
        return createRequest(keyPair, str, cAReferenceField, holderReferenceField, null, BouncyCastleProvider.PROVIDER_NAME);
    }

    public static CVCertificate createRequest(KeyPair keyPair, String str, CAReferenceField cAReferenceField, HolderReferenceField holderReferenceField, String str2) throws ConstructionException, NoSuchAlgorithmException, SignatureException, IOException, InvalidKeyException, NoSuchProviderException {
        return createRequest(keyPair, str, cAReferenceField, holderReferenceField, null, str2);
    }

    public static CVCertificate createRequest(KeyPair keyPair, String str, CAReferenceField cAReferenceField, HolderReferenceField holderReferenceField, Collection<CVCDiscretionaryDataTemplate> collection, String str2) throws ConstructionException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, IOException, NoSuchProviderException {
        CVCertificate cVCertificate = new CVCertificate(new CVCertificateBody(cAReferenceField, KeyFactory.createInstance(keyPair.getPublic(), str, (AuthorizationRoleEnum) null), holderReferenceField, collection));
        Signature signature = Signature.getInstance(AlgorithmUtil.convertAlgorithmNameToCVC(str), str2);
        signature.initSign(keyPair.getPrivate());
        signature.update(cVCertificate.getTBS());
        cVCertificate.setSignature(BCECUtil.convertX962SigToCVC(str, signature.sign()));
        return cVCertificate;
    }

    public static CVCAuthenticatedRequest createAuthenticatedRequest(CVCertificate cVCertificate, KeyPair keyPair, String str, CAReferenceField cAReferenceField) throws ConstructionException, NoSuchAlgorithmException, SignatureException, IOException, InvalidKeyException, NoSuchProviderException {
        return createAuthenticatedRequest(cVCertificate, keyPair, str, cAReferenceField, BouncyCastleProvider.PROVIDER_NAME);
    }

    public static CVCAuthenticatedRequest createAuthenticatedRequest(CVCertificate cVCertificate, KeyPair keyPair, String str, CAReferenceField cAReferenceField, String str2) throws ConstructionException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, IOException, NoSuchProviderException {
        CVCAuthenticatedRequest cVCAuthenticatedRequest = new CVCAuthenticatedRequest(cVCertificate, cAReferenceField);
        Signature signature = Signature.getInstance(AlgorithmUtil.convertAlgorithmNameToCVC(str), str2);
        signature.initSign(keyPair.getPrivate());
        signature.update(cVCAuthenticatedRequest.getTBS());
        cVCAuthenticatedRequest.setSignature(BCECUtil.convertX962SigToCVC(str, signature.sign()));
        return cVCAuthenticatedRequest;
    }
}
