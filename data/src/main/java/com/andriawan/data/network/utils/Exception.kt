package com.andriawan.data.network.utils

import java.io.IOException

class ApiException(message: String): IOException(message)
class NetworkException(message: String): IOException(message)