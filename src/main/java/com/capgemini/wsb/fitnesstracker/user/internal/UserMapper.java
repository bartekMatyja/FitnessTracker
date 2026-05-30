package com.capgemini.wsb.fitnesstracker.user.internal;

import com.capgemini.wsb.fitnesstracker.user.api.User;
import org.springframework.stereotype.Component;

/**
 * Maps {@link User} entities to REST DTO records and back.
 */
@Component
class UserMapper {

    UserDto toDto(final User user) {
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getBirthdate(),
                user.getEmail());
    }

    UserSimpleDto toSimpleDto(final User user) {
        return new UserSimpleDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName());
    }

    UserEmailDto toEmailDto(final User user) {
        return new UserEmailDto(user.getId(), user.getEmail());
    }

    User toEntity(final UserDto userDto) {
        return new User(
                userDto.firstName(),
                userDto.lastName(),
                userDto.birthdate(),
                userDto.email());
    }

}
