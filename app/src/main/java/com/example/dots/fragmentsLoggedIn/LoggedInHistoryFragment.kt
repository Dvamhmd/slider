package com.example.dots.fragmentsLoggedIn

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dots.R
import com.example.dots.adapter.OrderHistoryAdapter
import com.example.dots.network.ApiClient
import com.example.dots.network.ApiService
import com.example.dots.repository.CheckoutRepository
import com.example.dots.viewmodel.CheckOutViewModel
import com.example.dots.viewmodel.factory.CheckOutViewModelFactory
import com.midtrans.sdk.uikit.api.model.TransactionResult
import com.midtrans.sdk.uikit.external.UiKitApi
import com.midtrans.sdk.uikit.internal.util.UiKitConstants

class LoggedInHistoryFragment : Fragment() {

      private lateinit var recyclerView: RecyclerView
      private lateinit var viewModel: CheckOutViewModel
      private lateinit var adapter: OrderHistoryAdapter

      override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
      ): View? {
            val view = inflater.inflate(R.layout.fragment_logged_in_history, container, false)
            recyclerView = view.findViewById(R.id.orderItemsRecycler)
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            return view
          }

      override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

          adapter = OrderHistoryAdapter(emptyList()) { transaksi ->
              if (transaksi.status == "menunggu pembayaran" && !transaksi.snapToken.isNullOrEmpty()) {
                  try {
                      UiKitApi.getDefaultInstance().startPaymentUiFlow(requireActivity(), launcher, transaksi.snapToken)
                  } catch (e: Exception) {
                      Toast.makeText(requireContext(), "Gagal memulai pembayaran", Toast.LENGTH_SHORT).show()
                      Log.e("SnapToken", "Error: ${e.message}")
                  }
              } else {
                  Toast.makeText(requireContext(), "Pesanan tidak bisa dibayar ulang", Toast.LENGTH_SHORT).show()
              }
          }

          recyclerView.adapter = adapter

          viewModel = ViewModelProvider(
                this,
              CheckOutViewModelFactory(CheckoutRepository(ApiClient.getApiService(requireContext())))
          )[CheckOutViewModel::class.java]






          viewModel.orders.observe(viewLifecycleOwner) { orders ->
              android.util.Log.d("loggedinhistoryfragment", "Jumlah orders: ${orders.size}")

              if (orders.isNotEmpty()) {
                  // Tampilkan recycler dan sembunyikan empty state
                  recyclerView.visibility = View.VISIBLE
                  view.findViewById<View>(R.id.empty_state).visibility = View.GONE
                  adapter.updateOrders(orders)
              } else {
                  // Jika kosong, tampilkan empty state
                  recyclerView.visibility = View.GONE
                  view.findViewById<View>(R.id.empty_state).visibility = View.VISIBLE
              }
          }




          viewModel.error.observe(viewLifecycleOwner) {
                  Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }

            viewModel.getOrderHistory()
          }


    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val transactionResult = result.data?.getParcelableExtra<TransactionResult>(UiKitConstants.KEY_TRANSACTION_RESULT)
            when (transactionResult?.status) {
                UiKitConstants.STATUS_SUCCESS -> {
                    Toast.makeText(requireContext(), "Pembayaran Berhasil!", Toast.LENGTH_SHORT).show()
                    viewModel.getOrderHistory() // <-- refresh setelah sukses
                }
                UiKitConstants.STATUS_PENDING -> {
                    Toast.makeText(requireContext(), "Pembayaran Tertunda!", Toast.LENGTH_SHORT).show()
                    viewModel.getOrderHistory() // <-- refresh juga saat pending
                }
                UiKitConstants.STATUS_FAILED -> {
                    Toast.makeText(requireContext(), "Pembayaran Gagal!", Toast.LENGTH_SHORT).show()
                    viewModel.getOrderHistory() // <-- refresh juga jika gagal, status bisa berubah
                }
                else -> {
                    Toast.makeText(requireContext(), "Transaksi dibatalkan.", Toast.LENGTH_SHORT).show()
                    viewModel.getOrderHistory() // <-- tetap refresh
                }
            }
        } else {
            viewModel.getOrderHistory() // <-- user tekan back tanpa selesaiin pembayaran
        }
    }

    fun refreshOrder() {
        viewModel.getOrderHistory()
    }



}
