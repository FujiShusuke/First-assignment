package com.example.demo.dao;

import com.example.demo.entity.Admin;
import com.example.demo.entity.Params;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


import java.util.List;

@Repository
public interface AdminDao extends Mapper<Admin> {
//   基于注解的方式
//    @Select("select * from admin")
//    List<Admin> getUser();

      List<Admin> findBySearch(@Param("params") Params params);
}
