package org.example;

public class UnifDist_Dscrt
{
    public double[] values;
    public int k;
    public double mean = 0;
    public double variance = 0;
    public double probability = 0;

    public UnifDist_Dscrt(double[] vals)
    {
        if (vals == null) {throw new NullPointerException("vals is null");}
        else if (vals.length == 0) {values = new double[]{0};}
        else {values = vals;}

        k = values.length;

        if(!calculateMean())
        {
            throw new RuntimeException("Mean values are not calculated");
        }
        if (!calculateVariance())
        {
            throw new RuntimeException("Variance values are not calculated");
        }
        if(!calculateProbability())
        {
            throw new RuntimeException("Probability values are not calculated");
        }
    }
    private boolean calculateMean()
    {
        if (mean == 0)
        {
            for (double v : values)
            {
                mean +=v;
            }
            mean = mean/k;
            return true;
        }
        else {return false;}
    }
    private boolean calculateVariance()
    {
        if (variance == 0)
        {
            for (double v : values)
            {
                variance += (Math.pow(v-mean, 2));
            }
            variance = variance /k;
            return true;
        }
        else {return false;}
    }
    private boolean calculateProbability()
    {
        if (probability == 0)
        {
            probability = 1.0/k;
            return true;
        }
        else {return false;}
    }

    public double getVariance(){return variance;}
    public double getMean(){return mean;}
    public double getProbability(){return probability;}
    public double[] getValues(){return values;}
    public int getK(){return k;}
}