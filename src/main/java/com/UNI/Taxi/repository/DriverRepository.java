package com.UNI.Taxi.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.UNI.Taxi.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface DriverRepository extends JpaRepository<Driver,Long>
{
    List<Driver> findByFamilyContainingIgnoreCase(String keyword);
    List<Driver> findByNameContainingIgnoreCase(String name);
    @Query("UPDATE Driver t SET t.published = :published WHERE t.id = :id")
    @Modifying
    public void updatePublishedStatus(Long id, boolean published);
}
