package com.example.androidproj1.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.NavArgs
import androidx.navigation.fragment.navArgs
import com.example.androidproj1.R
import com.example.androidproj1.repository.MovieRepository
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_detailed_page.*
import kotlinx.android.synthetic.main.fragment_detailed_page.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
/**
 * A simple [Fragment] subclass.
 * Use the [DetailedPage.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailedPage : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val args by navArgs<DetailedPageArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detailed_page, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //make floating action button invisible as it is not needed in this activity
        activity?.fab?.visibility = View.GONE

        val id = args.MovieDetailsTransfer.id
        val detailMovieName = args.MovieDetailsTransfer.movieName
        val detailMoviePopularity = args.MovieDetailsTransfer.popularity
        val movieimg = args.MovieDetailsTransfer.movieImage
        val detailMovieDescription  = args.MovieDetailsTransfer.description
        val release = args.MovieDetailsTransfer.date

        textView7.text= release
        textView2.text = detailMovieName
        textView8.text = detailMoviePopularity
        textView4.text = detailMovieDescription
        if (detailMoviePopularity.toFloat() < 4 ){
            textView8.setTextColor(Color.parseColor("#ff0e0e"))
        }
        else if(detailMoviePopularity.toFloat() >= 4 && detailMoviePopularity.toFloat() <7 ){
            textView8.setTextColor(Color.parseColor("#e1c419"))
        }
        else {
            textView8.setTextColor(Color.parseColor("#2de71c"))

        }

        imageView2.setImageDrawable(movieimg.drawable)
        favButton.setOnClickListener{
            if (!MovieRepository.isMovieFav(id)){
                favButton.setImageResource(R.drawable.ic_baseline_favorite_24)
                MovieRepository.addMovieFav(id)
                //Toast.makeText(itemView.context, "Added ${movieName.text} to favourites", Toast.LENGTH_SHORT).show()
            }
            else{
                favButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                MovieRepository.deleteMovieFav(id)
                //Toast.makeText(itemView.context, "Removed ${movieName.text} from favourites", Toast.LENGTH_SHORT).show()
            }
        }

        //if movie is favorited mark it as favorite
        if(MovieRepository.isMovieFav(id)) favButton.setImageResource(R.drawable.ic_baseline_favorite_24)

        favButton.setOnClickListener{
            if (!MovieRepository.isMovieFav(id)){
                favButton.setImageResource(R.drawable.ic_baseline_favorite_24)
                MovieRepository.addMovieFav(id)
                Toast.makeText(context, "Added $detailMovieName to favourites",Toast.LENGTH_SHORT).show()
            }
            else{
                favButton.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                MovieRepository.deleteMovieFav(id)
                Toast.makeText(context, "Removed $detailMovieName from favourites",Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailedPage.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailedPage().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}