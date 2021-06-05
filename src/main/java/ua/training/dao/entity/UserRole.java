package ua.training.dao.entity;


/**
 * @author LeonovOleksand
 */
public enum UserRole {
    CASHIER(1), SENIOR_CASHIER(2), COMMODITY_EXPERT(3), DEFAULT(4);

    private UserRole userRole;
    private int roleId;

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    UserRole(int roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }


}

