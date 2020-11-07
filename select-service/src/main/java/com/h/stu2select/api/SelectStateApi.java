package com.h.stu2select.api;


import com.h.stu2select.entity.SelectState;
import com.h.stu2select.sevice.SelectStateService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Optional;

@RequestMapping("api/selectState")
// RestController 返回任意類型結果（怎麼是繁體還改不回來啊啊啊啊
@RestController
public class SelectStateApi {
    @Resource
    SelectStateService selectStateService;


    @DeleteMapping("{id}")
    public String deleteSelectStateById(@PathVariable("id") Integer id) {
        return selectStateService.deleteSelectStateById(id) ? "删除成功" : "删除失败";
    }

    // 從路徑獲取參數值
    @GetMapping("{page}/{size}")
    public Page<SelectState> getAllSelectState(@PathVariable("page") int page,
                                               @PathVariable("size") int size) {
        // PageRequest spring自帶分頁,page從0開始
        return selectStateService.getAllSelectState(PageRequest.of(page, size));
    }

    @GetMapping("{id}")
    // Optional<T> 正常返回T類型，否則返回null
    public Optional<SelectState> getSelectStateById(@PathVariable("id") Integer id) {
        return selectStateService.getSelectStateById(id);
    }

    @GetMapping("match/{stuId}/{period}")
    // Optional<T> 正常返回T類型，否則返回null
    public Optional<SelectState> getSelectStateByStuIdAndPeriod(@PathVariable("stuId") Integer id,
                                                                @PathVariable("period") Integer period) {
        return selectStateService.getSelectStateByStuIdAndPeriod(id, period);
    }

    @PostMapping
    public String saveSelectState(@RequestBody SelectState selectState) {
        return selectStateService.setSelectState(selectState) ? "插入成功" : "插入失败";
    }
}
