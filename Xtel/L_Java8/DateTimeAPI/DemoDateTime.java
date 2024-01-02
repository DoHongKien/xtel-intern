package L_Java8.DateTimeAPI;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class DemoDateTime {

    public static void main(String[] args) {
        // Get the date now
        LocalDate dateNow = LocalDate.now();

        // Get the date by specifying the date
        LocalDate specifyingDate = LocalDate.of(2024, 1, 1);

        // Increase, decrease by day
        LocalDate plusDays = specifyingDate.plusDays(1);
        LocalDate minusDays = specifyingDate.minusDays(1);

        // Increase, decrease by month
        LocalDate plusMonth = specifyingDate.plusMonths(1);
        LocalDate minusMonth = specifyingDate.minusMonths(1);

        // Increase, decrease by year
        LocalDate plusYears = specifyingDate.plusYears(1);
        LocalDate minusYears = specifyingDate.minusYears(1);

        // Compare 2 dates before or after together
        boolean isBefore = specifyingDate.isBefore(plusDays);
        boolean isAfter = specifyingDate.isAfter(plusDays);
        boolean isEqual = specifyingDate.isEqual(plusDays);

        // Specifying the time for the date
        LocalDateTime localDateTime = dateNow.atTime(LocalTime.now());

        // Get day of week, month and year
        int dayOfWeek = specifyingDate.getDayOfWeek().getValue();
        int dayOfMonth = specifyingDate.getDayOfMonth();
        int dayOfYear = specifyingDate.getDayOfYear();

        // Format date by dd-MM-yyyy
        String dateFormat = specifyingDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        // Format datetime by hh:mm:ss dd-MM-yyyy
        String dateTimeFormat = localDateTime.format(DateTimeFormatter.ofPattern("hh:mm:ss dd-MM-yyyy"));

    }
}
