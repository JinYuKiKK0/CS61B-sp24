package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 * <p>
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    // TODO: Add any necessary static/instance variables.
    HashMap<String, TimeSeries> wordsMap;//存储word与时间序列的映射
    TimeSeries countsMap;//存储各个年份的总卷数

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        // TODO: Fill in this constructor. See the "NGramMap Tips" section of the spec for help.
        wordsMap = new HashMap<>();
        countsMap = new TimeSeries();
        In words = new In(wordsFilename);
        String readLine = words.readLine();
        String[] wordsSplit = readLine.split("\t");
        String curWord = wordsSplit[0];
        TimeSeries wordTs = new TimeSeries();
        while (words.hasNextLine()) {
            readLine = words.readLine();
            wordsSplit = readLine.split("\t");
            String word = wordsSplit[0];
            String year = wordsSplit[1];
            String times = wordsSplit[2];
            if (word.equals(curWord)) {
                wordTs.put(Integer.parseInt(year), Double.parseDouble(times));
                wordsMap.put(word, wordTs);
            } else {
                TimeSeries newWordTs = new TimeSeries();
                newWordTs.put(Integer.parseInt(year), Double.parseDouble(times));
                wordsMap.put(word, wordTs);
                curWord = word;
                wordTs = newWordTs;
            }

        }
        In counts = new In(countsFilename);
        while (counts.hasNextLine()) {
            readLine = counts.readLine();
            String[] countsSplit = readLine.split(",");
            String year = countsSplit[0];
            String count = countsSplit[1];
            countsMap.put(Integer.parseInt(year),Double.parseDouble(count));
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     * 提供 STARTYEAR 到 ENDYEAR（包含两端）期间 WORD 的历史记录。
     * 返回的 TimeSeries 应为副本，而非指向此 NGramMap 的 TimeSeries 的链接。
     * 此函数返回的对象所做的更改不应影响NGramMap。这也称为“防御性复制”。
     * 如果该词不在数据文件中，返回一个空的 TimeSeries。
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        if (wordsMap.containsKey(word)) {
            TimeSeries wordSeries = wordsMap.get(word);
            TimeSeries ts = new TimeSeries(wordSeries, startYear, endYear);
            return ts;
        } else return new TimeSeries();
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        // TODO: Fill in this method.
        if (wordsMap.containsKey(word)) {
            TimeSeries wordSeries = wordsMap.get(word);
            List<Integer> years = wordSeries.years();
            return new TimeSeries(wordSeries, years.get(0), years.get(years.size() - 1));
        } else return new TimeSeries();
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     * 返回总卷数中每年记录的总数的副本
     */
    public TimeSeries totalCountHistory() {
        // TODO: Fill in this method.
        return new TimeSeries(countsMap,MIN_YEAR,MAX_YEAR);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     * 提供一个 TimeSeries，包含 WORD 在每年出现的相对频率，从 STARTYEAR 开始
     * 并且 ENDYEAR，包含两端。如果该词不在数据文件中，则返回空时间序列
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        // TODO: Fill in this method.
        if(wordsMap.containsKey(word)){
            TimeSeries wordSeries = new TimeSeries(wordsMap.get(word),startYear,endYear);
            return wordSeries.dividedBy(countsMap);
        }
        return new TimeSeries();
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        // TODO: Fill in this method.
        if(wordsMap.containsKey(word)){
            TimeSeries wordSeries = wordsMap.get(word);
            return wordSeries.dividedBy(countsMap);
        }
        return new TimeSeries();
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     * 提供 WORDS 中所有词语在 STARTYEAR 至ENDYEAR，包含两端 之间每年总的相对频率。
     * 如果某个词不在该时间范围内，则忽略它
     */
    public TimeSeries summedWeightHistory(Collection<String> words, int startYear, int endYear) {
        // TODO: Fill in this method.
        TimeSeries wordSeries = new TimeSeries();
        TimeSeries temp;
        for (String word : words) {
            temp = weightHistory(word,startYear,endYear);
            if(temp != null && !temp.isEmpty()){
                wordSeries = wordSeries.plus(temp);
            }

        }
        return wordSeries;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        // TODO: Fill in this method.
        TimeSeries wordSeries = new TimeSeries();
        TimeSeries temp;
        for (String word : words) {
            temp = weightHistory(word);
            wordSeries.plus(temp);
        }
        return wordSeries;
    }

    // TODO: Add any private helper methods.
    // TODO: Remove all TODO comments before submitting.
}
