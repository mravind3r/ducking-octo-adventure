package com.worl;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Hello world!
 */
public class HelloCascadingTool extends Configured implements Tool {

  public static void main(String[] args) throws Exception {
    int result = ToolRunner.run(new Configuration(), new HelloCascadingTool(), args);
    System.exit(result);
  }

  @Override
  public int run(String[] args) throws Exception {
    System.out.println("calling run method of tool...  call cascading flow here");

    return 0;
  }

}
