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

@RequestMapping("api/tutor")
// RestController 返回任意類型結果（怎麼是繁體還改不回來啊啊啊啊
@RestController
public class TutorApi {
    @Resource
    TutorService tutorService;
    @Resource
    StudentService studentService;

    @DeleteMapping("{id}")
    public String deleteTutorById(@PathVariable("id") Integer id) {
        return tutorService.deleteTutorById(id) ? "删除成功" : "删除失败";
    }

    // 從路徑獲取參數值
    @GetMapping("all/{page}/{size}")
    public Page<Tutor> getAllTutor(@PathVariable("page") int page,
                                   @PathVariable("size") int size) {
        // PageRequest spring自帶分頁,page從0開始
        return tutorService.getAllTutor(PageRequest.of(page, size));
    }


    // 從路徑獲取參數值
    @GetMapping({"enable/{period}/{id}/{page}/{size}"})
    public Page<Student> getEnableStudent(@PathVariable("page") int page,
                                          @PathVariable("size") int size,
                                          @PathVariable("id") Integer id,
                                          @PathVariable("period") Integer period) {
        return tutorService.getEnableStudent(PageRequest.of(page, size), id, period);
    }

    // 從路徑獲取參數值
    @GetMapping({"enableAll/{period}/{page}/{size}"})
    public Page<Student> getAllEnableStudent(@PathVariable("page") int page,
                                             @PathVariable("size") int size,
                                             @PathVariable("period") Integer period) {
        return tutorService.getAllEnableStudent(PageRequest.of(page, size), period);
    }

    @GetMapping("{id}")
    // Optional<T> 正常返回T類型，否則返回null
    public Optional<Tutor> getTutorById(@PathVariable("id") Integer id) {
        return tutorService.getTutorById(id);
    }

    /**
     * 导师查询我的学生api
     */
    @GetMapping("all/{id}/{page}/{size}")
    public Page<Student> getAllStudentByTutorId(@PathVariable("page") int page,
                                                @PathVariable("size") int size,
                                                @PathVariable("id") Integer id) {
        // PageRequest spring自帶分頁,page從0開始
        return studentService.getAllStudentByTutorId(PageRequest.of(page, size), id);
    }

    /**
     * 导师查询我的学生api
     */
    @GetMapping("select/{period}/{id}/{page}/{size}")
    public Page<Student> getAllStudentByTutorIdAndPeriod(@PathVariable("page") int page,
                                                         @PathVariable("size") int size,
                                                         @PathVariable("id") Integer id,
                                                         @PathVariable("period") Integer period) {
        // PageRequest spring自帶分頁,page從0開始
        return studentService.getAllStudentByTutorIdAndPeriod(PageRequest.of(page, size), id, period);
    }

    /**
     * 查询学生是否已经满额api
     */
    @GetMapping("checkCount/{period}/{id}")
    public Boolean checkCountAllStudentByTutorIdAndPeriod(@PathVariable("id") Integer id,
                                                          @PathVariable("period") Integer period) {
        Optional<Tutor> tutor = tutorService.getTutorById(id);
        return tutor.filter(value ->
                studentService.countAllStudentByTutorIdAndPeriod(id, period)
                        .equals(value.getNumLimit())).isPresent();
    }

    // 從路徑獲取參數值
    @GetMapping("smartSort/{period}/{id}/{page}/{size}")
    public Page<Student> getSmartSortStudent(@PathVariable("page") int page,
                                             @PathVariable("size") int size,
                                             @PathVariable("id") Integer id,
                                             @PathVariable("period") Integer period) {
        // PageRequest spring自帶分頁,page從0開始
        return studentService.getSmartSortStudent(PageRequest.of(page, size), id, period);
    }

    @PostMapping
    public String saveTutor(@RequestBody Tutor tutor) {
        tutorService.setTutor(tutor);
        return "插入成功";
    }
}
