package com.app.arcx.config;

import com.app.arcx.domain.AreaOfInterest;
import com.app.arcx.domain.AreaOfInterestItems;
import com.app.arcx.domain.AreaOfInterestSubItems;
import com.app.arcx.domain.StepsToHelpPrepare;
import com.app.arcx.domain.StepsToHelpPrepareItems;
import com.app.arcx.domain.StepsToHelpPrepareSubItems;
import com.app.arcx.domain.Regions;
import com.app.arcx.domain.AdditionalInformation;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import java.util.List;

@Component
public class SpringDataRestCustomization implements RepositoryRestConfigurer {

    @Qualifier("defaultValidator")
    @Autowired
    private Validator validator;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

        config.getCorsRegistry().addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*");

        config.exposeIdsFor(AreaOfInterest.class);

        //See where I added new ID for Items
        config.exposeIdsFor(AreaOfInterestItems.class);
        config.exposeIdsFor(AreaOfInterestSubItems.class);

        config.exposeIdsFor(StepsToHelpPrepare.class);
        config.exposeIdsFor(StepsToHelpPrepareItems.class);
        config.exposeIdsFor(StepsToHelpPrepareSubItems.class);

        config.exposeIdsFor(Regions.class);
        config.exposeIdsFor(AdditionalInformation.class);

        config.setReturnBodyOnCreate(true);
        config.setReturnBodyOnUpdate(true);
    }

    @Override
    public void configureConversionService(ConfigurableConversionService conversionService) {

    }

    @Override
    public void configureValidatingRepositoryEventListener(ValidatingRepositoryEventListener validatingListener) {
        validatingListener.addValidator("beforeCreate", validator);
    }

    @Override
    public void configureExceptionHandlerExceptionResolver(ExceptionHandlerExceptionResolver exceptionResolver) {

    }

    @Override
    public void configureHttpMessageConverters(List<HttpMessageConverter<?>> messageConverters) {

    }

    @Override
    public void configureJacksonObjectMapper(ObjectMapper objectMapper) {

    }
}
