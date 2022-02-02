package slobodan.siuvs2.configuration;

import slobodan.siuvs2.shared.thymeleaf.SiuvsDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Configuration
public class ThymeleafConfig extends WebMvcConfigurerAdapter {

    @Bean
    public SpringTemplateEngine templateEngine(ITemplateResolver templateResolver) {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        templateEngine.addDialect(new SpringSecurityDialect());
        templateEngine.addDialect(new Java8TimeDialect());
        templateEngine.addDialect(new SiuvsDialect());
        return templateEngine;
    }

}
