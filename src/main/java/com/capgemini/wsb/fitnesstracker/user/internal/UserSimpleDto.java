package com.capgemini.wsb.fitnesstracker.user.internal;

/**
 * Simplified user representation containing only identifier and name fields.
 *
 * @param id        unique identifier of the user
 * @param firstName user's first name
 * @param lastName  user's last name
 */
record UserSimpleDto(Long id, String firstName, String lastName) {

}
