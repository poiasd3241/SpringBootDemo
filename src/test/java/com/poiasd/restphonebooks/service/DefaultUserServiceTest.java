package com.poiasd.restphonebooks.service;

import com.poiasd.restphonebooks.dto.mapper.UserMapper;
import com.poiasd.restphonebooks.dto.model.UserDTO;
import com.poiasd.restphonebooks.model.Contact;
import com.poiasd.restphonebooks.model.PhoneBook;
import com.poiasd.restphonebooks.model.User;
import com.poiasd.restphonebooks.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * The test class for the {@link DefaultUserService}.
 */
@ExtendWith(MockitoExtension.class)
class DefaultUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private DefaultUserService userService;

    private final String userUID = UUID.randomUUID().toString();

    private final List<Contact> contacts = List.of(new Contact("ContactOne", "+1"));

    private final String fullUserName = "UserOne";
    private final User user = new User(fullUserName, new PhoneBook(contacts));
    private final UserDTO userDTO = UserMapper.toDTO(user);
    private final List<User> users = List.of(user);
    private final List<UserDTO> usersDTO = UserMapper.toDtoList(users);

    @DisplayName("Should return all users.")
    @Test
    void findAll_success() {
        // Given
        when(userRepository.findAll()).thenReturn(Optional.of(users));

        // When
        var result = userService.findAll();

        // Then
        verify(userRepository, times(1)).findAll();
        assertTrue(result.isPresent());
        assertEquals(usersDTO, result.get());
    }

    @DisplayName("Should return all users matching the specified name.")
    @Test
    void findByName_success() {
        // Given
        // Full/partial name search doesn't affect the service behavior when at least one user is retrieved.
        var isFullName = true;

        when(userRepository.findByName(fullUserName, isFullName)).thenReturn(Optional.of(users));

        // When
        var result = userService.findByName(fullUserName, isFullName);

        // Then
        verify(userRepository, times(1)).findByName(fullUserName, isFullName);
        verify(userRepository, times(1)).findByName(anyString(), anyBoolean());
        assertTrue(result.isPresent());
        assertEquals(usersDTO, result.get());
    }

    @DisplayName("Should return the user matching the specified UID.")
    @Test
    void getByUID_success() {
        // Given
        when(userRepository.getByUID(userUID)).thenReturn(user);

        // When
        var result = userService.getByUID(userUID);

        // Then
        verify(userRepository, times(1)).getByUID(userUID);
        verify(userRepository, times(1)).getByUID(anyString());
        assertNotNull(result);
        assertEquals(userDTO, result);
    }

    @DisplayName("Should make a call to the repository's 'create' method.")
    @Test
    void create_success() {
        // Given
        doNothing().when(userRepository).create(any(User.class));

        // When
        userService.create(userDTO);

        // Then
        verify(userRepository, times(1)).create(user);
        verify(userRepository, times(1)).create(any(User.class));
    }

    @DisplayName("Should make a call to the repository's 'update' method.")
    @Test
    void update_success() {
        // Given
        doNothing().when(userRepository).update(anyString(), any(User.class));

        // When
        userService.update(userUID, userDTO);

        // Then
        verify(userRepository, times(1)).update(userUID, user);
        verify(userRepository, times(1)).update(anyString(), any(User.class));
    }

    @DisplayName("Should make a call to the repository's 'delete' method.")
    @Test
    void delete_success() {
        // Given
        doNothing().when(userRepository).delete(anyString());

        // When
        userService.delete(userUID);

        // Then
        verify(userRepository, times(1)).delete(userUID);
        verify(userRepository, times(1)).delete(anyString());
    }
}
