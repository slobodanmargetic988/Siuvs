/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import slobodan.siuvs2.model.Distrikt;
import slobodan.siuvs2.model.Provincija;
import slobodan.siuvs2.model.Supervising;

public interface SupervisingService {

    Supervising findFirstByDistrikt(Distrikt distrikt);

    Supervising findFirstByProvincija(Provincija provincija);
}
