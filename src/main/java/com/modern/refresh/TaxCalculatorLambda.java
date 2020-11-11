package com.modern.refresh;

import java.util.function.DoubleUnaryOperator;

public class TaxCalculatorLambda {
    private DoubleUnaryOperator taxFunction = d -> d;

    public TaxCalculatorLambda with(DoubleUnaryOperator f) {
        taxFunction = taxFunction.andThen(f);
        return this;
    }

    public double calculate(Order order) {
        return taxFunction.applyAsDouble(order.getValue());
    }

}
