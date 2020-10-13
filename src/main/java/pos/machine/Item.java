package pos.machine;

public class Item extends ItemInfo{
    int quantity;
    int subtotal;
    public Item(String barcode, String name, int price, int quantity, int subtotal) {
        super(barcode, name, price);
        this.quantity = quantity;
        this.subtotal = subtotal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
}
