package parking_lot.design.pricing;

import java.math.BigDecimal;

import parking_lot.design.ticket.Ticket;

public class FlatRatePricing implements PricingStrategy {

    private final BigDecimal rate;

    public FlatRatePricing(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public BigDecimal calculateFee(Ticket ticket) {
        return rate;
    }
}
