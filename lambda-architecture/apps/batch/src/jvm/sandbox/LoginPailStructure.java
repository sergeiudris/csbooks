package jvm.sandbox;

import com.backtype.hadoop.pail.PailStructure;
import java.io.IOException;
import java.lang.RuntimeException;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.DataInputStream;



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

public class LoginPailStructure implements PailStructure<Login> {
  public Class getType() {
    return Login.class;
  }

  public byte[] serialize(Login login) {
    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    DataOutputStream dataOut = new DataOutputStream(byteOut);
    byte[] userBytes = login.userName.getBytes();
    try {
    dataOut.writeInt(userBytes.length);
    dataOut.write(userBytes);
    dataOut.writeLong(login.loginUnixTime);
    dataOut.close();
    } catch(IOException e) {
    throw new RuntimeException(e);
    }
    return byteOut.toByteArray();
  }

  public Login deserialize(byte[] serialized) {
    DataInputStream dataIn =
    new DataInputStream(new ByteArrayInputStream(serialized));
    try {
    byte[] userBytes = new byte[dataIn.readInt()];
    dataIn.read(userBytes);
    return new Login(new String(userBytes), dataIn.readLong());
    } catch(IOException e) {
    throw new RuntimeException(e);
    }
  }

  public List<String> getTarget(Login object) {
    return Collections.EMPTY_LIST;
    }


  public boolean isValidTarget(String... dirs) {
    return true;
    }

}