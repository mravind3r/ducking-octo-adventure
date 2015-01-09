package com.worl;

import org.apache.hadoop.fs.Path;

import com.beust.jcommander.IStringConverter;

public class StringToHadoopPath implements IStringConverter<Path> {

  @Override
  public Path convert(String value) {
    return new Path(value);
  }

}
