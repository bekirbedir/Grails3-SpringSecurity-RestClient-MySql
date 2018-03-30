package reybexmobil2

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class WooProductServiceSpec extends Specification {

    WooProductService wooProductService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new WooProduct(...).save(flush: true, failOnError: true)
        //new WooProduct(...).save(flush: true, failOnError: true)
        //WooProduct wooProduct = new WooProduct(...).save(flush: true, failOnError: true)
        //new WooProduct(...).save(flush: true, failOnError: true)
        //new WooProduct(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //wooProduct.id
    }

    void "test get"() {
        setupData()

        expect:
        wooProductService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<WooProduct> wooProductList = wooProductService.list(max: 2, offset: 2)

        then:
        wooProductList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        wooProductService.count() == 5
    }

    void "test delete"() {
        Long wooProductId = setupData()

        expect:
        wooProductService.count() == 5

        when:
        wooProductService.delete(wooProductId)
        sessionFactory.currentSession.flush()

        then:
        wooProductService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        WooProduct wooProduct = new WooProduct()
        wooProductService.save(wooProduct)

        then:
        wooProduct.id != null
    }
}
