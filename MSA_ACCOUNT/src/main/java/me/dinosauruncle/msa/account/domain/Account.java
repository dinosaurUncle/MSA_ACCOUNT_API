package me.dinosauruncle.msa.account.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name= "ACCOUNT")
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name="name")
    private String name;

    @Column(name="password")
    private String password;

    @Column(name="gender")
    @Enumerated(EnumType.ORDINAL)
    private Gender gender;

    @Column(name="email")
    private String email;

    @Column(name="phone")
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
}
