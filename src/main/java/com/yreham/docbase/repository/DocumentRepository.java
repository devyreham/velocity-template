/**
 * &copy; yreham.com
 */
package com.yreham.docbase.repository;

import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.yreham.docbase.model.DocumentEntity;

/**
 * @author yreham.com dev.yreham
 *
 */
@Repository
@PersistenceContext(
    unitName = com.yreham.docbase.config.DocbasePersistenceConfig.PERSISTENCE_UNIT)
public interface DocumentRepository extends JpaRepository<DocumentEntity, Long> {

  public DocumentEntity findByDocId(String docId);

  public Page<DocumentEntity> findByDocumentName(String documentName, Pageable pageRequest);

  public Page<DocumentEntity> findByDocType(String docType, Pageable pageRequest);

  public Page<DocumentEntity> findByCategoryId(String category, Pageable pageRequest);

  public Page<DocumentEntity> findByCategoryIdAndSubcategoryId(String category, String subCategory,
      Pageable pageRequest);

  public Page<DocumentEntity> findByStatusId(String status, Pageable pageRequest);

  public Page<DocumentEntity> findByDeleted(boolean deleted, Pageable pageRequest);

}
