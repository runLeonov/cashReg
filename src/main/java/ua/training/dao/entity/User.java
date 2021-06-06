package ua.training.dao.entity;

import java.io.Serializable;

/**
 * @author LeonovOleksand
 */
public class User extends Model implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String password;
    private String email;
    private UserRole role;
    private int userRoleId;

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

        if (userRoleId != user.userRoleId) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        return role == user.role;
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

    public static class Builder {
        private User user;

        public Builder() {
            user = new User();
        }

        public Builder withName(String name) {
            user.name = name;
            return this;
        }

        public Builder withPassword(String password) {
            user.password = password;
            return this;
        }

        public Builder withEmail(String email) {
            user.email = email;
            return this;
        }

        public Builder withUserRoleId(int userRoleId) {
            user.userRoleId = userRoleId;
            return this;
        }

        public Builder withUserRole(UserRole role) {
            user.role = role;
            return this;
        }

        public User build() {
            return user;
        }
    }
}
