package com.example.webflux.repository

import com.example.webflux.config.R2dbcTestConfiguration
import com.example.webflux.domain.Mileage
import com.example.webflux.util.MockUtil
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.annotation.Order
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@ContextConfiguration(
    classes = [
        R2dbcTestConfiguration::class
    ]
)
internal class MileageRepositoryTest {

    @Autowired
    private lateinit var mileageRepository: MileageRepository

    @Test
    @Order(1)
    fun `My Mileage Save`() {

        val mock: Mileage = MockUtil.readJsonFileToClass("json/mileage/save_.json", Mileage::class.java)!!

        val entity: Mileage = runBlocking {
            mileageRepository.save(mock)
        }

        assertNotNull(entity.id)
        assertEquals(mock.userId, entity.userId)
        assertEquals(mock.point, entity.point)
        assertNotNull(entity.createdAt)
        assertNotNull(entity.updatedAt)
    }

    @Test
    @Order(2)
    fun `Find User Id`() {

        val mockUserId = 1L

        val mock: Mileage = MockUtil.readJsonFileToClass("json/mileage/save_.json", Mileage::class.java)!!

        val entity: Mileage = runBlocking {
            mileageRepository.findByUserId(mockUserId)!!
        }

        assertNotNull(entity.id)
        assertEquals(mock.userId, entity.userId)
        assertEquals(mock.point, entity.point)
        assertNotNull(entity.createdAt)
        assertNotNull(entity.updatedAt)
    }
}