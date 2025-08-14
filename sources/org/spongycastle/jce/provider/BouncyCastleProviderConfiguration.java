package org.spongycastle.jce.provider;

import java.security.Permission;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.crypto.spec.DHParameterSpec;
import org.spongycastle.jcajce.provider.asymmetric.util.EC5Util;
import org.spongycastle.jcajce.provider.config.ProviderConfiguration;
import org.spongycastle.jcajce.provider.config.ProviderConfigurationPermission;
import org.spongycastle.jce.spec.ECParameterSpec;

/* loaded from: classes7.dex */
class BouncyCastleProviderConfiguration implements ProviderConfiguration {
    private volatile Object dhDefaultParams;
    private volatile ECParameterSpec ecImplicitCaParams;
    private static Permission BC_EC_LOCAL_PERMISSION = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, "threadLocalEcImplicitlyCa");
    private static Permission BC_EC_PERMISSION = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, "ecImplicitlyCa");
    private static Permission BC_DH_LOCAL_PERMISSION = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, "threadLocalDhDefaultParams");
    private static Permission BC_DH_PERMISSION = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, "DhDefaultParams");
    private static Permission BC_EC_CURVE_PERMISSION = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, "acceptableEcCurves");
    private static Permission BC_ADDITIONAL_EC_CURVE_PERMISSION = new ProviderConfigurationPermission(BouncyCastleProvider.PROVIDER_NAME, "additionalEcParameters");
    private ThreadLocal ecThreadSpec = new ThreadLocal();
    private ThreadLocal dhThreadSpec = new ThreadLocal();
    private volatile Set acceptableNamedCurves = new HashSet();
    private volatile Map additionalECParameters = new HashMap();

    BouncyCastleProviderConfiguration() {
    }

    void setParameter(String str, Object obj) {
        ECParameterSpec eCParameterSpecConvertSpec;
        SecurityManager securityManager = System.getSecurityManager();
        if (str.equals("threadLocalEcImplicitlyCa")) {
            if (securityManager != null) {
                securityManager.checkPermission(BC_EC_LOCAL_PERMISSION);
            }
            if ((obj instanceof ECParameterSpec) || obj == null) {
                eCParameterSpecConvertSpec = (ECParameterSpec) obj;
            } else {
                eCParameterSpecConvertSpec = EC5Util.convertSpec((java.security.spec.ECParameterSpec) obj, false);
            }
            if (eCParameterSpecConvertSpec == null) {
                this.ecThreadSpec.remove();
                return;
            } else {
                this.ecThreadSpec.set(eCParameterSpecConvertSpec);
                return;
            }
        }
        if (str.equals("ecImplicitlyCa")) {
            if (securityManager != null) {
                securityManager.checkPermission(BC_EC_PERMISSION);
            }
            if ((obj instanceof ECParameterSpec) || obj == null) {
                this.ecImplicitCaParams = (ECParameterSpec) obj;
                return;
            } else {
                this.ecImplicitCaParams = EC5Util.convertSpec((java.security.spec.ECParameterSpec) obj, false);
                return;
            }
        }
        if (str.equals("threadLocalDhDefaultParams")) {
            if (securityManager != null) {
                securityManager.checkPermission(BC_DH_LOCAL_PERMISSION);
            }
            if (!(obj instanceof DHParameterSpec) && !(obj instanceof DHParameterSpec[]) && obj != null) {
                throw new IllegalArgumentException("not a valid DHParameterSpec");
            }
            if (obj == null) {
                this.dhThreadSpec.remove();
                return;
            } else {
                this.dhThreadSpec.set(obj);
                return;
            }
        }
        if (str.equals("DhDefaultParams")) {
            if (securityManager != null) {
                securityManager.checkPermission(BC_DH_PERMISSION);
            }
            if (!(obj instanceof DHParameterSpec) && !(obj instanceof DHParameterSpec[]) && obj != null) {
                throw new IllegalArgumentException("not a valid DHParameterSpec or DHParameterSpec[]");
            }
            this.dhDefaultParams = obj;
            return;
        }
        if (str.equals("acceptableEcCurves")) {
            if (securityManager != null) {
                securityManager.checkPermission(BC_EC_CURVE_PERMISSION);
            }
            this.acceptableNamedCurves = (Set) obj;
        } else if (str.equals("additionalEcParameters")) {
            if (securityManager != null) {
                securityManager.checkPermission(BC_ADDITIONAL_EC_CURVE_PERMISSION);
            }
            this.additionalECParameters = (Map) obj;
        }
    }

    @Override // org.spongycastle.jcajce.provider.config.ProviderConfiguration
    public ECParameterSpec getEcImplicitlyCa() {
        ECParameterSpec eCParameterSpec = (ECParameterSpec) this.ecThreadSpec.get();
        return eCParameterSpec != null ? eCParameterSpec : this.ecImplicitCaParams;
    }

    @Override // org.spongycastle.jcajce.provider.config.ProviderConfiguration
    public DHParameterSpec getDHDefaultParameters(int i) {
        Object obj = this.dhThreadSpec.get();
        if (obj == null) {
            obj = this.dhDefaultParams;
        }
        if (obj instanceof DHParameterSpec) {
            DHParameterSpec dHParameterSpec = (DHParameterSpec) obj;
            if (dHParameterSpec.getP().bitLength() == i) {
                return dHParameterSpec;
            }
            return null;
        }
        if (!(obj instanceof DHParameterSpec[])) {
            return null;
        }
        DHParameterSpec[] dHParameterSpecArr = (DHParameterSpec[]) obj;
        for (int i2 = 0; i2 != dHParameterSpecArr.length; i2++) {
            if (dHParameterSpecArr[i2].getP().bitLength() == i) {
                return dHParameterSpecArr[i2];
            }
        }
        return null;
    }

    @Override // org.spongycastle.jcajce.provider.config.ProviderConfiguration
    public Set getAcceptableNamedCurves() {
        return Collections.unmodifiableSet(this.acceptableNamedCurves);
    }

    @Override // org.spongycastle.jcajce.provider.config.ProviderConfiguration
    public Map getAdditionalECParameters() {
        return Collections.unmodifiableMap(this.additionalECParameters);
    }
}
