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
import slobodan.siuvs2.model.Tasks;
import org.springframework.stereotype.Service;

@Service
public class TasksFactoryImpl implements TasksFactory {

    @Override
    public Tasks empty(String name) {
        Tasks tasks = new Tasks();
        tasks.setName(name);
        return tasks;
    }

}
