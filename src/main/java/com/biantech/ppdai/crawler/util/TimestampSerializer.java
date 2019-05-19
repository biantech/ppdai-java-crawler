package com.biantech.ppdai.crawler.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;
import java.sql.Timestamp;

public class TimestampSerializer extends JsonSerializer<Timestamp> {

  @Override
  public void serialize(Timestamp value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
    gen.writeString(String.format("%s.%09d",
        // DateFormatUtils.format(value.getTime(), "yyyy-MM-ddTHH:mm:ss"),
        DateFormatUtils.ISO_DATETIME_FORMAT.format(value.getTime()), value.getNanos()));
  }

}
