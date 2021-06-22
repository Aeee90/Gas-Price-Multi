package aeee.gasPrice.core.api

import aeee.gasPrice.core.vo.GasPrice

interface InfuraAPI {

    fun getEth_getBlockByNumber(): GasPrice
}