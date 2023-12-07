package apapTutorial.bacabaca.service;

import apapTutorial.bacabaca.DTO.request.CreateUserRequestDTO;
import apapTutorial.bacabaca.DTO.request.LoginJwtRequestDTO;
import apapTutorial.bacabaca.DTO.request.LoginUserRequestDTO;
import apapTutorial.bacabaca.model.UserModel;

public interface UserService {
    UserModel addUser(UserModel user, CreateUserRequestDTO createUserRequestDTO);
    String encrypt(String password);
    String loginJwtAdmin(LoginJwtRequestDTO loginJwtRequestDTO);
    String loginJwtUser(LoginUserRequestDTO loginUserRequestDTO);
}
