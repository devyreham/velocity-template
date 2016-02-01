package com.yreham.docbase.services;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.yreham.docbase.model.VariableEntity;
import com.yreham.docbase.repository.VariableRepository;

@Component
@Order(value = 1)
public class SimpleVariableService implements VariableService {

  @Autowired
  VariableRepository repository;

  @Override
  public Iterable<VariableEntity> findAll() {

    return repository.findAll();
  }

  @Override
  public Iterable<VariableEntity> findSortedAll() {

    return repository.findAll(new Sort(new Sort.Order("varkey")));
  }

  @Override
  public VariableEntity findByKey(String key) {

    return repository.findByVarkey(key);
  }

  @Override
  public Iterable<VariableEntity> findByValue(String value, int pageNumber, int pageSize) {

    return repository.findByValue(value, new PageRequest(pageNumber, pageSize));
  }

  @Override
  public String resolveVariable(String key) {
    VariableEntity varentity = repository.findByVarkey(key);
    return varentity.getValue();
  }

  @Override
  public Map<String, String> toMap() {
    Collection<VariableEntity> varList = repository.findAll();
    if (varList != null) {
      HashMap<String, String> impl = Maps.newHashMapWithExpectedSize(varList.size());
      for (VariableEntity variable : varList) {
        impl.put(variable.getVarkey(), variable.getValue());
      }
      return impl;
    } else {
      return null;
    }
  }

  @Override
  public VariableEntity create(String key, String value) {
    VariableEntity entity = new VariableEntity(formatKey(key), value);

    return repository.saveAndFlush(entity);
  }

  @Override
  public VariableEntity save(VariableEntity entity) {
    return repository.saveAndFlush(entity);
  }

  @Override
  public VariableEntity remove(String key) {
    VariableEntity entity = repository.findByVarkey(key);
    repository.delete(entity);
    return null;
  }


  /**
   * replace space and special characters
   * 
   * @param key
   * @return
   */
  private String formatKey(String key) {
    String newKey = new String(key);
    newKey = newKey.replaceAll("\\s", "_").replaceAll("[^a-z_A-Z0-9]", StringUtils.EMPTY);

    return newKey.isEmpty() ? null : newKey;
  }

}
