package com.example.dots.fragmentsLoggedIn

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dots.R
import com.example.dots.activityLoginTrue.CheckOutActivity
import com.example.dots.adapter.KeranjangAdapter
import com.example.dots.models.Keranjang
import com.example.dots.models.RequestCheckoutItem
import com.example.dots.network.ApiClient
import com.example.dots.repository.KeranjangRepository
import com.example.dots.utilities.toRupiah
import com.example.dots.viewmodel.KeranjangViewModel
import com.google.gson.Gson

class LoggedInCartFragment : Fragment() {

    private lateinit var viewModel: KeranjangViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var emptyLayout: LinearLayout
    private lateinit var checkoutSection: LinearLayout
    private lateinit var totalHargaText: TextView
    private lateinit var keranjangAdapter: KeranjangAdapter
    private var justDeleted = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_logged_in_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerViewKeranjang)
        emptyLayout = view.findViewById(R.id.emptyCartLayout)
        checkoutSection = view.findViewById(R.id.checkoutSection)
        totalHargaText = view.findViewById(R.id.totalHarga)

        val checkoutButton = view.findViewById<Button>(R.id.btnCheckout)
        checkoutButton.setOnClickListener {
            goToCheckout()
        }


        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val repository = KeranjangRepository(ApiClient.getApiService(requireContext()))
        viewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return KeranjangViewModel(repository) as T
            }
        })[KeranjangViewModel::class.java]

        keranjangAdapter = KeranjangAdapter(mutableListOf()) { item ->

            Log.d("KeranjangFragment", "id keranjang: ${item.idKeranjang}")

            val idKeranjang = Keranjang(
                idKeranjang = item.idKeranjang,
                idUser = null,
                idToko = null,
                idProduk = null,
                namaProduk = null,
                jumlah = null,
                hargaTotal = null,
                harga = null,
                gambar = null,
                createdAt = null,
                updatedAt = null
            )


            AlertDialog.Builder(requireContext())
                .setTitle("Hapus Produk")
                .setMessage("Yakin ingin menghapus ${item.namaProduk} dari keranjang?")
                .setPositiveButton("Ya") { _, _ ->
                    justDeleted = true
                    viewModel.hapusKeranjang(idKeranjang)
                }
                .setNegativeButton("Batal", null)
                .show()
        }

        recyclerView.adapter = keranjangAdapter

        observeViewModel()
        viewModel.fetchKeranjang()
    }

    private fun observeViewModel() {
        viewModel.keranjangList.observe(viewLifecycleOwner) { list ->
            Log.d("KeranjangFragment", "Jumlah item setelah fetch: ${list.size}")

            if (list.isEmpty()) {
                recyclerView.visibility = View.GONE
                checkoutSection.visibility = View.GONE
                emptyLayout.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.VISIBLE
                checkoutSection.visibility = View.VISIBLE
                emptyLayout.visibility = View.GONE

                keranjangAdapter.updateData(list)

                if (justDeleted) {
                    Toast.makeText(requireContext(), "Produk berhasil dihapus", Toast.LENGTH_SHORT).show()
                    justDeleted = false
                }
            }
        }

        viewModel.totalHarga.observe(viewLifecycleOwner) {
            val total = it.toString().toRupiah()
            totalHargaText.text = "Total: $total"
        }

        viewModel.error.observe(viewLifecycleOwner) {
            it?.let {
                Toast.makeText(requireContext(), "Error: $it", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun goToCheckout() {
        val keranjangList = viewModel.keranjangList.value ?: emptyList()

        if (keranjangList.isEmpty()) {
            Toast.makeText(requireContext(), "Keranjang kosong", Toast.LENGTH_SHORT).show()
            return
        }

        val requestList = keranjangList.map {
            RequestCheckoutItem(
                id_produk = it.idProduk ?: "",
                jumlah = it.jumlah ?: 1
            )
        }

        val json = Gson().toJson(requestList)
        val prefs = requireContext().getSharedPreferences("cart", Context.MODE_PRIVATE)
        prefs.edit().putString("keranjang_items", json).apply()

        val intent = Intent(requireContext(), CheckOutActivity::class.java)
        intent.putExtra("SOURCE", "KERANJANG")
        intent.putExtra("id_toko", keranjangList.firstOrNull()?.idToko ?: "T001")
        startActivity(intent)
    }

}
