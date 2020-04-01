package me.dinosauruncle.service.portal.domain;



import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Account {

    @Id @Size(max = 250)
    private String accountId;

    private String accountName;

    private String password;

    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    private String email;

    private String phone;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "account")
    private List<AccountMappingRole> accountMappingRoles = new ArrayList<AccountMappingRole>();

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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
        if ((!this.getAccountName().equals(account.getAccountName())) && StringUtils.isNotEmpty(account.getAccountName()) ){
            this.setAccountName(account.getAccountName());
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
                "+accountId='" + accountId + '\'' +
                ", accountName='" + accountName + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
