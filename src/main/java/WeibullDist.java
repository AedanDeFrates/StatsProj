import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class WeibullDist
{
    /**
     * Larger α → distribution spreads out more; typical values of x increase.
     * Smaller α → distribution is “squished” toward 0.
     */
    private double alpha = 1;
    /**
     * β < 1 → decreasing failure rate; most probability near 0.
     * β = 1 → reduces to an exponential distribution.
     * β > 1 → increasing failure rate; the PDF rises to a peak then decays.
     */
    private double beta = 1;

    public WeibullDist(double a, double b)
    {
        if (a <= 0){throw new IllegalArgumentException("Alpha must be grater than 0");}
        if (b <= 0){throw new IllegalArgumentException("Beta must be grater than 0");}

        alpha = a;
        beta = b;
    }
    public double[] getRawValues()
    {
        double[] xVals = new double[ZScoreMap.zScoresArray.length];
        double[] zScores = ZScoreMap.zScoresArray;
        HashMap<Double, Double> map = ZScoreMap.getMap();

        int i = 0;

        for (double z : zScores)
        {
            double x = alpha * Math.pow(-Math.log(1 - map.get(z)), 1/beta);
            xVals[i] = x;
            i++;
        }
        return xVals;
    }
    public double[] evaluatePDF()
    {
        double[] xVals = getRawValues();
        double[] valsPDF = new double[xVals.length];
        double coeff = beta/alpha;

        for (int i = 0; i < xVals.length; i++)
        {
            double x = xVals[i];
            if (x < 0) {valsPDF[i] = 0; continue;}

            valsPDF[i] = coeff * (Math.pow(x/alpha, beta -1)) * Math.exp(-(Math.pow(x/alpha, beta)));
        }

        return valsPDF;
    }

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

        File file = new File("/home/fhlic/Desktop/IdeaProjects/StatsProject/WeibullDistGraph.png");
        ChartUtils.saveChartAsPNG(file, chart, 800, 600);
    }
}
