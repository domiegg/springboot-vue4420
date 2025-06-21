package cn.zwz.order.entity;

import cn.zwz.basics.baseClass.ZwzBaseEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.math.BigDecimal;

/**
 * @author 
 * 
 */
@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "a_dish_order")
@TableName("a_dish_order")
@ApiModel(value = "药品订单")
public class DishOrder extends ZwzBaseEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "药品ID")
    private String dishId;

    @ApiModelProperty(value = "药品名称")
    private String title;

    @ApiModelProperty(value = "药品类型")
    private String type;

    @ApiModelProperty(value = "药品介绍")
    private String content;

    @ApiModelProperty(value = "药品图片")
    private String image;

    @ApiModelProperty(value = "药品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "下单状态",notes = "已加购、已下单、已付款")
    private String status;

    @ApiModelProperty(value = "下单数量")
    private BigDecimal number;

    @ApiModelProperty(value = "下单人ID")
    private String orderId;

    @ApiModelProperty(value = "下单人")
    private String orderName;

    @ApiModelProperty(value = "下单时间")
    private String orderTime;

    @ApiModelProperty(value = "下单地址")
    private String address;

    @ApiModelProperty(value = "预约时间")
    private String date;
}
