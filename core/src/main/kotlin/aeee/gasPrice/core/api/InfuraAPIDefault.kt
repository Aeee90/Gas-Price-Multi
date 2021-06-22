package aeee.gasPrice.core.api

import aeee.gasPrice.core.api.config.HttpSender
import aeee.gasPrice.core.enums.InfuraMethod
import aeee.gasPrice.core.vo.GasPrice
import org.json.JSONArray
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
internal class InfuraAPIDefault(
    @Qualifier("InfuraAPIHttpSender") private val httpSender: HttpSender
): InfuraAPI {

    private fun getBaseParameter(method: InfuraMethod): JSONObject {
        val json = JSONObject()
        json.put("jsonrpc1", "2.0")
        json.put("method", method.value)
        json.put("id", 1)
        return json
    }

    private fun <T> request(clazz: Class<T>, method: InfuraMethod, params: JSONArray): T? {
        val json = getBaseParameter(method)
        json.put("params", params)
        return request(clazz, json.toString())
    }

    private fun <T> request(clazz: Class<T>, param: String): T? {
        val httpEntity= httpSender.getHttpEntity(param)
        return httpSender.post(httpEntity, clazz)
    }

    override fun getEth_getBlockByNumber(): GasPrice {
        val params = JSONArray()
        params.put("latest")
        params.put(true)
        return request(GasPrice::class.java, InfuraMethod.ETH_GET_BLOCK_BY_NUMBER, params) ?: GasPrice.Empty
    }
}