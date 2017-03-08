package com.example.jammy.ReserveSystem.menu;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.jammy.ReserveSystem.R;
import com.example.jammy.ReserveSystem.adapter.CarAdapter;
import com.example.jammy.ReserveSystem.adapter.MenuRecycleViewAdapter;
import com.example.jammy.ReserveSystem.base.BaseFragment;
import com.example.jammy.ReserveSystem.bean.Dish;
import com.example.jammy.ReserveSystem.settle.SettleActivity;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.widget.QTabView;

/**
 * Created by Jammy on 2017/1/16.
 */

public class MenuFragment extends BaseFragment implements MenuContract.View, MenuRecycleViewAdapter.ChangeToCar, CarAdapter.ChangeFromCar {
    @BindView(R.id.tablayout)
    VerticalTabLayout tablayout;

    View bottomSheet;
    RecyclerView rvSelected;
    CarAdapter carAdapter;
    List<Dish> selectedList;

    MenuPresenter presenter;
    QTabView tabViewMenu;
    QTabView tabViewDessert;
    QTabView tabViewDrink;
    @BindView(R.id.rv)
    RecyclerView rv;

    Map<String, Integer> mapCar = new HashMap<>();
    Map<String, Integer> mapSelect = new HashMap<>();

    MenuRecycleViewAdapter adapter;
    @BindView(R.id.car)
    RelativeLayout rl_car;
    @BindView(R.id.bottomSheetLayout)
    BottomSheetLayout bottomSheetLayout;

    int count = 0;
    @BindView(R.id.tvCount)
    TextView tvCount;
    @BindView(R.id.tvSubmit)
    TextView tvSubmit;
    @BindView(R.id.sw_fresh)
    SwipeRefreshLayout swFresh;

    int sum = 0;
    @BindView(R.id.tvCost)
    TextView tvCost;

    @Override
    protected int bindContentViewId() {
        return R.layout.fragment_menu;
    }

    @Override
    protected void initView() {
        swFresh.setRefreshing(true);
        swFresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getDish();
            }
        });
        presenter = new MenuPresenter();
        presenter.setVM(this, new MenuModel());
        adapter = new MenuRecycleViewAdapter(R.layout.item_dish, new ArrayList<Dish>());
        adapter.setMap(mapSelect);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);

        rv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                showToast("11111");
            }
        });

        selectedList = new ArrayList<>();
        initTab();
        initDish();
        initDish();
        bottomSheet = createBottomSheetView();
    }

    private void initDish() {
        presenter.getDish();
    }

    public void initTab() {
        tabViewMenu = new QTabView(getContext());
        tabViewDessert = new QTabView(getContext());
        tabViewDrink = new QTabView(getContext());
        QTabView.TabTitle.Builder builder = new QTabView.TabTitle.Builder(getActivity()).setTextSize(15).setContent("店长推荐").setTextColor(Color.rgb(111, 111, 111), Color.rgb(48, 63, 159));
        QTabView.TabTitle title = builder.build();
        tabViewMenu.setTitle(title);
        tabViewDessert.setTitle(builder.setContent("甜品").build());
        tabViewDrink.setTitle(builder.setContent("饮料").build());
        tablayout.addTab(tabViewMenu);
        tablayout.addTab(tabViewDessert);
        tablayout.addTab(tabViewDrink);
        adapter.setListener(this);
    }

    @Override
    public void getDishSuccess(List<Dish> list) {
        adapter.setNewData(list);
        swFresh.setRefreshing(false);
    }

    @Override
    public void getDishFail(String errorCode) {
        Toast.makeText(getContext(), "获取菜单失败", Toast.LENGTH_LONG).show();
        swFresh.setRefreshing(false);
    }

    private View createBottomSheetView() {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.layout_bottom_sheet, (ViewGroup) getActivity().getWindow().getDecorView(), false);
        rvSelected = (RecyclerView) view.findViewById(R.id.selectRecyclerView);
        rvSelected.setLayoutManager(new LinearLayoutManager(getContext()));
        carAdapter = new CarAdapter(R.layout.item_car, selectedList);
        carAdapter.setMap(mapCar);
        carAdapter.setListener(this);
        rvSelected.setAdapter(carAdapter);
        return view;
    }


    @OnClick({R.id.bottom, R.id.tvSubmit})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottom:
                if (bottomSheet == null) {
                    bottomSheet = createBottomSheetView();
                }
                if (bottomSheetLayout.isSheetShowing()) {
                    bottomSheetLayout.dismissSheet();
                } else {
                    if (selectedList.size() != 0) {
                        bottomSheetLayout.showWithSheetView(bottomSheet);
                    }
                }
                break;
            case R.id.tvSubmit:
                Gson gson = new Gson();
                //TODO:去到结算界面,记得传参过去，包括map和selectList
                Intent intent = new Intent(getActivity(), SettleActivity.class);
                String listJson = gson.toJson(selectedList);
                intent.putExtra("selList", listJson);
                ////:// TODO: 2017/3/2 如何将Map 传递过去并传到服务器
                String mapJson = gson.toJson(mapCar);
                intent.putExtra("mapJson", mapJson);
                startActivity(intent);
                break;
        }

    }


    @Override
    public void addItem(Dish dish) {
        if (mapCar.get(dish.getName()) == null) {
            mapCar.put(dish.getName(), 1);
            selectedList.add(dish);
        } else {
            mapCar.put(dish.getName(), mapCar.get(dish.getName()) + 1);
        }
        carAdapter.notifyDataSetChanged();
        count++;
        tvCount.setText(String.valueOf(count));
        tvSubmit.setVisibility(View.VISIBLE);
        sum += dish.getPrice();
        tvCost.setText("￥ "+String.valueOf(sum));
    }

    @Override
    public void delItem(Dish dish) {
        if (mapCar.get(dish.getName()) == 1) {
            mapCar.remove(dish.getName());
            selectedList.remove(dish);
        } else {
            mapCar.put(dish.getName(), mapCar.get(dish.getName()) - 1);
        }
        carAdapter.notifyDataSetChanged();
        count--;
        tvCount.setText(String.valueOf(count));
        if (count == 0) {
            tvSubmit.setVisibility(View.GONE);
            bottomSheetLayout.dismissSheet();
        }
        sum -= dish.getPrice();
        tvCost.setText("￥ "+String.valueOf(sum));
    }

    @Override
    public void addDish(Dish dish) {
        mapSelect.put(dish.getName(), mapSelect.get(dish.getName()) + 1);
        adapter.notifyDataSetChanged();
        count++;
        tvCount.setText(String.valueOf(count));
        tvSubmit.setVisibility(View.VISIBLE);
        sum += dish.getPrice();
        tvCost.setText("￥ "+String.valueOf(sum));
    }

    @Override
    public void removeDish(Dish dish) {
        if (mapSelect.get(dish.getName()) == 1) {
            mapSelect.remove(dish.getName());
        } else {
            mapSelect.put(dish.getName(), mapSelect.get(dish.getName()) - 1);
        }
        adapter.notifyDataSetChanged();
        count--;
        tvCount.setText(String.valueOf(count));
        if (count == 0) {
            tvSubmit.setVisibility(View.GONE);
            bottomSheetLayout.dismissSheet();
        }
        sum -= dish.getPrice();
        tvCost.setText("￥ "+String.valueOf(sum));
    }

}
