package com.pth.gui.helpers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class MergeDuplicateFieldsJsonNodeDeserializer extends JsonNodeDeserializer {

  private static final long serialVersionUID = -8807576497798597201L;

  public MergeDuplicateFieldsJsonNodeDeserializer() {

  }

  @Override
  protected void _handleDuplicateField(final JsonParser p, final DeserializationContext ctxt,
      final JsonNodeFactory nodeFactory, final String fieldName, final ObjectNode objectNode,
      final JsonNode oldValue, final JsonNode newValue) throws JsonProcessingException {

    super._handleDuplicateField(p, ctxt, nodeFactory, fieldName, objectNode, oldValue, newValue);

    ArrayNode array;
    if (oldValue instanceof ArrayNode) {
      array = (ArrayNode) oldValue;
      array.add(newValue);
    }
    else {
      // Merge first two elements
      array = nodeFactory.arrayNode();
      array.add(oldValue);
      array.add(newValue);
    }
    objectNode.set(fieldName, array);
  }
}
