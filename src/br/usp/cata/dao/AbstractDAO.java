package br.usp.cata.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;

import br.com.caelum.vraptor.util.hibernate.SessionCreator;


@SuppressWarnings(value = {"unchecked"})
public abstract class AbstractDAO<ID extends Serializable, T> implements BasicDAO<ID,T> {
    
	private final SessionCreator sessionCreator;

    private final Class<T> classEntity;

    protected AbstractDAO(final SessionCreator sessionCreator) {
        this.sessionCreator = sessionCreator;
        this.classEntity = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).
        		getActualTypeArguments()[1];
    }

    protected Session getSession() {
        return sessionCreator.getInstance();
    }

    protected Criteria createCriteria() {
        return getSession().createCriteria(classEntity);
    }

    public Class<T> getClassEntity() {
        return classEntity;
    }

    public void save(final T... objs) {
        for(T o : objs)
            getSession().save(o);
    }

    public void delete(final T... ts) {
        for(T t : ts)
            getSession().delete(t);
    }

    public void saveOrUpdate(final T... ts) {
        for(T t : ts)
            getSession().saveOrUpdate(t);
    }

    public T findByID(final ID id) {
        return (T)getSession().get(classEntity, id);
    }

    public List<T> findAll() {
        return findByCriteria();
    }

    public List<T> findByCriteria(final Criterion... criterion) {
        return findByCriteria(null, criterion);
    }

    public List<T> findByCriteria(final Order order, final Criterion... criterion) {
        final Criteria crit = this.createCriteria();
        
        for(Criterion c : criterion)
            crit.add(c);       
        if(order != null)
            crit.addOrder(order);       
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);

        return (List<T>)crit.list();
    }

    public List<T> findByExample(final T exampleInstance, final String... excludeProperty) {
        final Criteria crit = this.createCriteria();
        final Example example = Example.create(exampleInstance);
        
        for(String exclude : excludeProperty)
            example.excludeProperty(exclude);
            
        crit.add(example);
        crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        
        return (List<T>)crit.list();
    }
    
}
