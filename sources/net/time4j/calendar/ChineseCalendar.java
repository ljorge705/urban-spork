package net.time4j.calendar;

import androidx.exifinterface.media.ExifInterface;
import androidx.media3.common.PlaybackException;
import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusFocusInfoMakernoteDirectory;
import com.drew.metadata.exif.makernotes.OlympusImageProcessingMakernoteDirectory;
import java.io.Externalizable;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.text.ParsePosition;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import net.time4j.SystemClock;
import net.time4j.Weekday;
import net.time4j.Weekmodel;
import net.time4j.calendar.CommonElements;
import net.time4j.calendar.service.StdIntegerDateElement;
import net.time4j.calendar.service.StdWeekdayElement;
import net.time4j.engine.AttributeQuery;
import net.time4j.engine.CalendarEra;
import net.time4j.engine.CalendarSystem;
import net.time4j.engine.ChronoDisplay;
import net.time4j.engine.ChronoElement;
import net.time4j.engine.ChronoEntity;
import net.time4j.engine.ChronoException;
import net.time4j.engine.ChronoExtension;
import net.time4j.engine.ChronoFunction;
import net.time4j.engine.ChronoUnit;
import net.time4j.engine.ElementRule;
import net.time4j.engine.FormattableElement;
import net.time4j.engine.IntElementRule;
import net.time4j.engine.TimeAxis;
import net.time4j.format.Attributes;
import net.time4j.format.CalendarType;
import net.time4j.format.DisplayElement;
import net.time4j.format.LocalizedPatternSupport;
import net.time4j.format.TextElement;
import net.time4j.format.TextWidth;
import net.time4j.tz.OffsetSign;
import net.time4j.tz.ZonalOffset;

@CalendarType("chinese")
/* loaded from: classes4.dex */
public final class ChineseCalendar extends EastAsianCalendar<Unit, ChineseCalendar> implements LocalizedPatternSupport {
    private static final EastAsianCS<ChineseCalendar> CALSYS;
    public static final ChronoElement<Integer> CYCLE;

    @FormattableElement(format = "d")
    public static final StdCalendarElement<Integer, ChineseCalendar> DAY_OF_MONTH;

    @FormattableElement(format = ExifInterface.LONGITUDE_EAST)
    public static final StdCalendarElement<Weekday, ChineseCalendar> DAY_OF_WEEK;

    @FormattableElement(format = "D")
    public static final StdCalendarElement<Integer, ChineseCalendar> DAY_OF_YEAR;
    private static final TimeAxis<Unit, ChineseCalendar> ENGINE;

    @FormattableElement(format = "G")
    public static final ChronoElement<ChineseEra> ERA;
    private static final int[] LEAP_MONTHS = {4281, 5, 4284, 4, 4287, 1, 4289, 6, 4292, 5, 4295, 3, 4297, 8, 4300, 6, 4303, 4, 4306, 2, 4308, 7, 4311, 5, 4314, 3, 4316, 8, 4319, 6, 4322, 4, 4325, 3, 4327, 7, 4330, 5, 4333, 3, 4335, 7, 4338, 6, 4341, 4, 4344, 3, 4346, 7, 4349, 5, FujifilmMakernoteDirectory.TAG_AUTO_BRACKETING, 3, 4354, 8, 4357, 6, 4360, 4, 4363, 2, 4365, 7, 4368, 5, OlympusImageProcessingMakernoteDirectory.TagAspectFrame, 4, 4373, 9, 4376, 6, 4379, 4, 4382, 3, 4384, 7, 4387, 5, 4390, 4, 4392, 9, 4395, 6, 4398, 5, 4401, 2, 4403, 7, 4406, 5, 4409, 3, 4411, 10, 4414, 6, 4417, 5, 4420, 3, 4422, 7, 4425, 5, 4428, 4, 4431, 2, 4433, 6, 4436, 4, 4439, 2, 4441, 7, 4444, 5, 4447, 3, 4450, 2, 4452, 6, 4455, 4, 4458, 3, 4460, 7, 4463, 5, 4466, 4, 4468, 9, 4471, 6, 4474, 4, 4477, 3, 4479, 7, 4482, 5, 4485, 4, 4487, 8, 4490, 7, 4493, 5, 4496, 3, 4498, 8, 4501, 5, 4504, 4, 4506, 10, 4509, 6, 4512, 5, 4515, 3, 4517, 7, 4520, 5, 4523, 4, 4526, 2, 4528, 6, 4531, 5, 4534, 3, 4536, 8, 4539, 5, 4542, 4, 4545, 2, 4547, 6, 4550, 5, 4553, 2, 4555, 7, 4558, 5, 4561, 4, 4564, 2, 4566, 6, 4569, 5, 4572, 3, 4574, 7, 4577, 6, 4580, 4, 4583, 2, 4585, 7, 4588, 5, 4591, 3, 4593, 8, 4596, 6, 4599, 4, 4602, 3, 4604, 7, 4607, 5, OlympusImageProcessingMakernoteDirectory.TagMaxFaces, 4, OlympusFocusInfoMakernoteDirectory.TagExternalFlashBounce, 8, OlympusImageProcessingMakernoteDirectory.TagFaceDetectFrameCrop, 6, OlympusFocusInfoMakernoteDirectory.TagMacroLed, 4, 4620, 10, 4623, 6, 4626, 5, 4629, 3, 4631, 8, 4634, 5, 4637, 4, 4640, 2, 4642, 7, 4645, 5, 4648, 4, 4650, 9, 4653, 6, 4656, 4, 4659, 2, 4661, 6, 4664, 5, 4667, 3, 4669, 11, 4672, 6, 4675, 5, 4678, 2, 4680, 7, 4683, 5, 4686, 3, 4688, 8, 4691, 6, 4694, 4, 4697, 3, 4699, 7, 4702, 5, 4705, 4, 4707, 8, 4710, 6, 4713, 4, 4716, 3, 4718, 7, 4721, 5, 4724, 4, 4726, 8, 4729, 6, 4732, 4, 4735, 2, 4737, 7, 4740, 5, 4743, 4, 4745, 9, 4748, 6, 4751, 4, 4754, 3, 4756, 7, 4759, 5, 4762, 4, 4764, 11, 4767, 6, 4770, 5, 4773, 2, 4775, 7, 4778, 5, 4781, 4, 4783, 11, 4786, 6, 4789, 5, 4792, 3, 4794, 7, 4797, 6, 4800, 4, 4802, 10, 4805, 6, 4808, 5, 4811, 3, 4813, 7, 4816, 6, 4819, 4, 4822, 2, 4824, 6, 4827, 5, 4830, 3, 4832, 7, 4835, 6, 4838, 4, 4840, 9, 4843, 6, 4846, 4, 4849, 3, 4851, 7, 4854, 5, 4857, 4, 4859, 9, 4862, 7, FujifilmMakernoteDirectory.TAG_FOCUS_WARNING, 5, FujifilmMakernoteDirectory.TAG_GE_IMAGE_SIZE, 3, OlympusImageProcessingMakernoteDirectory.TagCameraTemperature, 8, 4873, 5, 4876, 4, 4878, 11, 4881, 6, 4884, 5, 4887, 3, 4889, 7, 4892, 6, 4895, 5, 4898, 1, 4900, 7, 4903, 5, 4906, 3, 4908, 8, 4911, 6, 4914, 4, 4917, 2, 4919, 6, 4922, 5, 4925, 3, 4927, 7, 4930, 6, 4933, 4, 4936, 2, 4938, 6, 4941, 5, 4944, 3, 4946, 7, 4949, 6, 4952, 4, 4954, 10, 4957, 7, 4960, 5, 4963, 3, 4965, 8, 4968, 6, 4971, 4, 4974, 3, 4976, 7, 4979, 5, 4982, 4, 4984, 8, 4987, 6, 4990, 5, 4993, 1, 4995, 7, 4998, 5, PlaybackException.ERROR_CODE_AUDIO_TRACK_INIT_FAILED, 4, 5003, 8, 5006, 6, 5009, 5, 5012, 2, 5014, 7, 5017, 5, 5020, 4, 5022, 10, 5025, 6, 5028, 4, 5031, 2, 5033, 6, 5036, 5, 5039, 3, 5041, 8, 5044, 6, 5047, 5, 5050, 2, 5052, 7, 5055, 5, 5058, 3, 5060, 8, 5063, 6, 5066, 4, 5069, 3, 5071, 7, 5074, 5, 5077, 4, 5079, 8, 5082, 7, 5085, 5, 5088, 3, 5090, 8, 5093, 5, 5096, 4, 5098, 8, 5101, 6, 5104, 5, 5107, 3, 5109, 7, 5112, 5, 5115, 4, 5117, 10, FujifilmMakernoteDirectory.TAG_DYNAMIC_RANGE, 6, FujifilmMakernoteDirectory.TAG_DEVELOPMENT_DYNAMIC_RANGE, 5, FujifilmMakernoteDirectory.TAG_MAX_APERTURE_AT_MIN_FOCAL, 3, 5128, 7, FujifilmMakernoteDirectory.TAG_AUTO_DYNAMIC_RANGE, 5, 5134, 4, 5136, 10, 5139, 6, 5142, 5, 5145, 2, 5147, 7, 5150, 5, 5153, 4, 5156, 1, 5158, 6, 5161, 5, 5164, 3, 5166, 7, 5169, 6, 5172, 4, 5175, 1, 5177, 7, 5180, 5, 5183, 3, 5185, 7, 5188, 6, 5191, 4, 5193, 8, 5196, 7, 5199, 5, 5202, 4, 5204, 7, 5207, 6, 5210, 4, 5212, 9, 5215, 7, 5218, 5, 5221, 3, 5223, 7, 5226, 6, 5229, 4, 5231, 10, 5234, 7, 5237, 5, 5240, 3, 5242, 8, 5245, 6, 5248, 4, 5250, 11, 5253, 6, 5256, 5, 5259, 3, 5261, 8, 5264, 6, 5267, 5, 5270, 1, 5272, 7, 5275, 5, 5278, 3, 5280, 8, 5283, 6, 5286, 4, 5289, 2, 5291, 7, 5294, 5, 5297, 3, 5299, 7, 5302, 6, 5305, 4, 5308, 3, 5310, 7, 5313, 5, 5316, 3, 5318, 7, 5321, 6, 5324, 4, 5327, 3, 5329, 7, 5332, 5, 5335, 3, 5337, 8, 5340, 6, 5343, 4, 5346, 2, 5348, 7, 5351, 5, 5354, 4, 5356, 9, 5359, 6, 5362, 5, 5364, 11, 5367, 7, 5370, 5, 5373, 4, 5375, 9, 5378, 6, 5381, 5, 5384, 2, 5386, 7, 5389, 6, 5392, 4, 5394, 8, 5397, 6, 5400, 5, 5403, 3, 5405, 7, 5408, 6, 5411, 4, 5413, 8, 5416, 6, 5419, 5, 5422, 3, 5424, 7, 5427, 6, 5430, 3, 5432, 8, 5435, 6, 5438, 4, 5441, 3, 5443, 7, 5446, 6, 5449, 4, 5451, 9, 5454, 7, 5457, 5, 5460, 3, 5462, 8, 5465, 5, 5468, 4, 5470, 9, 5473, 6, 5476, 5, 5479, 3, 5481, 8, 5484, 6, 5487, 4, 5489, 9, 5492, 6, 5495, 5, 5498, 3, 5500, 7, 5503, 6, 5506, 4, 5508, 10, 5511, 6, 5514, 5, 5517, 3, 5519, 7, 5522, 6, 5525, 4, 5527, 10, 5530, 6, 5533, 5, 5536, 3, 5538, 7, 5541, 6, 5544, 4, 5546, 11, 5549, 7, 5552, 5, 5555, 3, 5557, 8, 5560, 6, 5563, 4, 5565, 9, 5568, 7, 5571, 5, 5574, 4, 5576, 8, 5579, 6, 5582, 4, 5584, 8, 5587, 7, 5590, 5, 5593, 4, 5595, 8, 5598, 6, 5601, 5, 5603, 10, 5606, 7, 5609, 5, 5612, 3, 5614, 8, 5617, 6, 5620, 4, 5622, 10, 5625, 6, 5628, 5, 5631, 3, 5633, 8, 5636, 6
    };
    public static final StdCalendarElement<Integer, ChineseCalendar> MONTH_AS_ORDINAL;

    @FormattableElement(alt = "L", format = "M")
    public static final TextElement<EastAsianMonth> MONTH_OF_YEAR;
    public static final ChronoElement<SolarTerm> SOLAR_TERM;

    @FormattableElement(format = "F")
    public static final OrdinalWeekdayElement<ChineseCalendar> WEEKDAY_IN_MONTH;
    private static final WeekdayInMonthElement<ChineseCalendar> WIM_ELEMENT;

    @FormattableElement(format = "U")
    public static final TextElement<CyclicYear> YEAR_OF_CYCLE;

    @FormattableElement(format = "y")
    public static final StdCalendarElement<Integer, ChineseCalendar> YEAR_OF_ERA;
    private static final long serialVersionUID = 8743381746750717307L;

    public static TimeAxis<Unit, ChineseCalendar> axis() {
        return ENGINE;
    }

    @Override // net.time4j.calendar.EastAsianCalendar
    EastAsianCS<ChineseCalendar> getCalendarSystem() {
        return CALSYS;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.time4j.engine.TimePoint, net.time4j.engine.ChronoEntity
    public TimeAxis<Unit, ChineseCalendar> getChronology() {
        return ENGINE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // net.time4j.engine.ChronoEntity
    public ChineseCalendar getContext() {
        return this;
    }

    static {
        EraElement eraElement = EraElement.INSTANCE;
        ERA = eraElement;
        StdIntegerDateElement stdIntegerDateElement = new StdIntegerDateElement("CYCLE", ChineseCalendar.class, 72, 94, (char) 0, null, null);
        CYCLE = stdIntegerDateElement;
        StdIntegerDateElement stdIntegerDateElement2 = new StdIntegerDateElement("YEAR_OF_ERA", ChineseCalendar.class, 1, 5636, 'y', null, null);
        YEAR_OF_ERA = stdIntegerDateElement2;
        EastAsianCY eastAsianCY = EastAsianCY.SINGLETON;
        YEAR_OF_CYCLE = eastAsianCY;
        EastAsianST eastAsianST = EastAsianST.getInstance();
        SOLAR_TERM = eastAsianST;
        EastAsianME eastAsianME = EastAsianME.SINGLETON_EA;
        MONTH_OF_YEAR = eastAsianME;
        StdIntegerDateElement stdIntegerDateElement3 = new StdIntegerDateElement("MONTH_AS_ORDINAL", ChineseCalendar.class, 1, 12, (char) 0, null, null);
        MONTH_AS_ORDINAL = stdIntegerDateElement3;
        StdIntegerDateElement stdIntegerDateElement4 = new StdIntegerDateElement("DAY_OF_MONTH", ChineseCalendar.class, 1, 30, 'd');
        DAY_OF_MONTH = stdIntegerDateElement4;
        StdIntegerDateElement stdIntegerDateElement5 = new StdIntegerDateElement("DAY_OF_YEAR", ChineseCalendar.class, 1, 355, 'D');
        DAY_OF_YEAR = stdIntegerDateElement5;
        StdWeekdayElement stdWeekdayElement = new StdWeekdayElement(ChineseCalendar.class, getDefaultWeekmodel());
        DAY_OF_WEEK = stdWeekdayElement;
        WeekdayInMonthElement<ChineseCalendar> weekdayInMonthElement = new WeekdayInMonthElement<>(ChineseCalendar.class, stdIntegerDateElement4, stdWeekdayElement);
        WIM_ELEMENT = weekdayInMonthElement;
        WEEKDAY_IN_MONTH = weekdayInMonthElement;
        Transformer transformer = new Transformer();
        CALSYS = transformer;
        ENGINE = TimeAxis.Builder.setUp(Unit.class, ChineseCalendar.class, new Merger(), transformer).appendElement((ChronoElement) eraElement, (ElementRule) EraElement.INSTANCE).appendElement((ChronoElement) stdIntegerDateElement, EastAsianCalendar.getCycleRule(eastAsianCY)).appendElement((ChronoElement) stdIntegerDateElement2, (ElementRule) new YearOfEraRule()).appendElement(eastAsianCY, EastAsianCalendar.getYearOfCycleRule(eastAsianME), Unit.YEARS).appendElement((ChronoElement) eastAsianST, (ElementRule) EastAsianST.getInstance()).appendElement(eastAsianME, EastAsianCalendar.getMonthOfYearRule(stdIntegerDateElement4), Unit.MONTHS).appendElement(stdIntegerDateElement3, EastAsianCalendar.getMonthAsOrdinalRule(stdIntegerDateElement4), Unit.MONTHS).appendElement(stdIntegerDateElement4, EastAsianCalendar.getDayOfMonthRule(), Unit.DAYS).appendElement(stdIntegerDateElement5, EastAsianCalendar.getDayOfYearRule(), Unit.DAYS).appendElement(stdWeekdayElement, new WeekdayRule(getDefaultWeekmodel(), new ChronoFunction<ChineseCalendar, CalendarSystem<ChineseCalendar>>() { // from class: net.time4j.calendar.ChineseCalendar.1
            @Override // net.time4j.engine.ChronoFunction
            public CalendarSystem<ChineseCalendar> apply(ChineseCalendar chineseCalendar) {
                return ChineseCalendar.CALSYS;
            }
        }), Unit.DAYS).appendElement((ChronoElement) weekdayInMonthElement, WeekdayInMonthElement.getRule(weekdayInMonthElement)).appendElement((ChronoElement) CommonElements.RELATED_GREGORIAN_YEAR, (ElementRule) new RelatedGregorianYearRule(transformer, stdIntegerDateElement5)).appendUnit(Unit.CYCLES, EastAsianCalendar.getUnitRule(0), Unit.CYCLES.getLength(), Collections.singleton(Unit.YEARS)).appendUnit(Unit.YEARS, EastAsianCalendar.getUnitRule(1), Unit.YEARS.getLength(), Collections.singleton(Unit.CYCLES)).appendUnit(Unit.MONTHS, EastAsianCalendar.getUnitRule(2), Unit.MONTHS.getLength(), Collections.emptySet()).appendUnit(Unit.WEEKS, EastAsianCalendar.getUnitRule(3), Unit.WEEKS.getLength(), Collections.singleton(Unit.DAYS)).appendUnit(Unit.DAYS, EastAsianCalendar.getUnitRule(4), Unit.DAYS.getLength(), Collections.singleton(Unit.WEEKS)).appendExtension((ChronoExtension) new CommonElements.Weekengine(ChineseCalendar.class, stdIntegerDateElement4, stdIntegerDateElement5, getDefaultWeekmodel())).build();
    }

    private ChineseCalendar(int i, int i2, EastAsianMonth eastAsianMonth, int i3, long j) {
        super(i, i2, eastAsianMonth, i3, j);
    }

    public static ChineseCalendar ofNewYear(int i) {
        return of(EastAsianYear.forGregorian(i), EastAsianMonth.valueOf(1), 1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static ChineseCalendar ofQingMing(int i) {
        return (ChineseCalendar) ofNewYear(i).with(SolarTerm.MINOR_03_QINGMING_015.sinceLichun());
    }

    public static ChineseCalendar of(EastAsianYear eastAsianYear, EastAsianMonth eastAsianMonth, int i) {
        return of(eastAsianYear.getCycle(), eastAsianYear.getYearOfCycle().getNumber(), eastAsianMonth, i);
    }

    public static ChineseCalendar nowInSystemTime() {
        return (ChineseCalendar) SystemClock.inLocalView().now(axis());
    }

    public static boolean isValid(EastAsianYear eastAsianYear, EastAsianMonth eastAsianMonth, int i) {
        return CALSYS.isValid(eastAsianYear.getCycle(), eastAsianYear.getYearOfCycle().getNumber(), eastAsianMonth, i);
    }

    public static Weekmodel getDefaultWeekmodel() {
        return Weekmodel.of(Locale.CHINA);
    }

    static ChineseCalendar of(int i, int i2, EastAsianMonth eastAsianMonth, int i3) {
        return new ChineseCalendar(i, i2, eastAsianMonth, i3, CALSYS.transform(i, i2, eastAsianMonth, i3));
    }

    private Object writeReplace() {
        return new SPX(this);
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException {
        throw new InvalidObjectException("Serialization proxy required.");
    }

    public enum Unit implements ChronoUnit {
        CYCLES(1.893415507776E9d),
        YEARS(3.15569251296E7d),
        MONTHS(2551442.8775903997d),
        WEEKS(604800.0d),
        DAYS(86400.0d);

        private final transient double length;

        @Override // net.time4j.engine.ChronoUnit
        public double getLength() {
            return this.length;
        }

        @Override // net.time4j.engine.ChronoUnit
        public boolean isCalendrical() {
            return true;
        }

        Unit(double d) {
            this.length = d;
        }

        public int between(ChineseCalendar chineseCalendar, ChineseCalendar chineseCalendar2) {
            return (int) chineseCalendar.until(chineseCalendar2, (ChineseCalendar) this);
        }
    }

    private static class Transformer extends EastAsianCS<ChineseCalendar> {
        private static final long OFFSET_SWITCH_CHINA = -15705;
        private static final ZonalOffset OFFSET_OLD_CHINA = ZonalOffset.atLongitude(OffsetSign.AHEAD_OF_UTC, 116, 25, 0.0d);
        private static final ZonalOffset OFFSET_NEW_CHINA = ZonalOffset.ofHours(OffsetSign.AHEAD_OF_UTC, 8);

        @Override // net.time4j.calendar.EastAsianCS
        ZonalOffset getOffset(long j) {
            return j < OFFSET_SWITCH_CHINA ? OFFSET_OLD_CHINA : OFFSET_NEW_CHINA;
        }

        private Transformer() {
        }

        @Override // net.time4j.engine.CalendarSystem
        public List<CalendarEra> getEras() {
            return Arrays.asList(ChineseEra.values());
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // net.time4j.calendar.EastAsianCS
        public ChineseCalendar create(int i, int i2, EastAsianMonth eastAsianMonth, int i3, long j) {
            return new ChineseCalendar(i, i2, eastAsianMonth, i3, j);
        }

        @Override // net.time4j.calendar.EastAsianCS
        int[] getLeapMonths() {
            return ChineseCalendar.LEAP_MONTHS;
        }
    }

    private static class EraElement extends DisplayElement<ChineseEra> implements TextElement<ChineseEra>, ElementRule<ChineseCalendar, ChineseEra> {
        static final EraElement INSTANCE = new EraElement();
        private static final long serialVersionUID = -7868534502157983978L;

        private Object readResolve() throws ObjectStreamException {
            return INSTANCE;
        }

        @Override // net.time4j.engine.BasicElement, net.time4j.engine.ChronoElement
        public char getSymbol() {
            return 'G';
        }

        @Override // net.time4j.engine.ChronoElement
        public boolean isDateElement() {
            return true;
        }

        @Override // net.time4j.engine.BasicElement
        protected boolean isSingleton() {
            return true;
        }

        @Override // net.time4j.engine.ChronoElement
        public boolean isTimeElement() {
            return false;
        }

        private EraElement() {
            super("ERA");
        }

        @Override // net.time4j.engine.ChronoElement
        public Class<ChineseEra> getType() {
            return ChineseEra.class;
        }

        @Override // net.time4j.engine.ChronoElement
        public ChineseEra getDefaultMinimum() {
            return ChineseEra.QING_SHUNZHI_1644_1662;
        }

        @Override // net.time4j.engine.ChronoElement
        public ChineseEra getDefaultMaximum() {
            return ChineseEra.YELLOW_EMPEROR;
        }

        @Override // net.time4j.engine.ElementRule
        public ChineseEra getValue(ChineseCalendar chineseCalendar) {
            int relatedGregorianYear = getRelatedGregorianYear(chineseCalendar);
            if (relatedGregorianYear < 1662) {
                return ChineseEra.QING_SHUNZHI_1644_1662;
            }
            if (relatedGregorianYear < 1723) {
                return ChineseEra.QING_KANGXI_1662_1723;
            }
            if (relatedGregorianYear < 1736) {
                return ChineseEra.QING_YONGZHENG_1723_1736;
            }
            if (relatedGregorianYear < 1796) {
                return ChineseEra.QING_QIANLONG_1736_1796;
            }
            if (relatedGregorianYear < 1821) {
                return ChineseEra.QING_JIAQING_1796_1821;
            }
            if (relatedGregorianYear < 1851) {
                return ChineseEra.QING_DAOGUANG_1821_1851;
            }
            if (relatedGregorianYear < 1862) {
                return ChineseEra.QING_XIANFENG_1851_1862;
            }
            if (relatedGregorianYear < 1875) {
                return ChineseEra.QING_TONGZHI_1862_1875;
            }
            if (relatedGregorianYear < 1909) {
                return ChineseEra.QING_GUANGXU_1875_1909;
            }
            if (chineseCalendar.getDaysSinceEpochUTC() < -21873) {
                return ChineseEra.QING_XUANTONG_1909_1912;
            }
            return ChineseEra.YELLOW_EMPEROR;
        }

        @Override // net.time4j.engine.ElementRule
        public ChineseEra getMinimum(ChineseCalendar chineseCalendar) {
            return ChineseEra.QING_SHUNZHI_1644_1662;
        }

        @Override // net.time4j.engine.ElementRule
        public ChineseEra getMaximum(ChineseCalendar chineseCalendar) {
            return ChineseEra.YELLOW_EMPEROR;
        }

        @Override // net.time4j.engine.ElementRule
        /* renamed from: isValid, reason: avoid collision after fix types in other method */
        public boolean isValid2(ChineseCalendar chineseCalendar, ChineseEra chineseEra) {
            return getValue(chineseCalendar) == chineseEra;
        }

        @Override // net.time4j.engine.ElementRule
        /* renamed from: withValue, reason: avoid collision after fix types in other method */
        public ChineseCalendar withValue2(ChineseCalendar chineseCalendar, ChineseEra chineseEra, boolean z) {
            if (isValid2(chineseCalendar, chineseEra)) {
                return chineseCalendar;
            }
            if (chineseEra == null) {
                throw new IllegalArgumentException("Missing Chinese era.");
            }
            throw new IllegalArgumentException("Chinese era is read-only.");
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtFloor(ChineseCalendar chineseCalendar) {
            return ChineseCalendar.YEAR_OF_ERA;
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtCeiling(ChineseCalendar chineseCalendar) {
            return ChineseCalendar.YEAR_OF_ERA;
        }

        @Override // net.time4j.format.TextElement
        public void print(ChronoDisplay chronoDisplay, Appendable appendable, AttributeQuery attributeQuery) throws IOException, ChronoException {
            appendable.append(((ChineseEra) chronoDisplay.get(this)).getDisplayName((Locale) attributeQuery.get(Attributes.LANGUAGE, Locale.ROOT), (TextWidth) attributeQuery.get(Attributes.TEXT_WIDTH, TextWidth.WIDE)));
        }

        @Override // net.time4j.format.TextElement
        public ChineseEra parse(CharSequence charSequence, ParsePosition parsePosition, AttributeQuery attributeQuery) {
            Locale locale = (Locale) attributeQuery.get(Attributes.LANGUAGE, Locale.ROOT);
            boolean zBooleanValue = ((Boolean) attributeQuery.get(Attributes.PARSE_CASE_INSENSITIVE, Boolean.TRUE)).booleanValue();
            boolean zBooleanValue2 = ((Boolean) attributeQuery.get(Attributes.PARSE_PARTIAL_COMPARE, Boolean.FALSE)).booleanValue();
            TextWidth textWidth = (TextWidth) attributeQuery.get(Attributes.TEXT_WIDTH, TextWidth.WIDE);
            int index = parsePosition.getIndex();
            for (ChineseEra chineseEra : ChineseEra.values()) {
                String displayName = chineseEra.getDisplayName(locale, textWidth);
                int iMax = Math.max(Math.min(displayName.length() + index, charSequence.length()), index);
                if (iMax > index) {
                    String string = charSequence.subSequence(index, iMax).toString();
                    if (zBooleanValue) {
                        displayName = displayName.toLowerCase(locale);
                        string = string.toLowerCase(locale);
                    }
                    if (displayName.equals(string) || (zBooleanValue2 && displayName.startsWith(string))) {
                        parsePosition.setIndex(iMax);
                        return chineseEra;
                    }
                }
            }
            if (!locale.getLanguage().isEmpty() && !locale.getLanguage().equals("zh")) {
                for (ChineseEra chineseEra2 : ChineseEra.values()) {
                    String displayName2 = chineseEra2.getDisplayName(Locale.ROOT, textWidth);
                    int iMax2 = Math.max(Math.min(displayName2.length() + index, charSequence.length()), index);
                    if (iMax2 > index) {
                        String string2 = charSequence.subSequence(index, iMax2).toString();
                        if (zBooleanValue) {
                            displayName2 = displayName2.toLowerCase(Locale.ROOT);
                            string2 = string2.toLowerCase(Locale.ROOT);
                        }
                        if (displayName2.equals(string2) || (zBooleanValue2 && displayName2.startsWith(string2))) {
                            parsePosition.setIndex(iMax2);
                            return chineseEra2;
                        }
                    }
                }
            }
            parsePosition.setErrorIndex(index);
            return null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getRelatedGregorianYear(ChineseCalendar chineseCalendar) {
            return (((chineseCalendar.getCycle() - 1) * 60) + chineseCalendar.getYear().getNumber()) - 2637;
        }
    }

    private static class YearOfEraRule implements IntElementRule<ChineseCalendar> {
        private YearOfEraRule() {
        }

        @Override // net.time4j.engine.ElementRule
        public Integer getValue(ChineseCalendar chineseCalendar) {
            return Integer.valueOf(getInt(chineseCalendar));
        }

        @Override // net.time4j.engine.ElementRule
        public Integer getMinimum(ChineseCalendar chineseCalendar) {
            return Integer.valueOf(((ChineseEra) chineseCalendar.get(ChineseCalendar.ERA)).getMinYearOfEra());
        }

        @Override // net.time4j.engine.ElementRule
        public Integer getMaximum(ChineseCalendar chineseCalendar) {
            return Integer.valueOf(((ChineseEra) chineseCalendar.get(ChineseCalendar.ERA)).getMaxYearOfEra());
        }

        @Override // net.time4j.engine.ElementRule
        /* renamed from: isValid, reason: merged with bridge method [inline-methods] */
        public boolean isValid2(ChineseCalendar chineseCalendar, Integer num) {
            return num != null && isValid(chineseCalendar, num.intValue());
        }

        @Override // net.time4j.engine.ElementRule
        /* renamed from: withValue, reason: merged with bridge method [inline-methods] */
        public ChineseCalendar withValue2(ChineseCalendar chineseCalendar, Integer num, boolean z) {
            if (num == null) {
                throw new IllegalArgumentException("Missing year of era.");
            }
            return withValue(chineseCalendar, num.intValue(), z);
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtFloor(ChineseCalendar chineseCalendar) {
            return ChineseCalendar.MONTH_OF_YEAR;
        }

        @Override // net.time4j.engine.ElementRule
        public ChronoElement<?> getChildAtCeiling(ChineseCalendar chineseCalendar) {
            return ChineseCalendar.MONTH_OF_YEAR;
        }

        @Override // net.time4j.engine.IntElementRule
        public int getInt(ChineseCalendar chineseCalendar) {
            int relatedGregorianYear = EraElement.INSTANCE.getRelatedGregorianYear(chineseCalendar);
            return relatedGregorianYear < 1662 ? relatedGregorianYear - 1643 : relatedGregorianYear < 1723 ? relatedGregorianYear - 1661 : relatedGregorianYear < 1736 ? relatedGregorianYear - 1722 : relatedGregorianYear < 1796 ? relatedGregorianYear - 1735 : relatedGregorianYear < 1821 ? relatedGregorianYear - 1795 : relatedGregorianYear < 1851 ? relatedGregorianYear - 1820 : relatedGregorianYear < 1862 ? relatedGregorianYear - 1850 : relatedGregorianYear < 1875 ? relatedGregorianYear - 1861 : relatedGregorianYear < 1909 ? relatedGregorianYear - 1874 : chineseCalendar.getDaysSinceEpochUTC() < -21873 ? relatedGregorianYear - 1908 : relatedGregorianYear + 2698;
        }

        @Override // net.time4j.engine.IntElementRule
        public boolean isValid(ChineseCalendar chineseCalendar, int i) {
            ChineseEra chineseEra = (ChineseEra) chineseCalendar.get(ChineseCalendar.ERA);
            return i >= chineseEra.getMinYearOfEra() && i <= chineseEra.getMaxYearOfEra();
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // net.time4j.engine.IntElementRule
        public ChineseCalendar withValue(ChineseCalendar chineseCalendar, int i, boolean z) {
            if (isValid(chineseCalendar, i)) {
                return (ChineseCalendar) chineseCalendar.plus(i - getInt(chineseCalendar), Unit.YEARS);
            }
            throw new IllegalArgumentException("Invalid year of era: " + i);
        }
    }

    private static class Merger extends AbstractMergerEA<ChineseCalendar> {
        @Override // net.time4j.calendar.AbstractMergerEA, net.time4j.engine.ChronoMerger
        public /* bridge */ /* synthetic */ Object createFrom(ChronoEntity chronoEntity, AttributeQuery attributeQuery, boolean z, boolean z2) {
            return createFrom((ChronoEntity<?>) chronoEntity, attributeQuery, z, z2);
        }

        @Override // net.time4j.calendar.AbstractMergerEA, net.time4j.engine.ChronoMerger
        public /* bridge */ /* synthetic */ EastAsianCalendar createFrom(ChronoEntity chronoEntity, AttributeQuery attributeQuery, boolean z, boolean z2) {
            return createFrom((ChronoEntity<?>) chronoEntity, attributeQuery, z, z2);
        }

        Merger() {
            super(ChineseCalendar.class);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:19:0x0067  */
        @Override // net.time4j.calendar.AbstractMergerEA, net.time4j.engine.ChronoMerger
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public net.time4j.calendar.ChineseCalendar createFrom(net.time4j.engine.ChronoEntity<?> r4, net.time4j.engine.AttributeQuery r5, boolean r6, boolean r7) {
            /*
                r3 = this;
                net.time4j.engine.ChronoElement<java.lang.Integer> r5 = net.time4j.calendar.CommonElements.RELATED_GREGORIAN_YEAR
                int r5 = r4.getInt(r5)
                r6 = 1
                r7 = 0
                r0 = -2147483648(0xffffffff80000000, float:-0.0)
                if (r5 != r0) goto L69
                net.time4j.format.TextElement<net.time4j.calendar.CyclicYear> r5 = net.time4j.calendar.ChineseCalendar.YEAR_OF_CYCLE
                boolean r5 = r4.contains(r5)
                if (r5 == 0) goto L44
                net.time4j.format.TextElement<net.time4j.calendar.CyclicYear> r5 = net.time4j.calendar.ChineseCalendar.YEAR_OF_CYCLE
                java.lang.Object r5 = r4.get(r5)
                net.time4j.calendar.CyclicYear r5 = (net.time4j.calendar.CyclicYear) r5
                net.time4j.engine.ChronoElement<java.lang.Integer> r1 = net.time4j.calendar.ChineseCalendar.CYCLE
                int r1 = r4.getInt(r1)
                if (r1 != r0) goto L3f
                net.time4j.engine.ChronoElement<net.time4j.calendar.ChineseEra> r1 = net.time4j.calendar.ChineseCalendar.ERA
                boolean r1 = r4.contains(r1)
                if (r1 == 0) goto L67
                net.time4j.engine.ChronoElement<net.time4j.calendar.ChineseEra> r1 = net.time4j.calendar.ChineseCalendar.ERA
                java.lang.Object r1 = r4.get(r1)
                net.time4j.calendar.ChineseEra r1 = (net.time4j.calendar.ChineseEra) r1
                boolean r2 = r1.isQingDynasty()
                if (r2 == 0) goto L67
                net.time4j.calendar.EastAsianYear r5 = r5.inQingDynasty(r1)
                goto L6d
            L3f:
                net.time4j.calendar.EastAsianYear r5 = r5.inCycle(r1)
                goto L6d
            L44:
                net.time4j.engine.ChronoElement<net.time4j.calendar.ChineseEra> r5 = net.time4j.calendar.ChineseCalendar.ERA
                boolean r5 = r4.contains(r5)
                if (r5 == 0) goto L67
                net.time4j.calendar.StdCalendarElement<java.lang.Integer, net.time4j.calendar.ChineseCalendar> r5 = net.time4j.calendar.ChineseCalendar.YEAR_OF_ERA
                int r5 = r4.getInt(r5)
                if (r5 == r0) goto L67
                net.time4j.engine.ChronoElement<net.time4j.calendar.ChineseEra> r1 = net.time4j.calendar.ChineseCalendar.ERA
                java.lang.Object r1 = r4.get(r1)
                net.time4j.calendar.ChineseEra r1 = (net.time4j.calendar.ChineseEra) r1
                int r1 = r1.getStartAsGregorianYear()
                int r1 = r1 + r5
                int r1 = r1 - r6
                net.time4j.calendar.EastAsianYear r5 = net.time4j.calendar.EastAsianYear.forGregorian(r1)
                goto L6d
            L67:
                r5 = r7
                goto L6d
            L69:
                net.time4j.calendar.EastAsianYear r5 = net.time4j.calendar.EastAsianYear.forGregorian(r5)
            L6d:
                if (r5 != 0) goto L77
                net.time4j.engine.ValidationElement r5 = net.time4j.engine.ValidationElement.ERROR_MESSAGE
                java.lang.String r6 = "Cannot determine East Asian year."
                r4.with(r5, r6)
                return r7
            L77:
                net.time4j.format.TextElement<net.time4j.calendar.EastAsianMonth> r1 = net.time4j.calendar.ChineseCalendar.MONTH_OF_YEAR
                boolean r1 = r4.contains(r1)
                if (r1 == 0) goto L94
                net.time4j.format.TextElement<net.time4j.calendar.EastAsianMonth> r6 = net.time4j.calendar.ChineseCalendar.MONTH_OF_YEAR
                java.lang.Object r6 = r4.get(r6)
                net.time4j.calendar.EastAsianMonth r6 = (net.time4j.calendar.EastAsianMonth) r6
                net.time4j.calendar.StdCalendarElement<java.lang.Integer, net.time4j.calendar.ChineseCalendar> r1 = net.time4j.calendar.ChineseCalendar.DAY_OF_MONTH
                int r4 = r4.getInt(r1)
                if (r4 == r0) goto Lb1
                net.time4j.calendar.ChineseCalendar r4 = net.time4j.calendar.ChineseCalendar.of(r5, r6, r4)
                return r4
            L94:
                net.time4j.calendar.StdCalendarElement<java.lang.Integer, net.time4j.calendar.ChineseCalendar> r1 = net.time4j.calendar.ChineseCalendar.DAY_OF_YEAR
                int r4 = r4.getInt(r1)
                if (r4 == r0) goto Lb1
                if (r4 < r6) goto Lb1
                net.time4j.calendar.EastAsianMonth r7 = net.time4j.calendar.EastAsianMonth.valueOf(r6)
                net.time4j.calendar.ChineseCalendar r5 = net.time4j.calendar.ChineseCalendar.of(r5, r7, r6)
                int r4 = r4 - r6
                long r6 = (long) r4
                net.time4j.calendar.ChineseCalendar$Unit r4 = net.time4j.calendar.ChineseCalendar.Unit.DAYS
                net.time4j.engine.TimePoint r4 = r5.plus(r6, r4)
                net.time4j.calendar.ChineseCalendar r4 = (net.time4j.calendar.ChineseCalendar) r4
                return r4
            Lb1:
                return r7
            */
            throw new UnsupportedOperationException("Method not decompiled: net.time4j.calendar.ChineseCalendar.Merger.createFrom(net.time4j.engine.ChronoEntity, net.time4j.engine.AttributeQuery, boolean, boolean):net.time4j.calendar.ChineseCalendar");
        }
    }

    private static class SPX implements Externalizable {
        private static final int CHINESE = 14;
        private static final long serialVersionUID = 1;
        private transient Object obj;

        private Object readResolve() throws ObjectStreamException {
            return this.obj;
        }

        public SPX() {
        }

        SPX(Object obj) {
            this.obj = obj;
        }

        @Override // java.io.Externalizable
        public void writeExternal(ObjectOutput objectOutput) throws IOException {
            objectOutput.writeByte(14);
            writeChinese(objectOutput);
        }

        @Override // java.io.Externalizable
        public void readExternal(ObjectInput objectInput) throws IOException {
            if (objectInput.readByte() == 14) {
                this.obj = readChinese(objectInput);
                return;
            }
            throw new InvalidObjectException("Unknown calendar type.");
        }

        private void writeChinese(ObjectOutput objectOutput) throws IOException {
            EastAsianCalendar eastAsianCalendar = (EastAsianCalendar) this.obj;
            objectOutput.writeByte(eastAsianCalendar.getCycle());
            objectOutput.writeByte(eastAsianCalendar.getYear().getNumber());
            objectOutput.writeByte(eastAsianCalendar.getMonth().getNumber());
            objectOutput.writeBoolean(eastAsianCalendar.getMonth().isLeap());
            objectOutput.writeByte(eastAsianCalendar.getDayOfMonth());
        }

        private ChineseCalendar readChinese(ObjectInput objectInput) throws IOException {
            byte b = objectInput.readByte();
            byte b2 = objectInput.readByte();
            byte b3 = objectInput.readByte();
            boolean z = objectInput.readBoolean();
            byte b4 = objectInput.readByte();
            EastAsianMonth eastAsianMonthValueOf = EastAsianMonth.valueOf(b3);
            if (z) {
                eastAsianMonthValueOf = eastAsianMonthValueOf.withLeap();
            }
            return ChineseCalendar.of(b, b2, eastAsianMonthValueOf, b4);
        }
    }
}
