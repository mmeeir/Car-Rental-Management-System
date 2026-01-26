package services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PricingService {
    public double calculateCost(LocalDate start, LocalDate end, double pricePerDay) {
        long days = ChronoUnit.DAYS.between(start, end);
        if (days <= 0) days = 1;
        return days * pricePerDay;
    }
}
