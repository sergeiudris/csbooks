package jvm.bigdata.tap;

import jvm.bigdata.schema.Data;
import java.util.Collections;
import java.util.List;


public class DataPailStructure extends ThriftPailStructure<Data> {
  @Override
  protected Data createThriftObject() {
    return new Data();
  }

  public List<String> getTarget(Data object) {
    return Collections.EMPTY_LIST;
  }

  public boolean isValidTarget(String... dirs) {
    return true;
  }

  public Class getType() {
    return Data.class;
  }
}
