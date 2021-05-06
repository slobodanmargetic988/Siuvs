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

    public int uporediStari(LocalDate currentDate, LocalDate upitniDate) {
        int trazeniSlucaj = 0;
         if (upitniDate==null) {return 1;}
        Period period = Period.between(currentDate, upitniDate);
        int temp = period.getYears()*12+period.getMonths();
       


        if (temp <= 3) {
            trazeniSlucaj = 2;
        } else if (temp > 3 || temp <= 6) {
            trazeniSlucaj = 3;
        } else if (temp > 6) {
            trazeniSlucaj = 4;
        }
//        if (period.getYears() > 0) {
//       return     trazeniSlucaj = 4;}
        return trazeniSlucaj;
    }
        public int uporedi(LocalDate upitniDate) {
        int trazeniSlucaj = 0;
        LocalDate zelenidatum = LocalDate.now().plusMonths(6);
LocalDate zutidatum= LocalDate.now().plusMonths(3);

         if (upitniDate==null) {return 1;}
         
 
        if (upitniDate.isAfter(zelenidatum)){
       trazeniSlucaj = 4;
    }else{
        if (upitniDate.isAfter(zutidatum)){
          trazeniSlucaj = 3;
        }else{
          trazeniSlucaj = 2;
            
        }
        }

//        if (period.getYears() > 0) {
//       return     trazeniSlucaj = 4;}
        return trazeniSlucaj;
    
        }
}
/*

LocalDate zelenidatum = LocalDate.now().plusMonths(6);
LocalDate zutidatum= LocalDate.now().plusMonths(3);
calendarList.forEach((calendar) -> {
    if (calendar.getVazido().isAfter(zelenidatum)){
        calendarListZelena.add(calendar);
    }else{
        if (calendar.getVazido().isAfter(zutidatum)){
            calendarListZuta.add(calendar);
        }else{
            calendarListCrvena.add(calendar);
            
        }
        
    }     });
*/
