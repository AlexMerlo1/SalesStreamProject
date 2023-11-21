// logic/Items.java
package scenes.logic;

public class Items {

    public static class Item {
        private String itemName;
        private double itemPrice;

        public Item(String itemName, double itemPrice) {
            this.itemName = itemName;
            this.itemPrice = itemPrice;
        }

        public String getItemName() {
            return itemName;
        }

        public double getItemPrice() {
            return itemPrice;
        }
    }
}
