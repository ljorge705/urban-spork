package com.onfido.reactnative.sdk;

import javax.annotation.Nullable;

/* loaded from: classes6.dex */
class Response {
    public Document document;
    public Face face;
    public ProofOfAddress proofOfAddress;

    public class Identifiable {
        public String id;

        public Identifiable(String str) {
            this.id = str;
        }
    }

    public class Document {
        public Identifiable back;

        @Nullable
        public Identifiable countrySelected;
        public Identifiable front;
        public Identifiable nfcMediaId;
        public Identifiable typeSelected;

        public Document() {
        }
    }

    public class Face extends Identifiable {
        public String variant;

        public Face(String str, String str2) {
            super(str);
            this.variant = str2;
        }
    }

    public static class ProofOfAddress {

        @Nullable
        public ProofOfAddressSide back;
        public ProofOfAddressSide front;
        public String type;

        public ProofOfAddress(String str, ProofOfAddressSide proofOfAddressSide, @Nullable ProofOfAddressSide proofOfAddressSide2) {
            this.type = str;
            this.front = proofOfAddressSide;
            this.back = proofOfAddressSide2;
        }

        public static class ProofOfAddressSide {
            public String id;

            @Nullable
            public String type;

            public ProofOfAddressSide(String str, @Nullable String str2) {
                this.id = str;
                this.type = str2;
            }
        }
    }

    public Response(String str, String str2, String str3, String str4, String str5, ProofOfAddress proofOfAddress) {
        initDocument(str, str2, str5);
        initFace(str3, str4);
        this.proofOfAddress = proofOfAddress;
    }

    private void initDocument(String str, String str2, String str3) {
        if (str == null && str2 == null && str3 == null) {
            return;
        }
        Document document = new Document();
        this.document = document;
        if (str != null) {
            document.front = new Identifiable(str);
        }
        if (str2 != null) {
            this.document.back = new Identifiable(str2);
        }
        if (str3 != null) {
            this.document.nfcMediaId = new Identifiable(str3);
        }
    }

    private void initFace(String str, String str2) {
        if (str == null && str2 == null) {
            return;
        }
        this.face = new Face(str, str2);
    }
}
