package com.atguigu.demo.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;


// 测试EasyExcel写操作
public class TestEasyExcel {

    public static void main(String[] args) {
        // 实现Excel写的操作
        // 1 设置写入文件夹地址和Excel文件名称
        String fileName = "F:\\wirte.xlsx";

        /**
         * 2 调用EasyExcel里面的方法实现写操作
         *  write()里面的两个参数，第一个参数是文件路径名称，第二个参数是实体类class
         */
        EasyExcel.write(fileName, DemoData.class).sheet("学生列表").doWrite(getData());
    }

    // 创建一个方法
    private static List<DemoData> getData(){
        ArrayList<DemoData> list = new ArrayList<>();
        for (int i = 0; i < 10; i++ ) {
            DemoData demoData = new DemoData();
            demoData.setSno(i);
            demoData.setName("zhangsan"+i);
            list.add(demoData);
        }
        return list;
    }
}
