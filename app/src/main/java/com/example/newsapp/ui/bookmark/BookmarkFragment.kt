package com.example.newsapp.ui.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.shareNews
import com.example.newsapp.ui.adapter.BookmarkedNewsAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookmarkFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var bookmarkAdapter: BookmarkedNewsAdapter
    private val bookmarkViewModel by viewModels<BookMarkViewModel>()

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
        bookmarkAdapter = BookmarkedNewsAdapter(
            onClick = { news ->
                //Todo
            },
            onUnBookmarkedClick = { news ->
                news?.let {
                    bookmarkViewModel.removeBookmark(it.title)
                }
            },
            onShare = { news ->
                shareNews(requireContext(), news?.url)
            }
        )

        binding.newsRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = bookmarkAdapter
            PagerSnapHelper().attachToRecyclerView(this)
        }

        bookmarkViewModel.bookmarkedNews.observe(viewLifecycleOwner) {
            binding.tvEmpty.isVisible = it.isNullOrEmpty()
            it?.let { it1 -> bookmarkAdapter.submitData(it1) }

        }

        bookmarkViewModel.getBookmarkedNews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}