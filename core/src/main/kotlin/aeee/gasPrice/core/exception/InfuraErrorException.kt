package aeee.gasPrice.core.exception

class InfuraErrorException: RuntimeException {

    companion object {
        private val message = "서버에 이상이 있습니다."
    }

    private var data: Any? = null

    constructor(): this(InfuraErrorException.message, null)
    constructor(data: Any?): this(InfuraErrorException.message, data)

    constructor(message: String, data: Any?): super(message) {
        this.data = data
    }
}