/**
 * &copy; yreham.com
 */
package com.yreham.docbase.repository;

import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yreham.docbase.model.VariableEntity;

/**
 * @author yreham.com dev.yreham
 *
 */
@Repository
@PersistenceContext(
    unitName = com.yreham.docbase.config.DocbasePersistenceConfig.PERSISTENCE_UNIT)
public interface VariableRepository extends JpaRepository<VariableEntity, Long> {

  public VariableEntity findByVarkey(String varkey);

  public Page<VariableEntity> findByValue(String value, Pageable pagerequest);
}
