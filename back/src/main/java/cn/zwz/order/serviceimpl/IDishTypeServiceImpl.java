package cn.zwz.order.serviceimpl;

import cn.zwz.order.mapper.DishTypeMapper;
import cn.zwz.order.entity.DishType;
import cn.zwz.order.service.IDishTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 药品类型 服务层接口实现
 * @author
 */
@Slf4j
@Service
@Transactional
public class IDishTypeServiceImpl extends ServiceImpl<DishTypeMapper, DishType> implements IDishTypeService {

    @Autowired
    private DishTypeMapper dishTypeMapper;
}
