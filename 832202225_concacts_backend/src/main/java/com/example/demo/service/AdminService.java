package com.example.demo.service;

import com.example.demo.dao.AdminDao;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Params;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminService {

    @Resource
    private AdminDao adminDao;

    @GetMapping
    public List<Admin> getUser() {
        return adminDao.selectAll();
    }


    public PageInfo<Admin> findBySearch(Params params) {
//        分页查询
        PageHelper.startPage(params.getPageNum(),params.getPageSize());

        List<Admin> list = adminDao.findBySearch(params);
        return PageInfo.of(list);
    }


    public void add(Admin admin) {
//        初始化密码
        if(admin.getPassword() == null){
            admin.setPassword("123456");
        }
        adminDao.insertSelective(admin);
    }

    public void edit(Admin admin) {
        adminDao.updateByPrimaryKeySelective(admin);


    }

    public void delete(Integer id) {
        adminDao.deleteByPrimaryKey(id);
    }
}
