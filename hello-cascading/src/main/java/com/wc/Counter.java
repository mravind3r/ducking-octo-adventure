package com.wc;

import cascading.flow.FlowProcess;
import cascading.operation.Aggregator;
import cascading.operation.AggregatorCall;
import cascading.operation.BaseOperation;
import cascading.tuple.Fields;
import cascading.tuple.Tuple;

public class Counter extends BaseOperation<Counter.Context> implements Aggregator<Counter.Context> {

  public Counter(Fields outputFields) {
    super(outputFields);
  }

  private static final long serialVersionUID = 1L;

  public class Context {
    int counter;
  }

  @Override
  public void start(FlowProcess flowProcess, AggregatorCall<Context> aggregatorCall) {
    aggregatorCall.setContext(new Context());
  }

  @Override
  public void aggregate(FlowProcess flowProcess, AggregatorCall<Context> aggregatorCall) {
    aggregatorCall.getContext().counter++;
  }

  @Override
  public void complete(FlowProcess flowProcess, AggregatorCall<Context> aggregatorCall) {
    aggregatorCall.getOutputCollector().add(new Tuple(aggregatorCall.getContext().counter));
  }

}