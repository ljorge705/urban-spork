package org.spongycastle.tsp.cms;

import java.io.IOException;
import java.io.OutputStream;
import org.spongycastle.asn1.cms.AttributeTable;
import org.spongycastle.asn1.cms.TimeStampAndCRL;
import org.spongycastle.asn1.cms.TimeStampedData;
import org.spongycastle.asn1.cms.TimeStampedDataParser;
import org.spongycastle.asn1.x509.AlgorithmIdentifier;
import org.spongycastle.cms.CMSException;
import org.spongycastle.operator.DigestCalculator;
import org.spongycastle.operator.DigestCalculatorProvider;
import org.spongycastle.operator.OperatorCreationException;
import org.spongycastle.tsp.TSPException;
import org.spongycastle.tsp.TimeStampToken;
import org.spongycastle.util.Arrays;

/* loaded from: classes7.dex */
class TimeStampDataUtil {
    private final MetaDataUtil metaDataUtil;
    private final TimeStampAndCRL[] timeStamps;

    TimeStampAndCRL[] getTimeStamps() {
        return this.timeStamps;
    }

    TimeStampDataUtil(TimeStampedData timeStampedData) {
        this.metaDataUtil = new MetaDataUtil(timeStampedData.getMetaData());
        this.timeStamps = timeStampedData.getTemporalEvidence().getTstEvidence().toTimeStampAndCRLArray();
    }

    TimeStampDataUtil(TimeStampedDataParser timeStampedDataParser) throws IOException {
        this.metaDataUtil = new MetaDataUtil(timeStampedDataParser.getMetaData());
        this.timeStamps = timeStampedDataParser.getTemporalEvidence().getTstEvidence().toTimeStampAndCRLArray();
    }

    TimeStampToken getTimeStampToken(TimeStampAndCRL timeStampAndCRL) throws CMSException {
        try {
            return new TimeStampToken(timeStampAndCRL.getTimeStampToken());
        } catch (IOException e) {
            throw new CMSException("unable to parse token data: " + e.getMessage(), e);
        } catch (IllegalArgumentException e2) {
            throw new CMSException("token data invalid: " + e2.getMessage(), e2);
        } catch (TSPException e3) {
            if (e3.getCause() instanceof CMSException) {
                throw ((CMSException) e3.getCause());
            }
            throw new CMSException("token data invalid: " + e3.getMessage(), e3);
        }
    }

    void initialiseMessageImprintDigestCalculator(DigestCalculator digestCalculator) throws IOException, CMSException {
        this.metaDataUtil.initialiseMessageImprintDigestCalculator(digestCalculator);
    }

    DigestCalculator getMessageImprintDigestCalculator(DigestCalculatorProvider digestCalculatorProvider) throws OperatorCreationException, IOException {
        try {
            DigestCalculator digestCalculator = digestCalculatorProvider.get(new AlgorithmIdentifier(getTimeStampToken(this.timeStamps[0]).getTimeStampInfo().getMessageImprintAlgOID()));
            initialiseMessageImprintDigestCalculator(digestCalculator);
            return digestCalculator;
        } catch (CMSException e) {
            throw new OperatorCreationException("unable to extract algorithm ID: " + e.getMessage(), e);
        }
    }

    TimeStampToken[] getTimeStampTokens() throws CMSException {
        TimeStampToken[] timeStampTokenArr = new TimeStampToken[this.timeStamps.length];
        int i = 0;
        while (true) {
            TimeStampAndCRL[] timeStampAndCRLArr = this.timeStamps;
            if (i >= timeStampAndCRLArr.length) {
                return timeStampTokenArr;
            }
            timeStampTokenArr[i] = getTimeStampToken(timeStampAndCRLArr[i]);
            i++;
        }
    }

    byte[] calculateNextHash(DigestCalculator digestCalculator) throws IOException, CMSException {
        TimeStampAndCRL timeStampAndCRL = this.timeStamps[r0.length - 1];
        OutputStream outputStream = digestCalculator.getOutputStream();
        try {
            outputStream.write(timeStampAndCRL.getEncoded("DER"));
            outputStream.close();
            return digestCalculator.getDigest();
        } catch (IOException e) {
            throw new CMSException("exception calculating hash: " + e.getMessage(), e);
        }
    }

    void validate(DigestCalculatorProvider digestCalculatorProvider, byte[] bArr) throws ImprintDigestInvalidException, IOException, CMSException {
        int i = 0;
        while (true) {
            TimeStampAndCRL[] timeStampAndCRLArr = this.timeStamps;
            if (i >= timeStampAndCRLArr.length) {
                return;
            }
            try {
                TimeStampToken timeStampToken = getTimeStampToken(timeStampAndCRLArr[i]);
                if (i > 0) {
                    DigestCalculator digestCalculator = digestCalculatorProvider.get(timeStampToken.getTimeStampInfo().getHashAlgorithm());
                    digestCalculator.getOutputStream().write(this.timeStamps[i - 1].getEncoded("DER"));
                    bArr = digestCalculator.getDigest();
                }
                compareDigest(timeStampToken, bArr);
                i++;
            } catch (IOException e) {
                throw new CMSException("exception calculating hash: " + e.getMessage(), e);
            } catch (OperatorCreationException e2) {
                throw new CMSException("cannot create digest: " + e2.getMessage(), e2);
            }
        }
    }

    void validate(DigestCalculatorProvider digestCalculatorProvider, byte[] bArr, TimeStampToken timeStampToken) throws ImprintDigestInvalidException, IOException, CMSException {
        try {
            byte[] encoded = timeStampToken.getEncoded();
            int i = 0;
            while (true) {
                TimeStampAndCRL[] timeStampAndCRLArr = this.timeStamps;
                if (i < timeStampAndCRLArr.length) {
                    try {
                        TimeStampToken timeStampToken2 = getTimeStampToken(timeStampAndCRLArr[i]);
                        if (i > 0) {
                            DigestCalculator digestCalculator = digestCalculatorProvider.get(timeStampToken2.getTimeStampInfo().getHashAlgorithm());
                            digestCalculator.getOutputStream().write(this.timeStamps[i - 1].getEncoded("DER"));
                            bArr = digestCalculator.getDigest();
                        }
                        compareDigest(timeStampToken2, bArr);
                        if (Arrays.areEqual(timeStampToken2.getEncoded(), encoded)) {
                            return;
                        } else {
                            i++;
                        }
                    } catch (IOException e) {
                        throw new CMSException("exception calculating hash: " + e.getMessage(), e);
                    } catch (OperatorCreationException e2) {
                        throw new CMSException("cannot create digest: " + e2.getMessage(), e2);
                    }
                } else {
                    throw new ImprintDigestInvalidException("passed in token not associated with timestamps present", timeStampToken);
                }
            }
        } catch (IOException e3) {
            throw new CMSException("exception encoding timeStampToken: " + e3.getMessage(), e3);
        }
    }

    private void compareDigest(TimeStampToken timeStampToken, byte[] bArr) throws ImprintDigestInvalidException {
        if (!Arrays.areEqual(bArr, timeStampToken.getTimeStampInfo().getMessageImprintDigest())) {
            throw new ImprintDigestInvalidException("hash calculated is different from MessageImprintDigest found in TimeStampToken", timeStampToken);
        }
    }

    String getFileName() {
        return this.metaDataUtil.getFileName();
    }

    String getMediaType() {
        return this.metaDataUtil.getMediaType();
    }

    AttributeTable getOtherMetaData() {
        return new AttributeTable(this.metaDataUtil.getOtherMetaData());
    }
}
