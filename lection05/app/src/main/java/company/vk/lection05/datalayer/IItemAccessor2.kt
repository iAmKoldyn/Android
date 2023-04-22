package company.vk.lection05.datalayer

import company.vk.lection05.objects.Item
import retrofit2.http.GET
import retrofit2.http.Query

interface IItemAccessor2 {
	@GET("/api/cats")
	suspend fun items2(@Query("skip") skip: Int, @Query("limit") limit: Int = 100): List<Item>
}
