package com.spring.henallux.firstSpringProject.dataAccess.dao;

import java.util.ArrayList;

public interface CategoryDataAccess {
    ArrayList<String> getCategories(String locale);
}
