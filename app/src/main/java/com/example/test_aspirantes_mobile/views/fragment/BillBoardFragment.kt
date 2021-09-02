package com.example.test_aspirantes_mobile.views.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test_aspirantes_mobile.databinding.FragmentBillboardBinding
import com.example.test_aspirantes_mobile.model.models.BillBoardResponse
import com.example.test_aspirantes_mobile.model.viewmodel.BillBoardViewModel
import com.example.test_aspirantes_mobile.views.MyApplication
import com.example.test_aspirantes_mobile.views.adapter.BillBoardAdapter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class BillBoardFragment : Fragment() {

    private var _binding: FragmentBillboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var billBoardAdapter: BillBoardAdapter
    private lateinit var billBoardViewModel: BillBoardViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentBillboardBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        billBoardAdapter = BillBoardAdapter()
        billBoardViewModel =
            ViewModelProvider(this).get(BillBoardViewModel::class.java)
        obtainerBillBoard()
    }

    private fun obtainerBillBoard(){
        billBoardViewModel.allBillBoard.observe(requireActivity(), {
            bindBillBoardRecyclerView(Json.decodeFromString(it[0].data))
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



    private fun bindBillBoardRecyclerView(billBoardResponse: BillBoardResponse){
        _binding!!.fragmentCarteleraRvCartelera.layoutManager = GridLayoutManager(MyApplication.appContext!!, 2)
        billBoardAdapter.setBillboard(MyApplication.appContext!!,requireActivity(),billBoardResponse)
        _binding!!.fragmentCarteleraRvCartelera.adapter= billBoardAdapter
    }


}