package com.capgemini.wsb.fitnesstracker.user.internal;

/**
 * Minimal user representation used for email-based search results.
 *
 * @param id    unique identifier of the user
 * @param email user's email address
 */
record UserEmailDto(Long id, String email) {

}
