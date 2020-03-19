package me.dinosauruncle.msa.account.domain;



import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
public class Account {

    @Id @Size(max = 250)
    private String id;

    private String name;

    private String password;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    private String email;

    private String phone;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void update(Account account){
        if ((!this.getName().equals(account.getName())) && StringUtils.isNotEmpty(account.getName()) ){
            this.setName(account.getName());
        }
        if (!this.getGender().equals(account.getGender())
                && (account.getGender() != null)){
            this.setGender(account.getGender());
        }
        if (!this.getEmail().equals(account.getEmail())
                && StringUtils.isNotEmpty(account.getEmail()) ){
            this.setEmail(account.getEmail());
        }
        if (!this.getPhone().equals(account.getPhone())
                && StringUtils.isNotEmpty(account.getPhone())){
            this.setPhone(account.getPhone());
        }

    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
