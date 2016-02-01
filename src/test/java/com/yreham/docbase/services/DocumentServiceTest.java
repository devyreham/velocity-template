package com.yreham.docbase.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DocbasePersistenceConfig.class, DocbaseWebConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
@WebAppConfiguration
public class DocumentServiceTest {

  @Autowired
  DocumentService service;

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @Before
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @After
  public void tearDown() throws Exception {}

  public void testCreate() {
    String documentName = "contrat ABC";
    String description = "test contrat";
    String docType = "pdf";

    assertNotNull(service.create(documentName, docType, description));
  }

  public void testGetDocument() {

    String refId = "1";
    assertNotNull(service.getDocument(refId));
  }

  @Test
  public void testGetDocumentById() {

    String documentName = "contrat ABC";
    String description = "test contrat";
    String docType = "pdf";
    DocumentEntity doc = service.create(documentName, docType, description);
    assertNotNull(doc);
    assertNotNull(service.getDocumentById(doc.getDocId()));
    System.out.println(doc.getDocId());
  }

  @Test
  public void testFindAll() {

    String documentName = "contrat ABC";
    String description = "test contrat";
    String docType = "pdf";

    assertNotNull(service.create(documentName, docType, description));
    Iterable<DocumentEntity> docList = service.findAll(0, 20);
    assertTrue(docList != null ? docList.iterator().hasNext() : false);
  }

  @Test
  public void testFindBy() {
    String documentName = "contrat ABC";
    String description = "test contrat";
    String docType = "pdf";
    String category = null;
    String subCategory = null;
    String status = null;

    assertNotNull(service.create(documentName, docType, description));
    documentName = null;

    Iterable<DocumentEntity> docList =
        service.findBy(documentName, docType, category, subCategory, status, 0, 20);
    assertTrue(docList != null ? docList.iterator().hasNext() : false);
  }

  @Test
  public void testFindDeleted() {
    String documentName = "contrat ABC";
    String description = "test contrat";
    String docType = "pdf";

    assertNotNull(service.create(documentName, docType, description));
    Iterable<DocumentEntity> docList = service.findDeleted(0, 20);
    assertTrue(docList != null ? !docList.iterator().hasNext() : true);
  }

  @Test
  public void testGetContent() {

    String documentName = "contrat ABC";
    String description = "test contrat";
    String docType = "pdf";
    DocumentEntity doc = service.create(documentName, docType, description);
    assertNotNull(doc);
    doc.setContent("<p>I&nbsp;write&nbsp;test</p><p>Iwrite&nbsp;enw line</p>\n");
    System.out.println(doc.getContent());

    service.save(doc);
    InputStream input = new ByteArrayInputStream(service.getContent(doc.getDocId()));
    assertNotNull(input);
    BufferedReader br = new BufferedReader(new InputStreamReader(input));
    try {
      String str = br.readLine();
      while (str != null) {
        System.out.println(str);
        str = br.readLine();
      }
      input.close();
    } catch (IOException e1) {
      // TODO Auto-generated catch block
      e1.printStackTrace();
    }
  }

}
