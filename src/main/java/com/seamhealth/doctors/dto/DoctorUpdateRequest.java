package com.seamhealth.doctors.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DoctorUpdateRequest {

    @NotNull(message = "Id cannot be null")
    private Long id;

    @NotBlank(message = "Doctor's firstName should not be blank")
    private String firstName;

    @NotBlank(message = "Doctor's lastName should not be blank")
    private String lastName;

    @NotBlank(message = "Doctor's email should not be blank")
    @Email(message = "Doctor's email must be valid")
    private String email;

    @NotBlank(message = "Doctor's phoneNumber should not be blank")
    private String phoneNumber;

    @NotBlank(message = "Doctor's homeAddress should not be blank")
    private String homeAddress;

    @NotBlank(message = "Doctor's State should not be blank")
    private String state;

    @NotBlank(message = "Doctor's LGA should not be blank")
    private String lga;

}
