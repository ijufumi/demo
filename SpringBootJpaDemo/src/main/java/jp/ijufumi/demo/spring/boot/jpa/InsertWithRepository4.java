package jp.ijufumi.demo.spring.boot.jpa;

import jp.ijufumi.demo.spring.boot.jpa.domain.Customer;
import jp.ijufumi.demo.spring.boot.jpa.domain.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Created by iju on 2015/09/18.
 */
@Service("insert4")
public class InsertWithRepository4 implements Insert {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    @Transactional
    public void insert() {
        List<Customer> list = new ArrayList<>(100);
        IntStream.range(0, 10000).forEach(x -> {
            Customer customer = new Customer();
            customer.setName(String.format("customer_%d", x));
            list.add(customer);
            if (x%100 == 0)
            {
                customerRepository.save(list);
                list.clear();
                customerRepository.flush();
            }
        });
        customerRepository.flush();
    }
}
