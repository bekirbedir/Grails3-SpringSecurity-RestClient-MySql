package reybexmobil2

import grails.gorm.transactions.Transactional


@Transactional
class WsService {
    private static Map basicMarketplaceParams = [
            provider: 'WooCommerce', authentication:
                    [authUsername:'ck_169ede82bf347bb43656812ed8ef9a9051a327a5',
                     authPassword:'cs_741379d8d550b539d94dc20a4bcaebad1472381d',
                     username:'reybextest', password:null, apiKey:'reybextest',
                     endpointUrl:'http://augletics.de/wp-json/wc/v2']]

    def serviceMethod() {

        println basicMarketplaceParams.provider
        println "--------------test alani yazisi"

    }

    private getProduct(){
        println basicMarketplaceParams.provider
        println "--------------get product() "
    }

}
