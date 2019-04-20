package lab.cascalog;

import lab.cascalog.TmpParent;

public class Tmp extends TmpParent  {

  public Number val;
  
  public String getTarget(Login login){
    return login.userName + val.toString();
  }

  public Number getVal(){
    return val;
  }

}