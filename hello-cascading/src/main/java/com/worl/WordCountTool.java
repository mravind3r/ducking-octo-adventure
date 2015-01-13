package com.worl;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import cascading.flow.Flow;
import cascading.flow.FlowDef;
import cascading.flow.hadoop.HadoopFlowConnector;
import cascading.pipe.Pipe;
import cascading.tap.Tap;

import com.beust.jcommander.JCommander;
import com.wc.WordCountAssembly;

/**
 * Hello world!
 */
public class WordCountTool extends Configured implements Tool {

  public static void main(String[] args) throws Exception {
    int result = ToolRunner.run(new Configuration(), new WordCountTool(), args);
    System.exit(result);
  }

  @Override
  public int run(String[] args) {
    MappedArguments arguments = new MappedArguments();
    new JCommander(arguments, args);
    String path = System.getProperty("user.dir");
    System.out.println(path);
    Tap inTap = TextLineTap.getInputTap(arguments.getSourceDataPath().toString());
    Tap sinkTap = TextLineTap.getOutputTap(arguments.getOutputPath().toString());

    Pipe pipe = new Pipe("word-count");
    pipe = new WordCountAssembly(pipe);

    FlowDef flowDef = new FlowDef().addSource(pipe, inTap).addTailSink(pipe, sinkTap);
    Flow<?> flow = new HadoopFlowConnector().connect(flowDef);
    flow.complete();

    return 0;
  }

}
