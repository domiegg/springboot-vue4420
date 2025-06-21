package cn.zwz.order.controller;

import cn.hutool.core.date.DateUtil;
import cn.zwz.basics.utils.PageUtil;
import cn.zwz.basics.utils.ResultUtil;
import cn.zwz.basics.baseVo.PageVo;
import cn.zwz.basics.baseVo.Result;
import cn.zwz.basics.utils.SecurityUtil;
import cn.zwz.data.entity.User;
import cn.zwz.data.utils.ZwzNullUtils;
import cn.zwz.order.entity.DishOrder;
import cn.zwz.order.entity.DishVariety;
import cn.zwz.order.service.IDishOrderService;
import cn.hutool.core.util.StrUtil;
import cn.zwz.order.service.IDishVarietyService;
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
import java.util.Objects;

/**
 * @author 
 * 
 */
@Slf4j
@RestController
@Api(tags = "药品订单管理接口")
@RequestMapping("/zwz/dishOrder")
@Transactional
public class DishOrderController {

    @Autowired
    private IDishOrderService iDishOrderService;

    @Autowired
    private IDishVarietyService iDishVarietyService;

    @Autowired
    private SecurityUtil securityUtil;

    @RequestMapping(value = "/addOne", method = RequestMethod.GET)
    @ApiOperation(value = "加购")
    public Result<DishOrder> addOne(@RequestParam String id, @RequestParam BigDecimal number){
        DishVariety dish = iDishVarietyService.getById(id);
        if(dish == null) {
            return ResultUtil.error("药品不存在");
        }
        User currUser = securityUtil.getCurrUser();
        QueryWrapper<DishOrder> qw = new QueryWrapper<>();
        qw.eq("dish_id",dish.getId());
        qw.eq("status","已加购");
        qw.eq("order_id",currUser.getId());
        qw.last("limit 1");
        DishOrder order = iDishOrderService.getOne(qw);
        if(order != null) {
            order.setNumber(order.getNumber().add(number));
            iDishOrderService.saveOrUpdate(order);
            return ResultUtil.success();
        }
        DishOrder o = new DishOrder();
        o.setDishId(dish.getId());
        o.setTitle(dish.getTitle());
        o.setType(dish.getType());
        o.setContent(dish.getContent());
        o.setImage(dish.getImage());
        o.setPrice(dish.getPrice());
        o.setStatus("已加购");
        o.setNumber(number);
        o.setOrderId(currUser.getId());
        o.setOrderName(currUser.getNickname());
        o.setOrderTime(DateUtil.now());
        iDishOrderService.saveOrUpdate(o);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/getOne", method = RequestMethod.GET)
    @ApiOperation(value = "查询单条药品订单")
    public Result<DishOrder> get(@RequestParam String id){
        return new ResultUtil<DishOrder>().setData(iDishOrderService.getById(id));
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部药品订单个数")
    public Result<Long> getCount(){
        return new ResultUtil<Long>().setData(iDishOrderService.count());
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ApiOperation(value = "查询全部药品订单")
    public Result<List<DishOrder>> getAll(){
        return new ResultUtil<List<DishOrder>>().setData(iDishOrderService.list());
    }

    @RequestMapping(value = "/getByPage", method = RequestMethod.GET)
    @ApiOperation(value = "查询药品订单")
    public Result<IPage<DishOrder>> getByPage(@ModelAttribute DishOrder dishOrder ,@ModelAttribute PageVo page){
        QueryWrapper<DishOrder> qw = new QueryWrapper<>();
        User currUser = securityUtil.getCurrUser();
        qw.eq("order_id",currUser.getId());
        if(!ZwzNullUtils.isNull(dishOrder.getTitle())) {
            qw.like("title",dishOrder.getTitle());
        }
        if(!ZwzNullUtils.isNull(dishOrder.getStatus())) {
            if(Objects.equals("已加购",dishOrder.getStatus())) {
                qw.eq("status",dishOrder.getStatus());
            } else {
                qw.ne("status","已加购");
            }
        }
        if(!ZwzNullUtils.isNull(dishOrder.getOrderName())) {
            qw.like("order_name",dishOrder.getOrderName());
        }
        IPage<DishOrder> data = iDishOrderService.page(PageUtil.initMpPage(page),qw);
        return new ResultUtil<IPage<DishOrder>>().setData(data);
    }

    @RequestMapping(value = "/insertOrUpdate", method = RequestMethod.POST)
    @ApiOperation(value = "增改药品订单")
    public Result<DishOrder> saveOrUpdate(DishOrder dishOrder){
        if(iDishOrderService.saveOrUpdate(dishOrder)){
            return new ResultUtil<DishOrder>().setData(dishOrder);
        }
        return ResultUtil.error();
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "新增药品订单")
    public Result<DishOrder> insert(DishOrder dishOrder){
        iDishOrderService.saveOrUpdate(dishOrder);
        return new ResultUtil<DishOrder>().setData(dishOrder);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ApiOperation(value = "编辑药品订单")
    public Result<DishOrder> update(DishOrder dishOrder){
        iDishOrderService.saveOrUpdate(dishOrder);
        return new ResultUtil<DishOrder>().setData(dishOrder);
    }

    @RequestMapping(value = "/delByIds", method = RequestMethod.POST)
    @ApiOperation(value = "删除药品订单")
    public Result<Object> delByIds(@RequestParam String[] ids){
        for(String id : ids){
            iDishOrderService.removeById(id);
        }
        return ResultUtil.success();
    }

    @RequestMapping(value = "/order", method = RequestMethod.POST)
    @ApiOperation(value = "下单")
    public Result<Object> order(@RequestParam String id,@RequestParam String address,@RequestParam String date){
        DishOrder order = iDishOrderService.getById(id);
        if(order == null) {
            return ResultUtil.error("订单不存在");
        }
        order.setStatus("已下单");
        order.setAddress(address);
        order.setDate(date);
        iDishOrderService.saveOrUpdate(order);
        return ResultUtil.success();
    }

    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    @ApiOperation(value = "付款")
    public Result<Object> pay(@RequestParam String id){
        DishOrder order = iDishOrderService.getById(id);
        if(order == null) {
            return ResultUtil.error("订单不存在");
        }
        order.setStatus("已付款");
        iDishOrderService.saveOrUpdate(order);
        return ResultUtil.success();
    }
}
