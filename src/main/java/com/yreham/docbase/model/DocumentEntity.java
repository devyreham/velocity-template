/**
 * &copy; yreham.com 2015-2016
 */
package com.yreham.docbase.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author yreham.com dev.yreham
 *
 */
@Entity
@EntityListeners({AuditingEntityListener.class})
@Table(name = "documents")
public class DocumentEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * unique reference
   */
  @Column(name = "doc_id", length = 100)
  private String docId;

  /**
   * human readable name
   */
  @Column(name = "doc_name", length = 255)
  private String documentName;

  /**
   * optional document type
   */
  @Column(name = "doc_type", length = 255)
  private String docType;

  /**
   * optional physical location of the doc
   */
  @Column(name = "doc_url", length = 255)
  private String docUrl;

  /**
   * human readable description
   */
  @Column(name = "description", length = 255)
  private String description;

  /**
   * content if stored in db
   */
  @Lob
  @Column(name = "content", length = 1048576)
  private String content;

  /**
   * start date
   */
  @Column(name = "active_date")
  private Long activeDate;

  /**
   * expiration date
   */
  @Column(name = "exp_date")
  private Long expDate;

  /**
   * reserved feature category
   */
  @Column(name = "category_id", length = 100)
  private String categoryId;

  /**
   * reserved feature subcategory
   */
  @Column(name = "subcategory_id", length = 100)
  private String subcategoryId;

  /**
   * status of the document: active, open, in progress, whatever
   */
  @Column(name = "status_id", length = 100)
  private String statusId;

  /**
   * mark the document as deleted without actually deleting it
   */
  @Column(name = "deleted")
  private boolean deleted;

  /**
   * reserved feature
   */
  @Column(name = "is_template")
  private boolean isTemplate;

  @CreatedDate
  @Column(name = "date_created")
  private Long dateCreated;

  @LastModifiedDate
  @Column(name = "date_modified")
  private Long dateModified;

  @CreatedBy
  @Column(name = "created_by")
  private String createdBy;

  @LastModifiedBy
  @Column(name = "modified_by")
  private String modifiedBy;

  /**
   * 
   */
  public DocumentEntity() {}

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the docId
   */
  public String getDocId() {
    return docId;
  }

  /**
   * @param docId the docId to set
   */
  public void setDocId(String docId) {
    this.docId = docId;
  }

  /**
   * @return the documentName
   */
  public String getDocumentName() {
    return documentName;
  }

  /**
   * @param documentName the documentName to set
   */
  public void setDocumentName(String documentName) {
    this.documentName = documentName;
  }

  /**
   * @return the docType
   */
  public String getDocType() {
    return docType;
  }

  /**
   * @param docType the docType to set
   */
  public void setDocType(String docType) {
    this.docType = docType;
  }

  /**
   * @return the docUrl
   */
  public String getDocUrl() {
    return docUrl;
  }

  /**
   * @param docUrl the docUrl to set
   */
  public void setDocUrl(String docUrl) {
    this.docUrl = docUrl;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * avoid returning null content
   * 
   * @return the content
   */
  public String getContent() {
    return (this.content != null) ? this.content : StringUtils.EMPTY;
  }

  /**
   * @param content the content to set
   */
  public void setContent(String content) {
    this.content = content;
  }

  /**
   * @return the activeDate
   */
  public Long getActiveDate() {
    return activeDate;
  }

  /**
   * @param activeDate the activeDate to set
   */
  public void setActiveDate(Long activeDate) {
    this.activeDate = activeDate;
  }

  /**
   * @return the expDate
   */
  public Long getExpDate() {
    return expDate;
  }

  /**
   * @param expDate the expDate to set
   */
  public void setExpDate(Long expDate) {
    this.expDate = expDate;
  }

  /**
   * @return the categoryId
   */
  public String getCategoryId() {
    return categoryId;
  }

  /**
   * @param categoryId the categoryId to set
   */
  public void setCategoryId(String categoryId) {
    this.categoryId = categoryId;
  }

  /**
   * @return the subcategoryId
   */
  public String getSubcategoryId() {
    return subcategoryId;
  }

  /**
   * @param subcategoryId the subcategoryId to set
   */
  public void setSubcategoryId(String subcategoryId) {
    this.subcategoryId = subcategoryId;
  }

  /**
   * @return the statusId
   */
  public String getStatusId() {
    return statusId;
  }

  /**
   * @param statusId the statusId to set
   */
  public void setStatusId(String statusId) {
    this.statusId = statusId;
  }

  /**
   * @return the deleted
   */
  public boolean isDeleted() {
    return deleted;
  }

  /**
   * @param deleted the deleted to set
   */
  public void setDeleted(boolean deleted) {
    this.deleted = deleted;
  }

  /**
   * @return the isTemplate
   */
  public boolean isTemplate() {
    return isTemplate;
  }

  /**
   * @param isTemplate the isTemplate to set
   */
  public void setTemplate(boolean isTemplate) {
    this.isTemplate = isTemplate;
  }

  /**
   * @return the dateCreated
   */
  public Long getDateCreated() {
    return dateCreated;
  }

  /**
   * @param dateCreated the dateCreated to set
   */
  public void setDateCreated(Long dateCreated) {
    this.dateCreated = dateCreated;
  }

  /**
   * @return the dateModified
   */
  public Long getDateModified() {
    return dateModified;
  }

  /**
   * @param dateModified the dateModified to set
   */
  public void setDateModified(Long dateModified) {
    this.dateModified = dateModified;
  }

  /**
   * @return the createdBy
   */
  public String getCreatedBy() {
    return createdBy;
  }

  /**
   * @param createdBy the createdBy to set
   */
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  /**
   * @return the modifiedBy
   */
  public String getModifiedBy() {
    return modifiedBy;
  }

  /**
   * @param modifiedBy the modifiedBy to set
   */
  public void setModifiedBy(String modifiedBy) {
    this.modifiedBy = modifiedBy;
  }

}
