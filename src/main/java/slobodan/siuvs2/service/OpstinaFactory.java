/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import slobodan.siuvs2.model.Distrikt;
import slobodan.siuvs2.model.Opstina;
import slobodan.siuvs2.model.Provincija;

public interface OpstinaFactory {

    Opstina empty(Distrikt distrikt, Provincija provincija);
}
