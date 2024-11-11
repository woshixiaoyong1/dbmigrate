<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryRef"
      :inline="true"
      v-show="showSearch"
      label-width="100px"
    >
      <el-form-item label="数据源名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入数据源名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="驱动版本" prop="version">
        <el-input
          v-model="queryParams.version"
          placeholder="请输入驱动版本"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['connection:connection:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['connection:connection:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['connection:connection:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['connection:connection:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        v-model:showSearch="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="connectionList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="数据源名称" align="center" prop="name" />
      <el-table-column label="数据库类型" align="center" prop="type" />
      <el-table-column label="驱动版本" align="center" prop="version" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createTime"
        width="180"
      >
        <template #default="scope">
          <span>{{ parseTime(scope.row.createTime, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column label="用户名" align="center" prop="username" />
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template #default="scope">
          <el-button
            link
            type="primary"
            icon="Edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['connection:connection:edit']"
            >修改</el-button
          >
          <el-button
            link
            type="primary"
            icon="Delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['connection:connection:remove']"
            >删除</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改数据源对话框 -->
    <el-dialog :title="title" v-model="open" width="1000px" append-to-body>
      <el-form
        ref="connectionRef"
        :model="form"
        :rules="rules"
        label-width="100px"
      >
        <el-form-item label="数据源名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入数据源名称" />
        </el-form-item>
        <el-form-item label="驱动版本" prop="version">
          <el-input v-model="form.version" placeholder="请输入驱动版本" />
        </el-form-item>
        <el-form-item label="连接地址" prop="address">
          <el-input v-model="form.address" placeholder="请输入连接地址" />
        </el-form-item>
        <el-form-item label="连接端口号" prop="port">
          <el-input v-model="form.port" placeholder="请输入连接端口号" />
        </el-form-item>
        <el-form-item label="数据库名" prop="databaseName">
          <el-input v-model="form.databaseName" placeholder="请输入数据库名" />
        </el-form-item>
        <el-form-item label="编码格式" prop="characterEncoding">
          <el-input
            v-model="form.characterEncoding"
            placeholder="请输入编码格式"
          />
        </el-form-item>
        <el-form-item label="JDBC连接串" prop="url">
          <el-input
            v-model="form.url"
            type="textarea"
            placeholder="请输入内容"
          />
        </el-form-item>
        <el-form-item label="连接账号" prop="username">
          <el-input v-model="form.username" placeholder="请输入连接账号" />
        </el-form-item>
        <el-form-item label="账号密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入账号密码" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Connection">
const route = useRoute();
const router = useRouter();
import {
  listConnection,
  getConnection,
  delConnection,
  addConnection,
  updateConnection,
} from "@/api/connection/connection/connection.js";

const { proxy } = getCurrentInstance();

const connectionList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    name: null,
    type: null,
    version: null,
  },
  rules: {
    name: [{ required: true, message: "数据源名称不能为空", trigger: "blur" }],
    type: [
      { required: true, message: "数据库类型不能为空", trigger: "change" },
    ],
    version: [{ required: true, message: "驱动版本不能为空", trigger: "blur" }],
    driver: [
      { required: true, message: "驱动类名称不能为空", trigger: "blur" },
    ],
    mode: [
      {
        required: true,
        message: "连接模式：0默认 1专业不能为空",
        trigger: "blur",
      },
    ],
    address: [{ required: true, message: "连接地址不能为空", trigger: "blur" }],
    port: [{ required: true, message: "连接端口号不能为空", trigger: "blur" }],
    databaseName: [
      { required: true, message: "数据库名不能为空", trigger: "blur" },
    ],
    characterEncoding: [
      { required: true, message: "编码格式不能为空", trigger: "blur" },
    ],
    username: [
      { required: true, message: "连接账号不能为空", trigger: "blur" },
    ],
    password: [
      { required: true, message: "账号密码不能为空", trigger: "blur" },
    ],
    createTime: [
      { required: true, message: "创建时间不能为空", trigger: "blur" },
    ],
    updateTime: [
      { required: true, message: "修改时间不能为空", trigger: "blur" },
    ],
  },
});

const { queryParams, form, rules } = toRefs(data);

/** 查询数据源列表 */
function getList() {
  loading.value = true;
  listConnection(queryParams.value).then((response) => {
    connectionList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    id: null,
    name: null,
    type: null,
    version: null,
    driver: null,
    mode: null,
    address: null,
    port: null,
    databaseName: null,
    characterEncoding: null,
    url: null,
    username: null,
    password: null,
    createTime: null,
    updateTime: null,
  };
  proxy.resetForm("connectionRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map((item) => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  router.push("/connection/connection/add");
  /*  reset();
  open.value = true;
  title.value = "添加数据源";*/
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value;
  getConnection(_id).then((response) => {
    form.value = response.data;
    open.value = true;
    title.value = "修改数据源";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["connectionRef"].validate((valid) => {
    if (valid) {
      if (form.value.id != null) {
        updateConnection(form.value).then((response) => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addConnection(form.value).then((response) => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _ids = row.id || ids.value;
  proxy.$modal
    .confirm('是否确认删除数据源编号为"' + _ids + '"的数据项？')
    .then(function () {
      return delConnection(_ids);
    })
    .then(() => {
      getList();
      proxy.$modal.msgSuccess("删除成功");
    })
    .catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download(
    "connection/connection/export",
    {
      ...queryParams.value,
    },
    `connection_${new Date().getTime()}.xlsx`
  );
}

getList();
</script>
