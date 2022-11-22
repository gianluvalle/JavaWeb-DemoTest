package it.unikey.primaconnessionedbpostgres.BLL.service.abstracts;

import it.unikey.primaconnessionedbpostgres.BLL.exception.NotFoundException;

import java.util.List;

public interface CrudService<T> {
    T insert(T dto);
    T getById(Integer id) throws NotFoundException;
    List<T> getAll();
    T update(T dto) throws NotFoundException;
    void delete(Integer id) throws NotFoundException;
}
