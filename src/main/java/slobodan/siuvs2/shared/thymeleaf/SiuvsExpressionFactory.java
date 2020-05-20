package slobodan.siuvs2.shared.thymeleaf;

import org.thymeleaf.context.IExpressionContext;
import org.thymeleaf.expression.IExpressionObjectFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SiuvsExpressionFactory implements IExpressionObjectFactory {

    private static final String SIUVS_EVALUATION_VARIABLE_NAME = "siuvs";

    private static final Set<String> ALL_EXPRESSION_OBJECT_NAMES = Collections.unmodifiableSet(
            new HashSet<>(Arrays.asList(SIUVS_EVALUATION_VARIABLE_NAME))
    );

    @Override
    public Set<String> getAllExpressionObjectNames() {
        return ALL_EXPRESSION_OBJECT_NAMES;
    }

    @Override
    public Object buildObject(IExpressionContext context, String expressionObjectName) {
        if (SIUVS_EVALUATION_VARIABLE_NAME.equals(expressionObjectName)) {
            return new SiuvsExpressions();
        }
        return null;
    }

    @Override
    public boolean isCacheable(String expressionObjectName) {
        return expressionObjectName != null && SIUVS_EVALUATION_VARIABLE_NAME.equals(expressionObjectName);
    }
}
