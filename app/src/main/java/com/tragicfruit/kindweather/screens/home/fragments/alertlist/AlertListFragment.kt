package com.tragicfruit.kindweather.screens.home.fragments.alertlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.tragicfruit.kindweather.R
import com.tragicfruit.kindweather.components.AlertCell
import com.tragicfruit.kindweather.model.WeatherAlert
import com.tragicfruit.kindweather.screens.WFragment
import com.tragicfruit.kindweather.utils.PermissionHelper
import io.realm.RealmResults
import kotlinx.android.synthetic.main.fragment_alert_list.*

class AlertListFragment : WFragment(), AlertListContract.View, AlertCell.Listener {

    override var statusBarColor = R.color.white

    private val presenter = AlertListPresenter(this)
    private lateinit var adapter: AlertListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_alert_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.init()
    }

    override fun initView(alertList: RealmResults<WeatherAlert>) {
        adapter = AlertListAdapter(alertList, this)
        alertListRecyclerView.adapter = adapter
        alertListRecyclerView.layoutManager = LinearLayoutManager(context)

        alertListAllowLocationButton.setOnClickListener {
            presenter.onAllowLocationClicked()
        }
    }

    override fun onAlertClicked(alert: WeatherAlert) {
        val position = adapter.getItemPosition(alert)
        presenter.onAlertClicked(alert, position)
    }

    override fun showAlertDetailScreen(alert: WeatherAlert, position: Int) {
        // TODO: shared element transition
//        val cell = alertListRecyclerView.layoutManager?.findViewByPosition(position) as? AlertCell

        val action = AlertListFragmentDirections.actionAlertsFragmentToAlertDetailFragment(alert.id)
        findNavController().navigate(action)
    }

    override fun requestLocationPermission() {
        requestPermissions(PermissionHelper.FULL_LOCATION, REQUEST_LOCATION_PERMISSION)
    }

    override fun refreshList() {
        adapter.notifyDataSetChanged()

        context?.let {
            alertListAllowLocation.isVisible = !PermissionHelper.hasFullLocationPermission(it)
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.resume()
    }

    override fun onPause() {
        super.onPause()
        presenter.pause()
    }

    companion object {
        private const val REQUEST_LOCATION_PERMISSION = 800
    }

}