package com.lzhs.forms;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Description: 描述 <br/>
 * Author: LZHS <br/>
 * Email: 1050629507@qq.com <br/>
 * Time: 2018/11/15 : 2:35 PM<br/>
 */
@Data
public class OrderForm {
    /**
     * address : 慕课网总部
     * phone : 18868822111
     * openid : ew3euwhd7sjw9diwkq
     * name : 张三
     * items : [{"productQuantity":1561561465,"productId":"1423113435324"}]
     */
    @NotEmpty(message = "买家姓名不能为空")
    private String name;
    @NotEmpty(message = "买家手机号不能为空")
    private String phone;
    @NotEmpty(message = "买家地址不能为空")
    private String address;
    @NotEmpty(message = "买家用户ID不能为空")
    private String openid;
    @NotNull(message = "购物车信息不能为空")
    private List<ItemsEntity> items;

    @Data
    public static class ItemsEntity {
        /**
         * productQuantity : 1561561465
         * productId : 1423113435324
         */
        @Min(value = 1, message = "购买商品数量必须大于等于1")
        private int productQuantity;
        @NotNull(message = "商品ID 不能为空")
        private String productId;

    }
}
