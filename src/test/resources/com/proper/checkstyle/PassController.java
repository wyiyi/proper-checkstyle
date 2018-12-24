package com.proper.checkstyle;

import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;

@Api(tags = "/restController")
@RestController
public class PassController {

    @ApiOperation("blah blah")
    @PutMapping
    public String update(String id) {
        return "";
    }

    @GetMapping
    @ApiOperation("测试‍")
    public String get(String ids) {
        return "";
    }

    @ApiOperation("wa‍")
    @DeleteMapping
    public String delete(String id) {
        return "";
    }

    @PostMapping
    @ApiOperation("post")
    public String post() {
        return "";
    }

    @RequestMapping
    @ApiOperation("request")
    public String request() {
        return "";
    }

    private void priMethod1() {
        return;
    }

    private void priMethod2() {
        return;
    }

    public static class AdminAppCatalogVO {

        @ApiModelProperty(name = "‍应用类别编码", required = true)
        private String code;

        @ApiModelProperty(name = "‍应用类别名称", required = true)
        private String typeName;

        @ApiModelProperty(name = "‍应用顺序", required = true)
        private String sort;

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        @Override
        public String toString() {
            return JSONUtil.toJSONIgnoreException(this);
        }
    }
}
