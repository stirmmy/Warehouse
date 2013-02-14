import java.util.*;
import java.lang.*;
import java.io.*;
public class Manufacturer implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String address;
  private String id;


  public Manufacturer(String name, String address, String id) {
    this.name = name;
    this.address = address;
    this.id = id;
  }

  public String getAddress() {
    return address;
  }
  public String getName() {
    return name;
  }
  public String getId() {
    return id;
  }

  public String toString() {
      return "name " + name + " address " + address + " id " + id;
  }
}
