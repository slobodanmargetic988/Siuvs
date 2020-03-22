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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.bitbucket.pbosko.siuvs.repository.PosebanCiljRepository;
import org.bitbucket.pbosko.siuvs.valueObject.PlanID;
import org.bitbucket.pbosko.siuvs.valueObject.PosebanCiljID;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PosebanCiljServiceImpl implements PosebanCiljService{
      @Autowired
    private PosebanCiljRepository posebanCiljRepository;

      @Override
  public PosebanCilj findOne(PosebanCiljID posebanCiljID){
      return posebanCiljRepository.findOne(posebanCiljID.getValue());
  }

@Override
  public List<PosebanCilj> findAllByPlanOrderByRedosledAsc(Plan plan){
   return posebanCiljRepository.findAllByPlanOrderByRedosledAsc(plan);
           };
  
   @Override
  // @Transactional
   public void save(PosebanCilj posebanCilj){

   posebanCiljRepository.save(posebanCilj);
   
   }
    @Override
 public void delete(PosebanCiljID posebanCiljID){
 posebanCiljRepository.delete(posebanCiljID.getValue());
 };
}
