package co.istad.ecommerceapi.mapper;

import co.istad.ecommerceapi.features.auth.dto.RegisterRequest;
import co.istad.ecommerceapi.domain.User;
import co.istad.ecommerceapi.features.user.dto.UpdateUserRequest;
import co.istad.ecommerceapi.features.user.dto.UserRequest;
import co.istad.ecommerceapi.features.user.dto.UserResponse;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromRegister (RegisterRequest registerRequest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUpdateUserRequestPartially(UpdateUserRequest updateUserRequest,
                                        @MappingTarget User user);

    User fromUserRequest(UserRequest userRequest);

    List<UserResponse> toUserListResponse(List<User> users);

    UserResponse toUserResponse(User user);
}
