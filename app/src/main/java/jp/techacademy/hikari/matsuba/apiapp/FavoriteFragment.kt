package jp.techacademy.hikari.matsuba.apiapp

import androidx.fragment.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import jp.techacademy.hikari.matsuba.apiapp.databinding.FragmentApiBinding

class FavoriteFragment: Fragment() {
    private val favoriteAdapter by lazy { FavoriteAdapter() }
    private var _binding: FragmentApiBinding? = null

    // FavoriteFragment -> MainActivity に削除を通知する
    private var fragmentCallback: FragmentCallback? = null
    private val binding get() = _binding!!


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentCallback) {
            fragmentCallback = context
        }
    }
    //FragmentがActivityへアタッチされたときに呼び出される

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentApiBinding.inflate(inflater, container, false)
        return binding.root
    }
    //このFragmentのメインとなるViewを生成し、返す必要があるときに呼び出される

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // ここから初期化処理を行う
        // FavoriteAdapterのお気に入り削除用のメソッドの追加を行う
        favoriteAdapter.apply {
            // Adapterの処理をそのままActivityに通知
            onClickDeleteFavorite = {
                fragmentCallback?.onDeleteFavorite(it.id)
            }

            // Itemをクリックしたとき
            onClickItem = {
                fragmentCallback?.onClickItem(it)
            }
        }
        // RecyclerViewの初期化
        binding.recyclerView.apply {
            adapter = favoriteAdapter
            layoutManager = LinearLayoutManager(requireContext()) // 一列ずつ表示
        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            updateData()
        }
        updateData()
    }
    //このFragmentのViewが生成された後に呼び出され、Viewの初期化・Fragmentの状態の復元を行う

    fun updateData() {
        favoriteAdapter.submitList(FavoriteShop.findAll())
        binding.swipeRefreshLayout.isRefreshing = false
    }
}