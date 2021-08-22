package com.peter.gooroo.data.source

import com.peter.gooroo.data.Combine
import com.peter.gooroo.data.PostTen
import com.peter.gooroo.data.Process
import com.peter.gooroo.data.Result

/**
 *
 * Concrete implementation to load Gooroo sources.
 */

class DefaultGoorooRepository(private val goorooRemoteDataSource: GoorooDataSource) : GoorooRepository {
    override suspend fun getNumberValue(number: Int): Result<Process> {
        return goorooRemoteDataSource.getNumberValue(number)
    }

    override suspend fun postTenNumber(tenNumber: PostTen): Result<Combine> {
        return goorooRemoteDataSource.postTenNumber(tenNumber)
    }
}