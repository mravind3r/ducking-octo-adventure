package com.wc;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import cascading.pipe.Pipe;
import cascading.tuple.Fields;
import cascading.tuple.TupleEntry;

import com.hotels.plunger.Data;
import com.hotels.plunger.DataBuilder;
import com.hotels.plunger.Plunger;

public class WordCountAssemblyTest {

  private static final Fields OUTPUT_FIELDS = new Fields("word", String.class).append(new Fields("count", int.class));

  @Test
  public void singleWord() {
    Data sourceData = new DataBuilder(new Fields("line", String.class)).addTuple("hello hello hello").build();
    Plunger plunger = new Plunger();
    Pipe source = plunger.newNamedPipe("source", sourceData);
    WordCountAssembly wordCountAssembly = new WordCountAssembly(source);
    List<TupleEntry> result = plunger.newBucket(OUTPUT_FIELDS, wordCountAssembly).result().asTupleEntryList();
    assertThat(result.get(0).getInteger("count"), is(3));
  }
}
