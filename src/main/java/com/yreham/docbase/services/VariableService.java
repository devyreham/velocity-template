/**
 * &copy; yreham.com
 */
package com.yreham.docbase.services;

import java.util.Map;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yreham.docbase.model.VariableEntity;

/**
 * @author yreham.com dev.yreham
 *
 */
@Service
@Transactional(value = "transactionManager")
public interface VariableService {

  /**
   * @return
   */
  public Iterable<VariableEntity> findAll();

  /**
   * @param sort
   * @return
   */
  public Iterable<VariableEntity> findSortedAll();

  /**
   * @param key
   * @return
   */
  public VariableEntity findByKey(String key);

  /**
   * @param value
   * @return
   */
  public Iterable<VariableEntity> findByValue(String value, int pageNumber, int pageSize);

  /**
   * @param key
   * @return
   */
  public String resolveVariable(String key);

  /**
   * @return
   */
  public Map<String, String> toMap();

  /**
   * @param key
   * @param value
   * @return
   */
  public VariableEntity create(String key, String value);

  /**
   * @param entity
   * @return
   */
  public VariableEntity save(VariableEntity entity);

  /**
   * @param key
   * @return
   */
  public VariableEntity remove(String key);

}
