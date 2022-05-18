package com.app.arcx.repository;

import com.app.arcx.domain.AdditionalInformation;
import com.app.arcx.domain.AreaOfInterest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "additional_information", path = "additional_information")
public interface AdditionalInformationRepository extends CrudRepository<AdditionalInformation, Integer> {

    @Override
    @Query
    public List<AdditionalInformation> findAll();


}
