package com.peter.gooroo.data.source

import com.peter.gooroo.data.Combine
import com.peter.gooroo.data.PostTen
import com.peter.gooroo.data.Process
import com.peter.gooroo.data.Result

/**
 *
 * Main entry point for accessing Stylish sources.
 */

interface GoorooDataSource {

    suspend fun getNumberValue(number: Int): Result<Process>

    suspend fun postTenNumber(tenNumber: PostTen): Result<Combine>

}