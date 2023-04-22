package company.vk.lection05.presentationlayer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import company.vk.lection05.R
import company.vk.lection05.businesslayer.CoroutineItemProvider
import company.vk.lection05.datalayer.IItemAccessor2
import company.vk.lection05.datalayer.RetrofitProvider
import company.vk.lection05.presentationlayer.adapters.ItemAdapter
import company.vk.lection05.objects.Result
import android.widget.Button
import android.widget.TextView

class SimpleListFragment : Fragment() {
    protected val provider by lazy { initializeProvider() }
    protected val itemAdapter = ItemAdapter()
    private val accessor = RetrofitProvider().provide().create(IItemAccessor2::class.java)
    private var page = 1
    private lateinit var retryButton: Button
    private lateinit var errorMessage: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.content_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retryButton = view.findViewById(R.id.retry_button)
        errorMessage = view.findViewById(R.id.error_message)

        retryButton.setOnClickListener {
            loadData()
        }

        view.findViewById<RecyclerView>(R.id.list).apply {
            layoutManager = GridLayoutManager(requireContext(), COLUMN_COUNT)
            adapter = itemAdapter
        }

        loadData()
    }

    private fun loadData() {
        provider.load(page) { result ->
            when (result) {
                is Result.Success -> {
                    Log.d("TECH", "submit list with size=${result.data.size}")
                    itemAdapter.submitList(result.data)

                    retryButton.visibility = View.GONE
                    errorMessage.visibility = View.GONE
                }
                is Result.Error -> {
                    Log.e("TECH", "Failed to load data", result.exception)

                    retryButton.visibility = View.VISIBLE
                    errorMessage.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun initializeProvider(): CoroutineItemProvider {
        return CoroutineItemProvider(accessor)
    }

    companion object {
        protected const val COLUMN_COUNT = 3

        fun newInstance(): SimpleListFragment {
            return SimpleListFragment()
        }
    }
}
