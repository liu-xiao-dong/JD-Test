package com.sxjs.jd.composition.main.classificationfragment;

import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.jd.R;

/**
 * Created by admin on 2017/3/22.
 */

public class TypeOfGoodsNameAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public TypeOfGoodsNameAdapter(int layoutResId) {
        super(layoutResId);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item ,int position) {
        helper.setText(R.id.goods_type_name,item);
    }
}
