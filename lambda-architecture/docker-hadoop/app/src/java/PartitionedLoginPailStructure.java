package app.java;

import com.backtype.hadoop.pail.PailStructure;
import java.io.IOException;
import java.lang.RuntimeException;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.text.ParseException;
import java.util.Date;
import java.text.SimpleDateFormat;



// import org.apache.thrift.scheme.IScheme;
// import org.apache.thrift.scheme.SchemeFactory;
// import org.apache.thrift.scheme.StandardScheme;

// import org.apache.thrift.scheme.TupleScheme;
// import org.apache.thrift.protocol.TTupleProtocol;
// import org.apache.thrift.protocol.TProtocolException;
// import org.apache.thrift.EncodingUtils;
// import org.apache.thrift.TException;
// import org.apache.thrift.async.AsyncMethodCallback;
// import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PartitionedLoginPailStructure extends LoginPailStructure {
  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

  public List<String> getTarget(Login object) {
    ArrayList<String> directoryPath = new ArrayList<String>();
    Date date = new Date(object.loginUnixTime * 1000L);
    directoryPath.add(formatter.format(date));
    return directoryPath;
  }

  public boolean isValidTarget(String... strings) {
    if (strings.length != 2)
      return false;
    try {
      return (formatter.parse(strings[0]) != null);
    } catch (ParseException e) {
      return false;
    }
  }
}