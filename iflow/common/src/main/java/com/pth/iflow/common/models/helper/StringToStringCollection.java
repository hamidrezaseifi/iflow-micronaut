package com.pth.iflow.common.models.helper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class StringToStringCollection extends StdDeserializer<Collection<String>> {

  protected StringToStringCollection() {
    super(Collection.class);
  }

  @Override
  public Collection<String> deserialize(final JsonParser parser, final DeserializationContext ctxt) throws IOException, JsonProcessingException {

    final JsonNode node = parser.getCodec().readTree(parser);

    final List<String> list = new ArrayList<>();

    node.forEach(jsonNode -> {
      final String txt = jsonNode.asText();
      list.add(txt);
    });

    return list;
  }

}
