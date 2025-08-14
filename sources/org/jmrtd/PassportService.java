package org.jmrtd;

import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;
import javax.crypto.SecretKey;
import net.sf.scuba.smartcards.APDUEvent;
import net.sf.scuba.smartcards.APDUListener;
import net.sf.scuba.smartcards.CardFileInputStream;
import net.sf.scuba.smartcards.CardService;
import net.sf.scuba.smartcards.CardServiceException;
import net.sf.scuba.smartcards.CommandAPDU;
import net.sf.scuba.smartcards.ISOFileInfo;
import net.sf.scuba.smartcards.ResponseAPDU;
import org.jmrtd.cert.CVCPrincipal;
import org.jmrtd.cert.CardVerifiableCertificate;
import org.jmrtd.protocol.AAAPDUSender;
import org.jmrtd.protocol.AAProtocol;
import org.jmrtd.protocol.AAResult;
import org.jmrtd.protocol.BACAPDUSender;
import org.jmrtd.protocol.BACProtocol;
import org.jmrtd.protocol.BACResult;
import org.jmrtd.protocol.EACCAAPDUSender;
import org.jmrtd.protocol.EACCAProtocol;
import org.jmrtd.protocol.EACCAResult;
import org.jmrtd.protocol.EACTAAPDUSender;
import org.jmrtd.protocol.EACTAProtocol;
import org.jmrtd.protocol.EACTAResult;
import org.jmrtd.protocol.PACEAPDUSender;
import org.jmrtd.protocol.PACEProtocol;
import org.jmrtd.protocol.PACEResult;
import org.jmrtd.protocol.ReadBinaryAPDUSender;
import org.jmrtd.protocol.SecureMessagingWrapper;

/* loaded from: classes4.dex */
public class PassportService extends AbstractMRTDCardService {
    public static final byte CAN_PACE_KEY_REFERENCE = 2;
    public static final int DEFAULT_MAX_BLOCKSIZE = 223;
    public static final short EF_CARD_ACCESS = 284;
    public static final short EF_CARD_SECURITY = 285;
    public static final short EF_COM = 286;
    public static final short EF_CVCA = 284;
    public static final short EF_DG1 = 257;
    public static final short EF_DG10 = 266;
    public static final short EF_DG11 = 267;
    public static final short EF_DG12 = 268;
    public static final short EF_DG13 = 269;
    public static final short EF_DG14 = 270;
    public static final short EF_DG15 = 271;
    public static final short EF_DG16 = 272;
    public static final short EF_DG2 = 258;
    public static final short EF_DG3 = 259;
    public static final short EF_DG4 = 260;
    public static final short EF_DG5 = 261;
    public static final short EF_DG6 = 262;
    public static final short EF_DG7 = 263;
    public static final short EF_DG8 = 264;
    public static final short EF_DG9 = 265;
    public static final short EF_SOD = 285;
    public static final int EXTENDED_MAX_TRANCEIVE_LENGTH = 65536;
    public static final byte MRZ_PACE_KEY_REFERENCE = 1;
    public static final int NORMAL_MAX_TRANCEIVE_LENGTH = 256;
    public static final byte NO_PACE_KEY_REFERENCE = 0;
    public static final byte PIN_PACE_KEY_REFERENCE = 3;
    public static final byte PUK_PACE_KEY_REFERENCE = 4;
    public static final byte SFI_CARD_ACCESS = 28;
    public static final byte SFI_CARD_SECURITY = 29;
    public static final byte SFI_COM = 30;
    public static final byte SFI_CVCA = 28;
    public static final byte SFI_DG1 = 1;
    public static final byte SFI_DG10 = 10;
    public static final byte SFI_DG11 = 11;
    public static final byte SFI_DG12 = 12;
    public static final byte SFI_DG13 = 13;
    public static final byte SFI_DG14 = 14;
    public static final byte SFI_DG15 = 15;
    public static final byte SFI_DG16 = 16;
    public static final byte SFI_DG2 = 2;
    public static final byte SFI_DG3 = 3;
    public static final byte SFI_DG4 = 4;
    public static final byte SFI_DG5 = 5;
    public static final byte SFI_DG6 = 6;
    public static final byte SFI_DG7 = 7;
    public static final byte SFI_DG8 = 8;
    public static final byte SFI_DG9 = 9;
    public static final byte SFI_SOD = 29;
    private AAAPDUSender aaSender;
    private DefaultFileSystem appletFileSystem;
    private BACAPDUSender bacSender;
    private EACCAAPDUSender eacCASender;
    private EACTAAPDUSender eacTASender;
    private boolean isAppletSelected;
    private boolean isOpen;
    private int maxBlockSize;
    private int maxTranceiveLengthForPACEProtocol;
    private int maxTranceiveLengthForSecureMessaging;
    private PACEAPDUSender paceSender;
    private ReadBinaryAPDUSender readBinarySender;
    private DefaultFileSystem rootFileSystem;
    private CardService service;
    private boolean shouldCheckMAC;
    private SecureMessagingWrapper wrapper;
    private static final Logger LOGGER = Logger.getLogger("org.jmrtd");
    protected static final byte[] APPLET_AID = {ISOFileInfo.A0, 0, 0, 2, 71, 16, 1};

    public int getMaxTranceiveLength() {
        return this.maxTranceiveLengthForSecureMessaging;
    }

    @Override // net.sf.scuba.smartcards.CardService
    public boolean isOpen() {
        return this.isOpen;
    }

    public boolean shouldCheckMAC() {
        return this.shouldCheckMAC;
    }

    public PassportService(CardService cardService, int i, int i2, boolean z, boolean z2) {
        this(cardService, 256, i, i2, z, z2);
    }

    public PassportService(CardService cardService, int i, int i2, int i3, boolean z, boolean z2) {
        this.service = cardService;
        this.bacSender = new BACAPDUSender(cardService);
        this.paceSender = new PACEAPDUSender(cardService);
        this.aaSender = new AAAPDUSender(cardService);
        this.eacCASender = new EACCAAPDUSender(cardService);
        this.eacTASender = new EACTAAPDUSender(cardService);
        this.readBinarySender = new ReadBinaryAPDUSender(cardService);
        this.maxTranceiveLengthForPACEProtocol = i;
        this.maxTranceiveLengthForSecureMessaging = i2;
        this.maxBlockSize = i3;
        this.shouldCheckMAC = z2;
        this.isAppletSelected = false;
        this.isOpen = false;
        this.rootFileSystem = new DefaultFileSystem(this.readBinarySender, false);
        this.appletFileSystem = new DefaultFileSystem(this.readBinarySender, z);
    }

    @Override // net.sf.scuba.smartcards.CardService
    public void open() throws CardServiceException {
        if (isOpen()) {
            return;
        }
        synchronized (this) {
            this.service.open();
            this.isOpen = true;
        }
    }

    @Override // org.jmrtd.AbstractMRTDCardService
    public void sendSelectApplet(boolean z) throws CardServiceException {
        if (this.isAppletSelected) {
            LOGGER.info("Re-selecting ICAO applet");
        }
        if (z) {
            this.readBinarySender.sendSelectApplet(this.wrapper, APPLET_AID);
        } else {
            this.readBinarySender.sendSelectApplet(null, APPLET_AID);
        }
        this.isAppletSelected = true;
    }

    @Override // org.jmrtd.AbstractMRTDCardService
    public void sendSelectMF() throws CardServiceException {
        this.readBinarySender.sendSelectMF();
        this.wrapper = null;
    }

    @Override // org.jmrtd.AbstractMRTDCardService
    public synchronized BACResult doBAC(AccessKeySpec accessKeySpec) throws CardServiceException {
        BACResult bACResultDoBAC;
        if (!(accessKeySpec instanceof BACKeySpec)) {
            throw new IllegalArgumentException("Unsupported key type");
        }
        bACResultDoBAC = new BACProtocol(this.bacSender, this.maxTranceiveLengthForSecureMessaging, this.shouldCheckMAC).doBAC(accessKeySpec);
        SecureMessagingWrapper wrapper = bACResultDoBAC.getWrapper();
        this.wrapper = wrapper;
        this.appletFileSystem.setWrapper(wrapper);
        return bACResultDoBAC;
    }

    @Override // org.jmrtd.AbstractMRTDCardService
    public synchronized BACResult doBAC(SecretKey secretKey, SecretKey secretKey2) throws GeneralSecurityException, CardServiceException {
        BACResult bACResultDoBAC;
        bACResultDoBAC = new BACProtocol(this.bacSender, this.maxTranceiveLengthForSecureMessaging, this.shouldCheckMAC).doBAC(secretKey, secretKey2);
        SecureMessagingWrapper wrapper = bACResultDoBAC.getWrapper();
        this.wrapper = wrapper;
        this.appletFileSystem.setWrapper(wrapper);
        return bACResultDoBAC;
    }

    @Override // org.jmrtd.AbstractMRTDCardService
    public synchronized PACEResult doPACE(AccessKeySpec accessKeySpec, String str, AlgorithmParameterSpec algorithmParameterSpec, BigInteger bigInteger) throws CardServiceException {
        PACEResult pACEResultDoPACE;
        pACEResultDoPACE = new PACEProtocol(this.paceSender, this.wrapper, this.maxTranceiveLengthForPACEProtocol, this.maxTranceiveLengthForSecureMessaging, this.shouldCheckMAC).doPACE(accessKeySpec, str, algorithmParameterSpec, bigInteger);
        SecureMessagingWrapper wrapper = pACEResultDoPACE.getWrapper();
        this.wrapper = wrapper;
        this.appletFileSystem.setWrapper(wrapper);
        return pACEResultDoPACE;
    }

    @Override // org.jmrtd.AbstractMRTDCardService
    public synchronized EACCAResult doEACCA(BigInteger bigInteger, String str, String str2, PublicKey publicKey) throws CardServiceException {
        EACCAResult eACCAResultDoCA;
        eACCAResultDoCA = new EACCAProtocol(this.eacCASender, getWrapper(), this.maxTranceiveLengthForSecureMessaging, this.shouldCheckMAC).doCA(bigInteger, str, str2, publicKey);
        SecureMessagingWrapper wrapper = eACCAResultDoCA.getWrapper();
        this.wrapper = wrapper;
        this.appletFileSystem.setWrapper(wrapper);
        return eACCAResultDoCA;
    }

    @Override // org.jmrtd.AbstractMRTDCardService
    public synchronized EACTAResult doEACTA(CVCPrincipal cVCPrincipal, List<CardVerifiableCertificate> list, PrivateKey privateKey, String str, EACCAResult eACCAResult, String str2) throws CardServiceException {
        return new EACTAProtocol(this.eacTASender, getWrapper()).doEACTA(cVCPrincipal, list, privateKey, str, eACCAResult, str2);
    }

    @Override // org.jmrtd.AbstractMRTDCardService
    public synchronized EACTAResult doEACTA(CVCPrincipal cVCPrincipal, List<CardVerifiableCertificate> list, PrivateKey privateKey, String str, EACCAResult eACCAResult, PACEResult pACEResult) throws CardServiceException {
        return new EACTAProtocol(this.eacTASender, getWrapper()).doTA(cVCPrincipal, list, privateKey, str, eACCAResult, pACEResult);
    }

    @Override // org.jmrtd.AbstractMRTDCardService
    public AAResult doAA(PublicKey publicKey, String str, String str2, byte[] bArr) throws CardServiceException {
        return new AAProtocol(this.aaSender, getWrapper()).doAA(publicKey, str, str2, bArr);
    }

    @Override // net.sf.scuba.smartcards.CardService
    public void close() {
        try {
            this.service.close();
            this.wrapper = null;
        } finally {
            this.isOpen = false;
        }
    }

    @Override // org.jmrtd.AbstractMRTDCardService
    public SecureMessagingWrapper getWrapper() {
        SecureMessagingWrapper secureMessagingWrapper = (SecureMessagingWrapper) this.appletFileSystem.getWrapper();
        if (secureMessagingWrapper != null && secureMessagingWrapper.getSendSequenceCounter() > this.wrapper.getSendSequenceCounter()) {
            this.wrapper = secureMessagingWrapper;
        }
        return this.wrapper;
    }

    @Override // net.sf.scuba.smartcards.CardService
    public ResponseAPDU transmit(CommandAPDU commandAPDU) throws CardServiceException {
        return this.service.transmit(commandAPDU);
    }

    @Override // net.sf.scuba.smartcards.CardService
    public byte[] getATR() throws CardServiceException {
        return this.service.getATR();
    }

    @Override // net.sf.scuba.smartcards.CardService
    public boolean isConnectionLost(Exception exc) {
        return this.service.isConnectionLost(exc);
    }

    @Override // org.jmrtd.FileSystemCardService
    @Deprecated
    public synchronized CardFileInputStream getInputStream(short s) throws CardServiceException {
        return getInputStream(s, this.maxBlockSize);
    }

    @Override // org.jmrtd.FileSystemCardService
    public synchronized CardFileInputStream getInputStream(short s, int i) throws CardServiceException {
        CardFileInputStream cardFileInputStream;
        CardFileInputStream cardFileInputStream2;
        if (!this.isAppletSelected) {
            synchronized (this.rootFileSystem) {
                this.rootFileSystem.selectFile(s);
                cardFileInputStream2 = new CardFileInputStream(i, this.rootFileSystem);
            }
            return cardFileInputStream2;
        }
        synchronized (this.appletFileSystem) {
            this.appletFileSystem.selectFile(s);
            cardFileInputStream = new CardFileInputStream(i, this.appletFileSystem);
        }
        return cardFileInputStream;
    }

    @Override // org.jmrtd.AbstractMRTDCardService
    public int getMaxReadBinaryLength() {
        DefaultFileSystem defaultFileSystem = this.appletFileSystem;
        if (defaultFileSystem == null) {
            return 256;
        }
        return defaultFileSystem.getMaxReadBinaryLength();
    }

    @Override // net.sf.scuba.smartcards.CardService
    public void addAPDUListener(APDUListener aPDUListener) {
        this.service.addAPDUListener(aPDUListener);
    }

    @Override // net.sf.scuba.smartcards.CardService
    public void removeAPDUListener(APDUListener aPDUListener) {
        this.service.removeAPDUListener(aPDUListener);
    }

    @Override // net.sf.scuba.smartcards.CardService
    public Collection<APDUListener> getAPDUListeners() {
        return this.service.getAPDUListeners();
    }

    @Override // net.sf.scuba.smartcards.CardService
    protected void notifyExchangedAPDU(APDUEvent aPDUEvent) {
        Collection<APDUListener> aPDUListeners = getAPDUListeners();
        if (aPDUListeners == null || aPDUListeners.isEmpty()) {
            return;
        }
        Iterator<APDUListener> it = aPDUListeners.iterator();
        while (it.hasNext()) {
            it.next().exchangedAPDU(aPDUEvent);
        }
    }
}
