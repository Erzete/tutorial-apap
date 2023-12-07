package apapTutorial.bacabaca.service;

import apapTutorial.bacabaca.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllList();
    Role getRoleByRoleName(String name);
}
