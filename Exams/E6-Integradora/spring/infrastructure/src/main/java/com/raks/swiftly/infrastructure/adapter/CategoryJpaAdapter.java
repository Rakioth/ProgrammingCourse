package com.raks.swiftly.infrastructure.adapter;

import com.raks.swiftly.domain.model.dto.CategoryDto;
import com.raks.swiftly.domain.port.spi.CategoryJpaPort;
import com.raks.swiftly.infrastructure.mapper.CategoryMapper;
import com.raks.swiftly.infrastructure.model.entity.Category;
import com.raks.swiftly.infrastructure.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryJpaAdapter extends JpaAdapter<Category, CategoryDto, Long> implements CategoryJpaPort {

    @Autowired
    public CategoryJpaAdapter(CategoryRepository categoryRepository) {
        super(categoryRepository, CategoryMapper.INSTANCE);
    }

}