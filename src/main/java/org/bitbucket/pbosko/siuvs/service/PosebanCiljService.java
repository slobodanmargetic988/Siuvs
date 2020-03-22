/*
 * 
 */
package org.bitbucket.pbosko.siuvs.service;

/**
 *
 * @author deca
 */
import java.util.List;
import org.bitbucket.pbosko.siuvs.model.PosebanCilj;
import org.bitbucket.pbosko.siuvs.model.Plan;
import org.bitbucket.pbosko.siuvs.valueObject.PlanID;
import org.bitbucket.pbosko.siuvs.valueObject.PosebanCiljID;

public interface PosebanCiljService {
    
    PosebanCilj findOne(PosebanCiljID posebanCiljID);
    
List<PosebanCilj> findAllByPlanOrderByRedosledAsc(Plan plan);

    void save(PosebanCilj posebanCilj);
//void addPosebanCilj(DynamicTable dynamicTable, DynamicRow dynamicRow, Client client) throws SiuvsException;
    void delete(PosebanCiljID posebanCiljID);
}
