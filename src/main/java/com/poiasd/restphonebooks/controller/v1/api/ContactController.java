package com.poiasd.restphonebooks.controller.v1.api;

import com.poiasd.restphonebooks.dto.model.ContactDTO;
import com.poiasd.restphonebooks.service.ContactService;
import com.poiasd.restphonebooks.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user/{userUID}/contact")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @GetMapping(path = "{/all}")
    public Result<List<ContactDTO>> findAll(@PathVariable("userUID") String userUID) {
        Optional<List<ContactDTO>> contacts = contactService.findAllByUserUID(userUID);
        return contacts.map(Result::success).orElse(Result.failure("You don't have any contacts."));
    }

    @GetMapping("/{contactUID}")
    public ContactDTO getByUID(@PathVariable("userUID") String userUID,
                               @PathVariable("contactUID") String contactUID) {
        return contactService.getByUID(contactUID);
    }

    @GetMapping("/number/{number}")
    public Result<ContactDTO> findByPhoneNumber(@PathVariable("userUID") String userUID,
                                                @PathVariable("number") String phoneNumber) {
        Optional<ContactDTO> contacts = contactService.findByPhoneNumber(userUID, phoneNumber);
        return contacts.map(Result::success).orElse(Result.failure("No contact with the phone number " + phoneNumber + "."));
    }

    @PostMapping()
    public String create(@PathVariable("userUID") String userUID,
                         @Valid @RequestBody ContactDTO contactDTO) {
        contactService.create(userUID, contactDTO);
        return "Contact created successfully.";
    }

    @PutMapping("/{contactUID}")
    public String update(@PathVariable("userUID") String userUID,
                         @PathVariable("contactUID") String contactUID,
                         @Valid @RequestBody ContactDTO contactDTO) {
        contactService.update(contactUID, contactDTO);
        return "Contact updated successfully.";
    }

    @DeleteMapping("/{contactUID}")
    public String delete(@PathVariable("userUID") String userUID,
                         @PathVariable("contactUID") String contactUID) {
        contactService.delete(contactUID);
        return "Contact deleted successfully.";
    }
}
