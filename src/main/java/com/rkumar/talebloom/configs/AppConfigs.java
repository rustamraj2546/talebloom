package com.rkumar.talebloom.configs;

import com.rkumar.talebloom.entities.TagEntity;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfigs {

    @Bean
    ModelMapper getModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Custom converter for TagEntity -> String
        Converter<TagEntity, String> tagToStringConverter = new Converter<TagEntity, String>() {
            public String convert(MappingContext<TagEntity, String> context) {
                // If the source TagEntity is not null, return its tagName. Otherwise, return null.
                return context.getSource() == null ? null : context.getSource().getTagName();
            }
        };

        modelMapper.addConverter(tagToStringConverter);

        return modelMapper;
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
