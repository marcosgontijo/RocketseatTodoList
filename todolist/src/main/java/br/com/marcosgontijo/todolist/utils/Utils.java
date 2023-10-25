package br.com.marcosgontijo.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class Utils {

    public static void copyNonNullProperties(Object source, Object target){
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }


    //Coletando tudo que tenho de propriedade null, seja id etc.... atribui dentro do meu copyProperties
    public static String[] getNullPropertyNames(Object source){
        //Uma interface que oferece uma forma pra acessar as propiedades de um objeto dentro do java
        final BeanWrapper src = new BeanWrapperImpl(source);

        PropertyDescriptor [] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();

        for(PropertyDescriptor pd: pds){
            Object srcValue = src.getPropertyValue(pd.getName());
            if(srcValue == null){
                emptyNames.add(pd.getName());
            }
        }

         String[] result = new String[emptyNames.size()];
         return emptyNames.toArray(result);
    }
    
}
