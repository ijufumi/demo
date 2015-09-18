package jp.ijufumi.demo.spring.boot.jpa;

import java.util.stream.IntStream;

import jp.ijufumi.demo.spring.boot.jpa.domain.Customer;
import jp.ijufumi.demo.spring.boot.jpa.domain.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by iju on 2015/09/18.
 */
@Service("insert1")
public class InsertWithRepository implements Insert {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    @Transactional
    public void insert() {
        IntStream.range(0, 10000).forEach(x -> {
            Customer customer = new Customer();
            customer.setName(String.format("customer_%d", x));
            customerRepository.saveAndFlush(customer);
        });
    }
}
