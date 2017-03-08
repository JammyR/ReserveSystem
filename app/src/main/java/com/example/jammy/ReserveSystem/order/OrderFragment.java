package com.example.jammy.ReserveSystem.order;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.jammy.ReserveSystem.R;
import com.example.jammy.ReserveSystem.adapter.OrderAdapter;
import com.example.jammy.ReserveSystem.base.BaseFragment;
import com.example.jammy.ReserveSystem.base.LoadMoreView;
import com.example.jammy.ReserveSystem.base.SpaceItemDecoration;
import com.example.jammy.ReserveSystem.bean.Order;
import com.example.jammy.ReserveSystem.orderDetail.OrderDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Jammy on 2017/1/16.
 */

public class OrderFragment extends BaseFragment implements OrderContract.View {

    OrderAdapter adapter;
    OrderPresenter presenter;
    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.view_fresh)
    SwipeRefreshLayout viewFresh;

    @Override
    protected int bindContentViewId() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initView() {
        presenter = new OrderPresenter();
        presenter.setVM(this, new OrderModel());
        adapter = new OrderAdapter(R.layout.item_order, new ArrayList<Order>(), getContext());
        adapter.setEmptyView(LayoutInflater.from(getContext()).inflate(R.layout.order_empty, null));
        adapter.setLoadMoreView(new LoadMoreView());

        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        rv.setAdapter(adapter);
        rv.addItemDecoration(new SpaceItemDecoration(30));
        presenter.getOrderList();
        viewFresh.setRefreshing(true);
        viewFresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.getOrderList();
            }
        });
        rv.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(getContext(), OrderDetailActivity.class);
                intent.putExtra("order", (Order)adapter.getData().get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public void getOrderListSuccess(List<Order> list) {
        if(list.size()==10){
            adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {
                    presenter.loadMore();
                }
            });
        }
        adapter.setNewData(list);
        viewFresh.setRefreshing(false);
    }

    @Override
    public void getOrderListFail(String errorCode) {
        showFail(errorCode);
        viewFresh.setRefreshing(false);
    }

    @Override
    public void loadMoreSuccess(List<Order> list) {
        if (list.size()!=0) {
            adapter.getData().addAll(list);
            adapter.notifyDataSetChanged();
            adapter.loadMoreComplete();
        }else{
            adapter.loadMoreEnd();
        }
    }

    @Override
    public void loadMoreFail(String errorCode) {
        showFail(errorCode);
        adapter.loadMoreFail();
    }

}
