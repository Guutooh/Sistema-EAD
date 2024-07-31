package br.com.ead.course.configs;

import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration // Indica que esta classe é uma classe de configuração Spring
public class ResolverConfig extends WebMvcConfigurationSupport {

    // Sobrescreve o método para adicionar resolvers de argumentos personalizados
    @Override
    protected void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

        // Adiciona um resolver de argumentos para Specification, permitindo a construção dinâmica de queries
        argumentResolvers.add(new SpecificationArgumentResolver());

        // Configura e adiciona um resolver de argumentos para paginação
        PageableHandlerMethodArgumentResolver pageableResolver = new PageableHandlerMethodArgumentResolver();
        argumentResolvers.add(pageableResolver);

        // Chama o método da superclasse para garantir que quaisquer outros resolvers sejam adicionados
        super.addArgumentResolvers(argumentResolvers);
    }
}

