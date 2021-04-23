package io.geektech.myrepeat;

import io.geektech.myrepeat.ui.home.HomeAdapter.HomeModel;

public interface OnClickItemRecyclerView {
    void onClick(int pos, HomeModel model);
    void onLongClick(int pos, HomeModel model);
}
