package com.dhabasoft.navigationexample.ui.deeplink

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dhabasoft.navigationexample.R
import com.dhabasoft.navigationexample.ui.main.MainActivity
import com.dhabasoft.navigationexample.ui.main.MainViewModel

/**
 * created by Dhaba
 */
class DeeplinkFragment : Fragment() {
    private val sharedViewModel: MainViewModel by lazy {
        ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_deeplink, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val textView = view.findViewById<TextView>(R.id.text)
        textView.text = arguments?.getString("myarg")

        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        if(findNavController().graph.startDestination != findNavController().currentDestination?.id) {
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(true)
            toolbar.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
        else {
            (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(false)
            (activity as AppCompatActivity).supportActionBar?.setDisplayShowHomeEnabled(false)
        }

        val notificationButton = view.findViewById<Button>(R.id.send_notification_button)
        notificationButton.setOnClickListener {
            val editArgs = view.findViewById<EditText>(R.id.args_edit_text)
            val args = Bundle()
            args.putString("myarg", editArgs.text.toString())

            val deeplink = findNavController().createDeepLink()
                    .setDestination(if(sharedViewModel.position != MainActivity.POSITION_HOME) R.id.fragmentNotificationsDeeplink else R.id.fragmentDeeplink)
                    .setArguments(args)
                    .createPendingIntent()

            val notificationManager =
                    context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationManager.createNotificationChannel(
                    NotificationChannel(
                        "deeplink", "Deep Links", NotificationManager.IMPORTANCE_HIGH)
                )
            }

            val builder = NotificationCompat.Builder(
                    requireContext(), "deeplink")
                    .setContentTitle("Navigation ${editArgs.text}")
                    .setContentText("Deep link to Android")
                    .setSmallIcon(R.drawable.ic_baseline_chat_24)
                    .setContentIntent(deeplink)
                    .setAutoCancel(true)
            notificationManager.notify(0, builder.build())
        }
    }
}