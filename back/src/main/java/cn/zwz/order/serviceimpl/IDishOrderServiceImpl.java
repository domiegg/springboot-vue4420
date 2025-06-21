package cn.zwz.order.serviceimpl;

import cn.zwz.order.mapper.DishOrderMapper;
import cn.zwz.order.entity.DishOrder;
import cn.zwz.order.service.IDishOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 药品订单 服务层接口实现
 * @author
 */
@Slf4j
@Service
@Transactional
public class IDishOrderServiceImpl extends ServiceImpl<DishOrderMapper, DishOrder> implements IDishOrderService {

    @Autowired
    private DishOrderMapper dishOrderMapper;
}
