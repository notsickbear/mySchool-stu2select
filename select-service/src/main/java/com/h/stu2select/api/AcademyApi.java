package com.h.stu2select.api;


import com.h.stu2select.entity.Academy;
import com.h.stu2select.sevice.AcademyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

@RequestMapping("api/academy")
// RestController 返回任意類型結果（怎麼是繁體還改不回來啊啊啊啊
@RestController
public class AcademyApi {
    @Resource
    AcademyService academyService;

    @DeleteMapping("{id}")
    public String deleteAcademyById(@PathVariable("id") Integer id) {
        return academyService.deleteAcademyById(id) ? "删除成功" : "删除失败";
    }

    // 從路徑獲取參數值
    @GetMapping("{page}/{size}")
    public Page<Academy> getAllAcademy(@PathVariable("page") int page,
                                       @PathVariable("size") int size) {
        // PageRequest spring自帶分頁,page從0開始
        return academyService.getAllAcademy(PageRequest.of(page, size));
    }

    @GetMapping("{id}")
    // Optional<T> 正常返回T類型，否則返回null
    public Optional<Academy> getAcademyById(@PathVariable("id") Integer id) {
        return academyService.getAcademyById(id);
    }

    @PostMapping
    public String saveAcademy(@RequestBody Academy academy) {
        academyService.setAcademy(academy);
        return "插入成功";
    }
}
