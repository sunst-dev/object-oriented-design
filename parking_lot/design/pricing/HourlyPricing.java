package parking_lot.design.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;

import parking_lot.design.ticket.Ticket;

public class HourlyPricing implements PricingStrategy {

    private final BigDecimal hourlyRate;

    public HourlyPricing(BigDecimal hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    @Override
    public BigDecimal calculateFee(Ticket ticket) {
        long hours = Math.max(1, ticket.getDuration().toHours());
        return hourlyRate.multiply(BigDecimal.valueOf(hours)).setScale(2, RoundingMode.HALF_UP);
    }
}
