package jp.ijufumi.sample.domain;

import org.seasar.doma.Domain;

@Domain(valueType = String.class)
public class BookName {
    private String name;

    public BookName(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
