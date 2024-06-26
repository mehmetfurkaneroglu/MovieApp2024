package com.eroglu.movieapp.view.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.eroglu.movieapp.databinding.FragmentFavoriteBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

//    private lateinit var favoriteRecyclerView: RecyclerView
//    private lateinit var favoriteAdapter: FavoriteAdapter
//    private val favoriteList: MutableList<FavoriteModel> = mutableListOf()
    /*
    private val viewModel: FavoriteViewModel by viewModels()


    private lateinit var movieDatabase: DatabaseReference
    private lateinit var tvSeriesDatabase: DatabaseReference
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.setVariable(BR.viewModel,viewModel)
        binding.lifecycleOwner = this


        viewModel.selectedFavoriteItem.observe(viewLifecycleOwner){favoriteItem->
            val bundle = Bundle()
            when (favoriteItem.itemType) {
                FavoriteItemTypeEnum.MOVIES -> {
                    bundle.putInt(Keys.MOVIE_DETAIL_KEY, favoriteItem.itemId?.toInt() ?: 0)
                    Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
                        .navigate(R.id.action_favoriteFragment_to_detailFragment, bundle)
                }

                FavoriteItemTypeEnum.TV_SERIES -> {
                    bundle.putInt(Keys.TV_SERIES_DETAIL_KEY, favoriteItem.itemId?.toInt() ?: 0)
                    Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
                        .navigate(R.id.action_favoriteFragment_to_tvSeriesDetailFragment, bundle)
                }

                else -> {}
            }
        }

        getDataFromFirebase()

        binding.moviesTextview.setOnClickListener{
            viewModel.adapter.postValue(viewModel.favoriteMovieAdapter)
        }

        binding.tvSeriesTextview.setOnClickListener{
            viewModel.adapter.postValue(viewModel.favoriteTvSeriesAdapter)
        }

    }

    fun getDataFromFirebase(){
        val currentUserUid = Firebase.auth.currentUser?.uid

        movieDatabase = FirebaseDatabase.getInstance().reference.child("users").child(currentUserUid ?: "").child("favorite_movies")
        movieDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val a = arrayListOf<CommonModel>()

                for (data in snapshot.children) {
                    val commonModel: CommonModel? = data.getValue(CommonModel::class.java)
                    commonModel?.let {
                        a.add(commonModel)
                    }
                }
                viewModel.favoriteMovieAdapter.favoriteList = a
//                viewModel.favoriteAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        tvSeriesDatabase = FirebaseDatabase.getInstance().reference.child("users").child(currentUserUid ?: "").child("favorite_tv_series")
        tvSeriesDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val a = arrayListOf<CommonModel>()
                for (data in snapshot.children) {
                    val commonModel: CommonModel? = data.getValue(CommonModel::class.java)
                    commonModel?.let {
                        a.add(commonModel)
                    }
                }
                viewModel.favoriteTvSeriesAdapter.favoriteList = a
//                viewModel.favoriteAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

     */
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mViewPager: ViewPager2 = binding.viewPager
        val mTabLayout: TabLayout = binding.tabLayout

        mViewPager.adapter = FavoriteViewPagerAdapter(this)
        TabLayoutMediator(mTabLayout, mViewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Movies"
                1 -> tab.text = "Tv Series"
                else -> null
            }
        }.attach()
    }


}
