package com.ccsw.tutorial.category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ccsw.tutorial.category.model.CategoryDto;

/**
 * @author ccsw
 *
 */
@Service
public class CategoryServicelmpl implements CategoryService {

    private long SEQUENCE = 1;
    private Map<Long, CategoryDto> categories = new HashMap<Long, CategoryDto>();

    /**
     * {@inheritDoc}
     */
    public List<CategoryDto> findAll() {

        return new ArrayList<CategoryDto>(this.categories.values());
    }

    /**
     * {@inheritDoc}
     */
    public void save(Long id, CategoryDto dto) {

        CategoryDto category;

        if (id == null) {
            category = new CategoryDto();
            category.setId(this.SEQUENCE++);
            this.categories.put(category.getId(), category);
        } else {
            category = this.categories.get(id);
        }

        category.setName(dto.getName());
    }

    /**
     * {@inheritDoc}
     */
    public void delete(Long id) {

        this.categories.remove(id);
    }

}