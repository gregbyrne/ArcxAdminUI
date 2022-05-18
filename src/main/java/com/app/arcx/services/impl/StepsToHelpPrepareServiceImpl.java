package com.app.arcx.services.impl;

import com.app.arcx.services.StepsToHelpPrepareService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class StepsToHelpPrepareServiceImpl implements StepsToHelpPrepareService {

    @PersistenceContext
    protected EntityManager entityManager;

    @Transactional
    @Override
    public void deleteStepToHelpPrepare(int step_item_id) {
        try {

            entityManager.createQuery("delete from StepsToHelpPrepareItems p where p.parentid=:step_item_id")
                    .setParameter("step_item_id", step_item_id)
                    .executeUpdate();


            entityManager.createQuery("delete from StepsToHelpPrepare p where p.id=:step_item_id")
                    .setParameter("step_item_id", step_item_id)
                    .executeUpdate();

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    @Transactional
    @Override
    public void deleteStepToHelpPrepareItem(int step_item_id) {
        try {

            entityManager.createQuery("delete from StepsToHelpPrepareItems p where p.id=:step_item_id")
                    .setParameter("step_item_id", step_item_id)
                    .executeUpdate();

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
