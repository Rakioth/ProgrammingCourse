package com.raks.swiftly.domain.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CategoryDto {

    private Long              id;
    private String            code;
    private String            description;
    private CategoryDto       categoryParent;
    private List<CategoryDto> categoriesChild;
    private AuditDto          audit;
    private ClientDto         client;
    private ProductDto        product;
    private StoreDto          store;

}