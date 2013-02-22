import java.util.*;
import java.lang.*;
import java.io.*;
public class Manufacturer implements Serializable {
  private static final long serialVersionUID = 1L;
  private String name;
  private String id;


  public Manufacturer(String name, String id) {
    this.name = name;
    this.id = id;
  }

  public String getName() {
    return name;
  }
  public String getId() {
    return id;
  }

  public String toString() {
      return "name " + name + " id " + id;
  }
}
