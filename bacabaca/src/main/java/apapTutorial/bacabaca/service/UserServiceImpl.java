package apapTutorial.bacabaca.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import apapTutorial.bacabaca.DTO.request.CreateUserRequestDTO;
import apapTutorial.bacabaca.DTO.request.LoginJwtRequestDTO;
import apapTutorial.bacabaca.DTO.request.LoginUserRequestDTO;
import apapTutorial.bacabaca.model.UserModel;
import apapTutorial.bacabaca.repository.UserDb;
import apapTutorial.bacabaca.security.jwt.JwtUtils;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDb userDb;
    @Autowired
    private RoleService roleService;
    @Autowired
    private JwtUtils jwtUtils;

    @Override 
    public UserModel addUser(UserModel user, CreateUserRequestDTO createUserRequestDTO) {
        user.setRole((roleService.getRoleByRoleName(createUserRequestDTO.getRole())));
        String hashedPass = encrypt(user.getPassword());
        user.setPassword(hashedPass);
        return userDb.save(user);
    }

    @Override 
    public String encrypt(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    @Override 
    public String loginJwtAdmin(LoginJwtRequestDTO loginJwtRequestDTO) {
        String username = loginJwtRequestDTO.getUsername();
        String name = loginJwtRequestDTO.getName();

        UserModel user = userDb.findByUsername(username);

        if (user == null) {
            user = new UserModel();
            user.setName(name);
            user.setPassword(encrypt("bacabaca"));
            user.setUsername(username);
            user.setRole(roleService.getRoleByRoleName("Admin"));
            userDb.save(user);
        }
        return jwtUtils.generateJwtToken(loginJwtRequestDTO.getUsername());
    }

    @Override
    public String loginJwtUser(LoginUserRequestDTO loginUserRequestDTO) {
        String username = loginUserRequestDTO.getUsername();
        String password = loginUserRequestDTO.getPassword();

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  

        UserModel user = userDb.findByUsername(username);

        if (user == null) throw new NullPointerException("Username atau password salah");
        if (!encoder.matches(password, user.getPassword())) throw new NullPointerException("Username atau password salah");
        
        return jwtUtils.generateJwtToken(loginUserRequestDTO.getUsername());
    }
}
