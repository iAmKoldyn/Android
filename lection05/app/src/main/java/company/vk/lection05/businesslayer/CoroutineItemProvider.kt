package company.vk.lection05.businesslayer

import company.vk.lection05.datalayer.IItemAccessor2
import company.vk.lection05.objects.Item
import company.vk.lection05.objects.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class CoroutineItemProvider(private val accessor: IItemAccessor2) {
	private val scope = CoroutineScope(Dispatchers.Main)

	fun load(page: Int, callback: (Result<List<Item>>) -> Unit) {
		scope.launch {
			try {
				val result = withContext(Dispatchers.IO) { accessor.items2(page) }
				callback(Result.Success(result))
			} catch (error: Throwable) {
				callback(Result.Error(error))
			}
		}
	}
}