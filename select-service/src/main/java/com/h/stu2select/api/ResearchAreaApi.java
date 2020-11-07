package com.h.stu2select.api;


import com.h.stu2select.entity.ResearchArea;
import com.h.stu2select.sevice.ResearchAreaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

@RequestMapping("api/researchArea")
// RestController 返回任意類型結果（怎麼是繁體還改不回來啊啊啊啊
@RestController
public class ResearchAreaApi {
    @Resource
    ResearchAreaService researchAreaService;

    @DeleteMapping("{id}")
    public String deleteResearchAreaById(@PathVariable("id") Integer id) {
        return researchAreaService.deleteResearchAreaById(id) ? "删除成功" : "删除失败";
    }

    // 從路徑獲取參數值
    @GetMapping("{page}/{size}")
    public Page<ResearchArea> getAllResearchArea(@PathVariable("page") int page,
                                                 @PathVariable("size") int size) {
        // PageRequest spring自帶分頁,page從0開始
        return researchAreaService.getAllResearchArea(PageRequest.of(page, size));
    }

    @GetMapping("{id}")
    // Optional<T> 正常返回T類型，否則返回null
    public Optional<ResearchArea> getResearchAreaById(@PathVariable("id") Integer id) {
        return researchAreaService.getResearchAreaById(id);
    }

    @PostMapping
    public String saveResearchArea(@RequestBody ResearchArea researchArea) {
        researchAreaService.setResearchArea(researchArea);
        return "插入成功";
    }
}
