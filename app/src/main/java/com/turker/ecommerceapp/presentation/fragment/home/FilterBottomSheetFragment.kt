package com.turker.ecommerceapp.presentation.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.turker.ecommerceapp.R
import com.turker.ecommerceapp.data.model.FilterResult
import com.turker.ecommerceapp.databinding.FragmentFilterBottomSheetBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FilterBottomSheetFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentFilterBottomSheetBinding? = null
    private val binding get() = _binding!!

    // Gelen filtre verileri
    private val brandList = listOf("Samsung", "Apple", "Xiaomi", "Huawei", "Oppo")
    private val modelList = listOf("A50", "iPhone 14", "Mi 11", "P30", "F11")

    // Seçilen filtre değerleri
    private val selectedBrands = mutableSetOf<String>()
    private val selectedModels = mutableSetOf<String>()
    private var selectedSortOption: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFilterBottomSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCloseButton()
        setupBrandFilters()
        setupModelFilters()
        setupSortOptions()
        setupApplyButton()
    }

    private fun setupCloseButton() {
        binding.closeButton.setOnClickListener {
            dismiss()
        }
    }

    private fun setupBrandFilters() {
        updateCheckboxList(
            brandList,
            binding.brandCheckboxContainer,
            selectedBrands
        )

        binding.brandSearch.doAfterTextChanged { text ->
            val filtered = brandList.filter { it.contains(text.toString(), true) }
            updateCheckboxList(filtered, binding.brandCheckboxContainer, selectedBrands)
        }
    }

    private fun setupModelFilters() {
        updateCheckboxList(
            modelList,
            binding.modelCheckboxContainer,
            selectedModels
        )

        binding.modelSearch.doAfterTextChanged { text ->
            val filtered = modelList.filter { it.contains(text.toString(), true) }
            updateCheckboxList(filtered, binding.modelCheckboxContainer, selectedModels)
        }
    }

    private fun updateCheckboxList(
        items: List<String>,
        container: LinearLayout,
        selectedSet: MutableSet<String>
    ) {
        container.removeAllViews()
        items.forEach { item ->
            val checkbox = CheckBox(requireContext()).apply {
                text = item
                isChecked = selectedSet.contains(item)
                setOnCheckedChangeListener { _, isChecked ->
                    if (isChecked) selectedSet.add(item) else selectedSet.remove(item)
                }
            }
            container.addView(checkbox)
        }
    }

    private fun setupSortOptions() {
        binding.sortOptions.setOnCheckedChangeListener { _, checkedId ->
            selectedSortOption = when (checkedId) {
                R.id.rbOldToNew -> "old_to_new"
                R.id.rbNewToOld -> "new_to_old"
                R.id.rbPriceHighToLow -> "price_high_to_low"
                R.id.rbPriceLowToHigh -> "price_low_to_high"
                else -> null
            }
        }
    }

    private fun setupApplyButton() {
        binding.btnApplyFilters.setOnClickListener {
            val filterResult = FilterResult(
                brands = selectedBrands.toList(),
                models = selectedModels.toList(),
                sort = selectedSortOption
            )

            // Örnek: ViewModel'e ya da parent'a gönder
            (parentFragment as? FilterListener)?.onFilterApplied(filterResult)

            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    interface FilterListener {
        fun onFilterApplied(result: FilterResult)
    }
}

