package org.ejbca.cvc;

import org.ejbca.cvc.exception.ConstructionException;

/* loaded from: classes4.dex */
public class CVCAuthorizationTemplate extends AbstractSequence {
    private static CVCTagEnum[] allowedFields = {CVCTagEnum.OID, CVCTagEnum.ROLE_AND_ACCESS_RIGHTS};
    private static final long serialVersionUID = 1;

    @Override // org.ejbca.cvc.AbstractSequence
    protected CVCTagEnum[] getAllowedFields() {
        return allowedFields;
    }

    CVCAuthorizationTemplate() {
        super(CVCTagEnum.HOLDER_AUTH_TEMPLATE);
    }

    @Override // org.ejbca.cvc.AbstractSequence
    void addSubfield(CVCObject cVCObject) throws ConstructionException {
        super.addSubfield(cVCObject);
        if (cVCObject instanceof AuthorizationField) {
            try {
                ((AuthorizationField) cVCObject).fixEnumTypes((OIDField) getSubfield(CVCTagEnum.OID));
            } catch (NoSuchFieldException e) {
                throw new ConstructionException("Tried to add an AuthorizationField without an OID", e);
            }
        }
    }

    public CVCAuthorizationTemplate(AuthorizationRole authorizationRole, AccessRights accessRights) throws ConstructionException {
        this(authorizationRole, accessRights, getOIDForEnums(authorizationRole, accessRights));
    }

    public CVCAuthorizationTemplate(AuthorizationRole authorizationRole, AccessRights accessRights, String str) throws ConstructionException {
        this(authorizationRole, accessRights, new OIDField(str));
    }

    CVCAuthorizationTemplate(AuthorizationRole authorizationRole, AccessRights accessRights, OIDField oIDField) throws ConstructionException {
        this();
        addSubfield(oIDField);
        addSubfield(new AuthorizationField(authorizationRole, accessRights));
    }

    public CVCAuthorizationTemplate(AuthorizationRoleEnum authorizationRoleEnum, AccessRightEnum accessRightEnum) throws ConstructionException {
        this((AuthorizationRole) authorizationRoleEnum, (AccessRights) accessRightEnum);
    }

    public static OIDField getOIDForEnums(AuthorizationRole authorizationRole, AccessRights accessRights) {
        if ((authorizationRole instanceof AuthorizationRoleEnum) && (accessRights instanceof AccessRightEnum)) {
            return CVCObjectIdentifiers.id_EAC_ePassport;
        }
        if ((authorizationRole instanceof AuthorizationRoleAuthTermEnum) && (accessRights instanceof AccessRightAuthTerm)) {
            return CVCObjectIdentifiers.id_EAC_roles_AT;
        }
        if ((authorizationRole instanceof AuthorizationRoleSignTermEnum) && (accessRights instanceof AccessRightSignTermEnum)) {
            return CVCObjectIdentifiers.id_EAC_roles_ST;
        }
        throw new IllegalArgumentException("Unsupported roles/rights type (or mismatch). Got role of type " + authorizationRole.getClass().getSimpleName() + ", but rights of type " + accessRights.getClass().getSimpleName());
    }

    public String getObjectIdentifier() throws NoSuchFieldException {
        return ((OIDField) getSubfield(CVCTagEnum.OID)).getValue();
    }

    public AuthorizationField getAuthorizationField() throws NoSuchFieldException {
        return (AuthorizationField) getSubfield(CVCTagEnum.ROLE_AND_ACCESS_RIGHTS);
    }
}
