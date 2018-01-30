package cn.kane.ttms.common.dao;
/**
 * 通过此接口实现了对子类共性的方法进行提取
 * @author soft01
 *通过类上的泛型约束类中
 *1 方法的参数类型
 *2 方法的返回值类型
 * @param <T>
 */
public interface BaseDao<T> {
	public int insertObject(T entity);
	public int updateObject(T entity);
	public T findObjectById(Integer id);
	
}
