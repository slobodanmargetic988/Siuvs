/*
 * 
 */
package slobodan.siuvs2.model;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author deca
 */
public class DateKomparator {

    public int uporedi(LocalDate currentDate, LocalDate upitniDate) {
        int trazeniSlucaj = 0;
        Period period = Period.between(currentDate, upitniDate);
        int temp = period.getMonths();
        
if (period.getYears() > 0) {
       return     trazeniSlucaj = 4;}

        if (temp <= 3) {
            trazeniSlucaj = 2;
        } else if (temp > 3 || temp <= 6) {
            trazeniSlucaj = 3;
        } else if (temp > 6) {
            trazeniSlucaj = 4;
        }
        return trazeniSlucaj;
    }
}
