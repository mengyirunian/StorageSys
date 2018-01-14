package com.mengyirunian.entity;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.id
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.service_code
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    private Integer serviceCode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.service_name
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    private String serviceName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.role_name
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    private String roleName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.valid
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    private Integer valid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.create_at
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    private Date createAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column role.update_at
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    private Date updateAt;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table role
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.id
     *
     * @return the value of role.id
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.id
     *
     * @param id the value for role.id
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.service_code
     *
     * @return the value of role.service_code
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    public Integer getServiceCode() {
        return serviceCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.service_code
     *
     * @param serviceCode the value for role.service_code
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    public void setServiceCode(Integer serviceCode) {
        this.serviceCode = serviceCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.service_name
     *
     * @return the value of role.service_name
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    public String getServiceName() {
        return serviceName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.service_name
     *
     * @param serviceName the value for role.service_name
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName == null ? null : serviceName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.role_name
     *
     * @return the value of role.role_name
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.role_name
     *
     * @param roleName the value for role.role_name
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.valid
     *
     * @return the value of role.valid
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    public Integer getValid() {
        return valid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.valid
     *
     * @param valid the value for role.valid
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    public void setValid(Integer valid) {
        this.valid = valid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.create_at
     *
     * @return the value of role.create_at
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    public Date getCreateAt() {
        return createAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.create_at
     *
     * @param createAt the value for role.create_at
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column role.update_at
     *
     * @return the value of role.update_at
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    public Date getUpdateAt() {
        return updateAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column role.update_at
     *
     * @param updateAt the value for role.update_at
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table role
     *
     * @mbggenerated Sun Jan 14 17:56:40 CST 2018
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", serviceCode=").append(serviceCode);
        sb.append(", serviceName=").append(serviceName);
        sb.append(", roleName=").append(roleName);
        sb.append(", valid=").append(valid);
        sb.append(", createAt=").append(createAt);
        sb.append(", updateAt=").append(updateAt);
        sb.append("]");
        return sb.toString();
    }
}