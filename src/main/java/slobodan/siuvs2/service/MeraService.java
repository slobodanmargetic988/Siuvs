/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import slobodan.siuvs2.model.Mera;
import slobodan.siuvs2.valueObject.MeraID;

public interface MeraService {

    Mera findOne(MeraID meraID);

    void save(Mera mera);

    void delete(MeraID meraID);
}
