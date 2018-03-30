package reybexmobil2

import grails.gorm.transactions.Transactional
import grails.plugins.rest.client.RestBuilder
import org.grails.web.json.JSONObject

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

@Transactional
class WsService {
    Map params = [authentication:
                          [authUsername: 'ck_093e8986d5ed838205de8d0f859ab743737302b2',
                           authPassword: 'cs_e2dd63e0abd58dc688585856179ae96e4548a0bc',
                           username    : 'reybextest', password: null, apiKey: 'reybextest',
                           endpointUrl : 'http://wordpress.p409543.webspaceconfig.de/wp-json/wc/v2']]

    def serviceMethod() {

        println basicMarketplaceParams.provider
        println "--------------test alani yazisi"

    }

    private Map getProduct() {
        println "--------------get product() "
        def baseUrl, response, resp
        try {
            baseUrl = createBaseUrl(params, ["per_page=1","page=1"], "GET", "/products")

            resp = new RestBuilder().get(baseUrl);

            println "++++++++"
            println resp.json instanceof JSONObject

            resp.status == OK.value()
            resp.json.count == 100


        }
        catch (Exception ex) {
            log.error(" productListRequestWithPerpage exception error: " + ex)
        }



        return [response: resp.json ?: "hataaa"]
    }

    private String createBaseUrl(Map params, List queryParams, String method, String path) {
        String parseUrl


            String timestamp = System.currentTimeMillis() / 1000L
            String nonce = (Math.random() * 1000).toString()
            timestamp = timestamp.take(10)


            String BASE_SITE = params.authentication.endpointUrl
            String BASE_URL = BASE_SITE + path
            String COSTUMER_KEY = params.authentication.authUsername
            String COSTUMER_SECRET = params.authentication.authPassword + "&" // '&' very important!

            String firstEncodedString = method + "&" + java.net.URLEncoder.encode(BASE_URL, 'UTF-8')

            def parameters = ["oauth_signature_method=HMAC-SHA1", "oauth_nonce=$nonce", "oauth_consumer_key=$COSTUMER_KEY", "oauth_timestamp=$timestamp"]
            if (queryParams) {
                parameters << queryParams
                parameters = parameters.flatten()
            }
            parameters = parameters.sort()
            String parameterString = parameters.join('&')

            //or parameterString="oauth_consumer_key="+COSTUMER_KEY+"&oauth_nonce="+nonce+"&oauth_signature_method=HMAC-SHA1&oauth_timestamp="+timestamp
            String secondEncodedString = "&" + java.net.URLEncoder.encode(parameterString, 'UTF-8')
            String baseString = firstEncodedString + secondEncodedString

            Mac mac = Mac.getInstance("HmacSHA1")
            SecretKeySpec secretKeySpec = new SecretKeySpec(COSTUMER_SECRET.getBytes(), "HmacSHA1")
            mac.init(secretKeySpec)
            byte[] rawHmac = mac.doFinal(baseString.getBytes())
            String signature = rawHmac.encodeBase64()
            signature = java.net.URLEncoder.encode(signature, 'UTF-8')


            parameters << ["oauth_signature=$signature"]
            parameters = parameters.flatten()
            parameters = parameters.sort()

            parseUrl = BASE_URL + "?" + parameters.join('&')

            if (BASE_SITE.contains("https")) {
                parseUrl = parseUrl + "&" + "consumer_key=" + COSTUMER_KEY + "&" + "consumer_secret=" + COSTUMER_SECRET
            }





        println parseUrl

        return parseUrl

    }

}
