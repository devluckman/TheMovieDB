package com.man.movies.screen.dashboard.ui.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.man.movies.R

class UpComingFragment : Fragment() {

    private lateinit var upComingViewModel: UpComingViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_up_coming, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        return root
    }
}