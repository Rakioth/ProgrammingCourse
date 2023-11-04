package com.raks.swiftly.domain.port;

import java.util.List;

public interface PortCrud<T, ID> {

    T create(T dto);

    T read(ID id);

    void update(T dto);

    void delete(ID id);

    List<T> getAll();

}