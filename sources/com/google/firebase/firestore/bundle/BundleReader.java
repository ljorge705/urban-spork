package com.google.firebase.firestore.bundle;

import com.google.firebase.firestore.util.Logger;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class BundleReader {
    protected static final int BUFFER_CAPACITY = 1024;
    private static final Charset UTF8_CHARSET = Charset.forName("UTF-8");
    private ByteBuffer buffer;
    private final InputStream bundleInputStream;
    long bytesRead;
    private final InputStreamReader dataReader;
    BundleMetadata metadata;
    private final BundleSerializer serializer;

    public long getBytesRead() {
        return this.bytesRead;
    }

    public BundleReader(BundleSerializer bundleSerializer, InputStream inputStream) {
        this.serializer = bundleSerializer;
        this.bundleInputStream = inputStream;
        this.dataReader = new InputStreamReader(inputStream);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(1024);
        this.buffer = byteBufferAllocate;
        byteBufferAllocate.flip();
    }

    public BundleMetadata getBundleMetadata() throws JSONException, IOException, NumberFormatException {
        BundleMetadata bundleMetadata = this.metadata;
        if (bundleMetadata != null) {
            return bundleMetadata;
        }
        BundleElement nextElement = readNextElement();
        if (!(nextElement instanceof BundleMetadata)) {
            throw abort("Expected first element in bundle to be a metadata object");
        }
        BundleMetadata bundleMetadata2 = (BundleMetadata) nextElement;
        this.metadata = bundleMetadata2;
        this.bytesRead = 0L;
        return bundleMetadata2;
    }

    public BundleElement getNextElement() throws JSONException, IOException, NumberFormatException {
        getBundleMetadata();
        return readNextElement();
    }

    public void close() throws IOException {
        this.bundleInputStream.close();
    }

    private BundleElement readNextElement() throws JSONException, IOException, NumberFormatException {
        String lengthPrefix = readLengthPrefix();
        if (lengthPrefix == null) {
            return null;
        }
        String jsonString = readJsonString(Integer.parseInt(lengthPrefix));
        this.bytesRead += lengthPrefix.getBytes(UTF8_CHARSET).length + r1;
        return decodeBundleElement(jsonString);
    }

    private String readLengthPrefix() throws IOException {
        int iIndexOfOpenBracket;
        do {
            iIndexOfOpenBracket = indexOfOpenBracket();
            if (iIndexOfOpenBracket != -1) {
                break;
            }
        } while (pullMoreData());
        if (this.buffer.remaining() == 0) {
            return null;
        }
        if (iIndexOfOpenBracket == -1) {
            throw abort("Reached the end of bundle when a length string is expected.");
        }
        byte[] bArr = new byte[iIndexOfOpenBracket];
        this.buffer.get(bArr);
        return UTF8_CHARSET.decode(ByteBuffer.wrap(bArr)).toString();
    }

    private int indexOfOpenBracket() {
        this.buffer.mark();
        for (int i = 0; i < this.buffer.remaining(); i++) {
            try {
                if (this.buffer.get() == 123) {
                    return i;
                }
            } finally {
                this.buffer.reset();
            }
        }
        this.buffer.reset();
        return -1;
    }

    private String readJsonString(int i) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (i > 0) {
            if (this.buffer.remaining() == 0 && !pullMoreData()) {
                throw abort("Reached the end of bundle when more data was expected.");
            }
            int iMin = Math.min(i, this.buffer.remaining());
            byteArrayOutputStream.write(this.buffer.array(), this.buffer.arrayOffset() + this.buffer.position(), iMin);
            ByteBuffer byteBuffer = this.buffer;
            byteBuffer.position(byteBuffer.position() + iMin);
            i -= iMin;
        }
        return byteArrayOutputStream.toString(UTF8_CHARSET.name());
    }

    private boolean pullMoreData() throws IOException {
        this.buffer.compact();
        int i = this.bundleInputStream.read(this.buffer.array(), this.buffer.arrayOffset() + this.buffer.position(), this.buffer.remaining());
        boolean z = i > 0;
        if (z) {
            ByteBuffer byteBuffer = this.buffer;
            byteBuffer.position(byteBuffer.position() + i);
        }
        this.buffer.flip();
        return z;
    }

    private BundleElement decodeBundleElement(String str) throws JSONException, IOException {
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.has("metadata")) {
            BundleMetadata bundleMetadataDecodeBundleMetadata = this.serializer.decodeBundleMetadata(jSONObject.getJSONObject("metadata"));
            Logger.debug("BundleElement", "BundleMetadata element loaded", new Object[0]);
            return bundleMetadataDecodeBundleMetadata;
        }
        if (jSONObject.has("namedQuery")) {
            NamedQuery namedQueryDecodeNamedQuery = this.serializer.decodeNamedQuery(jSONObject.getJSONObject("namedQuery"));
            Logger.debug("BundleElement", "Query loaded: " + namedQueryDecodeNamedQuery.getName(), new Object[0]);
            return namedQueryDecodeNamedQuery;
        }
        if (jSONObject.has("documentMetadata")) {
            BundledDocumentMetadata bundledDocumentMetadataDecodeBundledDocumentMetadata = this.serializer.decodeBundledDocumentMetadata(jSONObject.getJSONObject("documentMetadata"));
            Logger.debug("BundleElement", "Document metadata loaded: " + bundledDocumentMetadataDecodeBundledDocumentMetadata.getKey(), new Object[0]);
            return bundledDocumentMetadataDecodeBundledDocumentMetadata;
        }
        if (jSONObject.has("document")) {
            BundleDocument bundleDocumentDecodeDocument = this.serializer.decodeDocument(jSONObject.getJSONObject("document"));
            Logger.debug("BundleElement", "Document loaded: " + bundleDocumentDecodeDocument.getKey(), new Object[0]);
            return bundleDocumentDecodeDocument;
        }
        throw abort("Cannot decode unknown Bundle element: " + str);
    }

    private IllegalArgumentException abort(String str) throws IOException {
        close();
        throw new IllegalArgumentException("Invalid bundle: " + str);
    }
}
