package com.example.urfutest.util;

import com.example.urfutest.entities.EducationalProgram;
import com.example.urfutest.services.EducationalProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class EducationalProgramValidator implements Validator {

    private final EducationalProgramService educationalProgramService;
    @Override
    public boolean supports(Class<?> clazz) {
        return EducationalProgram.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EducationalProgram educationalProgram = (EducationalProgram) target;

//        if (educationalProgram.getAccreditationTime() != )
    }
}
