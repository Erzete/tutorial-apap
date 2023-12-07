package apapTutorial.bacabaca.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import apapTutorial.bacabaca.DTO.UserMapper;
import apapTutorial.bacabaca.DTO.request.CreateUserRequestDTO;
import apapTutorial.bacabaca.DTO.request.LoginJwtRequestDTO;
import apapTutorial.bacabaca.DTO.request.LoginUserRequestDTO;
import apapTutorial.bacabaca.DTO.response.CreateUserResponseDTO;
import apapTutorial.bacabaca.DTO.response.LoginJwtResponseDTO;
import apapTutorial.bacabaca.model.UserModel;
import apapTutorial.bacabaca.service.UserService;

@RestController
@RequestMapping("/api")
public class UserRestController {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @PostMapping(value = "/user/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<?> addUser(@RequestBody CreateUserRequestDTO createUserRequestDTO) {
        try{
            UserModel userModel = userMapper.createUserRequestDTOToUserModel(createUserRequestDTO);
            userModel = userService.addUser(userModel, createUserRequestDTO);

            CreateUserResponseDTO createUserResponseDTO = userMapper.createUserResponseDTOToUserModel(userModel);
            createUserResponseDTO.setRole(userModel.getRole().getRole());
            return new ResponseEntity<>(createUserResponseDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginJwtUser(@RequestBody LoginUserRequestDTO loginUserRequestDTO) {
        try {
            String jwtToken = userService.loginJwtUser(loginUserRequestDTO);
            return new ResponseEntity<>(new LoginJwtResponseDTO(jwtToken), HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("message", "User atau password salah");
            errorResponse.put("status", false);

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(errorResponse);
        }
    }
}
