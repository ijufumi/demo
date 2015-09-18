package jp.ijufumi.demo.spring.boot.jpa;

import java.util.stream.IntStream;
import javax.persistence.EntityManager;

import jp.ijufumi.demo.spring.boot.jpa.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by iju on 2015/09/18.
 */
@Service("manager2")
public class InsertWithEntityManager2 implements Insert {

    @Autowired
    EntityManager entityManager;

    @Override
    @Transactional
    public void insert() {
        IntStream.range(0, 10000).forEach(x -> {
            Customer customer = new Customer();
            customer.setName(String.format("customer_%d", x));
            entityManager.persist(customer);
        });
        entityManager.flush();
    }
}
