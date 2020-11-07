package com.h.stu2select.api;


import com.h.stu2select.entity.Major;
import com.h.stu2select.sevice.MajorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

@RequestMapping("api/major")
// RestController 返回任意類型結果（怎麼是繁體還改不回來啊啊啊啊
@RestController
public class MajorApi {
    @Resource
    MajorService majorService;

    @DeleteMapping("{id}")
    public String deleteMajorById(@PathVariable("id") Integer id) {
        return majorService.deleteMajorById(id) ? "删除成功" : "删除失败";
    }

    // 從路徑獲取參數值
    @GetMapping("{page}/{size}")
    public Page<Major> getAllMajor(@PathVariable("page") int page,
                                   @PathVariable("size") int size) {
        // PageRequest spring自帶分頁,page從0開始
        return majorService.getAllMajor(PageRequest.of(page, size));
    }

    @GetMapping("{id}")
    // Optional<T> 正常返回T類型，否則返回null
    public Optional<Major> getMajorById(@PathVariable("id") Integer id) {
        return majorService.getMajorById(id);
    }

    @PostMapping
    public String saveMajor(@RequestBody Major major) {
        majorService.setMajor(major);
        return "插入成功";
    }
}
