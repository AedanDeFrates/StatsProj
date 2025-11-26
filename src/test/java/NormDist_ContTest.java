import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NormDist_ContTest
{
    @Test
    void testNormDist_Cont()
    {
        System.out.println(ZScoreMap.getLength());

        NormDist_Cont normDistCont = new NormDist_Cont(15.5, 18.4, 1.9);
        assertEquals(18.4, normDistCont.mean);
        assertEquals(1.9, normDistCont.stdDev);
        assertEquals(15.5, normDistCont.standardizedValue);
    }

}