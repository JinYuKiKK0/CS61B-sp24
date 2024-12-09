package ngrams;

import java.util.*;

/**
 * An object for mapping a year number (e.g. 1996) to numerical data. Provides
 * utility methods useful for data analysis.
 *
 * @author Josh Hug
 */
public class TimeSeries extends TreeMap<Integer, Double> {

    /** If it helps speed up your code, you can assume year arguments to your NGramMap
     * are between 1400 and 2100. We've stored these values as the constants
     * MIN_YEAR and MAX_YEAR here. */
    public static final int MIN_YEAR = 1400;
    public static final int MAX_YEAR = 2100;

    /**
     * Constructs a new empty TimeSeries.
     */
    public TimeSeries() {
        super();
    }

    /**
     * Creates a copy of TS, but only between STARTYEAR and ENDYEAR,
     * inclusive of both end points.
     */
    public TimeSeries(TimeSeries ts, int startYear, int endYear) {
        super();
        // TODO: Fill in this constructor.
       for(int i = startYear;i<=endYear;i++){
           Double dataPoint = ts.get(i);
           this.put(i,dataPoint);
       }
    }

    /**
     * Returns all years for this TimeSeries (in any order).
     */
    public List<Integer> years() {
        // TODO: Fill in this method.
        Set<Integer> yearSet = this.keySet();
        List<Integer> yearList = new ArrayList<>();
        for (Integer integer : yearSet) {
            yearList.add(integer);
        }
        return yearList;
    }

    /**
     * Returns all data for this TimeSeries (in any order).
     * Must be in the same order as years().
     */
    public List<Double> data() {
        // TODO: Fill in this method.
        List<Integer> years = this.years();
        List<Double> dataList = new ArrayList<>();
        for (Integer year : years) {
            Double data = this.get(year);
            dataList.add(data);
        }
        return dataList;
    }

    /**
     * Returns the year-wise sum of this TimeSeries with the given TS. In other words, for
     * each year, sum the data from this TimeSeries with the data from TS. Should return a
     * new TimeSeries (does not modify this TimeSeries).
     *
     * If both TimeSeries don't contain any years, return an empty TimeSeries.
     * If one TimeSeries contains a year that the other one doesn't, the returned TimeSeries
     * should store the value from the TimeSeries that contains that year.
     */
    public TimeSeries plus(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries total = new TimeSeries();
        total.putAll(this);
        for (Map.Entry<Integer, Double> integerDoubleEntry : ts.entrySet()) {
            int year = integerDoubleEntry.getKey();
            double data = integerDoubleEntry.getValue();

            if(total.containsKey(year)){
                total.put(year,get(year)+data);
            }else {
                total.put(year,data);
            }
        }
        return total;
    }

    /**
     * Returns the quotient of the value for each year this TimeSeries divided by the
     * value for the same year in TS. Should return a new TimeSeries (does not modify this
     * TimeSeries).     this/ts
     *
     * If TS is missing a year that exists in this TimeSeries, throw an
     * IllegalArgumentException.
     * If TS has a year that is not in this TimeSeries, ignore it.
     */
    public TimeSeries dividedBy(TimeSeries ts) {
        // TODO: Fill in this method.
        TimeSeries quotient = new TimeSeries();
        quotient.putAll(this);
        for (Map.Entry<Integer, Double> entry : quotient.entrySet()) {
            Integer year = entry.getKey();
            if(entry.getValue() == null){
                continue;
            }
            Double data = entry.getValue();
            if(ts.containsKey(year)){
                quotient.put(year,data/ts.get(year));
            }else throw new IllegalArgumentException();
        }
        return quotient;
    }

    // TODO: Add any private helper methods.

    // TODO: Remove all TODO comments before submitting.
}
