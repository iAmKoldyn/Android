package company.vk.lection05.presentationlayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import company.vk.lection05.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, SimpleListFragment.newInstance())
            .commit()
    }
}
