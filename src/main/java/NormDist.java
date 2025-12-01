import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.IOException;

public class NormDist
{
    //Mean is the known average of the continuous function
    protected double mean = 0;
    //Standar d Deviation or stdDev or sdis the spread of the disribution
    protected double stdDev = 1;

    /**
     * Constructs a NormalDistribution with a mean of 0, a stdDev of 1, and no standardizedValue.
     * */
    public NormDist() {}
    /**
     * Constructs a NormalDistribution object with a value, a mean for the continuous distribution, and a stdDev
     * From the mean and the standard deviation, we get an understanding of the curve
     * From the standardized value the probability of the value occurring can be derived from the function or from z-vals
     *
     * @param mu {double}  - The mean of the Normal Distribution
     * @param sigma {double}  - The standard deviation of the Normal Distribution
     * @throws IllegalArgumentException {exception} - Throws if the standard deviation is less than or equal to 0
    * */
    public NormDist(double mu, double sigma)
    {
        if (sigma <= 0) {throw new IllegalArgumentException("stdDev is less than or equal to zero");}

        mean =  mu;
        stdDev = sigma;
    }

    /**
     * This method evaluates, from zScores -3.49 to 3.49, all possible standardized values
     * in this range. If this method is called with the default values for mean and stdDev then
     * the function simply returns an array of Z-scores.
     *
     * @return xVals {double[]} - Set of all possible x values for a standard deviation
     */
    public double[] getRawValues()
    {
        if(mean == 0 && stdDev == 1) {return ZScoreMap.zScoresArray;}

        double[] xVals = new double[ZScoreMap.zScoresArray.length];
        double[] zScores = ZScoreMap.zScoresArray;
        int i = 0;

        for(double z : zScores)
        {
            double x = (z*stdDev) + mean;
            xVals[i] = x;
            i++;
        }
        return xVals;
    }

    /**
     * This method is a helper function to create a visual representation of the standard deviation.
     * Acting as a y Axis for the graph, the probabilities returned by the function correspond to a raw value from the z-score array.
     * (z-scores limited on the domain from -3.49 to 3.49)
     *
     * @return valsPDF {double[]} - Set of probabilities, each corresponding to a raw value.
     */
    public double[] evaluatePDF()
    {
        double fnct1 = 1 / (stdDev * Math.sqrt(2 * Math.PI));
        double[] xVals = getRawValues();
        double[] valsPDF = new double[xVals.length];

        for (int i = 0; i < xVals.length; i++)
        {
            double x = xVals[i];
            double exponent = -Math.pow(x - mean, 2) / (2 * Math.pow(stdDev, 2));
            valsPDF[i] = fnct1 * Math.exp(exponent);
        }

        return valsPDF;
    }

    /**
     * This function maps the function as an XY relationship, and creates a new file, which is a png of a graph of
     * the aproxomate function.
     *
     * @param title {String} - header for the graph
     * @param xAxis {String} - x-axis header for the graph
     * @throws IOException - mandatory throws
     */
    public void Graph(String title, String xAxis) throws IOException {
        double[] xVals = getRawValues();
        double[] probs = evaluatePDF();

        if(xVals.length != probs.length){throw new IllegalArgumentException("xVals.length != probs.length");}

        XYSeries series = new XYSeries("Probability of Values");
        for(int i = 0; i < xVals.length; i++)
        {
            series.add(xVals[i], probs[i]);
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        JFreeChart chart = ChartFactory.createXYLineChart(title, xAxis, "Probability", dataset);

        File file = new File("/home/fhlic/Desktop/IdeaProjects/StatsProject/NormDistGraph.png");
        ChartUtils.saveChartAsPNG(file, chart, 800, 600);
    }

    public void setMean(double mu){mean = mu;}
    public void setStdDev(double sigma) {
        if(sigma <= 0) {throw new  IllegalArgumentException("stdDev is less than or equal to zero");}
        else {stdDev = sigma;}
    }
    public double getMean(){return mean;}
    public double getStdDev(){return stdDev;}
}