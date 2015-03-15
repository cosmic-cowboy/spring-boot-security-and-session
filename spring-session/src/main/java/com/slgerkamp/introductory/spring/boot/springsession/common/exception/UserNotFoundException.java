package com.slgerkamp.introductory.spring.boot.springsession.common.exception;

/**
 * セキュリティ関連の例外処理
 *
 */
@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userId) {
        super("could not find user '" + userId + "'.");
    }
}