package com.company.consultant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.company.consultant.dto.AddressDTO;

public interface AddressRepository extends JpaRepository<AddressDTO, Long>{

}
