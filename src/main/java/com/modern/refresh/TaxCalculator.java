package com.modern.refresh;

import static com.modern.refresh.MixedOrderBuilder.*;
import static com.modern.refresh.MixedOrderBuilder.TradeBuilder.buy;
import static com.modern.refresh.MixedOrderBuilder.TradeBuilder.sell;

public class TaxCalculator {
    private boolean useRegional;
    private boolean useGeneral;
    private boolean useSurcharge;

    public TaxCalculator withRegion() {
        useRegional = true;
        return this;
    }

    public TaxCalculator withGeneral() {
        useGeneral = true;
        return this;
    }

    public TaxCalculator withSurcharge() {
        useSurcharge = true;
        return this;
    }

    public double calculate(Order order) {
        return Tax.calculate(order, useRegional, useGeneral, useSurcharge);
    }

    public static void main(String[] args) {
        final Order order = order("Vinayak",
                buy(tb -> {
                    tb.quantity(100);
                    tb.price(123.00);
                    tb.stock(s -> {
                        s.symbol("IBM");
                        s.market("BSE");
                    });
                }),
                sell(tb -> {
                    tb.quantity(80);
                    tb.price(2100.00);
                    tb.stock(s -> {
                        s.symbol("APPLE");
                        s.market("NSE");
                    });
                }));
        System.out.println(new TaxCalculator().withRegion().withSurcharge().calculate(order));

        System.out.println(new TaxCalculatorLambda()
                .with(Tax::regional)
                .with(Tax::surcharge)
                .calculate(order));

    }

}
