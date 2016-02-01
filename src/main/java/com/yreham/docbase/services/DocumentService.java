/**
 * &copy; yreham.com
 */
package com.yreham.docbase.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yreham.docbase.model.DocumentEntity;

/**
 * @author yreham.com dev.yreham
 *
 */
@Service
@Transactional(value = "transactionManager")
public interface DocumentService {

  /**
   * @param docId
   * @param documentName
   * @param docType
   * @param description
   * @return
   */
  public DocumentEntity create(String documentName, String docType, String description);

  /**
   * @param id
   * @return
   */
  public DocumentEntity save(DocumentEntity doc);

  /**
   * @param id
   * @return
   */
  public DocumentEntity remove(String id);

  /**
   * @param id
   * @return
   */
  public DocumentEntity update(String id, String documentName, String docType, String description,
      String statusId, String categoryId, String subcategoryId, String activeDate, String expDate,
      String content);

  /**
   * @param id
   * @return
   */
  public DocumentEntity getDocument(String id);

  /**
   * @param docId
   * @return
   */
  public DocumentEntity getDocumentById(String docId);

  /**
   * @param pageNumber
   * @param pageSize
   * @return
   */
  public Iterable<DocumentEntity> findAll(int pageNumber, int pageSize);

  /**
   * @param documentName
   * @param docType
   * @param category
   * @param subCategory
   * @param status
   * @param pageNumber
   * @param pageSize
   * @return
   */
  public Iterable<DocumentEntity> findBy(String documentName, String docType, String category,
      String subCategory, String status, int pageNumber, int pageSize);

  /**
   * @param pageNumber
   * @param pageSize
   * @return
   */
  public Iterable<DocumentEntity> findDeleted(int pageNumber, int pageSize);

  /**
   * @param docId
   * @return
   */
  public byte[] getContent(String docId);
}
