package com.nimbusds.jose;

import com.nimbusds.jose.util.JSONStringUtils;
import java.io.Serializable;
import net.jcip.annotations.Immutable;

@Immutable
/* loaded from: classes2.dex */
public class Algorithm implements Serializable {
    public static final Algorithm NONE = new Algorithm("none", Requirement.REQUIRED);
    private static final long serialVersionUID = 1;
    private final String name;
    private final Requirement requirement;

    public final String getName() {
        return this.name;
    }

    public final Requirement getRequirement() {
        return this.requirement;
    }

    public final String toString() {
        return this.name;
    }

    public Algorithm(String str, Requirement requirement) {
        if (str == null) {
            throw new IllegalArgumentException("The algorithm name must not be null");
        }
        this.name = str;
        this.requirement = requirement;
    }

    public Algorithm(String str) {
        this(str, null);
    }

    public final int hashCode() {
        return this.name.hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof Algorithm) && toString().equals(obj.toString());
    }

    public final String toJSONString() {
        return JSONStringUtils.toJSONString(this.name);
    }

    public static Algorithm parse(String str) {
        if (str == null) {
            return null;
        }
        return new Algorithm(str);
    }
}
