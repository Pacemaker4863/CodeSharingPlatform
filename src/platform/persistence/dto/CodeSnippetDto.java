package platform.persistence.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Objects;

public record CodeSnippetDto(String code,
                             String date,
                             int time,
                             int views,
                             @JsonIgnore boolean restricted,
                             @JsonIgnore boolean restrictedByTime,
                             @JsonIgnore boolean restrictedByViews
) implements Serializable {

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CodeSnippetDto) obj;
        return Objects.equals(this.code, that.code) &&
                Objects.equals(this.date, that.date) &&
                this.time == that.time &&
                this.views == that.views;
    }

    @Override
    public String toString() {
        return "CodeSnippetDto{" +
                "code='" + code + '\'' +
                ", date='" + date + '\'' +
                ", time=" + time +
                ", views=" + views +
                ", restricted=" + restricted +
                '}';
    }
}
