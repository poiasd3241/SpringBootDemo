package com.poiasd.restphonebooks.controller.v1.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poiasd.restphonebooks.dto.mapper.UserMapper;
import com.poiasd.restphonebooks.dto.model.UserDTO;
import com.poiasd.restphonebooks.model.Contact;
import com.poiasd.restphonebooks.model.PhoneBook;
import com.poiasd.restphonebooks.model.User;
import com.poiasd.restphonebooks.service.UserService;
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
 * The test class for the {@link UserController}.
 */
@ExtendWith(MockitoExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Autowired
    ObjectMapper objectMapper;

    private final String userUID = UUID.randomUUID().toString();
    private final Contact contact = new Contact("ContactOne", "+1");
    private final List<Contact> contacts = List.of(contact);

    private final String fullUserName = "UserOne";
    private final String partialUserName = "One";
    private final User user = new User(fullUserName, new PhoneBook(contacts));
    private final UserDTO userDTO = UserMapper.toDTO(user);
    private final List<User> users = List.of(user);
    private final List<UserDTO> usersDTO = UserMapper.toDtoList(users);

    /**
     * Returns the root (shared) controller path.
     */
    private String makeRootPath() {
        return "/api/v1/user";
    }

    /**
     * Returns the controller path according to the specified branch.
     *
     * @param branch The part of the path to add to the shared controller path.
     */
    private String makePath(String branch) {
        return String.format("%s%s", makeRootPath(), branch);
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

    @DisplayName("Should display all existing users (at least one user is present).")
    @Test
    void findAll_Present() throws Exception {
        // Given
        var expectedResponse = toJSON(Result.success(usersDTO));

        when(userService.findAll()).thenReturn(Optional.of(usersDTO));

        // When, Then
        mvc.perform(get(makePath("/all")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponse));
        verify(userService, times(1)).findAll();
    }

    @DisplayName("Should display the failure object when there are no users.")
    @Test
    void findAll_NotPresent() throws Exception {
        // Given
        var expectedResponseBody = toJSON(Result.failure("There are no users."));

        when(userService.findAll()).thenReturn(Optional.empty());

        // When, Then
        mvc.perform(get(makePath("/all")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponseBody));
        verify(userService, times(1)).findAll();
    }

    @DisplayName("Should display the user matching the specified UID.")
    @Test
    void getByUID_success() throws Exception {
        // Given
        var expectedResponseBody = toJSON(userDTO);

        when(userService.getByUID(userUID)).thenReturn(userDTO);

        // When, Then
        mvc.perform(get(makePath("/{userUID}"), userUID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponseBody));
        verify(userService, times(1)).getByUID(userUID);
        verify(userService, times(1)).getByUID(anyString());
    }

    @DisplayName("Should display the users matching the specified user name (at least one user is present).")
    @Test
    void findByName_Present() throws Exception {
        // Given
        // Full/partial name search doesn't affect the controller behavior when at least one user is retrieved.
        var isFullName = true;
        var expectedResponseBody = toJSON(Result.success(usersDTO));

        when(userService.findByName(fullUserName, isFullName)).thenReturn(Optional.of(usersDTO));

        // When, Then
        mvc.perform(get(makePath("/name/{name}"), fullUserName).param("fullName", "true"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponseBody));
        verify(userService, times(1)).findByName(fullUserName, isFullName);
        verify(userService, times(1)).findByName(anyString(), anyBoolean());
    }

    @DisplayName("Should display the failure object when no users have the specified full user name.")
    @Test
    void findByName_FullName_NotPresent() throws Exception {
        // Given
        var isFullName = true;
        var expectedResponseBody = toJSON(Result.failure(
                String.format("No users with the name %s.", fullUserName)));

        when(userService.findByName(fullUserName, isFullName)).thenReturn(Optional.empty());

        // When, Then
        mvc.perform(get(makePath("/name/{name}"), fullUserName).param("fullName", "true"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponseBody));
        verify(userService, times(1)).findByName(fullUserName, isFullName);
        verify(userService, times(1)).findByName(anyString(), anyBoolean());
    }

    @DisplayName("Should display the failure object when no users match the specified partial user name.")
    @Test
    void findByName_PartialName_NotPresent() throws Exception {
        // Given
        var isFullName = false;
        var expectedResponseBody = toJSON(Result.failure(
                String.format("No users with the name matching %s.", partialUserName)));

        when(userService.findByName(partialUserName, isFullName)).thenReturn(Optional.empty());

        // When, Then
        mvc.perform(get(makePath("/name/{name}"), partialUserName).param("fullName", "false"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(expectedResponseBody));
        verify(userService, times(1)).findByName(partialUserName, isFullName);
        verify(userService, times(1)).findByName(anyString(), anyBoolean());
    }

    @DisplayName("Should perform the partial name search when the request parameter is not specified (default).")
    @Test
    void findByName_PartialNameByDefault() throws Exception {
        // Given
        var isFullName = false;
        when(userService.findByName(partialUserName, isFullName)).thenReturn(Optional.empty());

        // When, Then
        mvc.perform(get(makePath("/name/{name}"), partialUserName))
                .andExpect(status().isOk());
        verify(userService, times(1)).findByName(partialUserName, isFullName);
        verify(userService, times(1)).findByName(anyString(), anyBoolean());
    }

    @DisplayName("Should display the success 'create' message.")
    @Test
    void create_success() throws Exception {
        // Given
        var requestBody = toJSON(userDTO);

        doNothing().when(userService).create(any(UserDTO.class));

        // When, Then
        mvc.perform(post(makeRootPath())
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("User created successfully."));
        verify(userService, times(1)).create(userDTO);
        verify(userService, times(1)).create(any(UserDTO.class));
    }

    @DisplayName("Should throw and handle when the @Valid-annotated request body (object for 'create') is invalid.")
    @Test
    void create_throw_whenInvalidRequestBody() throws Exception {
        // Given
        var invalidRequestBody = toJSON(new UserDTO(null, null));

        // When, Then
        var resolvedException = mvc.perform(post(makeRootPath())
                .content(invalidRequestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn().getResolvedException();
        assertTrue(resolvedException instanceof MethodArgumentNotValidException);
        assertEquals("userDTO", ((MethodArgumentNotValidException) resolvedException).getObjectName());
        assertEquals(2, ((MethodArgumentNotValidException) resolvedException).getFieldErrors().size());
        verify(userService, never()).create(any(UserDTO.class));
    }

    @DisplayName("Should display the success 'update' message.")
    @Test
    void update_success() throws Exception {
        // Given
        var requestBody = toJSON(userDTO);

        doNothing().when(userService).update(anyString(), any(UserDTO.class));

        // When, Then
        mvc.perform(put(makePath("/{userUID}"), userUID)
                .content(requestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("User updated successfully."));
        verify(userService, times(1)).update(userUID, userDTO);
        verify(userService, times(1)).update(anyString(), any(UserDTO.class));
    }

    @DisplayName("Should throw and handle when the @Valid-annotated request body (object for 'update') is invalid.")
    @Test
    void update_throw_whenInvalidRequestBody() throws Exception {
        // Given
        var invalidRequestBody = toJSON(new UserDTO(null, null));

        // When, Then
        var resolvedException = mvc.perform(put(makePath("/{userUID}"), userUID)
                .content(invalidRequestBody)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn().getResolvedException();
        assertTrue(resolvedException instanceof MethodArgumentNotValidException);
        assertEquals("userDTO", ((MethodArgumentNotValidException) resolvedException).getObjectName());
        assertEquals(2, ((MethodArgumentNotValidException) resolvedException).getFieldErrors().size());
        verify(userService, never()).update(anyString(), any(UserDTO.class));
    }

    @DisplayName("Should display the success 'delete' message.")
    @Test
    void delete_success() throws Exception {
        // Given
        doNothing().when(userService).delete(anyString());

        // When, Then
        mvc.perform(delete(makePath("/{userUID}"), userUID))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("User deleted successfully."));
        verify(userService, times(1)).delete(userUID);
        verify(userService, times(1)).delete(anyString());
    }
}
