package com.example.dots.fragmentsLoggedIn

import android.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dots.R
import com.example.dots.adapter.FavoritAdapter
import com.example.dots.viewmodel.FavoritViewModel

class LoggedInFavoriteFragment : Fragment() {

    private lateinit var favoritViewModel: FavoritViewModel
    private lateinit var favoritRecyclerView: RecyclerView
    private lateinit var emptyLayout: LinearLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_logged_in_favorite, container, false)

        favoritRecyclerView = view.findViewById(R.id.favoritRecyclerView)
        favoritRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        emptyLayout = view.findViewById(R.id.emptyLayout)

        favoritViewModel = ViewModelProvider(this)[FavoritViewModel::class.java]

        favoritViewModel.favoritList.observe(viewLifecycleOwner) { favoritList ->
            favoritList?.let { it ->
                if (favoritList.isNotEmpty()) {
                    favoritRecyclerView.visibility = View.VISIBLE
                    emptyLayout.visibility = View.GONE
                } else {
                    favoritRecyclerView.visibility = View.GONE
                    emptyLayout.visibility = View.VISIBLE
                }

                val adapter = FavoritAdapter(it,
                      onItemClick = { favorit ->
                    Toast.makeText(requireContext(), "Klik: ${favorit.namaProduk}", Toast.LENGTH_SHORT).show()
                  },
                  onDeleteClick = { favorit ->
                    AlertDialog.Builder(requireContext())
                      .setTitle("Hapus Favorit")
                      .setMessage("Yakin ingin menghapus ${favorit.namaProduk} dari daftar favorit?")
                      .setPositiveButton("Ya") { _, _ ->
                        favoritViewModel.hapusFavorit(favorit.idProduk ?: "")
                        Toast.makeText(requireContext(), "Produk dihapus dari favorit", Toast.LENGTH_SHORT).show()
                      }
                      .setNegativeButton("Batal", null)
                      .show()
                  }
                )


                favoritRecyclerView.adapter = adapter
            }
        }

        favoritViewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            errorMessage?.let {
                Toast.makeText(requireContext(), "Error: $it", Toast.LENGTH_SHORT).show()
            }
        }

        favoritViewModel.fetchFavorit()



        return view
    }
}
