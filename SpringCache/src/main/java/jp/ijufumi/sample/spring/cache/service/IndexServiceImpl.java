package jp.ijufumi.sample.spring.cache.service;

import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService{

    Logger logger = LoggerFactory.getLogger(getClass());

    @Cacheable(value = "default", key = "'list'")
    public List<Integer> list() {
        logger.info("list called.");
        return IntStream.range(1, 100).boxed().collect(Collectors.toList());
    }
}
