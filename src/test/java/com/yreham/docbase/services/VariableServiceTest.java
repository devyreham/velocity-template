package com.yreham.docbase.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Map;

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
import com.yreham.docbase.model.VariableEntity;
import com.yreham.docbase.services.VariableService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DocbasePersistenceConfig.class, DocbaseWebConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
@WebAppConfiguration
public class VariableServiceTest {

  @Autowired
  VariableService service;

  @Autowired
  private WebApplicationContext webApplicationContext;

  private MockMvc mockMvc;

  @Before
  public void setUp() throws Exception {
    mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
  }

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testFindAll() {
    service.create("a!e f$^", "eeageg");
    service.create("zezz", "eeageg");
    service.create("hbod", "eeageg");
    Iterable<VariableEntity> varList = service.findSortedAll();
    assertTrue(varList.iterator().hasNext());
    for (VariableEntity var : varList) {
      System.out.println(var.getVarkey());
    }
  }

  @Test
  public void testFindByKey() {
    service.create("e a ff", "eeageg");
    assertNotNull(service.findByKey("e_a_ff"));
  }

  @Test
  public void testFindByValue() {
    service.create("eee", "PEPERPOTT");
    service.create("fff", "PEPERPOTT");
    Iterable<VariableEntity> varList = service.findByValue("PEPERPOTT", 0, 20);
    assertTrue(varList.iterator().hasNext());
  }

  @Test
  public void testResolveVariable() {
    service.create("xeded", "resolved");
    assertTrue(service.resolveVariable("xeded").equals("resolved"));
  }

  @Test
  public void testToMap() {
    service.create("123", "0000000");
    service.create("456", "0000000");
    Map<String, String> extract = service.toMap();
    assertTrue(extract.containsKey("123") && extract.containsKey("456"));
  }

}
