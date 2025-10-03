package com.rkumar.talebloom.repositories;

import com.rkumar.talebloom.entities.ViewHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewHistoryRepository extends JpaRepository<ViewHistoryEntity, Long> {
}