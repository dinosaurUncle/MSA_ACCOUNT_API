package me.dinosauruncle.msa.account.domain;

import org.apache.commons.lang3.StringUtils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Page {

    @Id @GeneratedValue
    private Long pageId;

    private String pageName;

    private String pageUrl;

    private String description;

    public Long getPageId() {
        return pageId;
    }

    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void update(Page page){
        if(!(this.pageName.equals(page.pageName)) && StringUtils.isNotEmpty(page.pageName)) setPageName(page.pageName);
        if(!(this.pageUrl.equals(page.pageUrl)) && StringUtils.isNotEmpty(page.pageUrl)) setPageUrl(page.pageUrl);
        if(!(this.description.equals(page.description)) && StringUtils.isNotEmpty(page.description)) setDescription(page.description);
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageId=" + pageId +
                ", pageName='" + pageName + '\'' +
                ", pageUrl='" + pageUrl + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
