package com.example.moviesapp.movies.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.asLiveData
import com.example.moviesapp.data.remote.MovieDto
import com.example.moviesapp.data.remote.MovieDtoResponse
import com.bumptech.glide.Glide
import com.example.moviesapp.databinding.FragmentMovieBinding
import com.example.moviesapp.movies.FavoriteViewModel
import com.example.moviesapp.movies.MoviesViewModel
import com.example.moviesapp.utils.GlideBlurTransformation
import com.example.moviesapp.utils.UtilsPicture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieFragment : Fragment() {

    companion object {

        fun getFragmentTag(): String {
            return "MovieFragment"
        }
    }

    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private val movieViewModel: MoviesViewModel by activityViewModels()
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        manageViewModel()
        toggleFavorite()

    }

    private fun manageViewModel() {
        movieViewModel
            .currentMovie
            .asLiveData()
            .observe(viewLifecycleOwner, {
                manageMovieState(it)
            })

    }

    private fun manageMovieState(movieState: MoviesViewModel.MovieViewState) {
        val movie = movieState.data

        movie?.let {
            fragmentMovieBinding.movieContainer.transitionName = it.id.toString()
            fragmentMovieBinding.movieTitle.text = it.movieName
            fragmentMovieBinding.movieDescription.text = it.overview
            fragmentMovieBinding.movieRatingCountLabel.text = it.voteCount.toString()
            fragmentMovieBinding.movieRatingLabel.text = it.voteAverage.toString()

            it.moviePosterPath?.let {
                Glide.with(fragmentMovieBinding.moviePoster)
                    .asBitmap()
                    .load(UtilsPicture.getCompleteUrlFrom(it))
                    .into(fragmentMovieBinding.moviePoster)


                Glide.with(fragmentMovieBinding.movieBlurredBackGround)
                    .asBitmap()
                    .transform(GlideBlurTransformation(fragmentMovieBinding.movieBlurredBackGround.context))
                    .load(UtilsPicture.getCompleteUrlFrom(it))
                    .into(fragmentMovieBinding.movieBlurredBackGround)
            }
        }
    }

    fun toggleFavorite(){
        if (arguments?.getSerializable("datadetail") != null) {
            val getMovie = arguments?.getSerializable("datadetail") as MovieDto
            val id = getMovie.id
            val movieName = getMovie.movieName
            val moviePosterPath = getMovie.moviePosterPath

            var _isChecked = false
            CoroutineScope(Dispatchers.IO).launch {
                val count = viewModel.checkUser(id)
                withContext(Dispatchers.Main){
                    if (count != null){
                        if (count > 0){
                            binding.btnFavorite.isChecked = true
                            _isChecked = true
                        } else {
                            binding.btnFavorite.isChecked = false
                            _isChecked = false
                        }
                    }
                }
            }


            binding.btnFavorite.setOnClickListener{
                _isChecked = !_isChecked
                if (_isChecked) {
//                    viewModel.addToFavorite(id, movieName, moviePosterPath)
                } else {
//                    viewModel.removeFromFavorite(id, movieName, moviePosterPath)
                }
                binding.btnFavorite.isChecked = _isChecked
            }
        }

    }
}