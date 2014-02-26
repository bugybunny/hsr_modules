package ch.hsr.informatik.prog1.testat1;

/**
 * Class to test some functionality and create instances.
 * 
 * @author msyfrig
 */
public class Main {

    public static void main(String[] args) {

        ServiceItem serviceItem1 = new ServiceItem("Telefonische Auskunft", 1.5);
        ServiceItem serviceItem2 = new ServiceItem("Gewinnspiel", 3.0);
        ServiceItem serviceItem3 = new ServiceItem("foobar", 20);

        ProductItem productItem1 = new ProductItem("Erdnüsse", 45.7, 3);
        ProductItem productItem2 = new ProductItem("Erdbeeren", 500, 9);
        ProductItem productItem3 = new ProductItem("Toilettenpapier", 40, 7.8);

        Order order1 = new Order();
        order1.addItem(serviceItem1);
        order1.addItem(serviceItem2);
        order1.addItem(serviceItem3);

        order1.addItem(productItem1);
        order1.addItem(productItem2);
        order1.addItem(productItem3);

        order1.printItems();
        System.out.println(order1.getTotalPrice()); // 4973.6

        Order order2 = new Order();
        order2.addItem(productItem1);
        order2.addItem(serviceItem2);

        System.out.println(order2.getTotalPrice()); // 140.1

        BundleItem bundle1 = new BundleItem("Mengenrabatt 1", 9.5);
        bundle1.setItemList(order1.getItemList());
        System.out.println(bundle1.getPrice()); // 4973.6 * 0.905 = 4501.108,
                                                // 4501.10 gerundet

        BundleItem bundle2 = new BundleItem("bundle in a bundle", 10);
        bundle2.setItemList(order2.getItemList());
        bundle2.addItem(bundle1);

        System.out.println(bundle2.getPrice()); // (4501.1 + 140.1) * 0.9 =
                                                // 4177.08, gerundet 4177.1

    }
}