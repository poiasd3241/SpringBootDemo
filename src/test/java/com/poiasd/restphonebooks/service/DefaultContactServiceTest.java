package com.poiasd.restphonebooks.service;

import com.poiasd.restphonebooks.dto.mapper.ContactMapper;
import com.poiasd.restphonebooks.dto.model.ContactDTO;
import com.poiasd.restphonebooks.model.Contact;
import com.poiasd.restphonebooks.repository.ContactRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

/**
 * The test class for the {@link DefaultContactService}.
 */
@ExtendWith(MockitoExtension.class)
class DefaultContactServiceTest {

    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private DefaultContactService contactService;

    private final String userUID = UUID.randomUUID().toString();
    private final String contactUID = UUID.randomUUID().toString();

    private final String phoneNumber = "+1";
    private final Contact contact = new Contact("ContactOne", phoneNumber);
    private final ContactDTO contactDTO = ContactMapper.toDTO(contact);
    private final List<Contact> contacts = List.of(contact);
    private final List<ContactDTO> contactsDTO = ContactMapper.toDtoList(contacts);

    @DisplayName("Should return all contacts of the specified user.")
    @Test
    void findByUserUID_success() {
        // Given
        when(contactRepository.findByUserUID(userUID)).thenReturn(Optional.of(contacts));

        // When
        var result = contactService.findByUserUID(userUID);

        // Then
        verify(contactRepository, times(1)).findByUserUID(userUID);
        verify(contactRepository, times(1)).findByUserUID(anyString());
        assertTrue(result.isPresent());
        assertEquals(contactsDTO, result.get());
    }

    @DisplayName("Should return the specified user's contact matching the specified phone number.")
    @Test
    void findByPhoneNumber_success() {
        // Given
        when(contactRepository.findByPhoneNumber(userUID, phoneNumber)).thenReturn(Optional.of(contact));

        // When
        var result = contactService.findByPhoneNumber(userUID, phoneNumber);

        // Then
        verify(contactRepository, times(1)).findByPhoneNumber(userUID, phoneNumber);
        verify(contactRepository, times(1)).findByPhoneNumber(anyString(), anyString());
        assertTrue(result.isPresent());
        assertEquals(contactDTO, result.get());
    }


    @DisplayName("Should return the contact matching the specified UID.")
    @Test
    void getByUID_success() {
        // Given
        when(contactRepository.getByUID(contactUID)).thenReturn(contact);

        // When
        var result = contactService.getByUID(contactUID);

        // Then
        verify(contactRepository, times(1)).getByUID(contactUID);
        verify(contactRepository, times(1)).getByUID(anyString());
        assertEquals(contactDTO, result);
    }

    @DisplayName("Should make a call to the repository's 'create' method.")
    @Test
    void create_success() {
        // Given
        doNothing().when(contactRepository).create(anyString(), any(Contact.class));

        // When
        contactService.create(userUID, contactDTO);

        // Then
        verify(contactRepository, times(1)).create(userUID, contact);
        verify(contactRepository, times(1)).create(anyString(), any(Contact.class));
    }

    @DisplayName("Should make a call to the repository's 'update' method.")
    @Test
    void update_success() {
        // Given
        doNothing().when(contactRepository).update(anyString(), any(Contact.class));

        // When
        contactService.update(contactUID, contactDTO);

        // Then
        verify(contactRepository, times(1)).update(contactUID, contact);
        verify(contactRepository, times(1)).update(anyString(), any(Contact.class));
    }

    @DisplayName("Should make a call to the repository's 'delete' method.")
    @Test
    void delete_success() {
        // Given
        doNothing().when(contactRepository).delete(anyString());

        // When
        contactService.delete(contactUID);

        // Then
        verify(contactRepository, times(1)).delete(contactUID);
        verify(contactRepository, times(1)).delete(anyString());
    }
}
