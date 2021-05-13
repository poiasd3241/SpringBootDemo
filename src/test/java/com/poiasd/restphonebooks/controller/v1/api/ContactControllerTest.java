package com.poiasd.restphonebooks.controller.v1.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poiasd.restphonebooks.dto.mapper.ContactMapper;
import com.poiasd.restphonebooks.dto.model.ContactDTO;
import com.poiasd.restphonebooks.model.Contact;
import com.poiasd.restphonebooks.service.ContactService;
import com.poiasd.restphonebooks.util.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * The test class for the {@link ContactController}.
 */
@ExtendWith(MockitoExtension.class)
@WebMvcTest(ContactController.class)
class ContactControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ContactService contactService;

    @Autowired
    ObjectMapper objectMapper;

    private final String userUID = UUID.randomUUID().toString();
    private final String contactUID = UUID.randomUUID().toString();
    private final String phoneNumber = "+1";
    private final Contact contact = new Contact("ContactOne", phoneNumber);
    private final ContactDTO contactDTO = ContactMapper.toDTO(contact);
    private final List<Contact> contacts = List.of(contact);
    private final List<ContactDTO> contactsDTO = ContactMapper.toDtoList(contacts);

    /**
     * Returns the root (shared) controller path, using the custom user UID.
     *
     * @param userUID The custom user UID to use in the path.
     */
    private String makeRootPathCustomUser(String userUID) {
        return String.format("/api/v1/user/%s/contact", userUID);
    }

    /**
     * Returns the root (shared) controller path, using the default user UID ({@link #userUID}).
     */
    private String makeRootPath() {
        return makeRootPathCustomUser(userUID);
    }

    /**
     * Returns the controller path according to the specified user and branch.
     *
     * @param userUID The custom user UID to use in the path.
     * @param branch  The part of the path to add to the shared controller path.
     */
    private String makePathCustomUser(String userUID, String branch) {
        return String.format("%s%s", makeRootPathCustomUser(userUID), branch);
    }

    /**
     * Returns the controller path according to the specified branch.
     *
     * @param branch The part of the path to add to the shared controller path.
     */
    private String makePath(String branch) {
        return makePathCustomUser(userUID, branch);
    }

    /**
     * Converts the specified object to its JSON string representation.
     *
     * @param object The object to convert.
     * @throws JsonProcessingException
     */
    private String toJSON(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    @DisplayName("Should display all existing contacts of the specified user (at least one contact is present).")
    @Test
    void findAll_Present() throws Exception {
        // Given
        var expectedResponse = toJSON(Result.success(contactsDTO));

        when(contactService.findByUserUID(userUID)).thenReturn(Optional.of(contactsDTO));

        // When, Then
        mvc.perform(get(makePath("/all")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponse));
        verify(contactService, times(1)).findByUserUID(userUID);
        verify(contactService, times(1)).findByUserUID(anyString());
    }

    @DisplayName("Should display the failure object when the specified user doesn't have any contacts.")
    @Test
    void findAll_NotPresent() throws Exception {
        // Given
        var expectedResponseBody = toJSON(Result.failure("You don't have any contacts."));

        when(contactService.findByUserUID(userUID)).thenReturn(Optional.empty());

        // When, Then
        mvc.perform(get(makePath("/all")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponseBody));
        verify(contactService, times(1)).findByUserUID(userUID);
        verify(contactService, times(1)).findByUserUID(anyString());
    }

    @DisplayName("Should display the contact matching the specified UID.")
    @Test
    void getByUID_success() throws Exception {
        // Given
        var expectedResponseBody = toJSON(contactDTO);

        when(contactService.getByUID(contactUID)).thenReturn(contactDTO);

        // When, Then
        mvc.perform(get(makePath("/{contactUID}"), contactUID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponseBody));
        verify(contactService, times(1)).getByUID(contactUID);
        verify(contactService, times(1)).getByUID(anyString());
    }

    @DisplayName("Should display the specified user's contact matching the specified phone number.")
    @Test
    void findByPhoneNumber_Present() throws Exception {
        // Given
        var expectedResponseBody = toJSON(Result.success(contactDTO));

        when(contactService.findByPhoneNumber(userUID, phoneNumber)).thenReturn(Optional.of(contactDTO));

        // When, Then
        mvc.perform(get(makePath("/number/{phoneNumber}"), phoneNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponseBody));
        verify(contactService, times(1)).findByPhoneNumber(userUID, phoneNumber);
        verify(contactService, times(1)).findByPhoneNumber(anyString(), anyString());
    }

    @DisplayName("Should display the failure object when the specified user doesn't have a contact matching the specified phone number.")
    @Test
    void findByPhoneNumber_NotPresent() throws Exception {
        // Given
        var expectedResponseBody = toJSON(Result.failure(
                String.format("No contact with the phone number %s.", phoneNumber)));

        when(contactService.findByPhoneNumber(userUID, phoneNumber)).thenReturn(Optional.empty());

        // When, Then
        mvc.perform(get(makePath("/number/{phoneNumber}"), phoneNumber))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponseBody));
        verify(contactService, times(1)).findByPhoneNumber(userUID, phoneNumber);
        verify(contactService, times(1)).findByPhoneNumber(anyString(), anyString());
    }

    @DisplayName("Should display the success 'create' message.")
    @Test
    void create_success() throws Exception {
        // Given
        var requestBody = toJSON(contactDTO);

        doNothing().when(contactService).create(anyString(), any(ContactDTO.class));

        // When, Then
        mvc.perform(post(makeRootPath())
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("Contact created successfully."));
        verify(contactService, times(1)).create(userUID, contactDTO);
        verify(contactService, times(1)).create(anyString(), any(ContactDTO.class));
    }

    @DisplayName("Should throw and handle when the @Valid-annotated request body (object for 'create') is invalid.")
    @Test
    void create_throw_whenInvalidRequestBody() throws Exception {
        // Given
        var invalidRequestBody = toJSON(new ContactDTO(null, null));

        // When, Then
        var resolvedException = mvc.perform(post(makeRootPath())
                .content(invalidRequestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn().getResolvedException();
        assertTrue(resolvedException instanceof MethodArgumentNotValidException);
        assertEquals(2, ((MethodArgumentNotValidException) resolvedException).getFieldErrors().size());
        verify(contactService, never()).create(anyString(), any(ContactDTO.class));
    }

    @DisplayName("Should display the success 'update' message.")
    @Test
    void update_success() throws Exception {
        // Given
        var requestBody = toJSON(contactDTO);

        doNothing().when(contactService).update(anyString(), any(ContactDTO.class));

        // When, Then
        mvc.perform(put(makePath("/{contactUID}"), contactUID)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("Contact updated successfully."));
        verify(contactService, times(1)).update(contactUID, contactDTO);
        verify(contactService, times(1)).update(anyString(), any(ContactDTO.class));
    }

    @DisplayName("Should throw and handle when the @Valid-annotated request body (object for 'update') is invalid.")
    @Test
    void update_throw_whenInvalidRequestBody() throws Exception {
        // Given
        var invalidRequestBody = toJSON(new ContactDTO(null, null));

        // When, Then
        var resolvedException = mvc.perform(put(makePath("/{contactUID}"), contactUID)
                .content(invalidRequestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn().getResolvedException();
        assertTrue(resolvedException instanceof MethodArgumentNotValidException);
        assertEquals(2, ((MethodArgumentNotValidException) resolvedException).getFieldErrors().size());
        verify(contactService, never()).update(anyString(), any(ContactDTO.class));
    }

    @DisplayName("Should display the success 'delete' message.")
    @Test
    void delete_success() throws Exception {
        // Given
        doNothing().when(contactService).delete(anyString());

        // When, Then
        mvc.perform(delete(makePath("/{contactUID}"), contactUID))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("Contact deleted successfully."));
        verify(contactService, times(1)).delete(contactUID);
        verify(contactService, times(1)).delete(anyString());
    }
}
