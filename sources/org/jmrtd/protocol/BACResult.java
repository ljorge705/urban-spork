package org.jmrtd.protocol;

import java.io.Serializable;
import org.jmrtd.AccessKeySpec;

/* loaded from: classes4.dex */
public class BACResult implements Serializable {
    private static final long serialVersionUID = -7114911372181772099L;
    private AccessKeySpec bacKey;
    private SecureMessagingWrapper wrapper;

    public AccessKeySpec getBACKey() {
        return this.bacKey;
    }

    public SecureMessagingWrapper getWrapper() {
        return this.wrapper;
    }

    public BACResult(SecureMessagingWrapper secureMessagingWrapper) {
        this(null, secureMessagingWrapper);
    }

    public BACResult(AccessKeySpec accessKeySpec, SecureMessagingWrapper secureMessagingWrapper) {
        this.bacKey = accessKeySpec;
        this.wrapper = secureMessagingWrapper;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder("BACResult [bacKey: ");
        Object obj = this.bacKey;
        if (obj == null) {
            obj = "-";
        }
        return sb.append(sb2.append(obj).toString()).append(", wrapper: " + this.wrapper).append("]").toString();
    }

    public int hashCode() {
        AccessKeySpec accessKeySpec = this.bacKey;
        int iHashCode = (1303377669 + (accessKeySpec == null ? 0 : accessKeySpec.hashCode())) * 1234567891;
        SecureMessagingWrapper secureMessagingWrapper = this.wrapper;
        return iHashCode + (secureMessagingWrapper != null ? secureMessagingWrapper.hashCode() : 0);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BACResult bACResult = (BACResult) obj;
        AccessKeySpec accessKeySpec = this.bacKey;
        if (accessKeySpec == null) {
            if (bACResult.bacKey != null) {
                return false;
            }
        } else if (!accessKeySpec.equals(bACResult.bacKey)) {
            return false;
        }
        SecureMessagingWrapper secureMessagingWrapper = this.wrapper;
        if (secureMessagingWrapper == null) {
            if (bACResult.wrapper != null) {
                return false;
            }
        } else if (!secureMessagingWrapper.equals(bACResult.wrapper)) {
            return false;
        }
        return true;
    }
}
