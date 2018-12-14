package com.proper.checkstyle;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

public class TestController {

    @ApiOperation("blah blah")
    public String update(String id) {
        return "";
    }

    @GetMapping
    @ApiOperation("测试‍")
    public String get(String ids) {
        return "";
    }

    @GetMapping
    public String delete(String id) {
        return "";
    }

}
