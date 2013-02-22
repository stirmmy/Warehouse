import java.util.*;
import java.text.*;
import java.io.*;
public class UserInterface {
  private static UserInterface userInterface;
  private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  private static Warehouse warehouse;
  private static final int EXIT = 0;
  private static final int ADD_MANUFACTURER = 1;
  private static final int ADD_CLIENT = 2;
  private static final int ADD_PRODUCTS = 3;
  private static final int ADD_MANUF_TO_PRODUCT = 4;
  private static final int DEL_MANUF_FROM_PRODUCT = 5;
  private static final int ACCEPT_ORDER = 6;
  private static final int ACCEPT_SHIPMENT = 7;
  private static final int LIST_CLIENT_TRANS = 8;
  private static final int LIST_PRODUCT_PRICES = 9;
  private static final int LIST_OUTSTANDING = 10;
  private static final int LIST_MANUFACTURERS = 11;
  private static final int LIST_CLIENTS = 12;
  private static final int LIST_PRODUCTS = 13;
  private static final int SAVE = 14;
  private static final int RETRIEVE = 15;
  private static final int HELP = 16;

  /* Singleton constructor */

  private UserInterface() {
    if (yesOrNo("Look for saved data and  use it?")) {
      retrieve();
    } else {
      warehouse = Warehouse.instance();
    }
  }

  public static UserInterface instance() {
    if (userInterface == null) {
      return userInterface = new UserInterface();
    } else {
      return userInterface;
    }
  }

  /* Auxillary methods */

  public String getToken(String prompt) {
    do {
      try {
        System.out.println(prompt);
        String line = reader.readLine();
        StringTokenizer tokenizer = new StringTokenizer(line,"\n\r\f");
        if (tokenizer.hasMoreTokens()) {
          return tokenizer.nextToken();
        }
      } catch (IOException ioe) {
        System.exit(0);
      }
    } while (true);
  }

  private boolean yesOrNo(String prompt) {
    String more = getToken(prompt + " (Y|y)[es] or anything else for no");
    if (more.charAt(0) != 'y' && more.charAt(0) != 'Y') {
      return false;
    }
    return true;
  }

  public int getNumber(String prompt) {
    do {
      try {
        String item = getToken(prompt);
        Integer num = Integer.valueOf(item);
        return num.intValue();
      } catch (NumberFormatException nfe) {
        System.out.println("Please input a number ");
      }
    } while (true);
  }

  public Calendar getDate(String prompt) {
    do {
      try {
        Calendar date = new GregorianCalendar();
        String item = getToken(prompt);
        DateFormat df = SimpleDateFormat.getDateInstance(DateFormat.SHORT);
        date.setTime(df.parse(item));
        return date;
      } catch (Exception fe) {
        System.out.println("Please input a date as mm/dd/yy");
      }
    } while (true);
  }

  public int getCommand() {
    do {
      try {
        int value = Integer.parseInt(getToken("Enter command:" + HELP + " for help"));
        if (value >= EXIT && value <= HELP) {
          return value;
        }
      } catch (NumberFormatException nfe) {
        System.out.println("Enter a number");
      }
    } while (true);
  }

  /* User-Interface operations */

  public void help() {
    System.out.println("Enter a number between 0 and 16 as explained below:");
    System.out.println(EXIT + " to Exit\n");
    System.out.println(ADD_MANUFACTURER + " to add manufacturer(s)");
    System.out.println(ADD_CLIENT + " to add client(s)");
    System.out.println(ADD_PRODUCTS + " to add product(s)");
    System.out.println(ADD_MANUF_TO_PRODUCT + " to add a manufacturer to product");
    System.out.println(DEL_MANUF_FROM_PRODUCT + " to remove manufacturer from product");
    System.out.println(ACCEPT_ORDER + " to accept an order");
    System.out.println(ACCEPT_SHIPMENT + " to  place a hold on a book");
    System.out.println(LIST_CLIENT_TRANS + " to  remove a hold on a book");
    System.out.println(LIST_PRODUCT_PRICES + " to  process holds");
    System.out.println(LIST_OUTSTANDING + " to  print members");
    System.out.println(LIST_MANUFACTURERS + " to list all manufacturers");
    System.out.println(LIST_CLIENTS + " to list all clients");
    System.out.println(LIST_PRODUCTS + " to list all products");
    System.out.println(SAVE + " to  save data");
    System.out.println(RETRIEVE + " to  retrieve");
    System.out.println(HELP + " for help");
  }

  public void addManufacturer() {
    String name = getToken("Enter manufacturer name");
    String manufacturerID = getToken("Enter id");
    Manufacturer result;
    result = warehouse.addManufacturer(name, manufacturerID);
    if (result == null) {
      System.out.println("Could not add manufacturer");
    }
    System.out.println(result);
  }

  public void addClient() {
    String name = getToken("Enter client name");
    String address = getToken("Enter address");
    Client result;
    result = warehouse.addClient(name, address);
    if (result == null) {
      System.out.println("Could not add client");
    }
    System.out.println(result);
  }

  public void addProducts() {
    Product result;
    do {
      String name = getToken("Enter product name");
      String productID = getToken("Enter id");
      result = warehouse.addProduct(productID, name);
      if (result != null) {
        System.out.println(result);
      } else {
        System.out.println("Product could not be added");
      }
      if (!yesOrNo("Add more products?")) {
        break;
      }
    } while (true);
  }

  public void listManufacturers() {
      Iterator allManufacturers = warehouse.getManufacturers();
      while (allManufacturers.hasNext()){
	  Manufacturer manufacturer = (Manufacturer)(allManufacturers.next());
          System.out.println(manufacturer.toString());
      }
  }

  public void listClients() {
      Iterator allClients = warehouse.getClients();
      while (allClients.hasNext()){
	  Client client = (Client)(allClients.next());
          System.out.println(client.toString());
      }
  }

  public void listProducts() {
      Iterator allProducts = warehouse.getProducts();
      while (allProducts.hasNext()){
	  Product product = (Product)(allProducts.next());
          System.out.println(product.toString());
      }
  }

  /* Non-implemented user-interface operations */ 

  public void addManufacturerToProduct() {
         System.out.println("Dummy Action");
  }
  public void deleteManufacturerFromProduct() {
      System.out.println("Dummy Action");
  }
  public void acceptOrder() {
      System.out.println("Dummy Action");   
  }
  public void placeOrder() {
      System.out.println("Dummy Action");   
  }
  public void acceptPayment() {
      System.out.println("Dummy Action");   
  }
  public void acceptShipment() {
      System.out.println("Dummy Action");   
  }
  public void listClientTransactions() {
      System.out.println("Dummy Action");   
  }
  public void listProductPrices() {
      System.out.println("Dummy Action");   
  }
  public void listOutstandingBalances() {
      System.out.println("Dummy Action");   
  }
  /* User-interface operations for serialization */

  private void save() {
    if (warehouse.save()) {
      System.out.println(" The warehouse has been successfully saved in the file WarehouseData \n" );
    } else {
      System.out.println(" There has been an error in saving \n" );
    }
  }
  private void retrieve() {
    try {
      Warehouse tempWarehouse = Warehouse.retrieve();
      if (tempWarehouse != null) {
        System.out.println(" The warehouse has been successfully retrieved from the file WarehouseData \n" );
        warehouse = tempWarehouse;
      } else {
        System.out.println("File doesnt exist; creating new library" );
        warehouse = Warehouse.instance();
      }
    } catch(Exception cnfe) {
      cnfe.printStackTrace();
    }
  }

  /* User-interface */  

  public void process() {
    int command;
    help();
    while ((command = getCommand()) != EXIT) {
      switch (command) {
        case ADD_MANUFACTURER:  	addManufacturer();
                                	break;
        case ADD_CLIENT:        	addClient();
                                	break;
        case ADD_PRODUCTS:      	addProducts();
                                	break;
        case ADD_MANUF_TO_PRODUCT:      addManufacturerToProduct();
                                	break;
        case DEL_MANUF_FROM_PRODUCT:	deleteManufacturerFromProduct();
                                	break;
        case ACCEPT_ORDER:		acceptOrder();
                                	break;
        case ACCEPT_SHIPMENT:		acceptShipment();
                                	break;
        case LIST_CLIENT_TRANS:		listClientTransactions();
                                	break;
        case LIST_PRODUCT_PRICES:	listProductPrices();
                                	break;
        case LIST_OUTSTANDING:		listOutstandingBalances();
                                	break;
        case LIST_MANUFACTURERS:	listManufacturers();
					break;
	case LIST_CLIENTS:		listClients();
					break;
	case LIST_PRODUCTS:		listProducts();
					break;
        case SAVE:              	save();
                                	break;
        case RETRIEVE:          	retrieve();
                                	break;	
        case HELP:              	help();
                                	break;
      }
    }
  }

  /* Main */

  public static void main(String[] s) {
    UserInterface.instance().process();
  }
}
