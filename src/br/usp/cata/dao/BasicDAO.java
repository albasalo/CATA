package br.usp.cata.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.Criterion;


public interface BasicDAO<ID extends Serializable, T> {
	
    void save(T... objs);

    void delete(T... ts);

    void saveOrUpdate(T... ts);

    T findById(ID id);

    List<T> findAll();

    List<T> findByCriteria(Criterion... criterion);

    List<T> findByExample(T exampleInstance, String... excludeProperty);
    
}