import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NormDist_ContTest
{
    @Test
    void testNormDist_Cont()
    {
        System.out.println(ZScoreMap.getLength());

        NormDist_Cont normDistCont = new NormDist_Cont( 18.4, 1.9);
        assertEquals(18.4, normDistCont.mean);
        assertEquals(1.9, normDistCont.stdDev);
    }

    @Test
    void testMapToArray()
    {
        NormDist_Cont iqDist = new NormDist_Cont(100, 15);
        double[] mapToArray = iqDist.getRawValues();

        for(double value : mapToArray)
        {
            System.out.print(value + " ");
        }
    }
    @Test
    void testNormDist_Cont2()
    {
        NormDist_Cont normDistCont = new NormDist_Cont(100, 15);
        normDistCont.getRawValues();
    }

}