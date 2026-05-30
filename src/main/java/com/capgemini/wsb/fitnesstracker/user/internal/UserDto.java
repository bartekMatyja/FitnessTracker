package com.capgemini.wsb.fitnesstracker.user.internal;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.annotation.Nullable;

import java.time.LocalDate;

/**
 * Data transfer object representing full user details exposed through the REST API.
 *
 * @param id        unique identifier of the user, {@code null} when creating a new user
 * @param firstName user's first name
 * @param lastName  user's last name
 * @param birthdate user's date of birth
 * @param email     user's unique email address
 */
record UserDto(@Nullable Long id, String firstName, String lastName,
               @JsonFormat(pattern = "yyyy-MM-dd") LocalDate birthdate,
               String email) {

}
