package com.peter.gooroo.data.source

import com.peter.gooroo.data.Combine
import com.peter.gooroo.data.PostTen
import com.peter.gooroo.data.Process
import com.peter.gooroo.data.Result

/**
 *
 * Interface to the Gooroo layers.
 */

interface GoorooRepository {

    suspend fun getNumberValue(number: Int): Result<Process>

    suspend fun postTenNumber(tenNumber: PostTen): Result<Combine>

}