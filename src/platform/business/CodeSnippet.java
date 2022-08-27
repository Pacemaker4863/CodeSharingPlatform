package platform.business;

import platform.util.Dates;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table
public class CodeSnippet implements Comparable<CodeSnippet> {
    @Id
    private String id;
    @Column(length = 5000)
    private String code;
    @Column()
    private LocalDateTime date;
    @Column()
    private int time;
    @Column()
    private int views;
    @Column
    private boolean restricted;
    private boolean restrictedByTime;
    private boolean restrictedByViews;

    public CodeSnippet() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public boolean isRestricted() {
        return restricted;
    }

    public void setRestricted(boolean restricted) {
        this.restricted = restricted;
    }

    public boolean isRestrictedByTime() {
        return restrictedByTime;
    }

    public void setRestrictedByTime(boolean restrictedByTime) {
        this.restrictedByTime = restrictedByTime;
    }

    public boolean isRestrictedByViews() {
        return restrictedByViews;
    }

    public void setRestrictedByViews(boolean restrictedByViews) {
        this.restrictedByViews = restrictedByViews;
    }

    @Override
    public int compareTo(CodeSnippet otherDate) {
        return otherDate.getDate().compareTo(this.getDate());
    }

    @Override
    public String toString() {
        return "CodeSnippet{" + "id=" + id + ", code='" + code + '\'' + ", date=" + Dates.toString(date) + ", time=" + time + ", views=" + views + ", restricted=" + restricted + '}';
    }
}