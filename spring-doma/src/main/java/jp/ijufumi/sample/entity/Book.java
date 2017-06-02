package jp.ijufumi.sample.entity;

import jp.ijufumi.sample.domain.BookId;
import jp.ijufumi.sample.domain.BookName;
import org.seasar.doma.Entity;
import org.seasar.doma.GeneratedValue;
import org.seasar.doma.GenerationType;
import org.seasar.doma.Id;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public BookId id;
    public BookName name;
}
