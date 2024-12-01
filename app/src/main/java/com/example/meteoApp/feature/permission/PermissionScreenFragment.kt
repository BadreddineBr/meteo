package com.example.meteoApp.feature.permission

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.meteoApp.core.BaseFragment
import com.example.meteoApp.databinding.FragmentPermissionScreenBinding
import com.example.meteoApp.util.Location.requiredPermission
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PermissionScreenFragment : BaseFragment<FragmentPermissionScreenBinding>() {

    private val appSettingsLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (requiredPermission.all {
                ContextCompat.checkSelfPermission(
                    requireContext(),
                    it
                ) == PackageManager.PERMISSION_GRANTED
            }) {
            findNavController().navigate(
                PermissionScreenFragmentDirections.actionPermissionScreenFragmentToHomeScreenFragment()
            )
        }
    }

    override fun FragmentPermissionScreenBinding.onMain() {
        mtSettings.setOnClickListener {
            appSettingsLauncher.launch(
                Intent().apply {
                    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    data = Uri.fromParts("package", requireContext().packageName, null)
                }
            )
        }
    }

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPermissionScreenBinding =
        FragmentPermissionScreenBinding.inflate(inflater, container, false)
}