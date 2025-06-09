package pl.wsb.mapper;

import pl.wsb.doto.UserDto;
import pl.wsb.doto.UserDetailDto;
import pl.wsb.fitnesstracker.user.api.User;


public class UserMapper {

    public UserDto toUserDto(User user) {
        if (user == null) return null;
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName());
    }

    public static UserDetailDto toUserDetailDto(User user) {
        if (user == null) return null;
        return new UserDetailDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getBirthDate()
        );
    }

    public static User toUserEntity(UserDetailDto dto) {
        if (dto == null) return null;
        User user = new User();
        user.setId(dto.id());
        user.setFirstName(dto.firstName());
        user.setLastName(dto.lastName());
        user.setEmail(dto.email());
        user.setBirthDate(dto.birthDate());
        return user;
    }
}
