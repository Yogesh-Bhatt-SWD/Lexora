package com.lexora.exception;

import com.lexora.dto.ApiResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void shouldReturnNotFoundForResourceNotFoundException() {
        ResourceNotFoundException ex = new ResourceNotFoundException("Contract", 42L);
        ResponseEntity<ApiResponse<Void>> response = handler.handleResourceNotFound(ex);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().isSuccess()).isFalse();
    }

    @Test
    void shouldReturnInternalErrorForLexoraException() {
        LexoraException ex = new LexoraException("Something went wrong", HttpStatus.BAD_GATEWAY);
        ResponseEntity<ApiResponse<Void>> response = handler.handleLexoraException(ex);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_GATEWAY);
        assertThat(response.getBody().isSuccess()).isFalse();
    }

    @Test
    void shouldReturnInternalServerErrorForGenericException() throws Exception {
        Exception ex = new RuntimeException("Unexpected");
        ResponseEntity<ApiResponse<Void>> response = handler.handleGenericException(ex);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody().isSuccess()).isFalse();
    }
}
