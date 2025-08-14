package org.spongycastle.asn1;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes4.dex */
public class ASN1StreamParser {
    private final InputStream _in;
    private final int _limit;
    private final byte[][] tmpBuffers;

    public ASN1StreamParser(InputStream inputStream) {
        this(inputStream, StreamUtil.findLimit(inputStream));
    }

    public ASN1StreamParser(InputStream inputStream, int i) {
        this._in = inputStream;
        this._limit = i;
        this.tmpBuffers = new byte[11][];
    }

    public ASN1StreamParser(byte[] bArr) {
        this(new ByteArrayInputStream(bArr), bArr.length);
    }

    ASN1Encodable readIndef(int i) throws IOException {
        if (i == 4) {
            return new BEROctetStringParser(this);
        }
        if (i == 8) {
            return new DERExternalParser(this);
        }
        if (i == 16) {
            return new BERSequenceParser(this);
        }
        if (i == 17) {
            return new BERSetParser(this);
        }
        throw new ASN1Exception("unknown BER object encountered: 0x" + Integer.toHexString(i));
    }

    ASN1Encodable readImplicit(boolean z, int i) throws IOException {
        if (this._in instanceof IndefiniteLengthInputStream) {
            if (!z) {
                throw new IOException("indefinite-length primitive encoding encountered");
            }
            return readIndef(i);
        }
        if (z) {
            if (i == 4) {
                return new BEROctetStringParser(this);
            }
            if (i == 16) {
                return new DERSequenceParser(this);
            }
            if (i == 17) {
                return new DERSetParser(this);
            }
        } else {
            if (i == 4) {
                return new DEROctetStringParser((DefiniteLengthInputStream) this._in);
            }
            if (i == 16) {
                throw new ASN1Exception("sets must use constructed encoding (see X.690 8.11.1/8.12.1)");
            }
            if (i == 17) {
                throw new ASN1Exception("sequences must use constructed encoding (see X.690 8.9.1/8.10.1)");
            }
        }
        throw new ASN1Exception("implicit tagging not implemented");
    }

    ASN1Primitive readTaggedObject(boolean z, int i) throws IOException {
        if (!z) {
            return new DERTaggedObject(false, i, new DEROctetString(((DefiniteLengthInputStream) this._in).toByteArray()));
        }
        ASN1EncodableVector vector = readVector();
        if (this._in instanceof IndefiniteLengthInputStream) {
            if (vector.size() == 1) {
                return new BERTaggedObject(true, i, vector.get(0));
            }
            return new BERTaggedObject(false, i, BERFactory.createSequence(vector));
        }
        if (vector.size() == 1) {
            return new DERTaggedObject(true, i, vector.get(0));
        }
        return new DERTaggedObject(false, i, DERFactory.createSequence(vector));
    }

    public ASN1Encodable readObject() throws IOException {
        int i = this._in.read();
        if (i == -1) {
            return null;
        }
        set00Check(false);
        int tagNumber = ASN1InputStream.readTagNumber(this._in, i);
        boolean z = (i & 32) != 0;
        int length = ASN1InputStream.readLength(this._in, this._limit);
        if (length < 0) {
            if (!z) {
                throw new IOException("indefinite-length primitive encoding encountered");
            }
            ASN1StreamParser aSN1StreamParser = new ASN1StreamParser(new IndefiniteLengthInputStream(this._in, this._limit), this._limit);
            if ((i & 64) != 0) {
                return new BERApplicationSpecificParser(tagNumber, aSN1StreamParser);
            }
            if ((i & 128) != 0) {
                return new BERTaggedObjectParser(true, tagNumber, aSN1StreamParser);
            }
            return aSN1StreamParser.readIndef(tagNumber);
        }
        DefiniteLengthInputStream definiteLengthInputStream = new DefiniteLengthInputStream(this._in, length);
        if ((i & 64) != 0) {
            return new DERApplicationSpecific(z, tagNumber, definiteLengthInputStream.toByteArray());
        }
        if ((i & 128) != 0) {
            return new BERTaggedObjectParser(z, tagNumber, new ASN1StreamParser(definiteLengthInputStream));
        }
        if (!z) {
            if (tagNumber == 4) {
                return new DEROctetStringParser(definiteLengthInputStream);
            }
            try {
                return ASN1InputStream.createPrimitiveDERObject(tagNumber, definiteLengthInputStream, this.tmpBuffers);
            } catch (IllegalArgumentException e) {
                throw new ASN1Exception("corrupted stream detected", e);
            }
        }
        if (tagNumber == 4) {
            return new BEROctetStringParser(new ASN1StreamParser(definiteLengthInputStream));
        }
        if (tagNumber == 8) {
            return new DERExternalParser(new ASN1StreamParser(definiteLengthInputStream));
        }
        if (tagNumber == 16) {
            return new DERSequenceParser(new ASN1StreamParser(definiteLengthInputStream));
        }
        if (tagNumber == 17) {
            return new DERSetParser(new ASN1StreamParser(definiteLengthInputStream));
        }
        throw new IOException("unknown tag " + tagNumber + " encountered");
    }

    private void set00Check(boolean z) {
        InputStream inputStream = this._in;
        if (inputStream instanceof IndefiniteLengthInputStream) {
            ((IndefiniteLengthInputStream) inputStream).setEofOn00(z);
        }
    }

    ASN1EncodableVector readVector() throws IOException {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        while (true) {
            ASN1Encodable object = readObject();
            if (object == null) {
                return aSN1EncodableVector;
            }
            if (object instanceof InMemoryRepresentable) {
                aSN1EncodableVector.add(((InMemoryRepresentable) object).getLoadedObject());
            } else {
                aSN1EncodableVector.add(object.toASN1Primitive());
            }
        }
    }
}
