package com.app.arcx.services.impl;

import com.app.arcx.domain.AreaOfInterestItems;
import com.app.arcx.domain.AreaOfInterestSubItems;
import com.app.arcx.services.AreaOfInterestService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class AreaOfInterestServiceImpl implements AreaOfInterestService {

    @PersistenceContext
    protected EntityManager entityManager;

    @Transactional
    @Override
    public void deleteAreaOfInterest(int aoi_id) {
        try {

            List<AreaOfInterestItems> areaOfInterestItemsList = entityManager.createQuery("SELECT p FROM AreaOfInterestItems p WHERE p.parentid = " + aoi_id, AreaOfInterestItems.class).getResultList();


            for (AreaOfInterestItems aoi_items : areaOfInterestItemsList)
            {
                entityManager.createQuery("delete from AreaOfInterestSubItems p where p.parentid=:aoi_items_id")
                        .setParameter("aoi_items_id", aoi_items.getId())
                        .executeUpdate();

                entityManager.createQuery("delete from AreaOfInterestItems p where p.id=:aoi_items_id")
                        .setParameter("aoi_items_id", aoi_items.getId())
                        .executeUpdate();
            }

            entityManager.createQuery("delete from AreaOfInterest p where p.id=:aoi_id")
                    .setParameter("aoi_id", aoi_id)
                    .executeUpdate();

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }


    @Transactional
    @Override
    public void deleteAreaOfInterestItem(int item_id) {
        try {

            entityManager.createQuery("delete from AreaOfInterestSubItems p where p.parentid=:item_id")
                    .setParameter("item_id", item_id)
                    .executeUpdate();


            entityManager.createQuery("delete from AreaOfInterestItems p where p.id=:item_id")
                    .setParameter("item_id", item_id)
                    .executeUpdate();

        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }


}
