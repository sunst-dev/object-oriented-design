package parking_lot.design.pricing;

import java.math.BigDecimal;

import parking_lot.design.ticket.Ticket;

public interface PricingStrategy {
    BigDecimal calculateFee(Ticket ticket);
}
