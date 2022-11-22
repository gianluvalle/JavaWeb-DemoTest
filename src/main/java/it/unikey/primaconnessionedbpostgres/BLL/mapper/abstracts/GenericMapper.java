package it.unikey.primaconnessionedbpostgres.BLL.mapper.abstracts;

import java.util.List;

public interface GenericMapper<E, D> {
    D fromEntityToDto (E entity);
    E fromDtoToEntity(D dto);
    List<D> fromEntityListToDtoList(List<E> entities);
    List<E> fromDtoListToEntityList(List<D> dtos);
}
