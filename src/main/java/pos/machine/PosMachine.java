package pos.machine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PosMachine {
    private Character NEW_LINE = '\n';
    private String TOP_RECIEPT = "***<store earning no money>Receipt***" + NEW_LINE;
    private String LINE_SEPARATOR = "----------------------" + NEW_LINE;
    private String END_LINE = "**********************";
    StringBuilder output = new StringBuilder();

    public String printReceipt(List<String> barcodes) {
        ItemDataLoader itemDataLoader = new ItemDataLoader();
        List<ItemInfo> itemInfoList = getAllProductInfoByBarcode(itemDataLoader.loadBarcodes());
        List<Item> itemList = getQuantity(itemInfoList);
        int total = getTotalPrice(itemList);
        List list = ItemDataLoader.loadAllItemInfos();
        output.append(TOP_RECIEPT);
        for(Item itemLists : itemList){
            output.append("Name: ").append(itemLists.getName()).append(", ").append("Quantity: ")
                    .append(itemLists.getQuantity()).append(", ").append("Unit price: ").append(itemLists.getPrice())
                    .append(" (yuan)").append(", ").append("Subtotal: ").append(itemLists.getSubtotal()).append(" (yuan)\n");
        }
        output.append(LINE_SEPARATOR);
        output.append("Total: ").append(total).append(" (yuan)\n");
        output.append(END_LINE);
        return output.toString();
    }

    public List<ItemInfo> getAllProductInfoByBarcode(List<String> barcodes) {
        List<ItemInfo> iteminfo = ItemDataLoader.loadAllItemInfos();
        List<ItemInfo> iteminfos = new ArrayList<>();
        for (String barcode : barcodes) {
            for (ItemInfo itemInfo : iteminfo) {
                if (itemInfo.getBarcode().equals(barcode)) {
                    iteminfos.add(itemInfo);
                }
            }
        }
        return iteminfos;
    }

    public List<Item> getQuantity(List<ItemInfo> barcodes) {
        List<ItemInfo> distinctItem = new ArrayList<>();
        List<Item> itemList = new ArrayList<>();
        Set<ItemInfo> uniqueValues = new HashSet<>();
        for (ItemInfo iteminfos : barcodes) {
            if (uniqueValues.add(iteminfos)) {
                distinctItem.add(iteminfos);
            }
        }
        for (ItemInfo itemInfos : distinctItem) {
            int counter = 0;
            for (ItemInfo value1 : barcodes) {
                if (itemInfos.equals(value1)) {
                    counter++;
                }
            }
            Item item = new Item(itemInfos.getBarcode(), itemInfos.getName(), itemInfos.getPrice(), counter,
                    counter * itemInfos.getPrice());
            itemList.add(item);
        }
        return itemList;
    }

    public Integer getTotalPrice(List<Item> itemList) {
        int total = 0;
        for (Item item : itemList) {
            total += item.getSubtotal();
        }
        return total;
    }
}