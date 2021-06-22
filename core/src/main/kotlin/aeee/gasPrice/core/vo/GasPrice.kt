package aeee.gasPrice.core.vo

class GasPrice {

    companion object {
        val Empty = GasPrice().apply {
            jsonrpc = "-1"
            id = "-1"
            result = Result.Empty
        }
    }

    lateinit var jsonrpc: String
    lateinit var id: String

    lateinit var result: Result
}