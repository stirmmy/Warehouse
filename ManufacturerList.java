import java.util.*;
import java.lang.*;
import java.io.*;
public class ManufacturerList implements Serializable {
  private static final long serialVersionUID = 1L;
  private List manufacturers = new LinkedList();
  private static ManufacturerList mlist;
  private ManufacturerList() {
  }
  public static ManufacturerList instance() {
    if (mlist == null) {
      return (mlist = new ManufacturerList());
    } else {
      return mlist;
    }
  }
  
  public boolean insertManufacturer(Manufacturer manufacturer) {
    manufacturers.add(manufacturer);
    return true;
  }
  public Iterator getManufacturers() {
    return manufacturers.iterator();
  }
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(mlist);
    } catch(IOException ioe) {
      System.out.println(ioe);
    }
  }
  private void readObject(java.io.ObjectInputStream input) {
    try {
      if (mlist != null) {
        return;
      } else {
        input.defaultReadObject();
        if (mlist == null) {
          mlist = (ManufacturerList) input.readObject();
        } else {
          input.readObject();
        }
      }
    } catch(IOException ioe) {
      System.out.println("in ManufacturerList readObject \n" + ioe);
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
    }
  }
  public String toString() {
    return manufacturers.toString();
  }
}
