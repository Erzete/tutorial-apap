package apapTutorial.webadmin.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import apapTutorial.webadmin.DTO.UserRequestDTO;
import apapTutorial.webadmin.DTO.UserResponseDTO;
import apapTutorial.webadmin.restservice.UserRestService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserRestService userRestService;

    @GetMapping("/user/add")
    public String formAddUser(Model model) {
        UserRequestDTO userDTO = new UserRequestDTO();
        List<String> roles = new ArrayList<>();
        roles.add("Admin");
        roles.add("User");
        roles.add("Pustakawan");
        model.addAttribute("listRole", roles);
        model.addAttribute("userDTO", userDTO);
        return "form-add-user";
    }

    @PostMapping("/user/add")
    public String submitUser(@ModelAttribute UserRequestDTO userDTO, Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String jwtToken = (String) session.getAttribute("token");
        UserResponseDTO userResultDTO = userRestService.sendUser(userDTO, jwtToken);
        if (userResultDTO.getId() == null) {
            return "error-add-user";
        }
        model.addAttribute("user", userResultDTO);
        return "success-add-user";
    }
}
