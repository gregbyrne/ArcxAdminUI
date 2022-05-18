package com.app.arcx.repository;

import com.app.arcx.domain.StepsToHelpPrepare;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.jpa.repository.Query;
import java.util.List;



@RepositoryRestResource(collectionResourceRel = "steps_to_help_prepare", path = "steps_to_help_prepare")
public interface StepsToHelpPrepareRepository extends CrudRepository<StepsToHelpPrepare, Integer> {

    @Override
    @Query
    public List<StepsToHelpPrepare> findAll();

}




