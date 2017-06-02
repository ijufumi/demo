package jp.ijufumi.sample.domain;

import org.seasar.doma.Domain;

@Domain(valueType = Long.class)
public class BookId {
    private Long id;

    public BookId(Long id) {
        this.id = id;
    }

    public Long getValue() {
        return id;
    }
}
