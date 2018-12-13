package com.motel666.usereventservice.repository;


import com.motel666.usereventservice.entity.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

public interface UserEventRepository extends CrudRepository<Event, Integer> {

    @Query("SELECT c from Event c where c.userId = ?1")
    List<Event> findByUserId(String userId);

    @Query("SELECT c from Event c where c.eventTime> ?1 and c.eventTime< ?2")
    List<Event> findByDate(Date dateFrom, Date dateTo);

}
