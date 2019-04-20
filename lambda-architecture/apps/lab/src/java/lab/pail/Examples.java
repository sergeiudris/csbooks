package lab.pail;

import java.io.IOException;
import com.backtype.hadoop.pail.Pail;
import com.backtype.hadoop.pail.Pail.TypedRecordOutputStream;

import lab.pail.Login;
import lab.pail.PartitionedLoginPailStructure;


public class Examples {
  public static void partitionData(String path) throws IOException {
    PartitionedLoginPailStructure plps = new PartitionedLoginPailStructure();
    Pail<Login> pail = Pail.create(path, plps);

    // plps.getTarget(new Login("chris", 1352702020));

    TypedRecordOutputStream os = pail.openWrite();

    os.writeObject(new Login("chris", 1352702020));
    os.writeObject(new Login("david", 1352788472));
    os.close();
  }
}
