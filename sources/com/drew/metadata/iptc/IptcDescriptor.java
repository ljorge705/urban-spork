package com.drew.metadata.iptc;

import com.drew.lang.StringUtil;
import com.drew.metadata.TagDescriptor;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* loaded from: classes5.dex */
public class IptcDescriptor extends TagDescriptor<IptcDirectory> {
    public IptcDescriptor(IptcDirectory iptcDirectory) {
        super(iptcDirectory);
    }

    @Override // com.drew.metadata.TagDescriptor
    public String getDescription(int i) {
        if (i == 276) {
            return getFileFormatDescription();
        }
        if (i == 326) {
            return getDateSentDescription();
        }
        if (i == 336) {
            return getTimeSentDescription();
        }
        if (i == 537) {
            return getKeywordsDescription();
        }
        if (i == 542) {
            return getReleaseDateDescription();
        }
        if (i == 547) {
            return getReleaseTimeDescription();
        }
        if (i == 559) {
            return getReferenceDateDescription();
        }
        if (i == 567) {
            return getDateCreatedDescription();
        }
        if (i == 572) {
            return getTimeCreatedDescription();
        }
        if (i == 549) {
            return getExpirationDateDescription();
        }
        if (i == 550) {
            return getExpirationTimeDescription();
        }
        if (i == 574) {
            return getDigitalDateCreatedDescription();
        }
        if (i == 575) {
            return getDigitalTimeCreatedDescription();
        }
        return super.getDescription(i);
    }

    public String getDateDescription(int i) {
        String string = ((IptcDirectory) this._directory).getString(i);
        if (string == null) {
            return null;
        }
        return string.length() == 8 ? string.substring(0, 4) + AbstractJsonLexerKt.COLON + string.substring(4, 6) + AbstractJsonLexerKt.COLON + string.substring(6) : string;
    }

    public String getTimeDescription(int i) {
        String string = ((IptcDirectory) this._directory).getString(i);
        if (string == null) {
            return null;
        }
        return (string.length() == 6 || string.length() == 11) ? string.substring(0, 2) + AbstractJsonLexerKt.COLON + string.substring(2, 4) + AbstractJsonLexerKt.COLON + string.substring(4) : string;
    }

    public String getFileFormatDescription() {
        Integer integer = ((IptcDirectory) this._directory).getInteger(276);
        if (integer == null) {
            return null;
        }
        switch (integer.intValue()) {
            case 0:
                return "No ObjectData";
            case 1:
                return "IPTC-NAA Digital Newsphoto Parameter Record";
            case 2:
                return "IPTC7901 Recommended Message Format";
            case 3:
                return "Tagged Image File Format (Adobe/Aldus Image data)";
            case 4:
                return "Illustrator (Adobe Graphics data)";
            case 5:
                return "AppleSingle (Apple Computer Inc)";
            case 6:
                return "NAA 89-3 (ANPA 1312)";
            case 7:
                return "MacBinary II";
            case 8:
                return "IPTC Unstructured Character Oriented File Format (UCOFF)";
            case 9:
                return "United Press International ANPA 1312 variant";
            case 10:
                return "United Press International Down-Load Message";
            case 11:
                return "JPEG File Interchange (JFIF)";
            case 12:
                return "Photo-CD Image-Pac (Eastman Kodak)";
            case 13:
                return "Bit Mapped Graphics File [.BMP] (Microsoft)";
            case 14:
                return "Digital Audio File [.WAV] (Microsoft & Creative Labs)";
            case 15:
                return "Audio plus Moving Video [.AVI] (Microsoft)";
            case 16:
                return "PC DOS/Windows Executable Files [.COM][.EXE]";
            case 17:
                return "Compressed Binary File [.ZIP] (PKWare Inc)";
            case 18:
                return "Audio Interchange File Format AIFF (Apple Computer Inc)";
            case 19:
                return "RIFF Wave (Microsoft Corporation)";
            case 20:
                return "Freehand (Macromedia/Aldus)";
            case 21:
                return "Hypertext Markup Language [.HTML] (The Internet Society)";
            case 22:
                return "MPEG 2 Audio Layer 2 (Musicom), ISO/IEC";
            case 23:
                return "MPEG 2 Audio Layer 3, ISO/IEC";
            case 24:
                return "Portable Document File [.PDF] Adobe";
            case 25:
                return "News Industry Text Format (NITF)";
            case 26:
                return "Tape Archive [.TAR]";
            case 27:
                return "Tidningarnas Telegrambyra NITF version (TTNITF DTD)";
            case 28:
                return "Ritzaus Bureau NITF version (RBNITF DTD)";
            case 29:
                return "Corel Draw [.CDR]";
            default:
                return String.format("Unknown (%d)", integer);
        }
    }

    public String getByLineDescription() {
        return ((IptcDirectory) this._directory).getString(IptcDirectory.TAG_BY_LINE);
    }

    public String getByLineTitleDescription() {
        return ((IptcDirectory) this._directory).getString(IptcDirectory.TAG_BY_LINE_TITLE);
    }

    public String getCaptionDescription() {
        return ((IptcDirectory) this._directory).getString(IptcDirectory.TAG_CAPTION);
    }

    public String getCategoryDescription() {
        return ((IptcDirectory) this._directory).getString(527);
    }

    public String getCityDescription() {
        return ((IptcDirectory) this._directory).getString(IptcDirectory.TAG_CITY);
    }

    public String getCopyrightNoticeDescription() {
        return ((IptcDirectory) this._directory).getString(IptcDirectory.TAG_COPYRIGHT_NOTICE);
    }

    public String getCountryOrPrimaryLocationDescription() {
        return ((IptcDirectory) this._directory).getString(IptcDirectory.TAG_COUNTRY_OR_PRIMARY_LOCATION_NAME);
    }

    public String getCreditDescription() {
        return ((IptcDirectory) this._directory).getString(IptcDirectory.TAG_CREDIT);
    }

    public String getDateCreatedDescription() {
        return getDateDescription(IptcDirectory.TAG_DATE_CREATED);
    }

    public String getDigitalDateCreatedDescription() {
        return getDateDescription(IptcDirectory.TAG_DIGITAL_DATE_CREATED);
    }

    public String getDateSentDescription() {
        return getDateDescription(IptcDirectory.TAG_DATE_SENT);
    }

    public String getExpirationDateDescription() {
        return getDateDescription(549);
    }

    public String getExpirationTimeDescription() {
        return getTimeDescription(IptcDirectory.TAG_EXPIRATION_TIME);
    }

    public String getHeadlineDescription() {
        return ((IptcDirectory) this._directory).getString(IptcDirectory.TAG_HEADLINE);
    }

    public String getKeywordsDescription() {
        String[] stringArray = ((IptcDirectory) this._directory).getStringArray(537);
        if (stringArray == null) {
            return null;
        }
        return StringUtil.join(stringArray, ";");
    }

    public String getObjectNameDescription() {
        return ((IptcDirectory) this._directory).getString(517);
    }

    public String getOriginalTransmissionReferenceDescription() {
        return ((IptcDirectory) this._directory).getString(IptcDirectory.TAG_ORIGINAL_TRANSMISSION_REFERENCE);
    }

    public String getOriginatingProgramDescription() {
        return ((IptcDirectory) this._directory).getString(IptcDirectory.TAG_ORIGINATING_PROGRAM);
    }

    public String getProvinceOrStateDescription() {
        return ((IptcDirectory) this._directory).getString(IptcDirectory.TAG_PROVINCE_OR_STATE);
    }

    public String getRecordVersionDescription() {
        return ((IptcDirectory) this._directory).getString(512);
    }

    public String getReferenceDateDescription() {
        return getDateDescription(559);
    }

    public String getReleaseDateDescription() {
        return getDateDescription(542);
    }

    public String getReleaseTimeDescription() {
        return getTimeDescription(547);
    }

    public String getSourceDescription() {
        return ((IptcDirectory) this._directory).getString(IptcDirectory.TAG_SOURCE);
    }

    public String getSpecialInstructionsDescription() {
        return ((IptcDirectory) this._directory).getString(IptcDirectory.TAG_SPECIAL_INSTRUCTIONS);
    }

    public String getSupplementalCategoriesDescription() {
        return ((IptcDirectory) this._directory).getString(532);
    }

    public String getTimeCreatedDescription() {
        return getTimeDescription(IptcDirectory.TAG_TIME_CREATED);
    }

    public String getDigitalTimeCreatedDescription() {
        return getTimeDescription(IptcDirectory.TAG_DIGITAL_TIME_CREATED);
    }

    public String getTimeSentDescription() {
        return getTimeDescription(IptcDirectory.TAG_TIME_SENT);
    }

    public String getUrgencyDescription() {
        return ((IptcDirectory) this._directory).getString(522);
    }

    public String getWriterDescription() {
        return ((IptcDirectory) this._directory).getString(IptcDirectory.TAG_CAPTION_WRITER);
    }
}
