package jp.techacademy.hikari.matsuba.apiapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    val titleIds = listOf(R.string.tab_title_api, R.string.tab_title_favorite)
    //タブの名前を格納する。

    public val fragments = listOf(ApiFragment(), FavoriteFragment())
    //ページの中身。ここでは0ページ目がApiFragment、1ページ目がFavoriteFragmentで表示

    override fun getItemCount(): Int {
        return fragments.size
    }
    //ViewPager2が何ページあるのかという数字を返す。fragmentsの数を返す。

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
    //引数で受け取ったpositionのページのFragmentを返す。
}
