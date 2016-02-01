/**
 * &copy; yreham.com 2015-2016
 */
package com.yreham.docbase.fragments;

import com.yreham.docbase.Fragment;

/**
 * represent a plain text fragment
 * 
 * @author yreham.com dev.yreham
 *
 */
public class PlainTextFragment implements Fragment {

  private String docId;

  private String docType;

  private String content;

  /**
   * 
   */
  public PlainTextFragment() {
    this(null, null, null);
  }


  public PlainTextFragment(String docId, String docType, String content) {
    super();
    this.docId = docId;
    this.docType = docType;
    this.content = content;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.yreham..web.docbase.Fragment#getDocId()
   */
  @Override
  public String getDocId() {
    return this.docId;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.yreham..web.docbase.Fragment#getDocType()
   */
  @Override
  public String getDocType() {
    return this.docType;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.yreham..web.docbase.Fragment#getContent()
   */
  @Override
  public String getContent() {
    return this.content;
  }

}
