package company.vk.lection05.presentationlayer.adapters

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import company.vk.lection05.R
import company.vk.lection05.objects.Item

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val image = view.findViewById<ImageView>(R.id.image)
    private val imageLoader by lazy { Picasso.get() }

    fun bind(item:
             Item) {
        imageLoader.load(item.imageValue()).into(image)
    }
}