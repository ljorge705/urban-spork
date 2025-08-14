package com.drew.metadata.iptc;

import com.drew.metadata.Directory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes5.dex */
public class IptcDirectory extends Directory {
    public static final int TAG_ACTION_ADVISED = 554;
    public static final int TAG_APPLICATION_RECORD_VERSION = 512;
    public static final int TAG_ARM_IDENTIFIER = 376;
    public static final int TAG_ARM_VERSION = 378;
    public static final int TAG_AUDIO_DURATION = 665;
    public static final int TAG_AUDIO_OUTCUE = 666;
    public static final int TAG_AUDIO_SAMPLING_RATE = 663;
    public static final int TAG_AUDIO_SAMPLING_RESOLUTION = 664;
    public static final int TAG_AUDIO_TYPE = 662;
    public static final int TAG_BY_LINE = 592;
    public static final int TAG_BY_LINE_TITLE = 597;
    public static final int TAG_CAPTION = 632;
    public static final int TAG_CAPTION_WRITER = 634;
    public static final int TAG_CATEGORY = 527;
    public static final int TAG_CITY = 602;
    public static final int TAG_CODED_CHARACTER_SET = 346;
    public static final int TAG_CONTACT = 630;
    public static final int TAG_CONTENT_LOCATION_CODE = 538;
    public static final int TAG_CONTENT_LOCATION_NAME = 539;
    public static final int TAG_COPYRIGHT_NOTICE = 628;
    public static final int TAG_COUNTRY_OR_PRIMARY_LOCATION_CODE = 612;
    public static final int TAG_COUNTRY_OR_PRIMARY_LOCATION_NAME = 613;
    public static final int TAG_CREDIT = 622;
    public static final int TAG_DATE_CREATED = 567;
    public static final int TAG_DATE_SENT = 326;
    public static final int TAG_DESTINATION = 261;
    public static final int TAG_DIGITAL_DATE_CREATED = 574;
    public static final int TAG_DIGITAL_TIME_CREATED = 575;
    public static final int TAG_EDITORIAL_UPDATE = 520;
    public static final int TAG_EDIT_STATUS = 519;
    public static final int TAG_ENVELOPE_NUMBER = 296;
    public static final int TAG_ENVELOPE_PRIORITY = 316;
    public static final int TAG_ENVELOPE_RECORD_VERSION = 256;
    public static final int TAG_EXPIRATION_DATE = 549;
    public static final int TAG_EXPIRATION_TIME = 550;
    public static final int TAG_FILE_FORMAT = 276;
    public static final int TAG_FILE_VERSION = 278;
    public static final int TAG_FIXTURE_ID = 534;
    public static final int TAG_HEADLINE = 617;
    public static final int TAG_IMAGE_ORIENTATION = 643;
    public static final int TAG_IMAGE_TYPE = 642;
    public static final int TAG_JOB_ID = 696;
    public static final int TAG_KEYWORDS = 537;
    public static final int TAG_LANGUAGE_IDENTIFIER = 647;
    public static final int TAG_LOCAL_CAPTION = 633;
    public static final int TAG_MASTER_DOCUMENT_ID = 697;
    public static final int TAG_OBJECT_ATTRIBUTE_REFERENCE = 516;
    public static final int TAG_OBJECT_CYCLE = 587;
    public static final int TAG_OBJECT_NAME = 517;
    public static final int TAG_OBJECT_PREVIEW_DATA = 714;
    public static final int TAG_OBJECT_PREVIEW_FILE_FORMAT = 712;
    public static final int TAG_OBJECT_PREVIEW_FILE_FORMAT_VERSION = 713;
    public static final int TAG_OBJECT_TYPE_REFERENCE = 515;
    public static final int TAG_ORIGINAL_TRANSMISSION_REFERENCE = 615;
    public static final int TAG_ORIGINATING_PROGRAM = 577;
    public static final int TAG_OWNER_ID = 700;
    public static final int TAG_PRODUCT_ID = 306;
    public static final int TAG_PROGRAM_VERSION = 582;
    public static final int TAG_PROVINCE_OR_STATE = 607;
    public static final int TAG_RASTERIZED_CAPTION = 637;
    public static final int TAG_REFERENCE_DATE = 559;
    public static final int TAG_REFERENCE_NUMBER = 562;
    public static final int TAG_REFERENCE_SERVICE = 557;
    public static final int TAG_RELEASE_DATE = 542;
    public static final int TAG_RELEASE_TIME = 547;
    public static final int TAG_SERVICE_ID = 286;
    public static final int TAG_SHORT_DOCUMENT_ID = 698;
    public static final int TAG_SOURCE = 627;
    public static final int TAG_SPECIAL_INSTRUCTIONS = 552;
    public static final int TAG_SUBJECT_REFERENCE = 524;
    public static final int TAG_SUB_LOCATION = 604;
    public static final int TAG_SUPPLEMENTAL_CATEGORIES = 532;
    public static final int TAG_TIME_CREATED = 572;
    public static final int TAG_TIME_SENT = 336;
    public static final int TAG_UNIQUE_DOCUMENT_ID = 699;
    public static final int TAG_UNIQUE_OBJECT_NAME = 356;
    public static final int TAG_URGENCY = 522;
    protected static final HashMap<Integer, String> _tagNameMap;

    @Override // com.drew.metadata.Directory
    public String getName() {
        return "IPTC";
    }

    @Override // com.drew.metadata.Directory
    protected HashMap<Integer, String> getTagNameMap() {
        return _tagNameMap;
    }

    static {
        HashMap<Integer, String> map = new HashMap<>();
        _tagNameMap = map;
        map.put(256, "Enveloped Record Version");
        map.put(261, "Destination");
        map.put(276, "File Format");
        map.put(278, "File Version");
        map.put(286, "Service Identifier");
        map.put(296, "Envelope Number");
        map.put(306, "Product Identifier");
        map.put(316, "Envelope Priority");
        map.put(Integer.valueOf(TAG_DATE_SENT), "Date Sent");
        map.put(Integer.valueOf(TAG_TIME_SENT), "Time Sent");
        map.put(Integer.valueOf(TAG_CODED_CHARACTER_SET), "Coded Character Set");
        map.put(Integer.valueOf(TAG_UNIQUE_OBJECT_NAME), "Unique Object Name");
        map.put(Integer.valueOf(TAG_ARM_IDENTIFIER), "ARM Identifier");
        map.put(Integer.valueOf(TAG_ARM_VERSION), "ARM Version");
        map.put(512, "Application Record Version");
        map.put(515, "Object Type Reference");
        map.put(516, "Object Attribute Reference");
        map.put(517, "Object Name");
        map.put(519, "Edit Status");
        map.put(520, "Editorial Update");
        map.put(522, "Urgency");
        map.put(524, "Subject Reference");
        map.put(527, "Category");
        map.put(532, "Supplemental Category(s)");
        map.put(534, "Fixture Identifier");
        map.put(537, "Keywords");
        map.put(Integer.valueOf(TAG_CONTENT_LOCATION_CODE), "Content Location Code");
        map.put(539, "Content Location Name");
        map.put(542, "Release Date");
        map.put(547, "Release Time");
        map.put(549, "Expiration Date");
        map.put(Integer.valueOf(TAG_EXPIRATION_TIME), "Expiration Time");
        map.put(Integer.valueOf(TAG_SPECIAL_INSTRUCTIONS), "Special Instructions");
        map.put(Integer.valueOf(TAG_ACTION_ADVISED), "Action Advised");
        map.put(Integer.valueOf(TAG_REFERENCE_SERVICE), "Reference Service");
        map.put(559, "Reference Date");
        map.put(Integer.valueOf(TAG_REFERENCE_NUMBER), "Reference Number");
        map.put(Integer.valueOf(TAG_DATE_CREATED), "Date Created");
        map.put(Integer.valueOf(TAG_TIME_CREATED), "Time Created");
        map.put(Integer.valueOf(TAG_DIGITAL_DATE_CREATED), "Digital Date Created");
        map.put(Integer.valueOf(TAG_DIGITAL_TIME_CREATED), "Digital Time Created");
        map.put(Integer.valueOf(TAG_ORIGINATING_PROGRAM), "Originating Program");
        map.put(Integer.valueOf(TAG_PROGRAM_VERSION), "Program Version");
        map.put(Integer.valueOf(TAG_OBJECT_CYCLE), "Object Cycle");
        map.put(Integer.valueOf(TAG_BY_LINE), "By-line");
        map.put(Integer.valueOf(TAG_BY_LINE_TITLE), "By-line Title");
        map.put(Integer.valueOf(TAG_CITY), "City");
        map.put(Integer.valueOf(TAG_SUB_LOCATION), "Sub-location");
        map.put(Integer.valueOf(TAG_PROVINCE_OR_STATE), "Province/State");
        map.put(Integer.valueOf(TAG_COUNTRY_OR_PRIMARY_LOCATION_CODE), "Country/Primary Location Code");
        map.put(Integer.valueOf(TAG_COUNTRY_OR_PRIMARY_LOCATION_NAME), "Country/Primary Location Name");
        map.put(Integer.valueOf(TAG_ORIGINAL_TRANSMISSION_REFERENCE), "Original Transmission Reference");
        map.put(Integer.valueOf(TAG_HEADLINE), "Headline");
        map.put(Integer.valueOf(TAG_CREDIT), "Credit");
        map.put(Integer.valueOf(TAG_SOURCE), "Source");
        map.put(Integer.valueOf(TAG_COPYRIGHT_NOTICE), "Copyright Notice");
        map.put(Integer.valueOf(TAG_CONTACT), "Contact");
        map.put(Integer.valueOf(TAG_CAPTION), "Caption/Abstract");
        map.put(Integer.valueOf(TAG_LOCAL_CAPTION), "Local Caption");
        map.put(Integer.valueOf(TAG_CAPTION_WRITER), "Caption Writer/Editor");
        map.put(Integer.valueOf(TAG_RASTERIZED_CAPTION), "Rasterized Caption");
        map.put(Integer.valueOf(TAG_IMAGE_TYPE), "Image Type");
        map.put(Integer.valueOf(TAG_IMAGE_ORIENTATION), "Image Orientation");
        map.put(Integer.valueOf(TAG_LANGUAGE_IDENTIFIER), "Language Identifier");
        map.put(Integer.valueOf(TAG_AUDIO_TYPE), "Audio Type");
        map.put(Integer.valueOf(TAG_AUDIO_SAMPLING_RATE), "Audio Sampling Rate");
        map.put(Integer.valueOf(TAG_AUDIO_SAMPLING_RESOLUTION), "Audio Sampling Resolution");
        map.put(Integer.valueOf(TAG_AUDIO_DURATION), "Audio Duration");
        map.put(Integer.valueOf(TAG_AUDIO_OUTCUE), "Audio Outcue");
        map.put(Integer.valueOf(TAG_JOB_ID), "Job Identifier");
        map.put(Integer.valueOf(TAG_MASTER_DOCUMENT_ID), "Master Document Identifier");
        map.put(Integer.valueOf(TAG_SHORT_DOCUMENT_ID), "Short Document Identifier");
        map.put(Integer.valueOf(TAG_UNIQUE_DOCUMENT_ID), "Unique Document Identifier");
        map.put(700, "Owner Identifier");
        map.put(Integer.valueOf(TAG_OBJECT_PREVIEW_FILE_FORMAT), "Object Data Preview File Format");
        map.put(Integer.valueOf(TAG_OBJECT_PREVIEW_FILE_FORMAT_VERSION), "Object Data Preview File Format Version");
        map.put(Integer.valueOf(TAG_OBJECT_PREVIEW_DATA), "Object Data Preview Data");
    }

    public IptcDirectory() {
        setDescriptor(new IptcDescriptor(this));
    }

    public List<String> getKeywords() {
        String[] stringArray = getStringArray(537);
        if (stringArray == null) {
            return null;
        }
        return Arrays.asList(stringArray);
    }

    public Date getDateSent() {
        return getDate(TAG_DATE_SENT, TAG_TIME_SENT);
    }

    public Date getReleaseDate() {
        return getDate(542, 547);
    }

    public Date getExpirationDate() {
        return getDate(549, TAG_EXPIRATION_TIME);
    }

    public Date getDateCreated() {
        return getDate(TAG_DATE_CREATED, TAG_TIME_CREATED);
    }

    public Date getDigitalDateCreated() {
        return getDate(TAG_DIGITAL_DATE_CREATED, TAG_DIGITAL_TIME_CREATED);
    }

    private Date getDate(int i, int i2) {
        String string = getString(i);
        String string2 = getString(i2);
        if (string == null || string2 == null) {
            return null;
        }
        try {
            return new SimpleDateFormat("yyyyMMddHHmmssZ").parse(string + string2);
        } catch (ParseException unused) {
            return null;
        }
    }
}
