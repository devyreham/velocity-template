/**
 * &copy; yreham.com 2015-2016
 */
package com.yreham.docbase.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import com.yreham.docbase.model.DocumentEntity;
import com.yreham.docbase.repository.DocumentRepository;
import com.yreham.docbase.util.DocumentIdGenerator;
import com.yreham.docbase.util.RandomIdGenerator;

/**
 * @author yreham.com dev.yreham
 *
 */
@Component
@Order(value = 1)
public class SimpleDocumentService implements DocumentService {

  private static final String SPACE = " ";

  private static final String NBSP = "&nbsp;";

  @Autowired
  DocumentRepository repository;

  /*
   * (non-Javadoc)
   * 
   * @see com.yreham..web.docbase.services.DocumentService#create(java.lang. String,
   * java.lang.String, java.lang.String, java.lang.String)
   */
  @Override
  public DocumentEntity create(String documentName, String docType, String description) {

    DocumentEntity doc = new DocumentEntity();

    DocumentIdGenerator docIdGenerator = new RandomIdGenerator();
    doc.setDocId(docIdGenerator.generateKey(6));
    doc.setDocumentName(documentName);
    doc.setDocType(docType);
    doc.setDescription(description);

    return repository.saveAndFlush(doc);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.yreham..web.docbase.services.DocumentService#save(java.lang. String)
   */
  @Override
  public DocumentEntity save(DocumentEntity doc) {

    removeNBSP(doc);
    return repository.saveAndFlush(doc);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.yreham..web.docbase.services.DocumentService#remove(java.lang. String)
   */
  @Override
  public DocumentEntity remove(String id) {
    // repository.delete(Long.parseLong(id));
    DocumentEntity doc = repository.getOne(Long.parseLong(id));
    doc.setDeleted(true);

    return repository.saveAndFlush(doc);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.yreham..web.docbase.services.DocumentService#update(java.lang. String)
   */
  @Override
  public DocumentEntity update(String id, String documentName, String docType, String description,
      String statusId, String categoryId, String subcategoryId, String activeDate, String expDate,
      String content) {

    DocumentEntity doc = repository.getOne(Long.parseLong(id));
    doc.setDocumentName(documentName);
    doc.setDocType(docType);
    doc.setDescription(description);
    doc.setStatusId(statusId);
    doc.setCategoryId(categoryId);
    doc.setSubcategoryId(subcategoryId);
    doc.setActiveDate(Long.parseLong(activeDate));
    doc.setExpDate(Long.parseLong(expDate));
    doc.setContent(content);
    removeNBSP(doc);
    return repository.saveAndFlush(doc);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.yreham..web.docbase.services.DocumentService#getDocument(java. lang.String)
   */
  @Override
  public DocumentEntity getDocument(String id) {

    return repository.getOne(Long.parseLong(id));
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.yreham..web.docbase.services.DocumentService#getDocumentById(java .lang.String)
   */
  @Override
  public DocumentEntity getDocumentById(String docId) {

    return repository.findByDocId(docId);
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.yreham..web.docbase.services.DocumentService#findAll(int, int)
   */
  @Override
  public Iterable<DocumentEntity> findAll(int pageNumber, int pageSize) {

    return repository.findAll(new PageRequest(pageNumber, pageSize));
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.yreham..web.docbase.services.DocumentService#findBy(java.lang. String,
   * java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, int)
   */
  @Override
  public Iterable<DocumentEntity> findBy(String documentName, String docType, String category,
      String subCategory, String status, int pageNumber, int pageSize) {

    Iterable<DocumentEntity> docList = null;

    if (documentName != null) {
      docList = repository.findByDocumentName(documentName, new PageRequest(pageNumber, pageSize));
    } else if (docType != null) {
      docList = repository.findByDocType(docType, new PageRequest(pageNumber, pageSize));
    } else if (category != null && subCategory != null) {
      docList = repository.findByCategoryIdAndSubcategoryId(category, subCategory,
          new PageRequest(pageNumber, pageSize));
    } else if (category != null) {
      docList = repository.findByCategoryId(category, new PageRequest(pageNumber, pageSize));
    } else if (status != null) {
      docList = repository.findByStatusId(status, new PageRequest(pageNumber, pageSize));
    } else {
      docList = repository.findByDocumentName(null, new PageRequest(pageNumber, pageSize));
    }

    return docList;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.yreham..web.docbase.services.DocumentService#findDeleted(int, int)
   */
  @Override
  public Iterable<DocumentEntity> findDeleted(int pageNumber, int pageSize) {

    return repository.findByDeleted(true, new PageRequest(pageNumber, pageSize));
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.yreham..web.docbase.services.DocumentService#getContent(java.lang .String)
   */
  @Override
  public byte[] getContent(String docId) {
    DocumentEntity doc = repository.findByDocId(docId);

    return (doc.getContent() == null) ? null : doc.getContent().getBytes();
  }

  /**
   * @param doc
   */
  private void removeNBSP(DocumentEntity doc) {
    StringBuilder content = new StringBuilder(doc.getContent());
    Pattern pattern = Pattern.compile("</p>");
    Matcher matcher = pattern.matcher(content);
    while (matcher.find()) {
      content = new StringBuilder(matcher.replaceAll("</p>\n"));
    }
    matcher.reset();
    pattern = Pattern.compile(NBSP);
    matcher = pattern.matcher(content);
    doc.setContent(matcher.replaceAll(SPACE));
  }

}
