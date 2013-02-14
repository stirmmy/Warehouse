import java.util.*;
import java.io.*;
public class Warehouse implements Serializable {
  private static final long serialVersionUID = 1L;
  private ProductList productList;
  private ManufacturerList manufacturerList;
  private ClientList clientList;
  private static Warehouse warehouse;
  private Warehouse() {
    productList = ProductList.instance();
    manufacturerList = ManufacturerList.instance();
    clientList = ClientList.instance();
  }
  public static Warehouse instance() {
    if (warehouse == null) {
      ClientIdServer.instance(); // instantiate all singletons
      return (warehouse = new Warehouse());
    } else {
      return warehouse;
    }
  }
  public Manufacturer addManufacturer(String name, String address, String id) {
    Manufacturer manufacturer = new Manufacturer(name, address, id);
    if (manufacturerList.insertManufacturer(manufacturer)) {
      return (manufacturer);
    }
    return null;
  }
  public Client addClient(String name, String address) {
    Client client = new Client(name, address);
    if (clientList.insertClient(client)) {
      return (client);
    }
    return null;
  }
  public Product addProduct(String name, String id) {
    Product product = new Product(name, id);
    if (productList.insertProduct(product)) {
      return (product);
    }
    return null;
  }

  public Iterator getManufacturers() {
      return manufacturerList.getManufacturers();
  }
  public Iterator getClients() {
      return clientList.getClients();
  }
  public Iterator getProducts() {
      return productList.getProducts();
  }
  public static Warehouse retrieve() {
    try {
      FileInputStream file = new FileInputStream("WarehouseData");
      ObjectInputStream input = new ObjectInputStream(file);
      input.readObject();
      ClientIdServer.retrieve(input);
      return warehouse;
    } catch(IOException ioe) {
      ioe.printStackTrace();
      return null;
    } catch(ClassNotFoundException cnfe) {
      cnfe.printStackTrace();
      return null;
    }
  }
  public static boolean save() {
    try {
      FileOutputStream file = new FileOutputStream("WarehouseData");
      ObjectOutputStream output = new ObjectOutputStream(file);
      output.writeObject(warehouse);
      output.writeObject(ClientIdServer.instance());
      return true;
    } catch(IOException ioe) {
      ioe.printStackTrace();
      return false;
    }
  }
  private void writeObject(java.io.ObjectOutputStream output) {
    try {
      output.defaultWriteObject();
      output.writeObject(warehouse);
    } catch(IOException ioe) {
      System.out.println(ioe);
    }
  }
  private void readObject(java.io.ObjectInputStream input) {
    try {
      input.defaultReadObject();
      if (warehouse == null) {
        warehouse = (Warehouse) input.readObject();
      } else {
        input.readObject();
      }
    } catch(IOException ioe) {
      ioe.printStackTrace();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  public String toString() {
    return manufacturerList + "\n" + productList + "\n" + clientList;
  }
}
