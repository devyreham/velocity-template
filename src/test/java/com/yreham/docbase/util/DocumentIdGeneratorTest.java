package com.yreham.docbase.util;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.yreham.docbase.util.DocumentIdGenerator;
import com.yreham.docbase.util.RandomIdGenerator;

public class DocumentIdGeneratorTest {

  @Before
  public void setUp() throws Exception {}

  @After
  public void tearDown() throws Exception {}

  @Test
  public void testGenerateKey() {
    DocumentIdGenerator docIdGenerator = new RandomIdGenerator();

    String firstgen = docIdGenerator.generateKey(6);
    System.out.println(firstgen);
    firstgen = docIdGenerator.generateKey(10);
    System.out.println(firstgen);
    firstgen = docIdGenerator.generateKey(4);
    System.out.println(firstgen);
    assertNotNull(firstgen);
  }

}
