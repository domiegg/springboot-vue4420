package cn.zwz.order.serviceimpl;

import cn.zwz.order.mapper.DishVarietyMapper;
import cn.zwz.order.entity.DishVariety;
import cn.zwz.order.service.IDishVarietyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 药品 服务层接口实现
 * @author 
 */
@Slf4j
@Service
@Transactional
public class IDishVarietyServiceImpl extends ServiceImpl<DishVarietyMapper, DishVariety> implements IDishVarietyService {

    @Autowired
    private DishVarietyMapper dishVarietyMapper;
}
