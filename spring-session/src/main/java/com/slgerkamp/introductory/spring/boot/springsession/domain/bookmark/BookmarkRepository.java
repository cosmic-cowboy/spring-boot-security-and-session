package com.slgerkamp.introductory.spring.boot.springsession.domain.bookmark;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
	
    Collection<Bookmark> findByAccountUsername(String username);

}