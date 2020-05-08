package com.sample.openlibrary.ui.features.booksearch

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.openlibrary.R
import com.sample.openlibrary.databinding.FragmentBookSearchBinding
import com.sample.openlibrary.di.DataProvider
import com.sample.openlibrary.domain.functional.consume
import com.sample.openlibrary.ui.base.BaseFragment
import com.sample.openlibrary.ui.extension.isShowing
import com.sample.openlibrary.ui.features.booksearch.di.inject
import com.sample.openlibrary.ui.features.booksearch.recycler.BookSearchAdapter
import javax.inject.Provider

class BookSearchFragment : BaseFragment(R.layout.fragment_book_search) {

    private val viewModel: BookSearchViewModel by viewModels { factory }

    private var _binding: FragmentBookSearchBinding? = null
    private val binding: FragmentBookSearchBinding get() = checkNotNull(_binding)

    private val searchAdapter = BookSearchAdapter()

    @Suppress("UNCHECKED_CAST")
    override fun onAttach(context: Context) {
        (context as Provider<DataProvider>).get().also(this::inject)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentBookSearchBinding.bind(view)

        binding.searchRecycler.run {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = searchAdapter
        }

        binding.searchInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                doSearch()
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        binding.actionSearch.setOnClickListener {
            doSearch()
        }

        viewModel.state
            .subscribe { render(it) }
            .track()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun doSearch() {
        val query = binding.searchInput.text.toString()
        viewModel.searchQuery(query)
    }

    private fun render(state: BookSearchViewModel.UiState) {
        binding.queryResult.text = state.query

        binding.progressBar.isShowing(state.loading)
        binding.emptyView.isVisible = state.isEmpty

        searchAdapter.submitList(state.books)

        state.toast?.consume {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }
    }
}
