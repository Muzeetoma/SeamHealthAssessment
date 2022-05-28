package com.seamhealth.doctors.repositories;

import com.seamhealth.doctors.models.Address;
import com.seamhealth.doctors.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

    Address findByDoctor(Doctor doctor);

}
