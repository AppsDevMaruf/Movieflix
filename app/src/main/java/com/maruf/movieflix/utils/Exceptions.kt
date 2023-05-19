package com.maruf.movieflix.utils

import okio.IOException

class ApiException (message:String) : IOException(message)
class NoInternetException (message:String) : IOException(message)