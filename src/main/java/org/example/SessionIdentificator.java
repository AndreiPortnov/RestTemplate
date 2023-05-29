package org.example;

import org.springframework.stereotype.Component;

@Component
public class SessionIdentificator {
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public SessionIdentificator(String value) {
        this.value = value;
    }

    public SessionIdentificator() {
    }
}
