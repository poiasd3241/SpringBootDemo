package com.poiasd.restphonebooks.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * The test class for the {@link GlobalControllerExceptionHandler}.
 */
@ExtendWith(MockitoExtension.class)
@WebMvcTest(TestController.class)
class GlobalControllerExceptionHandlerTest {

    @Autowired
    private GlobalControllerExceptionHandler exceptionHandler;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Converts the specified object to its JSON string representation.
     *
     * @param object The object to convert.
     * @throws JsonProcessingException
     */
    private String toJSON(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    @DisplayName("Should have HttpStatus = NOT_FOUND and body = exception message.")
    @Test
    void handleNotFoundExceptions_success() {
        // Given
        var entityNotFoundMessage = "X entity doesn't exist.";
        var entityNotFoundException = new EntityRuntimeExceptions.EntityNotFoundException(entityNotFoundMessage);

        // When
        var result = exceptionHandler.handleNotFoundExceptions(entityNotFoundException);

        //Then
        assertEquals(entityNotFoundMessage, result.getBody().toString());
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @DisplayName("Should have HttpStatus = CONFLICT and body = exception message.")
    @Test
    void handleDuplicateExceptions_success() {
        // Given
        var duplicateEntityMessage = "X entity already exists.";
        var duplicateEntityException = new EntityRuntimeExceptions.DuplicateEntityException(duplicateEntityMessage);

        // When
        var result = exceptionHandler.handleDuplicateExceptions(duplicateEntityException);

        //Then
        assertEquals(duplicateEntityMessage, result.getBody().toString());
        assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
    }

    @DisplayName("Should have HttpStatus = BAD_REQUEST and body = custom message.")
    @Test
    void handleMethodArgumentNotValid_success() throws Exception {
        // Given
        var invalidRequestBody = toJSON(new ValidatedObject(""));
        var expectedResponseBody = String.format("Bad request - field(s):\n%s: %s",
                "notBlankField", "must not be blank");

        // When, Then
        // Obtain MethodArgumentNotValidException
        var resolvedException = mvc.perform(post("/test/postValidRequestBodyValidatedObject")
                .content(invalidRequestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andReturn().getResolvedException();
        assertTrue(resolvedException instanceof MethodArgumentNotValidException);
        var methodArgumentNotValidException = (MethodArgumentNotValidException) resolvedException;
        // Prepare parameters for the exception handler method under test.
        var mockHttpHeaders = mock(HttpHeaders.class);
        var mockWebRequest = mock(WebRequest.class);
        var whateverHttpStatus = HttpStatus.OK;

        var result = exceptionHandler.handleMethodArgumentNotValid(
                methodArgumentNotValidException, mockHttpHeaders, whateverHttpStatus, mockWebRequest);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(expectedResponseBody, result.getBody());
    }

    @DisplayName("Should have HttpStatus = INTERNAL_SERVER_ERROR and body = custom message.")
    @Test
    void handleAllUncaughtException_success() {
        // Given
        var expectedResponseBody = "Unknown error occurred";

        // When
        var result = exceptionHandler.handleAllUncaughtException();

        //Then
        assertEquals(expectedResponseBody, result.getBody().toString());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
    }
}
