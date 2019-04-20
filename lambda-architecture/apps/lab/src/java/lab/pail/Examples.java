package lab.pail;

import java.io.IOException;
import com.backtype.hadoop.pail.Pail;
import com.backtype.hadoop.pail.Pail.TypedRecordOutputStream;

import lab.pail.Login;
import lab.pail.PartitionedLoginPailStructure;


public class Examples {
  public static void partitionData(String path) throws IOException {
    Pail<Login> pail = Pail.create(path, new PartitionedLoginPailStructure());

    TypedRecordOutputStream os = pail.openWrite();
    os.writeObject(new Login("chris", 1352702020));
    os.writeObject(new Login("david", 1352788472));
    os.close();
  }
}
