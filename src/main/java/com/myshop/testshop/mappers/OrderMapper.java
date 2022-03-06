package com.myshop.testshop.mappers;

import com.myshop.testshop.dto.OrderDTO;
import com.myshop.testshop.entities.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Named("orderToOrderDTO")
    @Mappings({
            @Mapping(source="order.id", target="id"),
            @Mapping(source="order.code", target="code"),
            @Mapping(source="order.totalPrice", target="totalPrice"),
            @Mapping(source="order.user", target="user"),
            @Mapping(source="order.product", target="product")
    })
    OrderDTO orderToOrderDTO(Order order);//orderDTO
    @Mappings({
            @Mapping(source="orderDTO.id", target="id"),
            @Mapping(source="orderDTO.code", target="code"),
            @Mapping(source="orderDTO.totalPrice", target="totalPrice"),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "product", ignore = true),
            @Mapping(target = "createdDate", ignore = true)
    })
    Order orderDTOtoOrder(OrderDTO orderDTO);
}
