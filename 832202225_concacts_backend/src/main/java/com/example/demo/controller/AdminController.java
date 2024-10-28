package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.entity.Admin;
import com.example.demo.entity.Params;
import com.example.demo.service.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {


    @Resource
    private AdminService adminService;

    @PostMapping
    public Result save(@RequestBody Admin admin) {
        if(admin.getId()==null){
            adminService.add(admin);
        }else{
            adminService.edit(admin);
        }

        return Result.success();
    }


    @GetMapping
    public Result getUser(){
        List<Admin> list = adminService.getUser();
        return Result.success(list);
    }

    @GetMapping("/search")
    public Result findBySearch(Params params){
        PageInfo<Admin> info = adminService.findBySearch(params);
        return Result.success(info);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        adminService.delete(id);
        return Result.success();
    }
}
