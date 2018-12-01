package ru.itmo.wm4.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class NoticeCredentials {
    @NotEmpty
    @Size(min = 1, max = 500)
    @Pattern(regexp = "[a-z]+", message = "expected lowercase Latin letters")
    private String content;

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
