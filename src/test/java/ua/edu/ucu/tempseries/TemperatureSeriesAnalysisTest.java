package ua.edu.ucu.tempseries;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TemperatureSeriesAnalysisTest {

    @Test
    public void testAverageWithOneElementArray() {
        // setup input data and expected result
        double[] temperatureSeries = {-1.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = -1.0;

        // call tested method
        double actualResult = seriesAnalysis.average();

        // compare expected result with actual result
        assertEquals(expResult, actualResult, 0.00001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAverageWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.average();
    }

    @Test
    public void testAverage() {
        double[] temperatureSeries = {3.0, -5.0, 1.0, 5.0};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);
        double expResult = 1.0;

        double actualResult = seriesAnalysis.average();
        
        assertEquals(expResult, actualResult, 0.00001);        
    }

    @Test
    public void testDeviation(){
        double[] arr = {3, 4, 5, 6, 7, 8, 9};
        double sd = 2.0;
        TemperatureSeriesAnalysis tmp = new TemperatureSeriesAnalysis(arr);
        assertEquals(sd, tmp.deviation(), 0.000001);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeviationWithEmptyArray() {
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis();

        // expect exception here
        seriesAnalysis.deviation();
    }

    @Test
    public void testMin(){
        double[] arr = {3, 4, 5, 6, 7, 8, 9};
        double minVal = 3.0;
        TemperatureSeriesAnalysis tmp = new TemperatureSeriesAnalysis(arr);
        assertEquals(minVal, tmp.min(), 0.000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMinWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.min();
    }

    @Test
    public void testMax(){
        double[] arr = {3, 4, 5, 6, 7, 8, 9};
        double maxVal = 9.0;
        TemperatureSeriesAnalysis tmp = new TemperatureSeriesAnalysis(arr);
        assertEquals(maxVal, tmp.max(), 0.000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMaxWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.max();
    }

    @Test
    public void testFindTempClosestToZero(){
        double[] arr = {-0.2, 0.2, 3, 4, 10, 6, 7, 8, 9};
        double expected = 0.2;
        TemperatureSeriesAnalysis tmp = new TemperatureSeriesAnalysis(arr);
        assertEquals(expected, tmp.findTempClosestToZero(), 0.000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToZeroWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.findTempClosestToZero();
    }

    @Test
    public void testFindTempClosestToValue(){
        double[] arr = {-0.2, 0.2, 3, 4, 10, 6, 7, 8, 9};
        double expected = 6.0;
        TemperatureSeriesAnalysis tmp = new TemperatureSeriesAnalysis(arr);
        assertEquals(expected, tmp.findTempClosestToValue(5), 0.000001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempClosestToValueWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.findTempClosestToValue(5);
    }

    @Test
    public void testFindTempsLessThen(){
        double[] arr = {-0.2, 0.2, 3, 4, 10, 6, 7, 8, 9};
        double[] expected = {-0.2, 0.2, 3.0, 4.0};
        TemperatureSeriesAnalysis tmp = new TemperatureSeriesAnalysis(arr);
        double[] actual = tmp.findTempsLessThen(5.0);

        for (int i = 0; i < actual.length; i++){
            assertEquals(expected[i], actual[i], 0.00001);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsLessThenWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.findTempsLessThen(5);
    }

    @Test
    public void testFindTempsGreaterThen(){
        double[] arr = {-0.2, 0.2, 3, 4, 10, 6, 7, 8, 9};
        double[] expected = {10.0, 6.0, 7.0, 8.0, 9.0};
        TemperatureSeriesAnalysis tmp = new TemperatureSeriesAnalysis(arr);
        double[] actual = tmp.findTempsGreaterThen(5.0);

        for (int i = 0; i < actual.length; i++){
            assertEquals(expected[i], actual[i], 0.00001);
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindTempsGreaterThenWithEmptyArray() {
        double[] temperatureSeries = {};
        TemperatureSeriesAnalysis seriesAnalysis = new TemperatureSeriesAnalysis(temperatureSeries);

        // expect exception here
        seriesAnalysis.findTempsGreaterThen(5);
    }

    @Test
    public void testSummaryStatistics(){
        double[] arr = { 3, 4, 5, 6, 7, 8, 9};
        TemperatureSeriesAnalysis tmp = new TemperatureSeriesAnalysis(arr);
        TempSummaryStatistics stat = tmp.summaryStatistics();
        assertEquals(6.0, stat.getAvgTemp(), 0.00001);
        assertEquals(2.0, stat.getDevTemp(), 0.00001);
        assertEquals(3.0, stat.getMinTemp(), 0.00001);
        assertEquals(9.0, stat.getMaxTemp(), 0.00001);
    }

    @Test
    public void testAddTemps(){
        TemperatureSeriesAnalysis tmp = new TemperatureSeriesAnalysis();
        tmp.addTemps(1, 2, 3, 4, 5);
        assertEquals(5, tmp.getArrLength());
    }

}
