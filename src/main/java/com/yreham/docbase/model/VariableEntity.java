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
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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
@Table(name = "variables", uniqueConstraints = @UniqueConstraint(columnNames = {"varkey"}) )
public class VariableEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "varkey", nullable = false, length = 100)
  private String varkey;

  @Column(name = "value")
  private String value;

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
   * null constructor
   */
  public VariableEntity() {
    this(null, null);
  }

  /**
   * @param varKey
   * @param value
   */
  public VariableEntity(String varKey, String value) {
    this.varkey = varKey;
    this.value = value;
  }

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
   * @return the varkey
   */
  public String getVarkey() {
    return varkey;
  }

  /**
   * @param varkey the varkey to set
   */
  public void setVarkey(String varkey) {
    this.varkey = varkey;
  }

  /**
   * @return the value
   */
  public String getValue() {
    return value;
  }

  /**
   * @param value the value to set
   */
  public void setValue(String value) {
    this.value = value;
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
