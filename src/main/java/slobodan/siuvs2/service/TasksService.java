/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author deca
 */
import slobodan.siuvs2.model.Tasks;
import slobodan.siuvs2.valueObject.TasksID;


public interface TasksService {
    Tasks findOne(TasksID tasksID);

    void save(Tasks Tasks);
    void delete(TasksID tasksID);
}
