<template>
  <div id="app">
    <el-container>
      <el-header style="background-color: mediumpurple">
        <img src="@/assets/通讯录.png" alt="" style="width: 40px;margin-top: 10px;margin-right: 10px">
        <span style="font-size: 20px; margin-left: 15px; color: white;">通讯录管理系统</span>
      </el-header>
    </el-container>
    <el-container>
      <el-aside style="overflow: hidden; min-height: 100vh; background-color: darkslateblue;">
        <el-menu :default-active="$route.path" router background-color="#483D8B" text-color="#fff" active-text-color="#ffd04b">
          <el-menu-item index="/">
            <i class="el-icon-s-home"></i>
            <span slot="title">系统首页</span>
          </el-menu-item>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-location"></i><span>用户管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/admin">管理员信息</el-menu-item>
              <el-menu-item index="2-2">用户信息</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-location"></i><span>信息管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="3-1">xxx信息</el-menu-item>
              <el-menu-item index="3-2">yyy信息</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </div>
</template>

<style>
.el-menu{
  border-right: none !important;
}
</style>

