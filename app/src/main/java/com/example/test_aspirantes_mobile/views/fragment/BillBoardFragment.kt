package com.example.test_aspirantes_mobile.views.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.test_aspirantes_mobile.databinding.FragmentBillboardBinding
import com.example.test_aspirantes_mobile.model.BillBoardResponse
import com.example.test_aspirantes_mobile.rest.CinemasServices
import com.example.test_aspirantes_mobile.utils.Constants
import com.example.test_aspirantes_mobile.utils.ToastUtils
import com.example.test_aspirantes_mobile.utils.Utils
import com.example.test_aspirantes_mobile.views.MyApplication
import com.example.test_aspirantes_mobile.views.adapter.BillBoardAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BillBoardFragment : Fragment() {

    private var _binding: FragmentBillboardBinding? = null
    private val binding get() = _binding!!
    private lateinit var billBoardAdapter: BillBoardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _binding = FragmentBillboardBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        billBoardAdapter = BillBoardAdapter()
        if(Utils.checkWIFI(MyApplication.appContext!!)){
            getBillBoard()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getBillBoard(){
        val service = CinemasServices().webservice
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                var response = service.getBillBoard(Constants.DUMMY_COUNTRY_CODE,Constants.CINEMAS)
                try{
                    if(response.isSuccessful){
                        populateBillboardRecycler(response.body()!!)
                    }else{
                        ToastUtils.showErrorToastFromJson(MyApplication.appContext!!,response.errorBody().toString())
                    }
                }catch (e:Exception){
                    e.printStackTrace()
                }
            }
        }
    }


    private fun populateBillboardRecycler(billBoardResponse:BillBoardResponse){
        _binding!!.fragmentCarteleraRvCartelera.layoutManager = GridLayoutManager(MyApplication.appContext!!, 2)
        billBoardAdapter.setBillboard(billBoardResponse)
        _binding!!.fragmentCarteleraRvCartelera.adapter= billBoardAdapter
    }


}