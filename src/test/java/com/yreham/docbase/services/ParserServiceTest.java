package com.yreham.docbase.services;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

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

import com.yreham.docbase.TParser;
import com.yreham.docbase.config.DocbasePersistenceConfig;
import com.yreham.docbase.config.DocbaseWebConfig;
import com.yreham.docbase.services.ParserService;
import com.yreham.docbase.services.VariableService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DocbasePersistenceConfig.class, DocbaseWebConfig.class})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class, TransactionalTestExecutionListener.class})
@WebAppConfiguration
public class ParserServiceTest {

  @Autowired
  ParserService parserService;

  @Autowired
  VariableService varService;

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
  public void testExecute_with_conditions() throws FileNotFoundException {
    TParser tParser = parserService.create("$", "");
    File newFile = new File("/tmp/parse_with_conditions.html");

    varService.create("bubble", "apple");
    varService.create("gum", "an orange");
    varService.create("switch", "true");

    parserService.execute(tParser,
        "<div>\t<div> #if($switch) replace this $bubble by $gum #if($gum) inner if #else inner else #end after inner if #else replace $gum by $bubble #end </div></div>\n"
            .getBytes(),
        new FileOutputStream(newFile), varService.toMap());
    // if(newFile != null) newFile.delete();
  }

  @Test
  public void testExecute_with_errors() throws FileNotFoundException {
    TParser tParser = parserService.create("$", "");
    File newFile = new File("/tmp/parse_with_errors.html");

    varService.create("alpha", "one alpha");
    varService.create("beta", "two beta");

    parserService.execute(tParser,
        "<div>\t<div>#if($alpha) replace this $bubble by $gum #else replace #else </div></div>\n"
            .getBytes(),
        new FileOutputStream(newFile), varService.toMap());
    // if(newFile != null) newFile.delete();
  }

  @Test
  public void testExecute_with_emptyvariables() throws FileNotFoundException {
    TParser tParser = parserService.create("$", "");
    File newFile = new File("/tmp/parse_with_variables.html");

    varService.create("dummyvar", "dummy variable");

    parserService.execute(tParser, "<div>$dummyvar $foo</div>\n".getBytes(),
        new FileOutputStream(newFile), varService.toMap());
    // if(newFile != null) newFile.delete();
  }

  @Test
  public void testExecute_with_equals() throws FileNotFoundException {
    TParser tParser = parserService.create("$", "");
    File newFile = new File("/tmp/parse_with_equals.html");

    varService.create("equaltest", "random être ou ne pas être");

    parserService.execute(tParser,
        "<div>#if($equaltest == \"random être ou ne pas être\") reachable #end #if($equaltest == 'toto') est la not reachable #end $foo</div>\n"
            .getBytes(),
        new FileOutputStream(newFile), varService.toMap());
    // if(newFile != null) newFile.delete();
  }

}
