<html>
<head>
    <meta charset="UTF-8">
    <title>卖家列表</title>
    <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>


<#--主要内容content-->
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <table class="table table-bordered table-condensed">
                <thead>
                <tr>
                    <th>订单id</th>
                    <th>姓名</th>
                    <th>手机号</th>
                    <th>地址</th>
                    <th>金额</th>
                    <th>订单状态</th>
                    <th>支付状态</th>
                    <th>创建时间</th>
                    <th colspan="2">操作</th>
                </tr>
                </thead>

                <tbody>
                    <#list orderDTOPage.getContent() as orderDTO>
                    <tr>
                        <td>${orderDTO.getOrderId()}</td>
                        <td>${orderDTO.getBuyerName()}</td>
                        <td>${orderDTO.getBuyerIphone()}</td>
                        <td>${orderDTO.getBuyerAddress()}</td>
                        <td>${orderDTO.getOrderAmount()}</td>
                        <td>${orderDTO.getOrderStatusEnum().getMsg()}</td>
                        <td>${orderDTO.getPayStatusEnum().getMsg()}</td>
                        <td>${orderDTO.getCreateTime()}</td>
                        <#if orderDTO.getOrderStatusEnum().getCode()!=2>
                            <td>
                                <a href="/SpringBootSell/seller/order/cancel?orderId=${orderDTO.getOrderId()}">取消</a>
                            </td>
                        </#if>
                        <td>详情</td>
                    </tr>

                    </#list>
                </tbody>
            </table>

            <ul class="pagination pull-right">
                <#if currentPage lte 1>
                    <li class="disabled">
                        <a href="#">上一页</a>
                    </li>
                <#else >
                    <li>
                        <a href="/SpringBootSell/seller/order/list?page=${currentPage-1}&size=${size}">上一页</a>
                    </li>
                </#if>
                        <#list 1..orderDTOPage.getTotalPages() as indent>

                            <#if currentPage==indent>
                                   <li class="disabled">
                                       <a href="#">${indent}</a>
                                   </li>
                            <#else >
                                    <li>
                                        <a href="/SpringBootSell/seller/order/list?page=${indent}&size=${size}">${indent}</a>
                                    </li>
                            </#if>
                        </#list>
                <#if currentPage gte orderDTOPage.getTotalPages()>
                    <li class="disabled">
                        <a href="#">下一页</a>
                    </li>
                <#else>
                    <li>
                        <a href="/SpringBootSell/seller/order/list?page=${currentPage+1}&size=${size}">下一页</a>
                    </li>
                </#if>
            </ul>
        </div>
    </div>
</div>
</div>


</body>
</html>