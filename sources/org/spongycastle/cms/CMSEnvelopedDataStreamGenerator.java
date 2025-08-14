package org.spongycastle.cms;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import org.spongycastle.asn1.ASN1EncodableVector;
import org.spongycastle.asn1.ASN1Integer;
import org.spongycastle.asn1.ASN1ObjectIdentifier;
import org.spongycastle.asn1.ASN1Set;
import org.spongycastle.asn1.BERSequenceGenerator;
import org.spongycastle.asn1.BERSet;
import org.spongycastle.asn1.DERSet;
import org.spongycastle.asn1.DERTaggedObject;
import org.spongycastle.asn1.cms.CMSObjectIdentifiers;
import org.spongycastle.asn1.cms.EnvelopedData;
import org.spongycastle.operator.GenericKey;
import org.spongycastle.operator.OutputEncryptor;

/* loaded from: classes4.dex */
public class CMSEnvelopedDataStreamGenerator extends CMSEnvelopedGenerator {
    private boolean _berEncodeRecipientSet;
    private int _bufferSize;
    private ASN1Set _unprotectedAttributes = null;

    public void setBEREncodeRecipients(boolean z) {
        this._berEncodeRecipientSet = z;
    }

    public void setBufferSize(int i) {
        this._bufferSize = i;
    }

    private ASN1Integer getVersion() {
        if (this.originatorInfo != null || this._unprotectedAttributes != null) {
            return new ASN1Integer(2L);
        }
        return new ASN1Integer(0L);
    }

    private OutputStream doOpen(ASN1ObjectIdentifier aSN1ObjectIdentifier, OutputStream outputStream, OutputEncryptor outputEncryptor) throws IOException, CMSException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        GenericKey key = outputEncryptor.getKey();
        Iterator it = this.recipientInfoGenerators.iterator();
        while (it.hasNext()) {
            aSN1EncodableVector.add(((RecipientInfoGenerator) it.next()).generate(key));
        }
        return open(aSN1ObjectIdentifier, outputStream, aSN1EncodableVector, outputEncryptor);
    }

    protected OutputStream open(ASN1ObjectIdentifier aSN1ObjectIdentifier, OutputStream outputStream, ASN1EncodableVector aSN1EncodableVector, OutputEncryptor outputEncryptor) throws IOException {
        BERSequenceGenerator bERSequenceGenerator = new BERSequenceGenerator(outputStream);
        bERSequenceGenerator.addObject(CMSObjectIdentifiers.envelopedData);
        BERSequenceGenerator bERSequenceGenerator2 = new BERSequenceGenerator(bERSequenceGenerator.getRawOutputStream(), 0, true);
        bERSequenceGenerator2.addObject(getVersion());
        if (this.originatorInfo != null) {
            bERSequenceGenerator2.addObject(new DERTaggedObject(false, 0, this.originatorInfo));
        }
        if (this._berEncodeRecipientSet) {
            bERSequenceGenerator2.getRawOutputStream().write(new BERSet(aSN1EncodableVector).getEncoded());
        } else {
            bERSequenceGenerator2.getRawOutputStream().write(new DERSet(aSN1EncodableVector).getEncoded());
        }
        BERSequenceGenerator bERSequenceGenerator3 = new BERSequenceGenerator(bERSequenceGenerator2.getRawOutputStream());
        bERSequenceGenerator3.addObject(aSN1ObjectIdentifier);
        bERSequenceGenerator3.getRawOutputStream().write(outputEncryptor.getAlgorithmIdentifier().getEncoded());
        return new CmsEnvelopedDataOutputStream(outputEncryptor.getOutputStream(CMSUtils.createBEROctetOutputStream(bERSequenceGenerator3.getRawOutputStream(), 0, false, this._bufferSize)), bERSequenceGenerator, bERSequenceGenerator2, bERSequenceGenerator3);
    }

    protected OutputStream open(OutputStream outputStream, ASN1EncodableVector aSN1EncodableVector, OutputEncryptor outputEncryptor) throws IOException, CMSException {
        ASN1Set dERSet;
        try {
            BERSequenceGenerator bERSequenceGenerator = new BERSequenceGenerator(outputStream);
            bERSequenceGenerator.addObject(CMSObjectIdentifiers.envelopedData);
            BERSequenceGenerator bERSequenceGenerator2 = new BERSequenceGenerator(bERSequenceGenerator.getRawOutputStream(), 0, true);
            if (this._berEncodeRecipientSet) {
                dERSet = new BERSet(aSN1EncodableVector);
            } else {
                dERSet = new DERSet(aSN1EncodableVector);
            }
            bERSequenceGenerator2.addObject(new ASN1Integer(EnvelopedData.calculateVersion(this.originatorInfo, dERSet, this._unprotectedAttributes)));
            if (this.originatorInfo != null) {
                bERSequenceGenerator2.addObject(new DERTaggedObject(false, 0, this.originatorInfo));
            }
            bERSequenceGenerator2.getRawOutputStream().write(dERSet.getEncoded());
            BERSequenceGenerator bERSequenceGenerator3 = new BERSequenceGenerator(bERSequenceGenerator2.getRawOutputStream());
            bERSequenceGenerator3.addObject(CMSObjectIdentifiers.data);
            bERSequenceGenerator3.getRawOutputStream().write(outputEncryptor.getAlgorithmIdentifier().getEncoded());
            return new CmsEnvelopedDataOutputStream(outputEncryptor.getOutputStream(CMSUtils.createBEROctetOutputStream(bERSequenceGenerator3.getRawOutputStream(), 0, false, this._bufferSize)), bERSequenceGenerator, bERSequenceGenerator2, bERSequenceGenerator3);
        } catch (IOException e) {
            throw new CMSException("exception decoding algorithm parameters.", e);
        }
    }

    public OutputStream open(OutputStream outputStream, OutputEncryptor outputEncryptor) throws IOException, CMSException {
        return doOpen(new ASN1ObjectIdentifier(CMSObjectIdentifiers.data.getId()), outputStream, outputEncryptor);
    }

    public OutputStream open(ASN1ObjectIdentifier aSN1ObjectIdentifier, OutputStream outputStream, OutputEncryptor outputEncryptor) throws IOException, CMSException {
        return doOpen(aSN1ObjectIdentifier, outputStream, outputEncryptor);
    }

    private class CmsEnvelopedDataOutputStream extends OutputStream {
        private BERSequenceGenerator _cGen;
        private BERSequenceGenerator _eiGen;
        private BERSequenceGenerator _envGen;
        private OutputStream _out;

        public CmsEnvelopedDataOutputStream(OutputStream outputStream, BERSequenceGenerator bERSequenceGenerator, BERSequenceGenerator bERSequenceGenerator2, BERSequenceGenerator bERSequenceGenerator3) {
            this._out = outputStream;
            this._cGen = bERSequenceGenerator;
            this._envGen = bERSequenceGenerator2;
            this._eiGen = bERSequenceGenerator3;
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            this._out.write(i);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) throws IOException {
            this._out.write(bArr, i, i2);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr) throws IOException {
            this._out.write(bArr);
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this._out.close();
            this._eiGen.close();
            if (CMSEnvelopedDataStreamGenerator.this.unprotectedAttributeGenerator != null) {
                this._envGen.addObject(new DERTaggedObject(false, 1, new BERSet(CMSEnvelopedDataStreamGenerator.this.unprotectedAttributeGenerator.getAttributes(new HashMap()).toASN1EncodableVector())));
            }
            this._envGen.close();
            this._cGen.close();
        }
    }
}
