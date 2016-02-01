/**
 * &copy; yreham.com 2015-2016
 */
package com.yreham.docbase.services;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.yreham.docbase.config.DocbasePersistenceConfig;
import com.yreham.docbase.config.DocbaseWebConfig;
import com.yreham.docbase.model.DocumentEntity;
import com.yreham.docbase.services.DocumentService;
import com.yreham.docbase.util.DocumentIdGenerator;
import com.yreham.docbase.util.RandomIdGenerator;

/**
 * @author yreham.com dev.yreham
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DocbasePersistenceConfig.class, DocbaseWebConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
@WebAppConfiguration
public class DocumentTest_with_generator {

  @Autowired
  DocumentService service;

  DocumentIdGenerator docIdGenerator;

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @Before
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    docIdGenerator = new RandomIdGenerator();
  }

  /**
   * @throws java.lang.Exception
   */
  @After
  public void tearDown() throws Exception {}

  /**
   * Test method for
   * {@link com.yreham.docbase.services.DocumentService#create(java.lang.String, java.lang.String, java.lang.String, java.lang.String)}
   * .
   */
  @Test
  public void testCreate() {
    String documentName = "toto.html";
    String description = "this is a test";
    String docType = "html";
    assertNotNull(service.create(documentName, docType, description));
  }

  /**
   * Test method for
   * {@link com.yreham.docbase.services.DocumentService#getDocumentById(java.lang.String)} .
   */
  @Test
  public void testGetDocumentById() {
    String documentName = "titi.html";
    String description = "another test";
    String docType = "html";
    DocumentEntity doc = service.create(documentName, docType, description);
    assertNotNull(doc);
    assertNotNull(service.getDocumentById(doc.getDocId()));
  }

}
