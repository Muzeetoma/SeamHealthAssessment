package com.seamhealth.doctors.repositories;

import com.seamhealth.doctors.models.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Long> {

    Doctor findByEmailIgnoreCase(String email);


}
