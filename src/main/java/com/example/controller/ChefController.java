package com.example.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.CommonPage;
import com.example.common.CommonResult;
import com.example.dto.ChefParam;
import com.example.model.TChef;
import com.example.service.TChefService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 厨师
 * </p>
 *
 * @author lk
 * @since 2021-05-05
 */
@Api(value = "厨师接口", tags = "厨师接口类")
@RestController
@RequestMapping("/chef")
public class ChefController {
    @Autowired
    private TChefService chefService;

    @ApiOperation(value = "分页获取厨师列表")
    @GetMapping("/list")
    public CommonResult<CommonPage<TChef>> list(ChefParam chefParam,
                                                @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        Page page = chefService.list(chefParam, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(page));
    }

    @ApiOperation(value = "获取厨师信息")
    @GetMapping("/info/{id}")
    public CommonResult<TChef> info(@PathVariable("id")Long id) {
        return CommonResult.success(chefService.info(id));
    }


    @ApiOperation(value = "认证")
    @PostMapping("/attest")
    public CommonResult attest(@RequestBody TChef chef) {
        int count = chefService.attest(chef);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }


    @ApiOperation(value = "入驻")
    @PostMapping("/add")
    public CommonResult add(@RequestBody TChef chef) {
        int count = chefService.add(chef);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }

    @ApiOperation(value = "接单")
    @GetMapping("/receiveOrder/{orderId}")
    public CommonResult receiveOrder(@PathVariable("orderId")Long orderId) {
        int count = chefService.receiveOrder(orderId);
        if(count > 0)
            return CommonResult.success(count);
        return CommonResult.failed();
    }


}
