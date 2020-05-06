/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */

import slobodan.siuvs2.model.Distrikt;
import slobodan.siuvs2.model.Provincija;
import slobodan.siuvs2.model.Supervising;



public interface SupervisingService {

    Supervising findFirstByDistrikt(Distrikt distrikt);
    Supervising findFirstByProvincija(Provincija provincija);
}
