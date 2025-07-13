package com.example.dots.fragmentsOrderType

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dots.R
import com.example.dots.activityLoginTrue.CreateAddressActivity
import com.example.dots.adapter.AlamatAdapter
import com.example.dots.models.Alamat
import com.example.dots.viewmodel.AlamatViewModel
import com.google.android.material.card.MaterialCardView

class FragmentDelivery : Fragment() {

    private lateinit var alamatViewModel: AlamatViewModel
    private lateinit var alamatAdapter: AlamatAdapter

    private lateinit var addressRecyclerView: RecyclerView
    private lateinit var addressEmptyCard: View
    private lateinit var loadingOverlay: View

    private lateinit var checkImages: List<ImageView>

    private val addressResultLauncher = registerForActivityResult(
        androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == android.app.Activity.RESULT_OK) {
            alamatViewModel.fetchAlamat()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_delivery, container, false)
        loadingOverlay = view.findViewById(R.id.addressLoadingOverlay)

        // Inisialisasi ViewModel
        alamatViewModel = ViewModelProvider(this)[AlamatViewModel::class.java]

        // Inisialisasi View
        addressRecyclerView = view.findViewById(R.id.addressRecyclerView)
        addressEmptyCard = view.findViewById(R.id.address_empty_card)
        val addAddress = view.findViewById<TextView>(R.id.add_address)

        // Setup RecyclerView
        addressRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        alamatAdapter = AlamatAdapter(
            emptyList(),
            onEditClick = { alamat ->
                val intent = Intent(requireContext(), CreateAddressActivity::class.java).apply {
                    putExtra("EDIT_MODE", true)
                    putExtra("ALAMAT_ID", alamat.idAlamat)
                    putExtra("LABEL", alamat.labelAlamat)
                    putExtra("NAMA", alamat.namaPenerima)
                    putExtra("HP", alamat.noHpPenerima)
                    putExtra("DETAIL", alamat.alamat)
                    putExtra("LAT", alamat.latitude)
                    putExtra("LNG", alamat.longitude)
                }
                addressResultLauncher.launch(intent)
            },
            onDeleteClick = { alamat ->
                showDeleteConfirmationDialog(requireContext(), alamat) {
                    showLoading(true) // Mulai loading
                    alamatViewModel.deleteAlamat(alamat.idAlamat!!)
                }
            },
            onSetUtamaClick = { alamat ->
                showLoading(true) // Mulai loading
                alamat.status = "utama"
                    alamatViewModel.editAlamat(alamat)
                  }
        )


        addressRecyclerView.adapter = alamatAdapter

        // Tambah alamat baru
        addAddress.setOnClickListener {
            val intent = Intent(requireContext(), CreateAddressActivity::class.java)
            addressResultLauncher.launch(intent)
        }

        // Observasi data alamat
        alamatViewModel.alamatList.observe(viewLifecycleOwner) { alamatList ->
            showLoading(false)
            if (!alamatList.isNullOrEmpty()) {
                addressRecyclerView.visibility = View.VISIBLE
                addressEmptyCard.visibility = View.GONE
                alamatAdapter.updateData(alamatList)
            } else {
                addressRecyclerView.visibility = View.GONE
                addressEmptyCard.visibility = View.VISIBLE
            }
        }

        alamatViewModel.error.observe(viewLifecycleOwner) {
            showLoading(false)
            if (it != null) {
                Toast.makeText(requireContext(), "Gagal memuat alamat: $it", Toast.LENGTH_SHORT).show()
            }
        }

        alamatViewModel.fetchAlamat()
        showLoading(true) // Mulai loading


        // ============ TOKO LOGIC TETAP SAMA ============
        val nearestStore1 = view.findViewById<CardView>(R.id.nearest_store1)
        val nearestStore2 = view.findViewById<CardView>(R.id.nearest_store2)
        val nearestStore3 = view.findViewById<CardView>(R.id.nearest_store3)

        val check1 = nearestStore1.findViewById<ImageView>(R.id.check)
        val check2 = nearestStore2.findViewById<ImageView>(R.id.check)
        val check3 = nearestStore3.findViewById<ImageView>(R.id.check)

        checkImages = listOf(check1, check2, check3)
        check1.setOnClickListener { selectStore(0) }
        check2.setOnClickListener { selectStore(1) }
        check3.setOnClickListener { selectStore(2) }

        view.findViewById<TextView>(R.id.storeName).text = getString(R.string.teh_idaman_concat)
        view.findViewById<TextView>(R.id.storeAddress).text = getString(R.string.concat_address)
        view.findViewById<TextView>(R.id.storeDistance).text = getString(R.string._1_5_km)

        val storeName2 = nearestStore2.findViewById<TextView>(R.id.storeName)
        val storeAddress2 = nearestStore2.findViewById<TextView>(R.id.storeAddress)
        val storeDistance2 = nearestStore2.findViewById<TextView>(R.id.storeDistance)

        storeName2.text = getString(R.string.teh_idaman_gejayan)
        storeAddress2.text = getString(R.string.gejayan_address)
        storeDistance2.text = getString(R.string._2_km)


        val storeName3 = nearestStore3.findViewById<TextView>(R.id.storeName)
        val storeAddress3 = nearestStore3.findViewById<TextView>(R.id.storeAddress)
        val storeDistance3 = nearestStore3.findViewById<TextView>(R.id.storeDistance)

        storeName3.text = getString(R.string.teh_idaman_wonosari)
        storeAddress3.text = getString(R.string.wonosari_address)
        storeDistance3.text = getString(R.string._12_km)


        return view
    }

    private fun selectStore(selectedIndex: Int) {
        checkImages.forEachIndexed { index, image ->
            image.setImageResource(if (index == selectedIndex) R.drawable.checked else R.drawable.uncheck)
        }

        val storeName = when (selectedIndex) {
            0 -> "Concat"
            1 -> "Gejayan"
            2 -> "Wonosari"
            else -> "Unknown Store"
        }

        Toast.makeText(requireContext(), "Teh Idaman $storeName", Toast.LENGTH_SHORT).show()
    }

    private fun showDeleteConfirmationDialog(context: Context, alamat: Alamat, onConfirm: () -> Unit) {
        val dialog = com.google.android.material.dialog.MaterialAlertDialogBuilder(context)
            .setTitle("Hapus Alamat?")
            .setMessage("Apakah kamu yakin ingin menghapus alamat ini?\n\nAlamat: ${alamat.alamat}")
            .setPositiveButton("Hapus") { dialog, _ ->
                onConfirm()
                dialog.dismiss()
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(false)
            .create()

        dialog.show()

        // Tambahkan warna tombol
        dialog.getButton(AlertDialog.BUTTON_POSITIVE)?.setTextColor(ContextCompat.getColor(context, R.color.red))
        dialog.getButton(AlertDialog.BUTTON_NEGATIVE)?.setTextColor(ContextCompat.getColor(context, R.color.soft_gray))
    }

    private fun showLoading(show: Boolean) {
          loadingOverlay.visibility = if (show) View.VISIBLE else View.GONE
    }


}
