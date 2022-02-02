package slobodan.siuvs2.shared.thymeleaf;

import org.thymeleaf.dialect.AbstractDialect;
import org.thymeleaf.dialect.IExpressionObjectDialect;
import org.thymeleaf.expression.IExpressionObjectFactory;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
public class SiuvsDialect extends AbstractDialect implements IExpressionObjectDialect {

    private final IExpressionObjectFactory SIUVS_EXPRESSION_OBJECTS_FACTORY = new SiuvsExpressionFactory();

    public SiuvsDialect() {
        super("siuvs");
    }

    @Override
    public IExpressionObjectFactory getExpressionObjectFactory() {
        return SIUVS_EXPRESSION_OBJECTS_FACTORY;
    }

}
