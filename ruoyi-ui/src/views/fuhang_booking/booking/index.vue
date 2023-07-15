<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="手机号" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="时间场次" prop="time">
        <el-date-picker clearable
          v-model="queryParams.time"
          type="datetime"
          placeholder="请选择时间场次">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="预定数量" prop="number">
        <el-input
          v-model="queryParams.number"
          placeholder="请输入预定数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单编号" prop="orderId">
        <el-input
          v-model="queryParams.orderId"
          placeholder="请输入订单编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="转赠手机号" prop="transferPhone">
        <el-input
          v-model="queryParams.transferPhone"
          placeholder="请输入转赠手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['fuhang_booking:booking:add']"
        >新增任务</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"

          @click="handleUpdate"
          v-hasPermi="['fuhang_booking:booking:edit']"
        >修改任务</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['fuhang_booking:booking:remove']"
        >删除任务</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="bookingList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="60" align="center" />
      <el-table-column label="任务id" align="center" prop="id" width="80"/>
      <el-table-column label="手机号" align="center" prop="phone" width="130"/>
      <el-table-column label="时间场次" align="center" prop="time" width="170">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.time, '{y}-{m}-{d} {h}:{m}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="票种" align="center" prop="ticketType" width="80">
        <template #default="scope">
          <el-tag
            :type="scope.row.ticketType == '1' ? 'success' : '' "
            disable-transitions
          >{{ scope.row.ticketType == '1' ? '前区票' : '普通票' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="预定数量" align="center" prop="number" width="80"/>
      <el-table-column label="任务授权" align="center" prop="authStatus" width="80">
        <template #default="scope">
          <el-tag
            :type="scope.row.authStatus == '1' ? 'success' : 'danger' "
          >{{ scope.row.authStatus == '1' ? '已授权' : '未授权' }}</el-tag>
        </template>
      </el-table-column>
      <!--任务状态(0:未开始 1:任务失败 2: 未支付已下单 3:已支付)-->
      <el-table-column label="任务状态" align="center" prop="orderStatus" width="100">
        <template #default="scope">
          <el-tag
            :type="scope.row.orderStatus == '0' ? 'info' : (scope.row.orderStatus == '1' ? 'danger' :(scope.row.orderStatus == '2' ? '' : 'success'))"
          >{{ scope.row.orderStatus == '0' ? '未开始' : (scope.row.orderStatus == '1' ? '任务失败' :(scope.row.orderStatus == '2' ? '未付款' : '已付款')) }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="成交状态" align="center" prop="dealStatus" width="80">
        <template #default="scope">
          <el-tag
            :type="scope.row.dealStatus == '1' ? 'success' : 'danger' "
          >{{ scope.row.dealStatus == '1' ? '已成交' : '未成交' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="订单编号" align="center" prop="orderId" width="140"/>
      <el-table-column label="转赠手机号" align="center" prop="transferPhone" width="130"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right">
        <template slot-scope="scope">

          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['fuhang_booking:booking:edit']"
          >修改</el-button>

          <el-button
            size="mini"
            type="text"
            icon="el-icon-money"
            @click="payBill(scope.row.orderId)"
            v-hasPermi="['fuhang_booking:booking:pay']"
          >支付订单</el-button>

          <el-button
            size="mini"
            type="text"
            icon="el-icon-share"
            @click="showtransfer(scope.row.phone)"
            v-hasPermi="['fuhang_booking:booking:transfer']"
          >转赠</el-button>

          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['fuhang_booking:booking:remove']"
          >删除</el-button>

        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <!-- 新增任务对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">

        <!-- 获取 图形验证码 -->
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号" style="width: 170px;margin-right:20px"/>
          <el-button type="primary" @click="getGraphCode(form.phone)" v-hasPermi="['fuhang_booking:booking:graphCode']">获取图形验证码</el-button>
        </el-form-item>

        <!--  填写 图形验证码 -->
        <el-form-item label="图形验证码" prop="graphCode" v-show="graphCodeShow">
          <el-input v-model="form.graphCode" placeholder="请输入图形验证码" style="width: 170px;margin-right:20px" />
          <el-image :src="imgFile" style="width: 100px; height: 40px"> </el-image>
        </el-form-item>

        <!--  填写 短信验证码 -->
        <el-form-item label="短信验证码" prop="smsCode">
          <el-input v-model="form.smsCode" placeholder="请输入短信验证码" style="width: 170px;margin-right:20px"/>
          <el-button type="primary" @click="getSMSCode(form.graphCode,form.phone)" v-hasPermi="['fuhang_booking:booking:SMSCode']">获取短信验证码</el-button>
        </el-form-item>

        <!--  填写 时间场次 -->
        <el-form-item label="时间场次" prop="time">
          <el-date-picker clearable
            v-model="form.time"
            type="datetime"
            placeholder="请选择">
          </el-date-picker>
        </el-form-item>

        <!--  填写 预定数量 -->
        <el-form-item label="预定数量" prop="number">
          <el-input v-model="form.number" placeholder="请输入预定数量" />
        </el-form-item>

        <!--  选择 票种 -->
        <el-form-item label="票种" prop="ticketType">
          <el-radio-group v-model="form.ticketType">
            <el-radio border label="1">前区票</el-radio>
            <el-radio border label="0">普通票</el-radio>
          </el-radio-group>

        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 转赠对话框-->
    <el-dialog
      :visible.sync="dialogVisible"
      title="转赠"
      width="30%"
    >
      <el-input size="medium" v-model="transferParams.transferphone" placeholder="请输入转赠手机号" clearable maxlength="11"></el-input>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="transferTicket()">
          确定
        </el-button>
      </span>
      </template>
    </el-dialog>

  </div>
</template>

<script>

import {
  listBooking,
  getBooking,
  delBooking,
  addBooking,
  updateBooking,
  getgraphcode,
  getsmscode,
  pay,
  transferorder
} from "@/api/fuhang_booking/booking";



export default {
  name: "Booking",
  data() {
    return {
      // 要转赠的手机号
      transferParams:{
        orginalphone: null,
        transferphone: null
      },
      // 是否展示图像验证码输入框
      graphCodeShow: false,
      // 图像验证码base64
      base64Img: '',
      // 图片地址
      imgFile:null,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // booking表格数据
      bookingList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否显示转赠对话框
      dialogVisible: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        phone: null,
        time: null,
        ticketType: null,
        number: null,
        authStatus: null,
        orderStatus: null,
        dealStatus: null,
        orderId: null,
        transferPhone: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        phone: [
          { required: true, message: "手机号不能为空", trigger: "blur" }
        ],
        time: [
          { required: true, message: "时间不能为空", trigger: "blur" }
        ],
        ticketType: [
          { required: true, message: "票种不能为空", trigger: "change" }
        ],
        number: [
          { required: true, message: "预约数量不能为空", trigger: "blur" }
        ],
        // authStatus: [
        //   { required: true, message: "预约授权不能为空", trigger: "change" }
        // ],
        // orderStatus: [
        //   { required: true, message: "任务状态不能为空", trigger: "change" }
        // ],
        // dealStatus: [
        //   { required: true, message: "成交状态不能为空", trigger: "change" }
        // ],
        smsCode: [
          { required: true, message: "短信验证码不能为空!", trigger: "change" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /**  */

    /** 获取短信验证码 */
    getSMSCode(graphCode,phone){
        getsmscode(graphCode,phone).then(res =>{
          if (res.data == "成功"){
            this.$message.success('短信验证码发送成功');
          }else {
            this.$message.error('短信验证码发送失败');
          }
        })
    },

    /**获取图像验证码 返回base64*/
    getGraphCode(phone){
      if (phone == null){
        this.$message.error('手机号不能为空!');
      }else {
        getgraphcode(phone).then(response =>{
          // this.imgFile = this.base64ImgtoFile(response.data);
          this.imgFile = response.data;
        })
        this.graphCodeShow = true;
      }

    },

    /** 查询booking列表 */
    getList() {
      this.loading = true;
      listBooking(this.queryParams).then(response => {
        this.bookingList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },

    // 表单重置
    reset() {
      this.imgFile = null;
      this.form = {
        id: null,
        phone: null,
        time: null,
        ticketType: null,
        number: null,
        authStatus: null,
        orderStatus: null,
        dealStatus: null,
        orderId: null,
        transferPhone: null
      };
      this.resetForm("form");
    },

    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },

    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },

    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },

    /** 新增按钮操作 */
    handleAdd() {
      // 清除上一次痕迹
      this.graphCodeShow = false;
      this.reset();

      this.open = true;
      this.title = "添加抢购任务";
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getBooking(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改booking";
      });
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateBooking(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBooking(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除booking编号为"' + ids + '"的数据项？').then(function() {
        return delBooking(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },

    /** 支付订单 */
    payBill(orderid){
      pay(orderid)
    },

    /** 展示转赠页面 */
    showtransfer(originalphone){
      this.transferParams.transferphone = null;
      this.transferParams.orginalphone = null;
      this.dialogVisible = true;
      this.transferParams.orginalphone = originalphone;
    },

    /** 转赠票 */
    transferTicket(){
      console.log(this.transferParams);
      transferorder(this.transferParams).then(response =>{
        this.$modal.msgSuccess("转赠成功");
        this.getList();
      })
    }


  }
};
</script>
