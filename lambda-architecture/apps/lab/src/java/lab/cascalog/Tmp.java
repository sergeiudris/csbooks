package lab.cascalog;

import lab.cascalog.TmpParent;

public class Tmp extends TmpParent  {

  public Number val;
  
  public String getTarget(Datum datum){
    return datum.name + val.toString();
  }

  public Number getVal(){
    return val;
  }

}