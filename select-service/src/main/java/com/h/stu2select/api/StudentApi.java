package com.h.stu2select.api;


import com.h.stu2select.entity.Student;
import com.h.stu2select.entity.Tutor;
import com.h.stu2select.sevice.StudentService;
import com.h.stu2select.sevice.TutorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

@RequestMapping("api/student")
// RestController 返回任意類型結果（怎麼是繁體還改不回來啊啊啊啊
@RestController
public class StudentApi {
    @Resource
    StudentService studentService;
    @Resource
    TutorService tutorService;

    @DeleteMapping("{id}")
    public String deleteStudentById(@PathVariable("id") Integer id) {
        return studentService.deleteStudentById(id) ? "删除成功" : "删除失败";
    }

    // 從路徑獲取參數值
    @GetMapping({"enable/{page}/{size}"})
    public Page<Tutor> getEnableTutor(@PathVariable("page") int page,
                                      @PathVariable("size") int size) {
        // PageRequest spring自帶分頁,page從0開始
        return tutorService.getAllEnableTutor(PageRequest.of(page, size));
    }

    // 從路徑獲取參數值
    @GetMapping("all/{page}/{size}")
    public Page<Student> getAllStudent(@PathVariable("page") int page,
                                       @PathVariable("size") int size) {
        return studentService.getAllStudent(PageRequest.of(page, size));
    }

    // 從路徑獲取參數值
    @GetMapping("smartSort/{id}/{page}/{size}")
    public Page<Tutor> getSmartSortTutor(@PathVariable("page") int page,
                                         @PathVariable("size") int size,
                                         @PathVariable("id") Integer id) {
        return tutorService.getSmartSortTutor(PageRequest.of(page, size), id);
    }

    @GetMapping("{id}")
    // Optional<T> 正常返回T類型，否則返回null
    public Optional<Student> getStudentById(@PathVariable("id") Integer id) {
        return studentService.getStudentById(id);
    }

    @PostMapping
    public String saveStudent(@RequestBody Student student) {
        studentService.setStudent(student);
        return "插入成功";
    }
}
