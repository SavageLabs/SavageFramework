package net.prosavage.baseplugin.reference;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {

    private static Pattern timePattern = Pattern.compile("(?:([0-9]+)\\s*y[a-z]*[,\\s]*)?(?:([0-9]+)\\s*mo[a-z]*[,\\s]*)?(?:([0-9]+)\\s*w[a-z]*[,\\s]*)?(?:([0-9]+)\\s*d[a-z]*[,\\s]*)?(?:([0-9]+)\\s*h[a-z]*[,\\s]*)?(?:([0-9]+)\\s*m[a-z]*[,\\s]*)?(?:([0-9]+)\\s*(?:s[a-z]*)?)?", 2);

    public static String removeTimePattern(String input) {
        return timePattern.matcher(input).replaceFirst("").trim();
    }

    public static long parseDateDiff(String time, boolean future) throws Exception {
        Matcher m = timePattern.matcher(time);

        int years = 0;
        int months = 0;
        int weeks = 0;
        int days = 0;
        int hours = 0;
        int minutes = 0;
        int seconds = 0;

        boolean found = false;

        while (m.find()) {
            if (m.group() == null || m.group().isEmpty()) continue;

            for (int c = 0; c < m.groupCount(); ++c) {
                if (m.group(c) == null || m.group(c).isEmpty()) continue;

                found = true;
                break;
            }

            if (!found) continue;

            if (m.group(1) != null && !m.group(1).isEmpty()) years = Integer.parseInt(m.group(1));
            if (m.group(2) != null && !m.group(2).isEmpty()) months = Integer.parseInt(m.group(2));
            if (m.group(3) != null && !m.group(3).isEmpty()) weeks = Integer.parseInt(m.group(3));
            if (m.group(4) != null && !m.group(4).isEmpty()) days = Integer.parseInt(m.group(4));
            if (m.group(5) != null && !m.group(5).isEmpty()) hours = Integer.parseInt(m.group(5));
            if (m.group(6) != null && !m.group(6).isEmpty()) minutes = Integer.parseInt(m.group(6));
            if (m.group(7) == null || m.group(7).isEmpty()) break;

            seconds = Integer.parseInt(m.group(7));
            break;
        }

        if (!found) throw new Exception("Illegal Date");

        GregorianCalendar var13 = new GregorianCalendar();

        if (years > 0) var13.add(1, years * (future ? 1 : -1));
        if (months > 0) var13.add(2, months * (future ? 1 : -1));
        if (weeks > 0) var13.add(3, weeks * (future ? 1 : -1));
        if (days > 0) var13.add(5, days * (future ? 1 : -1));
        if (hours > 0) var13.add(11, hours * (future ? 1 : -1));
        if (minutes > 0) var13.add(12, minutes * (future ? 1 : -1));
        if (seconds > 0) var13.add(13, seconds * (future ? 1 : -1));

        GregorianCalendar max = new GregorianCalendar();

        max.add(1, 10);
        return var13.after(max) ? max.getTimeInMillis() : var13.getTimeInMillis();
    }

    static int dateDiff(int type, Calendar fromDate, Calendar toDate, boolean future) {
        int diff = 0;
        long savedDate = fromDate.getTimeInMillis();

        while (future && !fromDate.after(toDate) || !future && !fromDate.before(toDate)) {
            savedDate = fromDate.getTimeInMillis();

            fromDate.add(type, future ? 1 : -1);

            ++diff;
        }

        fromDate.setTimeInMillis(savedDate);
        return --diff;
    }

    public static String formatDateDiff(long date) {
        GregorianCalendar c = new GregorianCalendar();

        c.setTimeInMillis(date);

        GregorianCalendar now = new GregorianCalendar();

        return DateUtil.formatDateDiff(now, c);
    }

    public static String formatDateDiff(Calendar fromDate, Calendar toDate) {
        boolean future = false;

        if (toDate.equals(fromDate)) return "now";
        if (toDate.after(fromDate)) future = true;

        StringBuilder sb = new StringBuilder();

        int[] types = new int[]{1, 2, 5, 11, 12, 13};

        String[] names = new String[]{"year", "years", "month", "months", "day", "days", "hour", "hours", "minute", "minutes", "second", "seconds"};
        int accuracy = 0;

        for (int i = 0; i < types.length && accuracy <= 2; ++i) {
            int diff = DateUtil.dateDiff(types[i], fromDate, toDate, future);
            if (diff <= 0) continue;

            ++accuracy;

            sb.append(" ").append(diff).append(" ").append(names[i * 2 + (diff > 1 ? 1 : 0)]);
        }

        return sb.length() == 0 ? "now" : sb.toString().trim();
    }

    public static String formatSimplifiedDateDiff(long date) {
        GregorianCalendar c = new GregorianCalendar();

        c.setTimeInMillis(date);

        GregorianCalendar now = new GregorianCalendar();

        return DateUtil.formatSimplifiedDateDiff(now, c);
    }

    public static String formatSimplifiedDateDiff(Calendar fromDate, Calendar toDate) {
        boolean future = false;

        if (toDate.equals(fromDate)) return "now";
        if (toDate.after(fromDate)) future = true;

        StringBuilder sb = new StringBuilder();

        int[] types = new int[]{1, 2, 5, 11, 12, 13};

        String[] names = new String[]{"y", "y", "m", "m", "d", "d", "h", "h", "m", "m", "s", "s"};
        int accuracy = 0;

        for (int i = 0; i < types.length && accuracy <= 2; ++i) {
            int diff = DateUtil.dateDiff(types[i], fromDate, toDate, future);

            if (diff <= 0) continue;

            ++accuracy;
            sb.append(" ").append(diff).append(names[i * 2 + (diff > 1 ? 1 : 0)]);
        }

        return sb.length() == 0 ? "now" : sb.toString().trim();
    }

    public static String readableTime(long time) {
        int second = 1000;
        int minute = 60 * second;
        int hour = 60 * minute;
        int day = 24 * hour;
        long ms = time;

        StringBuilder text = new StringBuilder();

        if (time > day) {
            text.append(time / day).append("d ");
            ms = time % day;
        }

        if (ms > hour) {
            text.append(ms / hour).append("h ");
            ms %= hour;
        }

        if (ms > minute) {
            text.append(ms / minute).append("m ");
            ms %= minute;
        }

        if (ms > second) text.append(ms / second).append("s ");

        return text.toString();
    }

    public static String readableTime(BigDecimal time) {
        String text = "";

        if (time.doubleValue() <= 60.0) {
            time = time.add(BigDecimal.valueOf(0.1));

            return text + " " + time + "s";
        }

        if (time.doubleValue() <= 3600.0) {
            int minutes = time.intValue() / 60;
            int seconds = time.intValue() % 60;

            DecimalFormat formatter = new DecimalFormat("00");

            return text + " " + formatter.format(minutes) + ":" + formatter.format(seconds) + "m";
        }

        return null;
    }
}
