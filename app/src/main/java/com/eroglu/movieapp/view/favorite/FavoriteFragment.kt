package com.eroglu.movieapp.view.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.eroglu.movieapp.BR
import com.eroglu.movieapp.R
import com.eroglu.movieapp.databinding.FragmentFavoriteBinding
import com.eroglu.movieapp.util.Keys
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

//    private lateinit var favoriteRecyclerView: RecyclerView
//    private lateinit var favoriteAdapter: FavoriteAdapter
//    private val favoriteList: MutableList<FavoriteModel> = mutableListOf()
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
                val a = arrayListOf<FavoriteModel>()

                for (data in snapshot.children) {
                    val favoriteModel: FavoriteModel? = data.getValue(FavoriteModel::class.java)
                    favoriteModel?.let {
                        a.add(favoriteModel)
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
                val a = arrayListOf<FavoriteModel>()
                for (data in snapshot.children) {
                    val favoriteModel: FavoriteModel? = data.getValue(FavoriteModel::class.java)
                    favoriteModel?.let {
                        a.add(favoriteModel)
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
}
