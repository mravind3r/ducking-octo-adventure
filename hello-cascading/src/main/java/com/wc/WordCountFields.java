package com.wc;

import cascading.tuple.Fields;

public class WordCountFields {

  private WordCountFields() {
  }

  public static final Fields WORD = new Fields("word", String.class);
  public static final Fields COUNT = new Fields("count", int.class);
  public static final Fields LINE = new Fields("line", String.class);

}
