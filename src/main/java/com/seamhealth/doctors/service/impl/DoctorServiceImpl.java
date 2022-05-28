package com.seamhealth.doctors.service.impl;

import com.seamhealth.doctors.dto.DoctorRegisterRequest;
import com.seamhealth.doctors.dto.DoctorUpdateRequest;
import com.seamhealth.doctors.models.Address;
import com.seamhealth.doctors.models.Doctor;
import com.seamhealth.doctors.repositories.AddressRepository;
import com.seamhealth.doctors.repositories.DoctorRepository;
import com.seamhealth.doctors.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final AddressRepository addressRepository;

    @Override
    public ResponseEntity<Doctor> register(DoctorRegisterRequest doctorRegisterRequest) {

        Doctor _doctor = doctorRepository.findByEmailIgnoreCase(doctorRegisterRequest.getEmail());

        if(Objects.nonNull(_doctor)){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        Address address = Address.builder()
                .homeAddress(doctorRegisterRequest.getHomeAddress())
                .state(doctorRegisterRequest.getState())
                .lga(doctorRegisterRequest.getLga())
                .build();

        Doctor doctor = Doctor.builder()
                .firstName(doctorRegisterRequest.getFirstName())
                .lastName(doctorRegisterRequest.getLastName())
                .email(doctorRegisterRequest.getEmail())
                .phoneNumber(doctorRegisterRequest.getPhoneNumber())
                .address(address)
                .build();

        doctorRepository.save(doctor);

        return new ResponseEntity<>(doctor,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Doctor> update(DoctorUpdateRequest doctorUpdateRequest) {

        Optional<Doctor> doctor = doctorRepository.findById(doctorUpdateRequest.getId());

        if(doctor.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        Doctor __doctor = doctorRepository.findByEmailIgnoreCase(doctorUpdateRequest.getEmail());

        if(Objects.nonNull(__doctor)&& !doctorUpdateRequest.getEmail().equals(doctor.get().getEmail())){
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }

        Doctor doctor1 = doctor.get();

        Address address = addressRepository.findByDoctor(doctor1);

        address.setHomeAddress(doctorUpdateRequest.getHomeAddress());
        address.setState(doctorUpdateRequest.getState());
        address.setLga(doctorUpdateRequest.getLga());
        addressRepository.save(address);

        doctor1.setFirstName(doctorUpdateRequest.getFirstName());
        doctor1.setLastName(doctorUpdateRequest.getLastName());
        doctor1.setEmail(doctorUpdateRequest.getEmail());
        doctor1.setPhoneNumber(doctorUpdateRequest.getPhoneNumber());

        doctorRepository.save(doctor1);


        return new ResponseEntity<>(doctor1,HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Doctor> getById(Long id) {
        if(id==0 || id < 1){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Optional<Doctor> doctorOptional = doctorRepository.findById(id);

        return doctorOptional.map(doctor ->
                        new ResponseEntity<>(doctor, HttpStatus.OK))
                       .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<Void> deleteById(Long id) {
        if(id==0 || id < 1){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        Optional<Doctor> doctorOptional = doctorRepository.findById(id);
        if(doctorOptional.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        doctorRepository.delete(doctorOptional.get());

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Page<Doctor>> getAll(int page, int record) {

        Pageable pageable = PageRequest.of(page,record);

        Page<Doctor> doctorPage = doctorRepository.findAll(pageable);

        return new ResponseEntity<>(doctorPage, HttpStatus.OK);
    }
}
