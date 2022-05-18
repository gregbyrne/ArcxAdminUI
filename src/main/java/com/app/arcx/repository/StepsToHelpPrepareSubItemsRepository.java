package com.app.arcx.repository;

import com.app.arcx.domain.StepsToHelpPrepareSubItems;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "steps_to_help_prepare_sub_items", path = "steps_to_help_prepare_sub_items")
public interface StepsToHelpPrepareSubItemsRepository extends CrudRepository<StepsToHelpPrepareSubItems, Integer> {

}
