package com.poiasd.restphonebooks.exception;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * The controller used for testing some of the app's functionality.
 */
@RestController
@RequestMapping("/test")
class TestController {
    /**
     * The empty method for testing the {@link GlobalControllerExceptionHandler#handleMethodArgumentNotValid}.
     *
     * @param validatedObject The @Valid-annotated request body object.
     */
    @PostMapping("postValidRequestBodyValidatedObject")
    void postValidRequestBodyValidatedObject(@Valid @RequestBody ValidatedObject validatedObject) {
    }
}
