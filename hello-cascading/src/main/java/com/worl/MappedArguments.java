package com.worl;

import org.apache.hadoop.fs.Path;

import com.beust.jcommander.Parameter;

public class MappedArguments {

  @Parameter(names = "-driverClassName", description = "this is the driver class", required = true)
  private String driverClassName;

  @Parameter(names = "-sourceFolder", description = "this is where source data resides", converter = StringToHadoopPath.class, required = true)
  private Path sourceDataPath;

  @Parameter(names = "-outputFolder", description = "folder for mapreduce output", converter = StringToHadoopPath.class, required = true)
  private Path outputPath;

  @Parameter(names = "-numOfReducers", description = "override the default reducers by providing a value", required = false)
  private Integer numReducers;

  public String getDriverClassName() {
    return driverClassName;
  }

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
