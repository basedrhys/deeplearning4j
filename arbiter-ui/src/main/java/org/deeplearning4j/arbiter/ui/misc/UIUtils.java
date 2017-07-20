package org.deeplearning4j.arbiter.ui.misc;

import org.joda.time.DurationFieldType;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

/**
 * Created by Alex on 20/07/2017.
 */
public class UIUtils {

    /**
     *
     * @param max
     * @param min
     * @param nTick
     * @return
     */
    public static double[] niceRange(double max, double min, int nTick){
        double range = niceNum(max-min, false);
        double d = niceNum(range / (nTick-1), true );
        double graphMin = Math.floor(min/d)*d;
        double graphMax = Math.ceil(max/d)*d;


        return new double[]{graphMin, graphMax};
    }

    public static double niceNum(double x, boolean round){

        double exp = Math.floor(Math.log10(x));
        double f = x / Math.pow(10, exp);

        double nf;
        if(round){
            if(f < 1.5 ){
                nf = 1;
            } else if( f < 3){
                nf = 2;
            } else if( f < 7){
                nf = 5;
            } else {
                nf = 10;
            }
        } else {
            if(f <= 1 ){
                nf = 1;
            } else if( f <= 2){
                nf = 2;
            } else if( f <= 5){
                nf = 5;
            } else {
                nf = 10;
            }
        }

        return nf * Math.pow(10, exp);
    }

    public static String formatDuration(long durationMs){
        Period period = Period.seconds((int)(durationMs/1000L));
        Period p2 = period.normalizedStandard(PeriodType.yearMonthDayTime());

        PeriodFormatter formatter = new PeriodFormatterBuilder()
                .appendYears()
                .appendSuffix(" years, ")
                .appendMonths()
                .appendSuffix(" months, ")
                .appendDays()
                .appendSuffix(" days, ")
                .appendHours()
                .appendSuffix(" hours, ")
                .appendMinutes()
                .appendSuffix(" minutes, ")
                .appendSeconds()
                .appendSuffix(" seconds")
                .toFormatter();

        String formatted = formatter.print(p2);

        return formatted;
    }

}
