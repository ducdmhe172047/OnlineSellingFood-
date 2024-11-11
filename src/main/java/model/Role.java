package model;

public class Role {
    private Integer roleID;
    private String name;

    public Role() {
    }

    public Role(Integer roleID, String name) {
        this.roleID = roleID;
        this.name = name;
    }

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
