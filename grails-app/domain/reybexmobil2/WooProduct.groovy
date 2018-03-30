package reybexmobil2

class WooProduct {
    String sku
    String productName
    Integer stock


    static constraints = {
        productName nullable: true, blank: true
        stock nullable: true, blank: true
        sku nullable: true , blank: true
    }
}
