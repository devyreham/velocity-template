/**
 * &copy; yreham.com 2015-2016
 */
package com.yreham.docbase.controllers;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yreham.docbase.model.DocumentEntity;
import com.yreham.docbase.services.DocumentService;

/**
 * @author yreham.com dev.yreham
 *
 */
@Controller
@RequestMapping("/document")
public class DocumentController {

  @Autowired
  DocumentService docService;

  /**
   * 
   */
  public DocumentController() {
    super();
  }

  /* persistence controller */
  /**
   * @param documentName
   * @param docType
   * @param description
   * @return
   */
  @RequestMapping(value = "/", method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public @ResponseBody DocumentEntity createDocument(
      @RequestParam("document_name") String documentName, @RequestParam("doc_type") String docType,
      @RequestParam("description") String description, HttpServletResponse response) {
    configcache(response);
    return docService.create(documentName, docType, description);
  }

  /**
   * @param id
   * @return
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public @ResponseBody DocumentEntity getDocument(@PathVariable("id") String id,
      HttpServletResponse response) {
    configcache(response);
    return docService.getDocument(id);
  }

  /**
   * @param id
   * @return
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public @ResponseBody DocumentEntity removeDocument(@PathVariable("id") String id,
      HttpServletResponse response) {
    configcache(response);
    return docService.remove(id);
  }

  /**
   * @param id
   * @param documentName
   * @param docType
   * @param description
   * @param content
   * @param categoryId
   * @param activeDate
   * @param expDate
   * @return
   */
  @RequestMapping(value = "/{id}", method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public @ResponseBody DocumentEntity updateDocument(@PathVariable("id") String id,
      @RequestParam("document_name") String documentName, @RequestParam("doc_type") String docType,
      @RequestParam("description") String description, @RequestParam("content") String content,
      @RequestParam("status_id") String statusId, @RequestParam("category_id") String categoryId,
      @RequestParam("subcategory_id") String subcategoryId,
      @RequestParam("activedate") String activeDate, @RequestParam("expdate") String expDate,
      HttpServletResponse response) {
    configcache(response);

    return docService.update(id, documentName, docType, description, statusId, categoryId,
        subcategoryId, activeDate, expDate, content);
  }

  /* business controllers */
  /**
   * @param docId
   * @return
   */
  @RequestMapping(value = "/find_doc", method = RequestMethod.GET)
  public @ResponseBody DocumentEntity getDocumentById(@RequestParam("doc_id") String docId,
      HttpServletResponse response) {
    configcache(response);
    return docService.getDocumentById(docId);
  }

  /**
   * @param pageNumber
   * @param pageSize
   * @return
   */
  @RequestMapping(value = "/find_all", method = RequestMethod.GET)
  public @ResponseBody Iterable<DocumentEntity> findAll(@RequestParam("page_number") int pageNumber,
      @RequestParam("page_size") int pageSize, HttpServletResponse response) {
    configcache(response);
    return docService.findAll(pageNumber, pageSize);
  }

  /**
   * @param pageNumber
   * @param pageSize
   * @param documentName
   * @param docType
   * @param category
   * @param subCategory
   * @param statusId
   * @return
   */
  @RequestMapping(value = "/find_by", method = RequestMethod.GET)
  public @ResponseBody Iterable<DocumentEntity> findBy(@RequestParam("page_number") int pageNumber,
      @RequestParam("page_size") int pageSize,
      @RequestParam(value = "document_name", required = false) String documentName,
      @RequestParam(value = "doc_type", required = false) String docType,
      @RequestParam(value = "category", required = false) String category,
      @RequestParam(value = "sub_category", required = false) String subCategory,
      @RequestParam(value = "status_id", required = false) String statusId,
      HttpServletResponse response) {
    configcache(response);
    return docService.findBy(documentName, docType, category, subCategory, statusId, pageNumber,
        pageSize);
  }

  /**
   * @param pageNumber
   * @param pageSize
   * @return
   */
  @RequestMapping(value = "/find_deleted", method = RequestMethod.GET)
  public @ResponseBody Iterable<DocumentEntity> findDeleted(
      @RequestParam("page_number") int pageNumber, @RequestParam("page_size") int pageSize,
      HttpServletResponse response) {
    configcache(response);
    return docService.findDeleted(pageNumber, pageSize);
  }

  /**
   * @param docId
   * @return
   */
  @RequestMapping(value = "/content", method = RequestMethod.GET)
  public @ResponseBody String getContent(@RequestParam("doc_id") String docId,
      HttpServletResponse response) {
    configcache(response);
    byte[] bytes = docService.getContent(docId);
    try {
      return new String(bytes, "UTF-8");
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
      return null;
    }
  }

  /**
   * Force no cache for Internet Explorer
   * 
   * @param response
   */
  private void configcache(HttpServletResponse response) {

    response.setHeader("Cache-Control", "no-cache,no-store,must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setDateHeader("Expires", 0);
  }

}
