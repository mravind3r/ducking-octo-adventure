package com.worl;

import org.apache.hadoop.fs.Path;

import com.beust.jcommander.Parameter;

public class MappedArguments {

  @Parameter(names = "-sourceFolder", description = "this is where source data resides", converter = StringToHadoopPath.class, required = true)
  private Path sourceDataPath;

  @Parameter(names = "-outputFolder", description = "folder for mapreduce output", converter = StringToHadoopPath.class, required = true)
  private Path outputPath;

  @Parameter(names = "-numOfReducers", description = "override the default reducers by providing a value", required = false)
  private Integer numReducers;

  public Path getSourceDataPath() {
    return sourceDataPath;
  }

  public Path getOutputPath() {
    return outputPath;
  }

  public Integer getNumReducers() {
    return numReducers;
  }

}
