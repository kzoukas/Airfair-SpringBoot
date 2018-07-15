package com.thesis.tuc.repository;

import com.thesis.tuc.services.rest.responseDTOs.AirLogo;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AirLogoRepository extends MongoRepository<AirLogo,String>  {


    List<AirLogo> findDistinctByCompanyName(String companyName);

}
