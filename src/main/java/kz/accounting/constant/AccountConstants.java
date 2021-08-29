package kz.accounting.constant;

import java.math.RoundingMode;
import java.time.ZoneOffset;
import java.util.TimeZone;

public class AccountConstants {

    public static final RoundingMode ROUNDING_MODE = RoundingMode.HALF_UP;

    public static final ZoneOffset TIME_ZONE = ZoneOffset.of("+06:00");

    public static final TimeZone REAL_TIME_ZONE = TimeZone.getTimeZone("UTC");
}
