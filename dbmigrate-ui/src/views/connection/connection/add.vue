<template>
  <div>
    <el-form ref="formRef" :model="formData" :rules="rules" size="default" label-width="100px">
      <el-form-item label="数据源名称" prop="name">
        <el-input v-model="formData.name" type="text" placeholder="请输入数据源名称" clearable
          :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item label="数据库类型" prop="type">
        <el-input v-model="formData.type" type="text" placeholder="请输入数据库类型" clearable
          :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item label="驱动版本" prop="version">
        <el-input v-model="formData.version" type="text" placeholder="请输入驱动版本" clearable
          :style="{width: '100%'}"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm">提交</el-button>
        <el-button @click="resetForm">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>
<script setup>
import {
  ElMessage
}
from 'element-plus'
const formRef = ref()
const data = reactive({
  formData: {
    name: undefined,
    type: undefined,
    version: undefined,
  },
  rules: {
    name: [{
      required: true,
      message: '请输入数据源名称',
      trigger: 'blur'
    }],
    type: [{
      required: true,
      message: '请输入数据库类型',
      trigger: 'blur'
    }],
    version: [{
      required: true,
      message: '请输入驱动版本',
      trigger: 'blur'
    }],
  }
})
const {
  formData,
  rules
} = toRefs(data)
/**
 * @name: 表单提交
 * @description: 表单提交方法
 * @return {*}
 */
function submitForm() {
  formRef.value.validate((valid) => {
    if (!valid) return
    // TODO 提交表单
  })
}
/**
 * @name: 表单重置
 * @description: 表单重置方法
 * @return {*}
 */
function resetForm() {
  formRef.value.resetFields()
}

</script>
<style>
</style>
