package com.nimbusds.jose.proc;

import com.nimbusds.jose.Algorithm;
import com.nimbusds.jose.EncryptionMethod;
import com.nimbusds.jose.JOSEObject;
import com.nimbusds.jose.JWEObject;
import com.nimbusds.jose.JWSObject;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes2.dex */
public class JOSEMatcher {
    private final Set<Algorithm> algs;
    private final Set<Class<? extends JOSEObject>> classes;
    private final Set<EncryptionMethod> encs;
    private final Set<URI> jkus;
    private final Set<String> kids;

    public Set<Algorithm> getAlgorithms() {
        return this.algs;
    }

    public Set<EncryptionMethod> getEncryptionMethods() {
        return this.encs;
    }

    public Set<Class<? extends JOSEObject>> getJOSEClasses() {
        return this.classes;
    }

    public Set<URI> getJWKURLs() {
        return this.jkus;
    }

    public Set<String> getKeyIDs() {
        return this.kids;
    }

    public static class Builder {
        private Set<Algorithm> algs;
        private Set<Class<? extends JOSEObject>> classes;
        private Set<EncryptionMethod> encs;
        private Set<URI> jkus;
        private Set<String> kids;

        public Builder algorithms(Set<Algorithm> set) {
            this.algs = set;
            return this;
        }

        public Builder encryptionMethods(Set<EncryptionMethod> set) {
            this.encs = set;
            return this;
        }

        public Builder joseClasses(Set<Class<? extends JOSEObject>> set) {
            this.classes = set;
            return this;
        }

        public Builder jwkURLs(Set<URI> set) {
            this.jkus = set;
            return this;
        }

        public Builder keyIDs(Set<String> set) {
            this.kids = set;
            return this;
        }

        public Builder joseClass(Class<? extends JOSEObject> cls) {
            if (cls == null) {
                this.classes = null;
            } else {
                this.classes = new HashSet(Collections.singletonList(cls));
            }
            return this;
        }

        public Builder joseClasses(Class<? extends JOSEObject>... clsArr) {
            joseClasses(new HashSet(Arrays.asList(clsArr)));
            return this;
        }

        public Builder algorithm(Algorithm algorithm) {
            if (algorithm == null) {
                this.algs = null;
            } else {
                this.algs = new HashSet(Collections.singletonList(algorithm));
            }
            return this;
        }

        public Builder algorithms(Algorithm... algorithmArr) {
            algorithms(new HashSet(Arrays.asList(algorithmArr)));
            return this;
        }

        public Builder encryptionMethod(EncryptionMethod encryptionMethod) {
            if (encryptionMethod == null) {
                this.encs = null;
            } else {
                this.encs = new HashSet(Collections.singletonList(encryptionMethod));
            }
            return this;
        }

        public Builder encryptionMethods(EncryptionMethod... encryptionMethodArr) {
            encryptionMethods(new HashSet(Arrays.asList(encryptionMethodArr)));
            return this;
        }

        public Builder jwkURL(URI uri) {
            if (uri == null) {
                this.jkus = null;
            } else {
                this.jkus = new HashSet(Collections.singletonList(uri));
            }
            return this;
        }

        public Builder jwkURLs(URI... uriArr) {
            jwkURLs(new HashSet(Arrays.asList(uriArr)));
            return this;
        }

        public Builder keyID(String str) {
            if (str == null) {
                this.kids = null;
            } else {
                this.kids = new HashSet(Collections.singletonList(str));
            }
            return this;
        }

        public Builder keyIDs(String... strArr) {
            keyIDs(new HashSet(Arrays.asList(strArr)));
            return this;
        }

        public JOSEMatcher build() {
            return new JOSEMatcher(this.classes, this.algs, this.encs, this.jkus, this.kids);
        }
    }

    public JOSEMatcher(Set<Class<? extends JOSEObject>> set, Set<Algorithm> set2, Set<EncryptionMethod> set3, Set<URI> set4, Set<String> set5) {
        this.classes = set;
        this.algs = set2;
        this.encs = set3;
        this.jkus = set4;
        this.kids = set5;
    }

    public boolean matches(JOSEObject jOSEObject) {
        URI jwkurl;
        Set<Class<? extends JOSEObject>> set = this.classes;
        if (set != null) {
            for (Class<? extends JOSEObject> cls : set) {
                if (cls == null || !cls.isInstance(jOSEObject)) {
                }
            }
            return false;
        }
        Set<Algorithm> set2 = this.algs;
        if (set2 != null && !set2.contains(jOSEObject.getHeader().getAlgorithm())) {
            return false;
        }
        Set<EncryptionMethod> set3 = this.encs;
        if (set3 != null && (!(jOSEObject instanceof JWEObject) || !set3.contains(((JWEObject) jOSEObject).getHeader().getEncryptionMethod()))) {
            return false;
        }
        String keyID = null;
        if (this.jkus != null) {
            if (jOSEObject instanceof JWSObject) {
                jwkurl = ((JWSObject) jOSEObject).getHeader().getJWKURL();
            } else {
                jwkurl = jOSEObject instanceof JWEObject ? ((JWEObject) jOSEObject).getHeader().getJWKURL() : null;
            }
            if (!this.jkus.contains(jwkurl)) {
                return false;
            }
        }
        if (this.kids == null) {
            return true;
        }
        if (jOSEObject instanceof JWSObject) {
            keyID = ((JWSObject) jOSEObject).getHeader().getKeyID();
        } else if (jOSEObject instanceof JWEObject) {
            keyID = ((JWEObject) jOSEObject).getHeader().getKeyID();
        }
        return this.kids.contains(keyID);
    }
}
