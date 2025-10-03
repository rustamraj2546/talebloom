package com.rkumar.talebloom.repositories;

import com.rkumar.talebloom.entities.BookmarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkRepository extends JpaRepository<BookmarkEntity, Long> {
}