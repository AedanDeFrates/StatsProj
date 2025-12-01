
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)
    {
        NormDist normDistCont = new NormDist(100, 15);
        try
        {
            normDistCont.Graph("Intelligence Quotient", "IQ");
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        LogNormDist logNormDistCont = new LogNormDist(10, 1);
        try
        {
            logNormDistCont.Graph("City Population", "Number of People");
        } catch  (Exception e)
        {
            e.printStackTrace();
        }
        WeibullDist weibullDistCont = new WeibullDist(100, 0.99);
        try
        {
            weibullDistCont.Graph("fart", "poop");
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}