package com.example.randomuserapp.model.view

import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.randomuserapp.R
import com.example.randomuserapp.UserApp
import com.example.randomuserapp.databinding.FragmentUserListBinding
import com.example.randomuserapp.databinding.UserListContentBinding
import com.example.randomuserapp.model.retrofit.convert
import com.example.randomuserapp.model.room.User
import kotlinx.coroutines.*

class UserListFragment : Fragment() {

    private val unhandledKeyEventListenerCompat =
        ViewCompat.OnUnhandledKeyEventListenerCompat { v, event ->
            if (event.keyCode == KeyEvent.KEYCODE_Z && event.isCtrlPressed) {
                Toast.makeText(
                    v.context,
                    "Undo (Ctrl + Z) shortcut triggered",
                    Toast.LENGTH_LONG
                ).show()
                true
            } else if (event.keyCode == KeyEvent.KEYCODE_F && event.isCtrlPressed) {
                Toast.makeText(
                    v.context,
                    "Find (Ctrl + F) shortcut triggered",
                    Toast.LENGTH_LONG
                ).show()
                true
            }
            false
        }

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.addOnUnhandledKeyEventListener(view, unhandledKeyEventListenerCompat)

        val recyclerView: RecyclerView = binding.userList
        val itemDetailFragmentContainer: View? = view.findViewById(R.id.item_detail_nav_container)

        val onClickListener = View.OnClickListener { itemView ->
            val user = itemView.tag as User
            val bundle = Bundle()
            bundle.putLong(
                UserDetailFragment.ARG_USER_ID,
                user.id
            )
            if (itemDetailFragmentContainer != null) {
                itemDetailFragmentContainer.findNavController()
                    .navigate(R.id.fragment_item_detail, bundle)
            } else {
                itemView.findNavController().navigate(R.id.show_item_detail, bundle)
            }
        }

        val onContextClickListener = View.OnContextClickListener { v ->
            val user = v.tag as User
            Toast.makeText(
                v.context,
                "Context click of item " + user.id,
                Toast.LENGTH_LONG
            ).show()
            true
        }
        setupRecyclerView(recyclerView, onClickListener, onContextClickListener)
    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        onClickListener: View.OnClickListener,
        onContextClickListener: View.OnContextClickListener
    ) {
        runBlocking(Dispatchers.IO) {
            val response = UserApp.retrofit.getUsers(50).execute()
            response.body()
                ?.convert()
                ?.forEach { UserApp.database.userDao().insert(it) }
        }

        val usersLocal = GlobalScope.async(Dispatchers.IO) {
            UserApp.database.userDao().getAll()
        }
        GlobalScope.launch(Dispatchers.Main) {
            recyclerView.adapter = SimpleItemRecyclerViewAdapter(
                //TODO change!!!!!
                usersLocal.await(),
                onClickListener,
                onContextClickListener
            )
        }

    }

    class SimpleItemRecyclerViewAdapter(
        private val values: List<User>,
        private val onClickListener: View.OnClickListener,
        private val onContextClickListener: View.OnContextClickListener
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val binding =
                UserListContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val user = values[position]
            holder.contentView.text = "${user.firstName} ${user.lastName}"

            with(holder.itemView) {
                tag = user
                setOnClickListener(onClickListener)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    setOnContextClickListener(onContextClickListener)
                }
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(binding: UserListContentBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val contentView: TextView = binding.content
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}