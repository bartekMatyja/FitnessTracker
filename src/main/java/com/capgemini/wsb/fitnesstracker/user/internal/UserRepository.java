package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                        .filter(user -> Objects.equals(user.getEmail(), email))
                        .findFirst();
    }

    /**
     * Searches users whose email contains the given fragment, ignoring letter case.
     *
     * @param emailFragment fragment of the email address
     * @return list of matching users
     */
    default List<User> findByEmailContainingIgnoreCase(String emailFragment) {
        final String normalizedFragment = emailFragment.toLowerCase(Locale.ROOT);
        return findAll().stream()
                        .filter(user -> user.getEmail().toLowerCase(Locale.ROOT).contains(normalizedFragment))
                        .toList();
    }

    /**
     * Searches users born before the given date.
     *
     * @param date threshold birth date
     * @return list of users with birth date earlier than the given date
     */
    default List<User> findBornBefore(LocalDate date) {
        return findAll().stream()
                        .filter(user -> user.getBirthdate().isBefore(date))
                        .toList();
    }

}
