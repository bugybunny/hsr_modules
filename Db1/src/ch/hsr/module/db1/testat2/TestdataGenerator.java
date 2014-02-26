package ch.hsr.module.db1.testat2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author msyfrig
 */
public class TestdataGenerator {
    private static final int                   NUMBER_OF_VENDING_MACHINES = 7;
    private static final int                   NUMBER_OF_PRODUCT_DESCRIPTIONS;
    private static final int                   NUMBER_OF_ROWS_PER_VENDING_MACHINE;
    private static final int                   SPECIFIC_ITEMS_PER_DESCRIPTION_SNACK;
    private static final int                   SPECIFIC_ITEMS_PER_DESCRIPTION_BOTTLE;

    private static final int                   SNACKROW_CAPACITY          = 700;
    private static final int                   BOTTLEROW_CAPACITY         = 300;

    private static final Map<Integer, Product> productMap                 = new HashMap<>();

    static {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("The Coca Cola Company", "Coca Cola", 2,
                50, ProductType.BOTTLE));
        productList.add(new Product("Mars Incorporated", "Mars", 1, 80,
                ProductType.SNACK));
        productList.add(new Product("Mars Incorporated", "Snickers", 1, 70,
                ProductType.SNACK));
        productList.add(new Product("Migros", "Hamburger", 8, 90,
                ProductType.SNACK));
        productList.add(new Product("Migros", "Handtasche", 50, 0,
                ProductType.SNACK));
        productList.add(new Product("Mars Incorporated", "Milky Way", 2, 30,
                ProductType.SNACK));
        productList.add(new Product("Mars Incorporated", "Bounty", 1, 40,
                ProductType.SNACK));
        productList.add(new Product("The Coca Cola Company", "Valser Classic",
                3, 50, ProductType.BOTTLE));
        productList.add(new Product("The Coca Cola Company",
                "Valser Naturelle", 3, 50, ProductType.BOTTLE));
        for (int productId = 0; productId < productList.size(); productId++) {
            productMap.put(productId + 1, productList.get(productId));
        }

        NUMBER_OF_PRODUCT_DESCRIPTIONS = productMap.size();
        NUMBER_OF_ROWS_PER_VENDING_MACHINE = NUMBER_OF_PRODUCT_DESCRIPTIONS;
        SPECIFIC_ITEMS_PER_DESCRIPTION_SNACK = NUMBER_OF_VENDING_MACHINES
                * NUMBER_OF_ROWS_PER_VENDING_MACHINE * SNACKROW_CAPACITY;
        SPECIFIC_ITEMS_PER_DESCRIPTION_BOTTLE = NUMBER_OF_VENDING_MACHINES
                * NUMBER_OF_ROWS_PER_VENDING_MACHINE * BOTTLEROW_CAPACITY;
    }

    private static final StringBuffer createProductDescriptions() {
        String message = "INSERT INTO productdescription (\n"
                + "\t\tproductdescriptionid, producer, productname)\n"
                + "\tVALUES ({0, number,#}, ''{1}'', ''{2}'');\n";

        StringBuffer sb = new StringBuffer("\n\n/* Productdescriptions */\n");
        for (Map.Entry<Integer, Product> tempProduct : productMap.entrySet()) {
            String sql = MessageFormat.format(message, tempProduct.getKey(),
                    tempProduct.getValue().producer,
                    tempProduct.getValue().productName);
            sb.append(sql + "\n");

        }
        return sb;
    }

    private static final StringBuffer createSpecificProducts() {
        int specificproductid = 1;
        StringBuffer sb = new StringBuffer("\n\n/* Specific Products ( "
                + SPECIFIC_ITEMS_PER_DESCRIPTION_SNACK
                + " of each snack productdescription, "
                + SPECIFIC_ITEMS_PER_DESCRIPTION_BOTTLE
                + " of each bottle productdescription) */\n");
        for (int vendingMachineId = 1; vendingMachineId <= NUMBER_OF_VENDING_MACHINES; vendingMachineId++) {
            for (int productDescriptionId = 1; productDescriptionId <= NUMBER_OF_PRODUCT_DESCRIPTIONS; productDescriptionId++) {
                int numberOfItemsToCreate = (productMap
                        .get(productDescriptionId).type == ProductType.SNACK ? SNACKROW_CAPACITY
                        : BOTTLEROW_CAPACITY);
                for (int j = 1; j <= numberOfItemsToCreate; j++) {
                    long offset = Timestamp.valueOf("2014-01-01 00:00:00")
                            .getTime();
                    long end = Timestamp.valueOf("2016-01-01 00:00:00")
                            .getTime();
                    long diff = end - offset + 1;
                    Timestamp rand = new Timestamp(offset
                            + (long) (Math.random() * diff));

                    String expirationDate = rand.toString().substring(0, 10);
                    double chanceThatProductIsNotInARow = Math.random();
                    String sql = "";
                    if (chanceThatProductIsNotInARow <= 0.1) {
                        String message = "INSERT INTO specificproduct (\n"
                                + "\t\tspecificproductid, productdescriptionid, expirationDate)\n"
                                + "\tVALUES ({0, number, #}, {1, number, #}, ''{2}'');\n";
                        sql = MessageFormat.format(message, specificproductid,
                                productDescriptionId, expirationDate);
                        System.out.println(sql);
                    } else {
                        String message = "INSERT INTO specificproduct (\n"
                                + "\t\tspecificproductid, productdescriptionid, productrowid, expirationDate)\n"
                                + "\tVALUES ({0, number,#}, {1, number,#}, {2, number,#}, ''{3}'');\n";
                        int productRowId = ((vendingMachineId - 1) * NUMBER_OF_PRODUCT_DESCRIPTIONS)
                                + productDescriptionId;
                        sql = MessageFormat.format(message, specificproductid,
                                productDescriptionId, productRowId,
                                expirationDate);
                    }
                    sb.append(sql + "\n");
                    specificproductid++;
                }
            }
        }
        return sb;
    }

    private static final StringBuffer createProductRows() {
        String message = "INSERT INTO productrow (\n"
                + "\t\tproductrowid, itemrownumber, vendingmachineid,\n"
                + "\t\tmaximumcapactiy, pricefrancs, priceCents)\n"
                + "\tVALUES ({0, number,#}, {1, number,#}, {2, number,#}, {3, number,#}, {4, number,#}, {5, number,#});\n";

        StringBuffer sb = new StringBuffer("\n\n/* Product Rows */\n");
        int productRowId = 1;
        for (int i = 1; i <= NUMBER_OF_VENDING_MACHINES; i++) {
            sb.append("/*Automat " + i + "*/\n");
            for (int j = 1; j <= NUMBER_OF_ROWS_PER_VENDING_MACHINE; j++) {
                int itemRowNumber = j + 10;
                int maxCapacity = (productMap.get(j).type == ProductType.BOTTLE ? BOTTLEROW_CAPACITY
                        : SNACKROW_CAPACITY);
                double chanceForHigherPrice = Math.random();
                int francs = productMap.get(j).normalPriceFrancs;
                int cents = productMap.get(j).normalPriceCents;
                if (chanceForHigherPrice < 0.1) {
                    // make it 10 cents more expensive
                    cents += 10;
                    if (cents >= 100) {
                        cents -= 100;
                        francs++;
                    }

                }
                String sql = MessageFormat.format(message, productRowId,
                        itemRowNumber, i, maxCapacity, francs, cents);
                sb.append(sql + "\n");
                productRowId++;
            }
        }
        return sb;
    }

    private static String readFile(String path, Charset encoding)
            throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return encoding.decode(ByteBuffer.wrap(encoded)).toString();
    }

    private static String readFile(String path) throws IOException {
        return readFile(path, Charset.forName("UTF-8"));
    }

    public static void main(String[] args) throws IOException {
        String predefinedStatementsFilePath = new File(TestdataGenerator.class
                .getResource("/predefined/vendingMachines.sql").getFile())
                .getAbsolutePath();

        File insertFileToCreate = new File(
                "C:/Users/Marco/Dropbox/HSR/Semester 3/Db1/Db1-Testat2/2_inserts_test.sql");
        insertFileToCreate.createNewFile();
        // overwrites old content
        Writer fileWriter = new BufferedWriter(new FileWriter(
                insertFileToCreate));
        fileWriter.append(readFile(predefinedStatementsFilePath));
        fileWriter.append(createProductDescriptions());
        fileWriter.append(createProductRows());
        fileWriter.append(createSpecificProducts());

        fileWriter.close();
    }
}

enum ProductType {
    BOTTLE, SNACK;
}

class Product {
    public String producer;
    public String productName;
    int           normalPriceFrancs;
    int           normalPriceCents;
    ProductType   type;

    /**
     * Creates a new instance of this class.
     * 
     * @param aProducer
     * @param aProductName
     * @param aNormalPriceFrancs
     * @param aNormalPriceCents
     * @param aType
     */
    public Product(String aProducer, String aProductName,
            int aNormalPriceFrancs, int aNormalPriceCents, ProductType aType) {
        super();
        producer = aProducer;
        productName = aProductName;
        normalPriceFrancs = aNormalPriceFrancs;
        normalPriceCents = aNormalPriceCents;
        type = aType;
    }
}
