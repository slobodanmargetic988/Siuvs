/*
 * 
 */
package slobodan.siuvs2.service;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.PosebanCilj;
import slobodan.siuvs2.model.Plan;

public interface PosebanCiljFactory {

    PosebanCilj empty(Plan plan, Page page);
}
