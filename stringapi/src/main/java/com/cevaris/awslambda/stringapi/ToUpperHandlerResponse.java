package com.cevaris.awslambda.stringapi;


import com.cevaris.awslambda.utils.JsonSerializable;
import com.cevaris.awslambda.utils.JsonUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ToUpperHandlerResponse implements JsonSerializable {

  private String value;

  @Override
  public String toString() {
    return toJson();
  }

  @Override
  public String toJson() {
    return JsonUtils.toJson(this);
  }
}
