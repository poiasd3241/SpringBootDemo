package com.poiasd.restphonebooks.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The test class for the {@link TestController}.
 */
@ExtendWith(MockitoExtension.class)
@WebMvcTest(TestController.class)
class TestControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    ObjectMapper objectMapper;

    /**
     * Converts the specified object to its JSON string representation.
     *
     * @param object The object to convert.
     * @throws JsonProcessingException
     */
    private String toJSON(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    @DisplayName("Should have HttpStatus.OK when the @Valid-annotated request body is valid.")
    @Test
    void postValidRequestBodyValidatedObject_success() throws Exception {
        // Given
        var validRequestBody = toJSON(new ValidatedObject("whatever"));

        // When, Then
        mvc.perform(post("/test/postValidRequestBodyValidatedObject")
                .content(validRequestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @DisplayName("Should have HttpStatus = BAD_REQUEST when the @Valid-annotated request body is invalid.")
    @Test
    void postValidRequestBodyValidatedObject_badRequest() throws Exception {
        // Given
        var invalidRequestBody = toJSON(new ValidatedObject(""));

        // When, Then
        mvc.perform(post("/test/postValidRequestBodyValidatedObject")
                .content(invalidRequestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
