package com.jacr.gravityapp.controllers.navigation.view;

import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewTreeObserver;

import com.jacr.gravityapp.R;
import com.jacr.gravityapp.controllers.catalog.view.CatalogFragment_;
import com.jacr.gravityapp.controllers.navigation.presenter.MainNavigationPresenter;
import com.jacr.gravityapp.controllers.navigation.presenter.BasicMainNavigationPresenter;
import com.jacr.gravityapp.controllers.navigation.NavigationItem;
import com.jacr.gravityapp.utilities.helpers.ViewHelper;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.ColorRes;
import org.androidannotations.annotations.res.DrawableRes;
import org.androidannotations.annotations.res.StringRes;

import java.util.ArrayList;

@EActivity(R.layout.main_navigation_activity)
public class MainDrawerActivity extends AppCompatActivity implements MainNavigationView {

    private MainNavigationPresenter presenter;

    //<editor-fold desc="View Instances">

    @ViewById(R.id.main_drawer_toolbar)
    Toolbar toolbar;

    //</editor-fold>

    //<editor-fold desc="String Resources">

    @StringRes(R.string.activity_drawer_item_catalog)
    String catalogDrawerItem;

    @StringRes(R.string.activity_drawer_item_exit)
    String exitDrawerItem;

    //</editor-fold>

    //<editor-fold desc="Drawable & Color Resources">

    @DrawableRes(R.drawable.drawer_header_background)
    Drawable headerBackground;

    @DrawableRes(R.drawable.ic_launcher)
    Drawable headerIcon;

    @ColorRes(R.color.app_drawer_text_selected)
    int drawerItemTextColor;

    //</editor-fold>

    @AfterViews
    void init() {
        // Setting up about Action Bar
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setDisplayHomeAsUpEnabled(true);
        }
        // --
        setupDrawer();
        presenter = new BasicMainNavigationPresenter(this);
    }

    //<editor-fold desc="Drawer Panel">

    private void setupDrawer() {
        // Drawer Header
        AccountHeader header = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(headerBackground)
                .withSelectionListEnabled(false)
                .addProfiles(new ProfileDrawerItem().withIcon(headerIcon))
                .build();
        // Creating Items for Drawer
        ArrayList<IDrawerItem> drawerItems = new ArrayList<>();
        drawerItems.add(new PrimaryDrawerItem()
                .withIdentifier(NavigationItem.CATALOG.getId())
                .withName(catalogDrawerItem)
                .withTag(catalogDrawerItem)
                .withSelectedTextColor(drawerItemTextColor));
        drawerItems.add(new PrimaryDrawerItem()
                .withIdentifier(NavigationItem.EXIT.getId())
                .withName(exitDrawerItem)
                .withTag(exitDrawerItem));
        // Building Drawer
        final Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withActionBarDrawerToggle(true)
                .withToolbar(toolbar)
                .withAccountHeader(header)
                .withDrawerItems(drawerItems)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {

                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem drawerItem) {
                        presenter.onTapPanelItem(getItem(drawerItem.getIdentifier()));
                        return false;
                    }

                }).build();
        // Load the default view with respect to the drawer. Thus, we add an observer that notifies
        // when the drawer is rendered
        drawer.getContent().getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        presenter.onLoadPanel();
                        // Remove observer
                        ViewHelper.removeGlobalLayoutListener(drawer.getContent().getViewTreeObserver(), this);
                    }

                });
    }

    private NavigationItem getItem(int itemId) {
        for (NavigationItem item : NavigationItem.values()) {
            if (item.getId() == itemId) {
                return item;
            }
        }
        return null;
    }

    private String getItemName(NavigationItem item) {
        String name = exitDrawerItem;
        if (item.equals(NavigationItem.CATALOG)) {
            name = catalogDrawerItem;
        }
        return name;
    }

    private Fragment getItemFragment(NavigationItem item) {
        Fragment fragment = null;
        if (item.equals(NavigationItem.CATALOG)) {
            fragment = new CatalogFragment_();
        }
        return fragment;
    }

    //</editor-fold>

    // <editor-fold desc="Overrides - Drawer Item">

    @Override
    public void loadNavigationView(NavigationItem item) {
        setTitle(getItemName(item)); // Add Toolbar title
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_drawer_view_fragment, getItemFragment(item))
                .commit();
    }

    @Override
    public void closeAllViews() {
        finish();
    }

    // </editor-fold>

}
