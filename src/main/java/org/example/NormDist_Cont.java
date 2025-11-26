package org.example;

import java.util.HashMap;

public class NormDist_Cont
{
    protected double standardizedValue = 0;
    protected double mean = 0;
    protected double stdDev = 0;

    protected HashMap<Double, Double> zScoresProbMap;

    public NormDist_Cont()
    {
        zScoresProbMap = ZScoreMap.getMap();
    }
    public NormDist_Cont(double x, double mu, double sigma)
    {
        assert sigma > 0 : "must declare standard deviation greater than zero";

        standardizedValue = x;
        mean =  mu;
        stdDev = sigma;

        zScoresProbMap = ZScoreMap.getMap();
    }
    protected double findZScore()
    {
        double zScore = (standardizedValue -mean)/stdDev;
        zScore = zScore - zScore % 0.01;
        return zScore;
    }
    protected double boundZScore(double z)
    {
        z = z - z % 0.01;
        if(z >= 3.49 || z <= -3.49)
        {
            throw new ArithmeticException("invalid z variable (either z >= 3.49 or z <= -3.49)");
        }
        return z;
    }
    public double findMeanFromZScore(double z)
    {
        z = boundZScore(z);
        return (standardizedValue/stdDev) - z;
    }
    public double findStdDevFromZScore(double z)
    {
        z = boundZScore(z);
        return (standardizedValue - mean)/z;
    }
    public double findStandardizedValueFromZScore(double z)
    {
        z = boundZScore(z);
        return (z*stdDev) - mean;
    }
    public void setStandardizedValue(double x){standardizedValue = x;}
    public void setMean(double mu){mean = mu;}
    public void setStdDev(double sigma)
    {
        if(sigma == 0)
        {
            throw new  IllegalArgumentException("Cannot declare 0 as standard deviation");
        }
        else {stdDev = sigma;}
    }
}