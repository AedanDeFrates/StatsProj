
import java.util.HashMap;
import java.util.Set;

public class NormDist_Cont
{
    //Standardized Value represented as X in the formula (val with some probability)
    protected double standardizedValue = 0;
    //Mean is the known average of the continuous function
    protected double mean = 0;
    //Standardized Value is the known Standardized value of the continuous function
    protected double stdDev = 1;
    protected double curZscore = 0;

    protected HashMap<Double, Double> zScoresProbMap;

    /**
     * Constructs a NormalDistribution with no mean, stdDev, or standardizedValue.
     * */
    public NormDist_Cont()
    {
        zScoresProbMap = ZScoreMap.getMap();
    }
    /**
     * Constructs a NormalDistribution object with a value, a mean for the continuous distribution, and a stdDev
     * From the mean and the standard deviation, we get an understanding of the curve
     * From the standardized value the probability of the value occuring can be derrived from the function or from z-vals
     *
     * @param {double} x - The standardized value
     * @param {double} mu - The mean of the Normal Distribution
     * @param {double} sigma - The standard deviation of the Normal Distribution
     * @throws {exception} IllegalArgumentException - Throws if the standard deviation is less than or equal to 0
    * */
    public NormDist_Cont(double x, double mu, double sigma)
    {
        if (sigma <= 0) {throw new IllegalArgumentException("stdDev is less than or equal to zero");}
        standardizedValue = x;
        mean =  mu;
        stdDev = sigma;

        zScoresProbMap = ZScoreMap.getMap();
    }
    protected double[] mapToArray()
    {
        double[] xVals = new double[zScoresProbMap.size()];
        Set<Double> zScores = zScoresProbMap.keySet();
        int i = 0;
        for(double z : zScores)
        {
            double x = (z*stdDev) - mean;
            xVals[i] = x;
            i++;
        }
        return new double[]{};
    }

    public void setStandardizedValue(double x){standardizedValue = x;}
    public void setMean(double mu){mean = mu;}
    public void setStdDev(double sigma)
    {
        if(sigma <= 0) {throw new  IllegalArgumentException("stdDev is less than or equal to zero");}
        else {stdDev = sigma;}
    }

    public double getStandardizedValue(){return standardizedValue;}
    public double getMean(){return mean;}
    public double getStdDev(){return stdDev;}
}