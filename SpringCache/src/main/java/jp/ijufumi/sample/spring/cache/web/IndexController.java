package jp.ijufumi.sample.spring.cache.web;

import java.util.List;

import jp.ijufumi.sample.spring.cache.service.IndexService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    IndexService indexService;

    @RequestMapping
    public List<Integer> index()
    {
        return indexService.list();
    }

    @RequestMapping("clear")
    @CacheEvict(value = "default", allEntries = true)
    public void clear()
    {
        logger.info("clear cache");
    }
}
