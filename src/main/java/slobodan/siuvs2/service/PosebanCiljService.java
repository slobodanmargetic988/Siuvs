/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */
import java.util.List;
import slobodan.siuvs2.model.PosebanCilj;
import slobodan.siuvs2.model.Plan;
import slobodan.siuvs2.valueObject.PlanID;
import slobodan.siuvs2.valueObject.PosebanCiljID;

public interface PosebanCiljService {
    
    PosebanCilj findOne(PosebanCiljID posebanCiljID);
    
List<PosebanCilj> findAllByPlanOrderByRedosledAsc(Plan plan);

    void save(PosebanCilj posebanCilj);
//void addPosebanCilj(DynamicTable dynamicTable, DynamicRow dynamicRow, Client client) throws SiuvsException;
    void delete(PosebanCiljID posebanCiljID);
}
