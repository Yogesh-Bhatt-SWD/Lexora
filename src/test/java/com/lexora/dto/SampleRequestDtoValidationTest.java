package com.lexora.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class SampleRequestDtoValidationTest {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void shouldPassValidationForValidInput() {
        SampleRequestDto dto = new SampleRequestDto();
        dto.setName("Yogesh Bhatt");
        dto.setEmail("yogesh@lexora.dev");
        Set<ConstraintViolation<SampleRequestDto>> violations = validator.validate(dto);
        assertThat(violations).isEmpty();
    }

    @Test
    void shouldFailValidationForBlankName() {
        SampleRequestDto dto = new SampleRequestDto();
        dto.setName("");
        dto.setEmail("yogesh@lexora.dev");
        Set<ConstraintViolation<SampleRequestDto>> violations = validator.validate(dto);
        assertThat(violations).isNotEmpty();
    }

    @Test
    void shouldFailValidationForInvalidEmail() {
        SampleRequestDto dto = new SampleRequestDto();
        dto.setName("Yogesh");
        dto.setEmail("not-an-email");
        Set<ConstraintViolation<SampleRequestDto>> violations = validator.validate(dto);
        assertThat(violations).isNotEmpty();
    }
}
