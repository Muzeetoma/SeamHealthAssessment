package com.seamhealth.doctors.controllers;

import com.seamhealth.doctors.dto.DoctorRegisterRequest;
import com.seamhealth.doctors.dto.DoctorUpdateRequest;
import com.seamhealth.doctors.models.Doctor;
import com.seamhealth.doctors.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> register(@Valid @RequestBody DoctorRegisterRequest doctorRegisterRequest){
        return doctorService.register(doctorRegisterRequest);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> update(@Valid @RequestBody DoctorUpdateRequest doctorUpdateRequest){
        return doctorService.update(doctorUpdateRequest);
    }

    @GetMapping(path = "get-by-id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Doctor> getById(@RequestParam(defaultValue = "1", name = "id") Long id){
        return doctorService.getById(id);
    }

    @GetMapping(path = "get-all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<Doctor>> getAll(@RequestParam(defaultValue = "0", name = "page") int page,
                                         @RequestParam(defaultValue = "20", name = "record") int record){
        return doctorService.getAll(page, record);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteById(@RequestParam(name = "id") Long id){
        return doctorService.deleteById(id);
    }


}
