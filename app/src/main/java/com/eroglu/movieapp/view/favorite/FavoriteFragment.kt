package com.eroglu.movieapp.view.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

class FavoriteFragment : Fragment() {

    private lateinit var favoriteRecyclerView: RecyclerView
    private lateinit var favoriteAdapter: FavoriteAdapter
    private val favoriteList: MutableList<FavoriteModel> = mutableListOf()
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

        favoriteAdapter = FavoriteAdapter(favoriteList, clickListener = object : ItemClickedListener {
            override fun onItemClicked(favoriteModel: FavoriteModel) {
                val bundle = Bundle()
                when (favoriteModel.itemType) {
                    FavoriteItemTypeEnum.MOVIES -> {
                        bundle.putInt(Keys.MOVIE_DETAIL_KEY, favoriteModel.itemId?.toInt() ?: 0)
                        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
                            .navigate(R.id.action_favoriteFragment_to_detailFragment, bundle)
                    }
                    FavoriteItemTypeEnum.TV_SERIES -> {
                        bundle.putInt(Keys.TV_SERIES_DETAIL_KEY, favoriteModel.itemId?.toInt() ?: 0)
                        Navigation.findNavController(requireActivity(), R.id.fragmentContainerView)
                            .navigate(R.id.action_favoriteFragment_to_tvSeriesDetailFragment, bundle)
                    }

                    else -> {}
                }
            }
        })

        favoriteRecyclerView = binding.favoriteRecyclerView
        favoriteRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        favoriteRecyclerView.adapter = favoriteAdapter

        val currentUserUid = Firebase.auth.currentUser?.uid

        movieDatabase = FirebaseDatabase.getInstance().reference.child("users").child(currentUserUid ?: "").child("favorite_movies")
        movieDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                favoriteList.clear()
                for (data in snapshot.children) {
                    val favoriteModel: FavoriteModel? = data.getValue(FavoriteModel::class.java)
                    favoriteModel?.let {
                        favoriteList.add(favoriteModel)
                    }
                }
                favoriteAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        tvSeriesDatabase = FirebaseDatabase.getInstance().reference.child("users").child(currentUserUid ?: "").child("favorite_tv_series")
        tvSeriesDatabase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children) {
                    val favoriteModel: FavoriteModel? = data.getValue(FavoriteModel::class.java)
                    favoriteModel?.let {
                        favoriteList.add(favoriteModel)
                    }
                }
                favoriteAdapter.notifyDataSetChanged()
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
