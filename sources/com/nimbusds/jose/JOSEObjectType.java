package com.nimbusds.jose;

import com.nimbusds.jose.util.JSONStringUtils;
import java.io.Serializable;
import net.jcip.annotations.Immutable;

@Immutable
/* loaded from: classes2.dex */
public final class JOSEObjectType implements Serializable {
    public static final JOSEObjectType JOSE = new JOSEObjectType("JOSE");
    public static final JOSEObjectType JOSE_JSON = new JOSEObjectType("JOSE+JSON");
    public static final JOSEObjectType JWT = new JOSEObjectType("JWT");
    private static final long serialVersionUID = 1;
    private final String type;

    public String getType() {
        return this.type;
    }

    public String toString() {
        return this.type;
    }

    public JOSEObjectType(String str) {
        if (str == null) {
            throw new IllegalArgumentException("The object type must not be null");
        }
        this.type = str;
    }

    public int hashCode() {
        return this.type.toLowerCase().hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof JOSEObjectType) && this.type.equalsIgnoreCase(((JOSEObjectType) obj).type);
    }

    public String toJSONString() {
        return JSONStringUtils.toJSONString(this.type);
    }
}
