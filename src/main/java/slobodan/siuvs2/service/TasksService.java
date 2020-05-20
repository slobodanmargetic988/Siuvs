/*
 * 
 */
package slobodan.siuvs2.service;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import slobodan.siuvs2.model.Tasks;
import slobodan.siuvs2.valueObject.TasksID;

public interface TasksService {

    Tasks findOne(TasksID tasksID);

    void save(Tasks Tasks);

    void delete(TasksID tasksID);
}
