package ua.training.dao.entity;

public class User extends Model {
    private String name;
    private String password;
    private String email;
    private UserRole role;
    private int userRoleId;

    public User(String name, String password, String email, UserRole role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(String name, String password, String email, UserRole role, int userRoleId) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.userRoleId = userRoleId;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return email != null ? email.equals(user.email) : user.email == null;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", userRoleId=" + userRoleId +
                '}';
    }

    @Override
    public int hashCode() {
        return email != null ? email.hashCode() : 0;
    }

    public void setIdRole(int userRoleId) {
        this.userRoleId = userRoleId;

    }

    public void setRoleById(int i) {

        this.role = getRoleById(i);

    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public UserRole getRoleById(int roleId) {
        if (roleId == 2) {
            return UserRole.SENIOR_CASHIER;
        } else if (roleId == 3) {
            return UserRole.COMMODITY_EXPERT;
        } else {
            return UserRole.CASHIER;
        }
    }
}
