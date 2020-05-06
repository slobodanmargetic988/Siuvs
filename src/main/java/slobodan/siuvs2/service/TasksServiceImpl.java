/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */

import slobodan.siuvs2.model.Tasks;
import slobodan.siuvs2.repository.TasksRepository;
import slobodan.siuvs2.valueObject.TasksID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TasksServiceImpl implements TasksService{
@Autowired
TasksRepository tasksRepository;

        @Override
    public Tasks findOne(TasksID tasksID) {
        return tasksRepository.findOne(tasksID.getValue());
    }

    @Override
    public void save(Tasks distrikt) {
      
        tasksRepository.save(distrikt);
    }
    
            @Override
    public void delete(TasksID tasksID){
   tasksRepository.delete(tasksID.getValue());
    };
}
