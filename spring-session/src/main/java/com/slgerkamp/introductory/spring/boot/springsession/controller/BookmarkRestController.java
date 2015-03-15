package com.slgerkamp.introductory.spring.boot.springsession.controller;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.slgerkamp.introductory.spring.boot.springsession.common.exception.UserNotFoundException;
import com.slgerkamp.introductory.spring.boot.springsession.domain.account.AccountRepository;
import com.slgerkamp.introductory.spring.boot.springsession.domain.bookmark.Bookmark;
import com.slgerkamp.introductory.spring.boot.springsession.domain.bookmark.BookmarkRepository;
import com.slgerkamp.introductory.spring.boot.springsession.domain.bookmark.BookmarkResource;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkRestController {

    @Autowired
    BookmarkRepository bookmarkRepository;

    @Autowired
    AccountRepository accountRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> add(Principal principal, @RequestBody Bookmark in) {
        String userId = principal.getName();
        return accountRepository
                .findByUsername(userId)
                .map(account -> {
                    Bookmark bookmark = bookmarkRepository
                            .save(new Bookmark(account, in.uri, in.description));
                    Link selfLink = new BookmarkResource(bookmark).getLink("self");
                    URI location = URI.create(selfLink.getHref());
                    return ResponseEntity.created(location).body(bookmark);
                })
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    @RequestMapping(value = "/{bookmarkId}", method = RequestMethod.GET)
    public BookmarkResource readBookmark(Principal principal, @PathVariable Long bookmarkId) {
        String userId = principal.getName();
        this.validateUser(userId);
        return new BookmarkResource(this.bookmarkRepository.findOne(bookmarkId));
    }

    @RequestMapping(method = RequestMethod.GET)
    public Resources<BookmarkResource> readBookmarks(Principal principal) {
        String userId = principal.getName();
        this.validateUser(userId);

        List<BookmarkResource> bookmarkResourceList = bookmarkRepository
                .findByAccountUsername(userId).stream().map(BookmarkResource::new)
                .collect(Collectors.toList());
        return new Resources<>(bookmarkResourceList);
    }

    private void validateUser(String userId) {
        this.accountRepository.findByUsername(userId).orElseThrow(
                () -> new UserNotFoundException(userId));
    }
}
