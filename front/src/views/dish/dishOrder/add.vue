<template>
<div>
    <Card>
        <div slot="title">
            <div class="edit-head">
                <a @click="close" class="back-title">
                    <Icon type="ios-arrow-back" />返回
                </a>
                <div class="head-name">添加药品订单</div>
                <span></span>
                <a @click="close" class="window-close">
                    <Icon type="ios-close" size="31" class="ivu-icon-ios-close" />
                </a>
            </div>
        </div>
        <Form ref="form" :model="form" :label-width="100" :rules="formValidate" label-position="left">
            <FormItem label="药品ID" prop="dishId">
                <Input v-model="form.dishId" clearable style="width:570px" />
            </FormItem>
            <FormItem label="药品名称" prop="title">
                <Input v-model="form.title" clearable style="width:570px" />
            </FormItem>
            <FormItem label="药品类型" prop="type">
                <Input v-model="form.type" clearable style="width:570px" />
            </FormItem>
            <FormItem label="药品介绍" prop="content">
                <Input v-model="form.content" clearable style="width:570px" />
            </FormItem>
            <FormItem label="药品图片" prop="image">
                <Input v-model="form.image" clearable style="width:570px" />
            </FormItem>
            <FormItem label="药品价格" prop="price">
                <InputNumber v-model="form.price" min="0" max="5000000" style="width:570px"></InputNumber>
            </FormItem>
            <FormItem label="下单状态" prop="status">
                <Input v-model="form.status" clearable style="width:570px" />
            </FormItem>
            <FormItem label="下单数量" prop="number">
                <InputNumber v-model="form.number" min="0" max="5000000" style="width:570px"></InputNumber>
            </FormItem>
            <FormItem label="下单人ID" prop="orderId">
                <Input v-model="form.orderId" clearable style="width:570px" />
            </FormItem>
            <FormItem label="下单人" prop="orderName">
                <Input v-model="form.orderName" clearable style="width:570px" />
            </FormItem>
            <FormItem label="下单时间" prop="orderTime">
                <Input v-model="form.orderTime" clearable style="width:570px" />
            </FormItem>
            <Form-item class="br">
                <Button @click="handleSubmit" :loading="submitLoading" type="primary">提交并保存</Button>
                <Button @click="handleReset">重置</Button>
                <Button type="dashed" @click="close">关闭</Button>
            </Form-item>
        </Form>
    </Card>
</div>
</template>

<script>
import {
    addDishOrder
} from "./api.js";
export default {
    name: "add",
    components: {},
    data() {
        return {
            submitLoading: false, // 表单提交状态
            form: { // 添加或编辑表单对象初始化数据
                dishId: "",
                title: "",
                type: "",
                content: "",
                image: "",
                price: 0,
                status: "",
                number: 0,
                orderId: "",
                orderName: "",
                orderTime: "",
            },
            // 表单验证规则
            formValidate: {}
        };
    },
    methods: {
        init() {},
        handleReset() {
            this.$refs.form.resetFields();
        },
        handleSubmit() {
            this.$refs.form.validate(valid => {
                if (valid) {
                    addDishOrder(this.form).then(res => {
                        this.submitLoading = false;
                        if (res.success) {
                            this.$Message.success("操作成功");
                            this.submited();
                        }
                    });
                }
            });
        },
        close() {
            this.$emit("close", true);
        },
        submited() {
            this.$emit("submited", true);
        }
    },
    mounted() {
        this.init();
    }
};
</script>

<style lang="less">
// 建议引入通用样式 具体路径自行修改 可删除下面样式代码
// @import "../../../styles/single-common.less";
.edit-head {
    display: flex;
    align-items: center;
    justify-content: space-between;
    position: relative;

    .back-title {
        color: #515a6e;
        display: flex;
        align-items: center;
    }

    .head-name {
        display: inline-block;
        height: 20px;
        line-height: 20px;
        font-size: 16px;
        color: #17233d;
        font-weight: 500;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
    }

    .window-close {
        z-index: 1;
        font-size: 12px;
        position: absolute;
        right: 0px;
        top: -5px;
        overflow: hidden;
        cursor: pointer;

        .ivu-icon-ios-close {
            color: #999;
            transition: color .2s ease;
        }
    }

    .window-close .ivu-icon-ios-close:hover {
        color: #444;
    }
}
</style>
