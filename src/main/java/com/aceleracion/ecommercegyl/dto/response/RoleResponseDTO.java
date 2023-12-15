package com.aceleracion.ecommercegyl.dto.response;


public class RoleResponseDTO {
    private Long roleId;
    private String roleName;
    private Boolean status;

    public RoleResponseDTO() {
    }

    public RoleResponseDTO(Long roleId, String roleName, Boolean status) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.status = status;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
