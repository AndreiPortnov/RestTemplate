package org.example;

import org.springframework.stereotype.Component;

@Component(value = "finalResult")
public class FinalResult {
    private final StringBuilder result = new StringBuilder();

    public StringBuilder getResult() {
        return result;
    }

    public void setResult(String value) {
        result.append(value);
    }

    @Override
    public String toString() {
        return "result = " + result;
    }
}
