package com.shenjinxiang.spb.controller;

import com.shenjinxiang.spb.domain.Student;
import com.shenjinxiang.spb.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: ShenJinXiang
 * @Date: 2020/4/3 17:50
 */
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("")
    public List<Student> queryAll() {
        return this.studentService.queryAllStudent();
    }

    @GetMapping("/{id}")
    public Student queryById(@PathVariable int id) {
        return this.studentService.queryById(id);
    }

    @PostMapping("")
    public int add(@RequestBody Student student) {
        return this.studentService.add(student);
    }

    @PutMapping("/{id}")
    public int upd(@PathVariable int id, @RequestBody Student student) {
        Student student1 = this.studentService.queryById(id);
        student1.setName(student.getName());
        student1.setAge(student.getAge());
        student1.setSex(student.isSex());
        student1.setDesc(student.getDesc());
        return this.studentService.upd(student1);
    }

    @DeleteMapping("/{id}")
    public int delById(@PathVariable int id) {
        return this.studentService.delById(id);
    }
}
