package ua.edu.ucu.tempseries;

import lombok.Getter;

@Getter
public class TempSummaryStatistics {
    private double avgTemp;
    private double devTemp;
    private double minTemp;
    private double maxTemp;

    public TempSummaryStatistics(double avg,double dev,double min,double max) {
        this.avgTemp = avg;
        this.devTemp = dev;
        this.minTemp = min;
        this.maxTemp = max;
    }

}
