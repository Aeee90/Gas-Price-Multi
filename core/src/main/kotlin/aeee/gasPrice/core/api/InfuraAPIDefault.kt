package aeee.gasPrice.core.api

import aeee.gasPrice.core.api.config.HttpSender
import aeee.gasPrice.core.enums.InfuraMethod
import aeee.gasPrice.core.vo.GasPrice
import org.json.JSONArray
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
internal class InfuraAPIDefault(
    @Qualifier("InfuraAPIHttpSender") private val httpSender: HttpSender
): InfuraAPI {

    private fun getBaseParameter(method: InfuraMethod): JSONObject {
        val params = JSONObject()
        params.put("jsonrpc1", "2.0")
        params.put("method", method.value)
        params.put("id", 1)
        return params
    }

    private fun <T> request(clazz: Class<T>, method: InfuraMethod, params: JSONArray): Mono<T> {
        val param = getBaseParameter(method)
        param.put("params", params)
        return request(clazz, param.toString())
    }

    private fun <T> request(clazz: Class<T>, param: String): Mono<T> = httpSender.post(param, clazz)

    override fun getEth_getBlockByNumber(): Mono<GasPrice> {
        val params = JSONArray()
        params.put("latest")
        params.put(true)
        return request(GasPrice::class.java, InfuraMethod.ETH_GET_BLOCK_BY_NUMBER, params)
            .defaultIfEmpty(GasPrice.Empty)
    }
}