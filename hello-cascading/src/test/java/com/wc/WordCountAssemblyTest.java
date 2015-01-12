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

  private static final Fields OUTPUT_FIELDS = WordCountFields.WORD.append(WordCountFields.COUNT);

  @Test
  public void singleLine() {
    Data sourceData = new DataBuilder(WordCountFields.LINE).addTuple("hello hello hello apple world").build();
    Plunger plunger = new Plunger();
    Pipe source = plunger.newNamedPipe("source", sourceData);
    WordCountAssembly wordCountAssembly = new WordCountAssembly(source);
    List<TupleEntry> result = plunger
        .newBucket(OUTPUT_FIELDS, wordCountAssembly)
        .result()
        .orderBy(WordCountFields.WORD)
        .asTupleEntryList();
    assertThat(result.get(0).getInteger("count"), is(1));
    assertThat(result.get(1).getInteger("count"), is(3));
    assertThat(result.get(2).getInteger("count"), is(1));
  }

  @Test
  public void multipleLines() {
    Data sourceData = new DataBuilder(WordCountFields.LINE)
        .addTuple("hello hello hello apple world")
        .addTuple("world apple apple mango hello")
        .build();
    Plunger plunger = new Plunger();
    Pipe source = plunger.newNamedPipe("source", sourceData);
    WordCountAssembly wordCountAssembly = new WordCountAssembly(source);
    List<TupleEntry> result = plunger
        .newBucket(OUTPUT_FIELDS, wordCountAssembly)
        .result()
        .orderBy(WordCountFields.WORD)
        .asTupleEntryList();
    assertThat(result.get(0).getInteger("count"), is(3)); // apple
    assertThat(result.get(1).getInteger("count"), is(4)); // hello
    assertThat(result.get(2).getInteger("count"), is(1)); // mango
    assertThat(result.get(3).getInteger("count"), is(2)); // world
  }

}
