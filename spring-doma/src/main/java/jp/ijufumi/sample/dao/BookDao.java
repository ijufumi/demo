package jp.ijufumi.sample.dao;

import jp.ijufumi.sample.entity.Book;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.boot.ConfigAutowireable;
import org.springframework.stereotype.Component;

import java.util.List;

@ConfigAutowireable
@Dao
public interface BookDao {
    @Select
    List<Book> selectAll();
}
