package org.wsan.hibernateapp.repositories;

import java.util.List;

public interface CrudRepository <T>{

    List<T> listar();
    T porId(Long id);
    void guradar(T t);
    void eliminar(Long id);
}
