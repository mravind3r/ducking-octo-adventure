package com.worl;

import cascading.scheme.Scheme;
import cascading.scheme.hadoop.TextLine;
import cascading.tap.SinkMode;
import cascading.tap.Tap;
import cascading.tap.hadoop.Hfs;
import cascading.tuple.Fields;

public class TextLineTap {

  public static Tap getInputTap(String path) {
    Scheme scheme = new TextLine(new Fields("line"));
    return new Hfs(scheme, path);
  }

  public static Tap getOutputTap(String path) {
    Scheme scheme = new TextLine(new Fields("line"));
    return new Hfs(scheme, path, SinkMode.REPLACE);
  }

}
