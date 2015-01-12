package com.wc;

import cascading.operation.Function;
import cascading.operation.regex.RegexGenerator;
import cascading.pipe.Each;
import cascading.pipe.Every;
import cascading.pipe.GroupBy;
import cascading.pipe.Pipe;
import cascading.pipe.SubAssembly;
import cascading.tuple.Fields;

public class WordCountAssembly extends SubAssembly {

  private static final long serialVersionUID = 1L;
  private static final Fields WORD = new Fields("word", String.class);
  private static final Fields COUNT = new Fields("count", int.class);

  public WordCountAssembly(Pipe pipe) {
    super(pipe);
    String regex = "\\w+\\-?\\w+\\-?\\.?\\w+";
    @SuppressWarnings("rawtypes")
    Function function = new RegexGenerator(WORD, regex);
    pipe = new Each(pipe, function);
    pipe = new GroupBy(pipe, WORD);
    pipe = new Every(pipe, new Counter(COUNT), Fields.ALL);
    setTails(pipe);
  }
}
