package com.sample.openlibrary.ui.features.booksearch

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.openlibrary.R
import com.sample.openlibrary.databinding.FragmentBookSearchBinding
import com.sample.openlibrary.domain.functional.consume
import com.sample.openlibrary.host.HostProvider
import com.sample.openlibrary.host.navigation.HostNavigator
import com.sample.openlibrary.ui.base.BaseFragment
import com.sample.openlibrary.ui.extension.isShowing
import com.sample.openlibrary.ui.extension.toast
import com.sample.openlibrary.ui.extension.viewBinding
import com.sample.openlibrary.ui.features.booksearch.di.inject
import com.sample.openlibrary.ui.features.booksearch.recycler.BookSearchAdapter
import dagger.Lazy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Provider

@ExperimentalCoroutinesApi
class BookSearchFragment : BaseFragment(R.layout.fragment_book_search) {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var _navigator: Lazy<HostNavigator>
    private val navigator get() = _navigator.get()

    private val viewModel: BookSearchViewModel by viewModels { factory }

    private val binding by viewBinding(FragmentBookSearchBinding::bind)

    private val searchAdapter = BookSearchAdapter {
        navigator.actionBookDetails(it)
    }

    @Suppress("UNCHECKED_CAST")
    override fun onAttach(context: Context) {
        (context as Provider<HostProvider>).get().also(this::inject)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
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

        lifecycleScope.launch {
            viewModel.state.collect { render(it) }
        }
    }

    private fun doSearch() {
        val query = binding.searchInput.text.toString()
        viewModel.searchQuery(query)
    }

    private fun render(state: BookSearchViewModel.UiState) = with(binding) {
        queryResult.text = state.query

        progressBar.isShowing(state.loading)
        emptyView.isVisible = state.isEmpty

        searchAdapter.submitList(state.books)

        state.toast?.consume { toast(it) }
    }
}
