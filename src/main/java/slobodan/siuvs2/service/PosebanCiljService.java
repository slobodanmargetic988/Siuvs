/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import java.util.List;
import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.PosebanCilj;
import slobodan.siuvs2.model.Plan;
import slobodan.siuvs2.valueObject.PosebanCiljID;

public interface PosebanCiljService {

    PosebanCilj findOne(PosebanCiljID posebanCiljID);

    List<PosebanCilj> findAllByPlanOrderByRedosledAsc(Plan plan);

    List<PosebanCilj> findAllByPlanOrderByPagePageIdAsc(Plan plan);

    void save(PosebanCilj posebanCilj);
//void addPosebanCilj(DynamicTable dynamicTable, DynamicRow dynamicRow, Client client) throws SiuvsException;

    void delete(PosebanCiljID posebanCiljID);

    List<PosebanCilj> findAllByClientAndPage(Client client, Page page);
     List<PosebanCilj> findAllByClientAndPageAndKomponenta(Client client,Page page, Integer komponenta);
     PosebanCilj findFirstByClient(Client client);
}
