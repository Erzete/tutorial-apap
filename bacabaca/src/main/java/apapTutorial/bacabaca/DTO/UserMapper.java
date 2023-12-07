package apapTutorial.bacabaca.DTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import apapTutorial.bacabaca.DTO.request.CreateUserRequestDTO;
import apapTutorial.bacabaca.DTO.response.CreateUserResponseDTO;
import apapTutorial.bacabaca.model.UserModel;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "role", ignore = true)
    UserModel createUserRequestDTOToUserModel(CreateUserRequestDTO createUserRequestDTO);

    @Mapping(target = "role", ignore = true)
    CreateUserResponseDTO createUserResponseDTOToUserModel(UserModel userModel);
}
