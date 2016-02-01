/**
 * &copy; yreham.com 2015-2016
 */
package com.yreham.docbase.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.yreham.docbase.model.VariableEntity;
import com.yreham.docbase.services.VariableService;

/**
 * @author yreham.com dev.yreham
 *
 */
@Controller
@RequestMapping("/variable")
public class VariableController {

  @Autowired
  VariableService varService;

  /**
   * 
   */
  public VariableController() {
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
  public @ResponseBody VariableEntity createVariable(@RequestParam("key") String key,
      @RequestParam("value") String value, HttpServletResponse response) {
    configcache(response);
    return varService.create(key, value);
  }

  /**
   * @param id
   * @return
   */
  @RequestMapping(value = "/", method = RequestMethod.DELETE)
  public @ResponseBody VariableEntity removeVariable(@RequestParam("key") String key,
      HttpServletResponse response) {
    configcache(response);
    return varService.remove(key);
  }

  /**
   * @param id
   * @param documentName
   * @param docType
   * @param description
   * @param content
   * @param statusId
   * @return
   */
  @RequestMapping(value = "/update", method = RequestMethod.POST,
      consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
  public @ResponseBody VariableEntity saveVariable(@RequestParam("key") String key,
      @RequestParam("value") String value, HttpServletResponse response) {
    configcache(response);
    VariableEntity var = varService.findByKey(key);
    var.setValue(value);

    return varService.save(var);
  }

  /**
   * @param response
   * @return
   */
  @RequestMapping(value = "/map", method = RequestMethod.GET)
  public @ResponseBody Map<String, String> toMap(HttpServletResponse response) {
    configcache(response);
    return Maps.newHashMap(varService.toMap());
  }

  /**
   * @param key
   * @param response
   * @return
   */
  @RequestMapping(value = "/resolve", method = RequestMethod.GET)
  public @ResponseBody String resolveVariable(@RequestParam("key") String key,
      HttpServletResponse response) {
    configcache(response);
    return varService.resolveVariable(key);
  }

  /**
   * @param pageNumber
   * @param pageSize
   * @param value
   * @param response
   * @return
   */
  @RequestMapping(value = "/find_by", method = RequestMethod.GET)
  public @ResponseBody Iterable<VariableEntity> findByValue(
      @RequestParam("page_number") int pageNumber, @RequestParam("page_size") int pageSize,
      @RequestParam("value") String value, HttpServletResponse response) {
    configcache(response);
    return varService.findByValue(value, pageNumber, pageSize);
  }

  /**
   * @param response
   * @return
   */
  @RequestMapping(value = "/find_all", method = RequestMethod.GET)
  public @ResponseBody Iterable<VariableEntity> findAll(HttpServletResponse response) {
    configcache(response);
    return varService.findSortedAll();
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
