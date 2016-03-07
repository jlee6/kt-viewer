package com.jlee.mobile.viewer.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.jlee.mobile.viewer.R
import com.jlee.mobile.viewer.ui.fragment.StreamListFragment
import com.jlee.mobile.viewer.ui.fragment.StreamViewFragment

class MainActivity : AppCompatActivity() {
    var menuList: MenuItem? = null
    var menuView: MenuItem? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Stream Viewer"
        makeTransaction(R.id.action_viewer)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.stream_menu, menu)

        menuView = menu.findItem(R.id.action_viewer)
        menuList = menu.findItem(R.id.action_list)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_viewer, R.id.action_list -> {
                makeTransaction(item.itemId)
                true }
            else ->
                super.onOptionsItemSelected(item)
        }
    }

    private fun makeTransaction(fragId: Int) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container,
                        when (fragId) {
                            R.id.action_list -> {
                                menuList?.setVisible(false)
                                menuView?.setVisible(true)
                                StreamListFragment()
                            }
                            else -> {
                                menuList?.setVisible(true)
                                menuView?.setVisible(false)
                                StreamViewFragment()
                            }
                        })
                .commit()
    }
}
