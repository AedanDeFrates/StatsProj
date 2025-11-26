

public class LogNormDist_Cont extends NormDist_Cont
{
    public LogNormDist_Cont(double x, double mu, double sigma)
    {
        assert sigma > 0 : "must declare standard deviation greater than zero";
        assert x > 0 : "ln(x) cannot be evaluated less than zero";

        standardizedValue = x;
        mean =  mu;
        stdDev = sigma;

        zScoresProbMap = ZScoreMap.getMap();
    }
    @Override
    public double findZScore()
    {
        double zScore = (standardizedValue -mean)/stdDev;
        zScore = zScore - zScore % 0.01;
        return zScore;
    }
    @Override
    public double findMeanFromZScore(double z)
    {
        z = boundZScore(z);
        return (Math.log(standardizedValue)/stdDev) - z;
    }
    @Override
    public double findStdDevFromZScore(double z)
    {
        z = boundZScore(z);
        return (Math.log(standardizedValue) - mean)/z;
    }
    @Override
    public double findStandardizedValueFromZScore(double z)
    {
        z = boundZScore(z);
        return Math.pow(Math.E, (z*stdDev) - mean);
    }
    @Override
    public void setStandardizedValue(double x)
    {
        if(x < 0)
        {
            throw new ArithmeticException("Cannot solve ln(x) if x is 0 or less than 0");
        }
        else {stdDev = x;}
    }
}
