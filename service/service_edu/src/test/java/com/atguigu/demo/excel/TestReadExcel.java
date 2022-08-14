package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;

// 测试EasyExcel读操作
public class TestReadExcel {
    public static void main(String[] args) {

        // 实现Excel读操作
        String fileName = "F:\\wirte.xlsx";

        // 调用EasyExcel中的读方法
        EasyExcel.read(fileName, DemoData.class, new ExcelListener()).sheet().doRead();
    }
}
