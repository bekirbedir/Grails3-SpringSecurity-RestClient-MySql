package reybexmobil2

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

@Secured(['permitAll'])
class WooProductController {

    WooProductService wooProductService
    def wsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        wsService.serviceMethod()
        params.max = Math.min(max ?: 10, 100)
        respond wooProductService.list(params), model:[wooProductCount: wooProductService.count()]
    }

    def callapi(){
        Map result = wsService.getProduct()


        //render(view : "callapi", model:[response: result]);
        respond result

    }
    def callapi2(){
        Map result = wsService.getProduct()

        println result
        WooProduct exProd =  new WooProduct(sku: result.response.sku , productName:result.response.name, stock: result.response.stock_quantity ?:15   )
        wooProductService.save(exProd)

        render exProd as JSON

    }

    def show(Long id) {
        respond wooProductService.get(id)
    }

    def create() {
        respond new WooProduct(params)
    }

    def save(WooProduct wooProduct) {
        if (wooProduct == null) {
            notFound()
            return
        }

        try {
            wooProductService.save(wooProduct)
        } catch (ValidationException e) {
            respond wooProduct.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'wooProduct.label', default: 'WooProduct'), wooProduct.id])
                redirect wooProduct
            }
            '*' { respond wooProduct, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond wooProductService.get(id)
    }

    def update(WooProduct wooProduct) {
        if (wooProduct == null) {
            notFound()
            return
        }

        try {
            wooProductService.save(wooProduct)
        } catch (ValidationException e) {
            respond wooProduct.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'wooProduct.label', default: 'WooProduct'), wooProduct.id])
                redirect wooProduct
            }
            '*'{ respond wooProduct, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        wooProductService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'wooProduct.label', default: 'WooProduct'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'wooProduct.label', default: 'WooProduct'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
