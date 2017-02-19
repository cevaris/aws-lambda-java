package com.cevaris.awslambda.stringapi;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ToUpperHandlerRequest {

  private String value;

}
