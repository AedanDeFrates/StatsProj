import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.IOException;

public class LogNormDist_Cont extends NormDist_Cont
{
    public LogNormDist_Cont(double mu, double sigma)
    {
        assert sigma > 0 : "must declare standard deviation greater than zero";

        mean =  mu;
        stdDev = sigma;
    }
    @Override
    public double[] getRawValues()
    {
        double[] xVals = new double[ZScoreMap.zScoresArray.length];
        double[] zScores = ZScoreMap.zScoresArray;
        int i = 0;

        for (double z : zScores)
        {
            double x = Math.exp((z*stdDev) + mean);
            xVals[i] = x;
            i++;

        }
        return xVals;
    }

    @Override
    public double[] evaluatePDF()
    {
        double[] xVals = getRawValues();
        double[] valsPDF = new double[xVals.length];
        double fnct1 = 1 / (stdDev * Math.sqrt(2*Math.PI));

        for (int i = 0; i < xVals.length; i++)
        {
            double x = xVals[i];
            if (x <= 0) {valsPDF[i] = fnct1; continue;}

            double exponent = -Math.pow(Math.log(x)-mean, 2) / (2* Math.pow(stdDev,2));
            valsPDF[i] = fnct1/x * Math.exp(exponent);
        }

        return valsPDF;
    }

    @Override
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

        File file = new File("/home/fhlic/Desktop/IdeaProjects/StatsProject/LogNormDistGraph.png");
        ChartUtils.saveChartAsPNG(file, chart, 800, 600);
    }
}
