package jp.techacademy.hikari.matsuba.apiapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.techacademy.hikari.matsuba.apiapp.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWebViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.webView.loadUrl(intent.getStringExtra(KEY_URL).toString())

        binding.favoriteImageView.apply{

            fun bind(shop: Shop, position: Int,adapter: ApiAdapter) {
                // お気に入り状態を取得
                val isFavorite = FavoriteShop.findBy(shop.id) != null

                // 白抜きの星を設定
                setImageResource(if (isFavorite) R.drawable.ic_star else R.drawable.ic_star_border)

                // 星をタップした時の処理
                setOnClickListener {
                    if (isFavorite) {
                        adapter.onClickDeleteFavorite?.invoke(shop)
                    } else {
                        adapter.onClickAddFavorite?.invoke(shop)
                    }
                    adapter.notifyItemChanged(position)
                }
            }
        }
    }

    companion object {
        private const val KEY_URL = "key_url"
        fun start(activity: Activity, url: String) {
            //activityクラスのstartActivityメソッドを呼び出す
            activity.startActivity(
                Intent(activity, WebViewActivity::class.java).putExtra(
                    KEY_URL,
                    url
                )
            )
        }
    }
}


