package com.yreham.docbase.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class DocumentAuditorAware implements AuditorAware<String> {

  @Override
  public String getCurrentAuditor() {
    try {
      UserDetails secUser =
          (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

      return (secUser == null) ? null : secUser.getUsername();
    } catch (NullPointerException npe) {
      return null;
    } catch (ClassCastException cce) {
      return null;
    }
  }

}
