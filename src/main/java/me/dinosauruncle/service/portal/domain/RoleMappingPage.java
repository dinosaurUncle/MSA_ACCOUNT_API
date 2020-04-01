package me.dinosauruncle.service.portal.domain;

import javax.persistence.*;

@Entity
public class RoleMappingPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "RoleMappingPage{" +
                "id=" + id +
                ", role=" + role +
                ", page=" + page +
                '}';
    }
}
