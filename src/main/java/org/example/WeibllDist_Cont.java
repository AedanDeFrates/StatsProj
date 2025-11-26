package org.example;

import java.util.HashMap;

public class WeibllDist_Cont
{
    public double variable;
    public double alpha;
    public double beta;

    public double mu;
    public double sigma;

    public HashMap<Double, Double> GammaVals;

    public WeibllDist_Cont(double x, double a, double b)
    {
        assert a < 0: "Invalid parameter. Scaler parameter alpha must be greater than zero";
        assert b < 0: "Invalid parameter. Scaler parameter beta must be greater than zero";
        assert x < 0: "Invalid parameter. Variable x must be greater than zero";

        variable = x;
        alpha = a;
        beta = b;
    }
}
