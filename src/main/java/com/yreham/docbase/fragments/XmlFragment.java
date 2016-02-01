/**
 * &copy; yreham.com 2015-2016
 */
package com.yreham.docbase.fragments;

import com.yreham.docbase.Fragment;

/**
 * represent an XML fragment, not implemented
 * 
 * @author yreham.com dev.yreham
 *
 */
/**
 * @author yreham.com dev.yreham
 *
 */
public class XmlFragment implements Fragment {

  private String docId;

  private String content;


  /**
   * null constructor
   */
  public XmlFragment() {
    this(null, null);
  }

  /**
   * @param docId
   * @param content
   */
  public XmlFragment(String docId, String content) {
    super();
    this.docId = docId;
    this.content = content;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.yreham..web.docbase.Fragment#getDocId()
   */
  @Override
  public String getDocId() {

    return docId;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.yreham..web.docbase.Fragment#getDocType()
   */
  @Override
  public String getDocType() {

    return "xml";
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.yreham..web.docbase.Fragment#getContent()
   */
  @Override
  public String getContent() {

    return content;
  }

}
