package com.google.rpc;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Parser;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes2.dex */
public final class ResourceInfo extends GeneratedMessageLite<ResourceInfo, Builder> implements ResourceInfoOrBuilder {
    private static final ResourceInfo DEFAULT_INSTANCE;
    public static final int DESCRIPTION_FIELD_NUMBER = 4;
    public static final int OWNER_FIELD_NUMBER = 3;
    private static volatile Parser<ResourceInfo> PARSER = null;
    public static final int RESOURCE_NAME_FIELD_NUMBER = 2;
    public static final int RESOURCE_TYPE_FIELD_NUMBER = 1;
    private String resourceType_ = "";
    private String resourceName_ = "";
    private String owner_ = "";
    private String description_ = "";

    public static ResourceInfo getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public String getDescription() {
        return this.description_;
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public String getOwner() {
        return this.owner_;
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public String getResourceName() {
        return this.resourceName_;
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public String getResourceType() {
        return this.resourceType_;
    }

    private ResourceInfo() {
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public ByteString getResourceTypeBytes() {
        return ByteString.copyFromUtf8(this.resourceType_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResourceType(String str) {
        str.getClass();
        this.resourceType_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResourceType() {
        this.resourceType_ = getDefaultInstance().getResourceType();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResourceTypeBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.resourceType_ = byteString.toStringUtf8();
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public ByteString getResourceNameBytes() {
        return ByteString.copyFromUtf8(this.resourceName_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResourceName(String str) {
        str.getClass();
        this.resourceName_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearResourceName() {
        this.resourceName_ = getDefaultInstance().getResourceName();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setResourceNameBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.resourceName_ = byteString.toStringUtf8();
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public ByteString getOwnerBytes() {
        return ByteString.copyFromUtf8(this.owner_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOwner(String str) {
        str.getClass();
        this.owner_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearOwner() {
        this.owner_ = getDefaultInstance().getOwner();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setOwnerBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.owner_ = byteString.toStringUtf8();
    }

    @Override // com.google.rpc.ResourceInfoOrBuilder
    public ByteString getDescriptionBytes() {
        return ByteString.copyFromUtf8(this.description_);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDescription(String str) {
        str.getClass();
        this.description_ = str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearDescription() {
        this.description_ = getDefaultInstance().getDescription();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDescriptionBytes(ByteString byteString) {
        checkByteStringIsUtf8(byteString);
        this.description_ = byteString.toStringUtf8();
    }

    public static ResourceInfo parseFrom(ByteBuffer byteBuffer) throws InvalidProtocolBufferException {
        return (ResourceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer);
    }

    public static ResourceInfo parseFrom(ByteBuffer byteBuffer, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ResourceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteBuffer, extensionRegistryLite);
    }

    public static ResourceInfo parseFrom(ByteString byteString) throws InvalidProtocolBufferException {
        return (ResourceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ResourceInfo parseFrom(ByteString byteString, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ResourceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, byteString, extensionRegistryLite);
    }

    public static ResourceInfo parseFrom(byte[] bArr) throws InvalidProtocolBufferException {
        return (ResourceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr);
    }

    public static ResourceInfo parseFrom(byte[] bArr, ExtensionRegistryLite extensionRegistryLite) throws InvalidProtocolBufferException {
        return (ResourceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, bArr, extensionRegistryLite);
    }

    public static ResourceInfo parseFrom(InputStream inputStream) throws IOException {
        return (ResourceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ResourceInfo parseFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ResourceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ResourceInfo parseDelimitedFrom(InputStream inputStream) throws IOException {
        return (ResourceInfo) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream);
    }

    public static ResourceInfo parseDelimitedFrom(InputStream inputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ResourceInfo) parseDelimitedFrom(DEFAULT_INSTANCE, inputStream, extensionRegistryLite);
    }

    public static ResourceInfo parseFrom(CodedInputStream codedInputStream) throws IOException {
        return (ResourceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream);
    }

    public static ResourceInfo parseFrom(CodedInputStream codedInputStream, ExtensionRegistryLite extensionRegistryLite) throws IOException {
        return (ResourceInfo) GeneratedMessageLite.parseFrom(DEFAULT_INSTANCE, codedInputStream, extensionRegistryLite);
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.createBuilder();
    }

    public static Builder newBuilder(ResourceInfo resourceInfo) {
        return DEFAULT_INSTANCE.createBuilder(resourceInfo);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ResourceInfo, Builder> implements ResourceInfoOrBuilder {
        /* synthetic */ Builder(AnonymousClass1 anonymousClass1) {
            this();
        }

        private Builder() {
            super(ResourceInfo.DEFAULT_INSTANCE);
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public String getResourceType() {
            return ((ResourceInfo) this.instance).getResourceType();
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public ByteString getResourceTypeBytes() {
            return ((ResourceInfo) this.instance).getResourceTypeBytes();
        }

        public Builder setResourceType(String str) {
            copyOnWrite();
            ((ResourceInfo) this.instance).setResourceType(str);
            return this;
        }

        public Builder clearResourceType() {
            copyOnWrite();
            ((ResourceInfo) this.instance).clearResourceType();
            return this;
        }

        public Builder setResourceTypeBytes(ByteString byteString) {
            copyOnWrite();
            ((ResourceInfo) this.instance).setResourceTypeBytes(byteString);
            return this;
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public String getResourceName() {
            return ((ResourceInfo) this.instance).getResourceName();
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public ByteString getResourceNameBytes() {
            return ((ResourceInfo) this.instance).getResourceNameBytes();
        }

        public Builder setResourceName(String str) {
            copyOnWrite();
            ((ResourceInfo) this.instance).setResourceName(str);
            return this;
        }

        public Builder clearResourceName() {
            copyOnWrite();
            ((ResourceInfo) this.instance).clearResourceName();
            return this;
        }

        public Builder setResourceNameBytes(ByteString byteString) {
            copyOnWrite();
            ((ResourceInfo) this.instance).setResourceNameBytes(byteString);
            return this;
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public String getOwner() {
            return ((ResourceInfo) this.instance).getOwner();
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public ByteString getOwnerBytes() {
            return ((ResourceInfo) this.instance).getOwnerBytes();
        }

        public Builder setOwner(String str) {
            copyOnWrite();
            ((ResourceInfo) this.instance).setOwner(str);
            return this;
        }

        public Builder clearOwner() {
            copyOnWrite();
            ((ResourceInfo) this.instance).clearOwner();
            return this;
        }

        public Builder setOwnerBytes(ByteString byteString) {
            copyOnWrite();
            ((ResourceInfo) this.instance).setOwnerBytes(byteString);
            return this;
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public String getDescription() {
            return ((ResourceInfo) this.instance).getDescription();
        }

        @Override // com.google.rpc.ResourceInfoOrBuilder
        public ByteString getDescriptionBytes() {
            return ((ResourceInfo) this.instance).getDescriptionBytes();
        }

        public Builder setDescription(String str) {
            copyOnWrite();
            ((ResourceInfo) this.instance).setDescription(str);
            return this;
        }

        public Builder clearDescription() {
            copyOnWrite();
            ((ResourceInfo) this.instance).clearDescription();
            return this;
        }

        public Builder setDescriptionBytes(ByteString byteString) {
            copyOnWrite();
            ((ResourceInfo) this.instance).setDescriptionBytes(byteString);
            return this;
        }
    }

    /* renamed from: com.google.rpc.ResourceInfo$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke;

        static {
            int[] iArr = new int[GeneratedMessageLite.MethodToInvoke.values().length];
            $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke = iArr;
            try {
                iArr[GeneratedMessageLite.MethodToInvoke.NEW_MUTABLE_INSTANCE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.NEW_BUILDER.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.BUILD_MESSAGE_INFO.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_DEFAULT_INSTANCE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_PARSER.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.GET_MEMOIZED_IS_INITIALIZED.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[GeneratedMessageLite.MethodToInvoke.SET_MEMOIZED_IS_INITIALIZED.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    @Override // com.google.protobuf.GeneratedMessageLite
    protected final Object dynamicMethod(GeneratedMessageLite.MethodToInvoke methodToInvoke, Object obj, Object obj2) {
        AnonymousClass1 anonymousClass1 = null;
        switch (AnonymousClass1.$SwitchMap$com$google$protobuf$GeneratedMessageLite$MethodToInvoke[methodToInvoke.ordinal()]) {
            case 1:
                return new ResourceInfo();
            case 2:
                return new Builder(anonymousClass1);
            case 3:
                return newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004Ȉ", new Object[]{"resourceType_", "resourceName_", "owner_", "description_"});
            case 4:
                return DEFAULT_INSTANCE;
            case 5:
                Parser<ResourceInfo> defaultInstanceBasedParser = PARSER;
                if (defaultInstanceBasedParser == null) {
                    synchronized (ResourceInfo.class) {
                        defaultInstanceBasedParser = PARSER;
                        if (defaultInstanceBasedParser == null) {
                            defaultInstanceBasedParser = new GeneratedMessageLite.DefaultInstanceBasedParser<>(DEFAULT_INSTANCE);
                            PARSER = defaultInstanceBasedParser;
                        }
                    }
                }
                return defaultInstanceBasedParser;
            case 6:
                return (byte) 1;
            case 7:
                return null;
            default:
                throw new UnsupportedOperationException();
        }
    }

    static {
        ResourceInfo resourceInfo = new ResourceInfo();
        DEFAULT_INSTANCE = resourceInfo;
        GeneratedMessageLite.registerDefaultInstance(ResourceInfo.class, resourceInfo);
    }

    public static Parser<ResourceInfo> parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }
}
