package com.example.newsapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.newsapp.ui.adapter.NewsAdapter
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.shareNews
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentNewsFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val recentNewsViewModel by viewModels<RecentNewsViewModel>()

    private lateinit var newsAdapter: NewsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsAdapter = NewsAdapter(onClick = { news ->
            //Todo
        }, onBookmarkedClick = { news, isBookmarked ->
            if (news != null) {
                if (isBookmarked) recentNewsViewModel.addNewsToBookmark(news)
                else recentNewsViewModel.removeBookmark(news.title)
            }
        }, onShare = { news ->
            shareNews(requireContext(), news?.url)
        })

        binding.newsRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = newsAdapter
            PagerSnapHelper().attachToRecyclerView(this)
        }

        recentNewsViewModel.news.observe(viewLifecycleOwner) {
            newsAdapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
        recentNewsViewModel.getTrendingNews("in")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}