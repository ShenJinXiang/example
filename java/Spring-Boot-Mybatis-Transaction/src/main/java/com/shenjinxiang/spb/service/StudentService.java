package com.shenjinxiang.spb.service;

import com.shenjinxiang.spb.domain.Student;

import java.util.List;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/4/3 17:44
 */
public interface StudentService {

    /**
     * 获取所有Student
     *
     * @return 列表
     */
    List<Student> queryAllStudent();

    /**
     * 通过id获取学生
     *
     * @param id
     * @return
     */
    Student queryById(int id);

    /**
     * 新增
     *
     * @param student
     * @return
     */
    int add(Student student);

    /**
     * 修改
     *
     * @param student
     * @return
     */
    int upd(Student student);

    /**
     * 删除
     * @param id
     * @return
     */
    int delById(int id);

}
