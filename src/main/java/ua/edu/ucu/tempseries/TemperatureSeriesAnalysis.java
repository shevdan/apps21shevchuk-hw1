package ua.edu.ucu.tempseries;

import java.util.InputMismatchException;
import java.util.Objects;

import static java.lang.Math.*;

public class TemperatureSeriesAnalysis {

    double[] tempArray;
    int arrLength = 0;

    public TemperatureSeriesAnalysis() {
        this.tempArray = new double[0];
    }

    public TemperatureSeriesAnalysis(double[] temperatureSeries) {
        for (int i = 0; i < temperatureSeries.length; i++) {
            if (temperatureSeries[i] < -273.0) {
                throw new InputMismatchException();
            }
        }
        this.tempArray = temperatureSeries;
        this.arrLength = temperatureSeries.length;
    }

    public double[] getTempArray() {
        return tempArray;
    }

    public void increaseArr() {
        double[] arrCopy = new double[2*(arrLength + 1)];
        for (int i = 0; i < arrLength; i++){
            arrCopy[i] = tempArray[i];
        }
        this.tempArray = arrCopy;
    }

    void insert(double val) {
        if (Objects.equals(arrLength, tempArray.length)) {
            increaseArr();
        }
        tempArray[arrLength] = val;
        arrLength += 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arrLength; i++) {
            sb.append(tempArray[i]);
            sb.append(" ");
        }
        return sb.toString();
    }



    public double average() {
        if (arrLength < 1) {
            throw new IllegalArgumentException();
        }
        double avg = 0;
        for (int i = 0; i < arrLength; i++) {
            avg += tempArray[i];
        }
        return avg / arrLength;
    }

    public double deviation() {
        if (arrLength < 1) {
            throw new IllegalArgumentException();
        }
        double mean = average(), variance = 0.0;
        for (int i = 0; i < arrLength; i++) {
            variance += pow(tempArray[i] - mean, 2);
        }
        return sqrt(variance / arrLength);
    }

    private double extremeVal(boolean isMin) {


        Double xtrmVal;
        if (isMin)
            xtrmVal = Double.POSITIVE_INFINITY;

        else
            xtrmVal = Double.NEGATIVE_INFINITY;


        for (int i = 0; i < arrLength; i++) {
            if ((tempArray[i] < xtrmVal && isMin) || (tempArray[i] > xtrmVal && !isMin))
                xtrmVal = tempArray[i];
        }

        return xtrmVal;
    }

    public double min() {
        if (arrLength < 1) {
            throw new IllegalArgumentException();
        }
        return extremeVal(true);
    }

    public double max() {
        if (arrLength < 1) {
            throw new IllegalArgumentException();
        }
        return extremeVal(false);
    }

    public double findTempClosestToZero() {
        return findTempClosestToValue(0.0);
    }

    public double findTempClosestToValue(double tempValue) {
        if (arrLength < 1) {
            throw new IllegalArgumentException();
        }

        double closestVal = Double.POSITIVE_INFINITY;
        double dist = Double.POSITIVE_INFINITY;
        for (int i = 0; i < arrLength; i++) {
            if (abs(tempArray[i] - tempValue) < dist) {
                dist = abs(tempArray[i] - tempValue);
                closestVal = tempArray[i];
            }
            else if (Objects.equals(abs(tempArray[i] - tempValue), dist))
                closestVal = Double.max(tempArray[i], closestVal);

        }

        return  closestVal;
    }

    public double[] findSubArray(double tempValue, boolean isLess) {
        int n = 0;
        for (int i = 0; i < arrLength; i++) {
            if ((tempArray[i] < tempValue && isLess) ||
                    (tempArray[i] > tempValue && !isLess)) {
                n += 1;
            }
        }
        double[] subArr = new double[n];
        int idx = 0;
        for (int i = 0; i < arrLength; i++) {
            if ((tempArray[i] < tempValue && isLess) || (tempArray[i] > tempValue && !isLess)) {
                subArr[idx] = tempArray[i];
                idx += 1;
            }
        }
        return subArr;
    }

    public double[] findTempsLessThen(double tempValue) {
        if (arrLength < 1) {
            throw new IllegalArgumentException();
        }
        return findSubArray(tempValue, true);
    }

    public double[] findTempsGreaterThen(double tempValue) {
        if (tempArray.length < 1) {
            throw new IllegalArgumentException();
        }
        return findSubArray(tempValue, false);
    }

    public TempSummaryStatistics summaryStatistics() {
        return new TempSummaryStatistics(average(), deviation(), min(), max());
    }

    public int addTemps(double... temps) {
        for (double val: temps) {
            insert(val);
        }
        return 0;
    }
}
