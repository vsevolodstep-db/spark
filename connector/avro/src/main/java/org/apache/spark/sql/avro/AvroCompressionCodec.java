/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.spark.sql.avro;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.avro.file.DataFileConstants;

/**
 * A mapper class from Spark supported avro compression codecs to avro compression codecs.
 */
public enum AvroCompressionCodec {
  UNCOMPRESSED(DataFileConstants.NULL_CODEC),
  DEFLATE(DataFileConstants.DEFLATE_CODEC),
  SNAPPY(DataFileConstants.SNAPPY_CODEC),
  BZIP2(DataFileConstants.BZIP2_CODEC),
  XZ(DataFileConstants.XZ_CODEC),
  ZSTANDARD(DataFileConstants.ZSTANDARD_CODEC);

  private final String codecName;

  AvroCompressionCodec(String codecName) {
    this.codecName = codecName;
  }

  public String getCodecName() {
    return this.codecName;
  }

  private static final Map<String, String> codecNameMap =
    Arrays.stream(AvroCompressionCodec.values()).collect(
      Collectors.toMap(codec -> codec.name(), codec -> codec.name().toLowerCase(Locale.ROOT)));

  public String lowerCaseName() {
    return codecNameMap.get(this.name());
  }

  public static AvroCompressionCodec fromString(String s) {
    return AvroCompressionCodec.valueOf(s.toUpperCase(Locale.ROOT));
  }
}
