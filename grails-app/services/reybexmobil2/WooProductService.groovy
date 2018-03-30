package reybexmobil2

import grails.gorm.services.Service

@Service(WooProduct)
interface WooProductService {

    WooProduct get(Serializable id)

    List<WooProduct> list(Map args)

    Long count()

    void delete(Serializable id)

    WooProduct save(WooProduct wooProduct)

}