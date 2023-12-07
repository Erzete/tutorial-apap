package apapTutorial.webadmin.restservice;

import apapTutorial.webadmin.DTO.UserRequestDTO;
import apapTutorial.webadmin.DTO.UserResponseDTO;

public interface UserRestService {
    String getToken(String username, String name);
    UserResponseDTO sendUser(UserRequestDTO userDTO, String jwtToken);
}
