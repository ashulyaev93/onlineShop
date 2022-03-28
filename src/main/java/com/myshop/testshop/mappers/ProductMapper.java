package com.myshop.testshop.mappers;

import com.myshop.testshop.dto.ProductDTO;
import com.myshop.testshop.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Named("productToProductDTO")
    @Mappings({
            @Mapping(source="product.id", target="id"),
            @Mapping(source="product.title", target="title"),
            @Mapping(source="product.price", target="price"),
            @Mapping(source="product.storageQuantity", target="storageQuantity"),
            @Mapping(source="product.measure", target="measure"),
            @Mapping(source="product.orders", target="orders")

    })
    ProductDTO productToProductDTO(Product product);
    @Mappings({
            @Mapping(source="productDTO.id", target="id"),
            @Mapping(source="productDTO.title", target="title"),
            @Mapping(source="productDTO.price", target="price"),
            @Mapping(source="productDTO.storageQuantity", target="storageQuantity"),
            @Mapping(source="productDTO.measure", target="measure"),
            @Mapping(target = "createdDate", ignore = true),
            @Mapping(target = "orders", ignore = true)
    })
    Product productDTOtoProduct(ProductDTO productDTO);
}
