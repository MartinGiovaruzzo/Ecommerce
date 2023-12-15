package com.aceleracion.ecommercegyl.utils;

import com.aceleracion.ecommercegyl.exception.MyException;

import java.util.List;

public class ListValidator {
    public static void validateResponseList(List<?> list) {
        if (list.isEmpty()) {
            throw new MyException("noDataFound");
        }
    }
}
