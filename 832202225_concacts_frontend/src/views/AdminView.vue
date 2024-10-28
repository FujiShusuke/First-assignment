<template>
  <div>
  <div style="margin-bottom: 15px">
    <el-input  v-model="params.name" style="width: 300px;" placeholder="请输入姓名" clearable></el-input>
    <el-input  v-model="params.phone" style="width: 300px; margin-left: 10px" placeholder="请输入电话" clearable></el-input>
    <el-button type="primary" style="margin-left: 10px" @click="findBySearch()">查询</el-button>
    <el-button type="warning" style="margin-left: 10px" @click="reset()">清空</el-button>
    <el-button type="danger" style="margin-left: 10px" @click="add()">新增</el-button>
  </div>
  <div>
     <el-table :data="tableData" style="width: 100%; margin: 15px">
        <el-table-column prop="name" label="姓名" width="180"></el-table-column>
        <el-table-column prop="password" label="密码" width="180"></el-table-column>
        <el-table-column prop="sex" label="性别" width="180"></el-table-column>
       <el-table-column prop="age" label="年龄" width="180"></el-table-column>
        <el-table-column prop="phone" label="电话"></el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
           <el-button type="text" @click="edit(scope.row)">编辑</el-button>
            <el-popconfirm title="确定删除吗？" @confirm="del(scope.row.id)">
              <el-button slot="reference" type="text" style="margin-left: 10px">删除</el-button>
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
  </div>
    <div class="block" style="margin-top: 15px; margin-left: 200px">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="params.pageNum"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="params.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
    <div>
      <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="25%">
        <el-form :model="form">
          <el-form-item label="姓名">
            <el-input v-model="form.name" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio v-model="form.sex" label="男">男</el-radio>
            <el-radio v-model="form.sex" label="女">女</el-radio>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="电话">
            <el-input v-model="form.phone" autocomplete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="submit()">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  data() {
    return {
      params: {
        name: '',
        phone: '',
        pageNum: 1,
        pageSize: 5,
      },
      tableData: [],
      total: 0,
      dialogFormVisible: false,
      form:{}
    }
  },
  created() {
    this.findBySearch();
  },
  methods:{
    findBySearch(){
      request.get("/admin/search", {
        params: this.params
      }).then(res => {
        if (res.code=== '0'){
          this.tableData = res.data.list;
          this.total = res.data.total;
        }else{
          this.$message({
            showClose: true,
            message: res.msg,
            type: 'success'
          });
        }
      })
    },
    add(){
      this.form = {}
      this.dialogFormVisible = true
    },
    edit(object){
      this.form = object;
      this.dialogFormVisible = true;
    },
    reset(){
      this.params = {
        name: '',
        phone: '',
        pageSize: this.params.pageSize,
        pageNum: this.params.pageNum,
      }
      this.findBySearch();
    },
    handleSizeChange(pageSize){
      this.params.pageSize = pageSize;
      this.findBySearch();
    },
    handleCurrentChange(pageNum){
      this.params.pageNum = pageNum;
      this.findBySearch();
    },
    submit(){
      request.post("/admin", this.form).then(res => {
        if (res.code=== '0'){
          this.$message({
            showClose: true,
            message: '操作成功',
            type: 'success'
          });
          this.dialogFormVisible = false;
          this.findBySearch();
        }else{
          this.$message({
            showClose: true,
            message: res.msg,
            type: 'success'
          });
        }
      })
    },
    del(id){
      request.delete("/admin/"+id).then(res => {
        if (res.code=== '0'){
          this.$message({
            showClose: true,
            message: '删除成功',
            type: 'success'
          });
          this.findBySearch();
        }else{
          this.$message({
            showClose: true,
            message: res.msg,
            type: 'success'
          });
        }
      })
    }
  }
}
</script>