package com.spring.henallux.firstSpringProject.dataAccess.dao;

import com.spring.henallux.firstSpringProject.dataAccess.entity.CategoryEntity;
import com.spring.henallux.firstSpringProject.dataAccess.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryDAOTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryDAO categoryDAO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCategories() {

        List<CategoryEntity> mockCategories = new ArrayList<>();

        CategoryEntity category1 = new CategoryEntity();
        category1.setCategoryID(1);
        category1.setCategoryFR("Catégorie 1");
        category1.setCategoryEN("Category 1");

        CategoryEntity category2 = new CategoryEntity();
        category2.setCategoryID(2);
        category2.setCategoryFR("Catégorie 2");
        category2.setCategoryEN("Category 2");

        mockCategories.add(category1);
        mockCategories.add(category2);

        when(categoryRepository.findAll()).thenReturn(mockCategories);

        ArrayList<CategoryEntity> categories = categoryDAO.getCategories();

        assertNotNull(categories);
        assertEquals(2, categories.size());
        assertEquals("Catégorie 1", categories.get(0).getCategoryFR());
        assertEquals("Category 1", categories.get(0).getCategoryEN());
        verify(categoryRepository, times(1)).findAll();
    }

    @Test
    void testGetCategoryById_Success() {
        CategoryEntity mockCategory = new CategoryEntity();
        mockCategory.setCategoryID(1);
        mockCategory.setCategoryFR("Catégorie 1");
        mockCategory.setCategoryEN("Category 1");

        when(categoryRepository.findById(1)).thenReturn(Optional.of(mockCategory));

        CategoryEntity category = categoryDAO.getCategoryById(1);

        assertNotNull(category);
        assertEquals(1, category.getCategoryID());
        assertEquals("Catégorie 1", category.getCategoryFR());
        assertEquals("Category 1", category.getCategoryEN());
        verify(categoryRepository, times(1)).findById(1);
    }

    @Test
    void testGetCategoryById_NotFound() {
        when(categoryRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> categoryDAO.getCategoryById(99));
        verify(categoryRepository, times(1)).findById(99);
    }
}
