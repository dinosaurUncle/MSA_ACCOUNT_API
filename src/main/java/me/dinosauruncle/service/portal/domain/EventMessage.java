package me.dinosauruncle.service.portal.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class EventMessage {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventMessageId;

    private String accountId;

    private String eventMessageType;

    private String eventMessageTitle;

    private String eventMessageDescription;

    private boolean isCheck;

    private Date date;

    public Long getEventMessageId() {
        return eventMessageId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getEventMessageType() {
        return eventMessageType;
    }

    public void setEventMessageType(String eventMessageType) {
        this.eventMessageType = eventMessageType;
    }

    public String getEventMessageTitle() {
        return eventMessageTitle;
    }

    public void setEventMessageTitle(String eventMessageTitle) {
        this.eventMessageTitle = eventMessageTitle;
    }

    public String getEventMessageDescription() {
        return eventMessageDescription;
    }

    public void setEventMessageDescription(String eventMessageDescription) {
        this.eventMessageDescription = eventMessageDescription;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "EventMessage{" +
                "eventMessageId=" + eventMessageId +
                ", accountId='" + accountId + '\'' +
                ", eventMessageType='" + eventMessageType + '\'' +
                ", eventMessageTitle='" + eventMessageTitle + '\'' +
                ", eventMessageDescription='" + eventMessageDescription + '\'' +
                ", isCheck=" + isCheck +
                ", date=" + date +
                '}';
    }
}
