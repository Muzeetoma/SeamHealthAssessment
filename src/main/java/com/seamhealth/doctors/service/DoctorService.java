package com.seamhealth.doctors.service;

import com.seamhealth.doctors.dto.DoctorRegisterRequest;
import com.seamhealth.doctors.dto.DoctorUpdateRequest;
import com.seamhealth.doctors.models.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface DoctorService {

    ResponseEntity<Doctor> register(DoctorRegisterRequest doctorRegisterRequest);

    ResponseEntity<Doctor> update(DoctorUpdateRequest doctorUpdateRequest);

    ResponseEntity<Doctor> getById(Long id);

    ResponseEntity<Void> deleteById(Long id);

    ResponseEntity<Page<Doctor>> getAll(int page, int record);
}
