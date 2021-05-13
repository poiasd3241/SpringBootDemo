package com.poiasd.restphonebooks.controller.v1.api;

import com.poiasd.restphonebooks.dto.model.ContactDTO;
import com.poiasd.restphonebooks.service.ContactService;
import com.poiasd.restphonebooks.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * The controller for interacting with the {@link ContactDTO} persistence through a subordinate link, the {@link ContactService}.
 */
@RestController
@RequestMapping("/api/v1/user/{userUID}/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    /**
     * Returns all contacts of the specified user, or, if the are none, the helpful message.
     *
     * @param userUID The user UID.
     */
    @GetMapping(path = "/all")
    public Result<List<ContactDTO>> findAll(@PathVariable("userUID") String userUID) {
        Optional<List<ContactDTO>> contacts = contactService.findByUserUID(userUID);
        return contacts.map(Result::success).orElse(Result.failure("You don't have any contacts."));
    }

    /**
     * Returns the the specified contact.
     *
     * @param userUID    The user UID.
     * @param contactUID The contact UID.
     */
    @GetMapping("/{contactUID}")
    public ContactDTO getByUID(@PathVariable("userUID") String userUID,
                               @PathVariable("contactUID") String contactUID) {
        return contactService.getByUID(contactUID);
    }

    /**
     * Returns a contact matching the specified phone number and belongs to the specified user.
     * If no such contact is found, returns the helpful message.
     *
     * @param userUID     The user UID.
     * @param phoneNumber The phone number.
     */
    @GetMapping("/number/{phoneNumber}")
    public Result<ContactDTO> findByPhoneNumber(@PathVariable("userUID") String userUID,
                                                @PathVariable("phoneNumber") String phoneNumber) {
        Optional<ContactDTO> contacts = contactService.findByPhoneNumber(userUID, phoneNumber);
        return contacts.map(Result::success).orElse(Result.failure(String.format("No contact with the phone number %s.", phoneNumber)));
    }

    /**
     * Creates a new contact for the specified user, using the specified contact data.
     * Returns the success message.
     *
     * @param userUID    The user UID.
     * @param contactDTO The contact data to save.
     */
    @PostMapping()
    public String create(@PathVariable("userUID") String userUID,
                         @Valid @RequestBody ContactDTO contactDTO) {
        contactService.create(userUID, contactDTO);
        return "Contact created successfully.";
    }

    /**
     * Updates the specified contact, using the specified updated contact data.
     * Returns the success message.
     *
     * @param userUID    The user UID.
     * @param contactUID The contact UID.
     * @param contactDTO The updated contact data.
     */
    @PutMapping("/{contactUID}")
    public String update(@PathVariable("userUID") String userUID,
                         @PathVariable("contactUID") String contactUID,
                         @Valid @RequestBody ContactDTO contactDTO) {
        contactService.update(contactUID, contactDTO);
        return "Contact updated successfully.";
    }

    /**
     * Deletes the specified contact.
     * Returns the success message.
     *
     * @param userUID    The user UID.
     * @param contactUID The contact UID.
     */
    @DeleteMapping("/{contactUID}")
    public String delete(@PathVariable("userUID") String userUID,
                         @PathVariable("contactUID") String contactUID) {
        contactService.delete(contactUID);
        return "Contact deleted successfully.";
    }
}
