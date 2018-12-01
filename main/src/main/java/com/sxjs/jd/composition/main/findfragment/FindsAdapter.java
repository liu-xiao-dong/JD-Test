package com.sxjs.jd.composition.main.findfragment;

import android.view.View;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.jd.entities.FindsBean;
import com.sxjs.jd.R;

/**
 * Created by admin on 2017/3/22.
 */

public class FindsAdapter extends BaseQuickAdapter<FindsBean.ContentBean,BaseViewHolder> {

    public FindsAdapter(int layoutResId) {
        super(layoutResId);
    }


    @Override
    protected void convert(BaseViewHolder helper, final FindsBean.ContentBean bean ,int position) {
        helper.setText(R.id.title_text,bean.title);
        helper.setText(R.id.content_text,bean.summary);
        helper.setText(R.id.author_name , bean.authorName);
        helper.setText(R.id.time_text , bean.showTime);
        helper.setText(R.id.page_view_count , ""+bean.pageView);
        SimpleDraweeView simpleDraweeView = helper.getView(R.id.content_img);
        SimpleDraweeView authorImg = helper.getView(R.id.author_img);
        simpleDraweeView.setImageURI(bean.indexImage);
        authorImg.setImageURI(bean.authorPic);
        helper.addOnClickListener(R.id.find_item_layout);

        setOnItemChildClickListener(new OnItemChildClickListener() {
            @Override
            public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(mContext, "第"+position+"页 ", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }


}
