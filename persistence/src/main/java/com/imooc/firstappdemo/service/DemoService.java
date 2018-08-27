package com.imooc.firstappdemo.service;

import com.imooc.firstappdemo.dao.DemoDao;
import com.imooc.firstappdemo.dao.DemoRepository;
import com.imooc.firstappdemo.domain.Demo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class DemoService {

    @Resource
    private DemoRepository demoRepository;

    @Resource
    private DemoDao demoDao;

    @Transactional
    public void save(Demo demo){
        demoRepository.save(demo);
    }

    public Iterable<Demo> findAll(){
        return demoRepository.findAll();
    }

    public Demo getById(long id){
        return demoDao.getById(id);
    }
}
