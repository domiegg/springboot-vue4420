package cn.zwz.order.controller;

import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.basics.utils.SecurityUtil;
import cn.zwz.data.entity.User;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.order.entity.DishCollect;
import cn.zwz.order.entity.DishOrder;
import cn.zwz.order.entity.DishVariety;
import cn.zwz.order.service.IDishCollectService;
import cn.zwz.order.service.IDishOrderService;
import cn.zwz.order.service.IDishVarietyService;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 
 * 
 */
@Slf4j
@RestController
@Api(tags = "药品管理接口")
@RequestMapping("/zwz/dishVariety")
@Transactional
public class DishVarietyController {

    @Autowired
    private IDishVarietyService iDishVarietyService;

    @Autowired
    private IDishCollectService iDishCollectService;

    @Autowired
    private IDishOrderService iDishOrderService;

    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条药品")
    public Result<DishVariety> get(@RequestParam String id){
        return new ResultUtil<DishVariety>().setData(iDishVarietyService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部药品个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iDishVarietyService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部药品")
    public Result<List<DishVariety>> getAll(){
        return new ResultUtil<List<DishVariety>>().setData(iDishVarietyService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询药品")
    public Result<IPage<DishVariety>> getByPage(@ModelAttribute DishVariety dishVariety ,@ModelAttribute PageVo page){
        QueryWrapper<DishVariety> qw = new QueryWrapper<>();
        if(!ZwzNullUtils.isNull(dishVariety.getTitle())) {
            qw.like("title",dishVariety.getTitle());
        }
        if(!ZwzNullUtils.isNull(dishVariety.getType())) {
            qw.eq("type",dishVariety.getType());
        }
        if(!ZwzNullUtils.isNull(dishVariety.getContent())) {
            qw.like("content",dishVariety.getContent());
        }
        IPage<DishVariety> data = iDishVarietyService.page(PageUtil.initMpPage(page),qw);
        User currUser = securityUtil.getCurrUser();
        for (DishVariety vo : data.getRecords()) {
            QueryWrapper<DishCollect> collQw = new QueryWrapper<>();
            collQw.eq("collect_id",currUser.getId());
            collQw.eq("dish_id",vo.getId());
            vo.setCollectFlag(iDishCollectService.count(collQw));
            QueryWrapper<DishOrder> orderQw = new QueryWrapper<>();
            orderQw.eq("dish_id",vo.getId());
            orderQw.eq("status","已加购");
            orderQw.last("limit 1");
            DishOrder order = iDishOrderService.getOne(orderQw);
            vo.setBuyNumber(order == null ? BigDecimal.ZERO : order.getNumber());
        }
        return new ResultUtil<IPage<DishVariety>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改药品")
    public Result<DishVariety> saveOrUpdate(DishVariety dishVariety){
        if(iDishVarietyService.saveOrUpdate(dishVariety)){
            return new ResultUtil<DishVariety>().setData(dishVariety);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增药品")
    public Result<DishVariety> insert(DishVariety dishVariety){
        iDishVarietyService.saveOrUpdate(dishVariety);
        return new ResultUtil<DishVariety>().setData(dishVariety);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑药品")
    public Result<DishVariety> update(DishVariety dishVariety){
        iDishVarietyService.saveOrUpdate(dishVariety);
        return new ResultUtil<DishVariety>().setData(dishVariety);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除药品")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iDishVarietyService.removeById(id);
        }
        return ResultUtil.success();
    }
}
