package org.ejbca.cvc;

import java.util.Collection;
import java.util.Date;
import org.ejbca.cvc.exception.ConstructionException;

/* loaded from: classes4.dex */
public class CVCertificateBody extends AbstractSequence {
    private static CVCTagEnum[] allowedFields = {CVCTagEnum.PROFILE_IDENTIFIER, CVCTagEnum.CA_REFERENCE, CVCTagEnum.PUBLIC_KEY, CVCTagEnum.HOLDER_REFERENCE, CVCTagEnum.HOLDER_AUTH_TEMPLATE, CVCTagEnum.EFFECTIVE_DATE, CVCTagEnum.EXPIRATION_DATE, CVCTagEnum.CERTIFICATE_EXTENSIONS};
    private static final long serialVersionUID = 1;

    @Override // org.ejbca.cvc.AbstractSequence
    protected CVCTagEnum[] getAllowedFields() {
        return allowedFields;
    }

    CVCertificateBody() {
        super(CVCTagEnum.CERTIFICATE_BODY);
    }

    public CVCertificateBody(CAReferenceField cAReferenceField, CVCPublicKey cVCPublicKey, HolderReferenceField holderReferenceField) throws ConstructionException {
        this(cAReferenceField, cVCPublicKey, holderReferenceField, null);
    }

    public CVCertificateBody(CAReferenceField cAReferenceField, CVCPublicKey cVCPublicKey, HolderReferenceField holderReferenceField, Collection<CVCDiscretionaryDataTemplate> collection) throws ConstructionException {
        this();
        if (cVCPublicKey == null) {
            throw new IllegalArgumentException("publicKey is null");
        }
        if (holderReferenceField == null) {
            throw new IllegalArgumentException("holderReference is null");
        }
        addSubfield(new IntegerField(CVCTagEnum.PROFILE_IDENTIFIER, 0));
        addSubfield(cAReferenceField);
        addSubfield(cVCPublicKey);
        addSubfield(holderReferenceField);
        if (collection != null) {
            addSubfield(new CVCertificateExtensions(collection));
        }
    }

    public CVCertificateBody(CAReferenceField cAReferenceField, CVCPublicKey cVCPublicKey, HolderReferenceField holderReferenceField, CVCAuthorizationTemplate cVCAuthorizationTemplate, Date date, Date date2) throws ConstructionException {
        this(cAReferenceField, cVCPublicKey, holderReferenceField);
        if (cVCAuthorizationTemplate == null) {
            throw new IllegalArgumentException("holderAuthorizationTemplate is null");
        }
        if (date == null) {
            throw new IllegalArgumentException("validFrom is null");
        }
        if (date2 == null) {
            throw new IllegalArgumentException("validTo is null");
        }
        addSubfield(cVCAuthorizationTemplate);
        addSubfield(new DateField(CVCTagEnum.EFFECTIVE_DATE, date));
        addSubfield(new DateField(CVCTagEnum.EXPIRATION_DATE, date2));
    }

    public CVCertificateBody(CAReferenceField cAReferenceField, CVCPublicKey cVCPublicKey, HolderReferenceField holderReferenceField, AuthorizationRole authorizationRole, AccessRights accessRights, Date date, Date date2) throws ConstructionException {
        this(cAReferenceField, cVCPublicKey, holderReferenceField, chat(authorizationRole, accessRights), date, date2);
    }

    private static CVCAuthorizationTemplate chat(AuthorizationRole authorizationRole, AccessRights accessRights) throws ConstructionException {
        if (authorizationRole == null) {
            throw new IllegalArgumentException("authRole is null");
        }
        if (accessRights == null) {
            throw new IllegalArgumentException("accessRight is null");
        }
        return new CVCAuthorizationTemplate(authorizationRole, accessRights);
    }

    public CVCertificateBody(CAReferenceField cAReferenceField, CVCPublicKey cVCPublicKey, HolderReferenceField holderReferenceField, AuthorizationRoleEnum authorizationRoleEnum, AccessRightEnum accessRightEnum, Date date, Date date2) throws ConstructionException {
        this(cAReferenceField, cVCPublicKey, holderReferenceField, (AuthorizationRole) authorizationRoleEnum, (AccessRights) accessRightEnum, date, date2);
    }

    public CVCAuthorizationTemplate getAuthorizationTemplate() throws NoSuchFieldException {
        return (CVCAuthorizationTemplate) getSubfield(CVCTagEnum.HOLDER_AUTH_TEMPLATE);
    }

    public CVCertificateBody(CAReferenceField cAReferenceField, CVCPublicKey cVCPublicKey, HolderReferenceField holderReferenceField, AuthorizationRole authorizationRole, AccessRights accessRights, Date date, Date date2, Collection<CVCDiscretionaryDataTemplate> collection) throws ConstructionException {
        this(cAReferenceField, cVCPublicKey, holderReferenceField, authorizationRole, accessRights, date, date2);
        if (collection != null) {
            addSubfield(new CVCertificateExtensions(collection));
        }
    }

    public Date getValidFrom() throws NoSuchFieldException {
        return ((DateField) getSubfield(CVCTagEnum.EFFECTIVE_DATE)).getDate();
    }

    public Date getValidTo() throws NoSuchFieldException {
        return ((DateField) getSubfield(CVCTagEnum.EXPIRATION_DATE)).getDate();
    }

    public CAReferenceField getAuthorityReference() throws NoSuchFieldException {
        return (CAReferenceField) getOptionalSubfield(CVCTagEnum.CA_REFERENCE);
    }

    public CVCPublicKey getPublicKey() throws NoSuchFieldException {
        return (CVCPublicKey) getSubfield(CVCTagEnum.PUBLIC_KEY);
    }

    public HolderReferenceField getHolderReference() throws NoSuchFieldException {
        return (HolderReferenceField) getSubfield(CVCTagEnum.HOLDER_REFERENCE);
    }

    public CVCertificateExtensions getCertificateExtensions() throws NoSuchFieldException {
        return (CVCertificateExtensions) getSubfield(CVCTagEnum.CERTIFICATE_EXTENSIONS);
    }
}
