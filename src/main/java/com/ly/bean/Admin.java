package com.ly.bean;

import java.io.Serializable;

/**
 * @author qlz小羽 SAMS
 * @create 2020-10-11 16:36
 */
public class Admin implements Serializable {
    private int admin_id;
    private String admin_sex;
    private String admin_name;
    private String admin_password;
    private String admin_email;
    private String admin_img;
    private String admin_role;

    public String getAdmin_role() {
        return admin_role;
    }

    public void setAdmin_role(String admin_role) {
        this.admin_role = admin_role;
    }

    public int getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(int admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_sex() {
        return admin_sex;
    }

    public void setAdmin_sex(String admin_sex) {
        this.admin_sex = admin_sex;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public void setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
    }

    public String getAdmin_email() {
        return admin_email;
    }

    public void setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
    }

    public String getAdmin_img() {
        return admin_img;
    }

    public void setAdmin_img(String admin_img) {
        this.admin_img = admin_img;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + admin_id +
                ", admin_sex='" + admin_sex + '\'' +
                ", admin_name='" + admin_name + '\'' +
                ", admin_password='" + admin_password + '\'' +
                ", admin_email='" + admin_email + '\'' +
                ", admin_img='" + admin_img + '\'' +
                ", admin_role='" + admin_role + '\'' +
                '}';
    }
}
