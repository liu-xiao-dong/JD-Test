package com.sxjs.jd.composition.main.homefragment;

import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.sxjs.common.base.baseadapter.BaseMultiItemQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseQuickAdapter;
import com.sxjs.common.base.baseadapter.BaseViewHolder;
import com.sxjs.common.widget.autoscrollviewpager.BGABanner;
import com.sxjs.common.widget.imageview.ExpandImageView;
import com.sxjs.common.widget.view_switcher.UpDownViewSwitcher;
import com.sxjs.jd.R;
import com.sxjs.jd.R2;
import com.sxjs.jd.data.Constant;
import com.sxjs.jd.entities.HomeIndex;


/**
 * @author admin 数据绑定未进行详细的数据验证，再实际使用中不可取
 */
public class HomeMultipleRecycleAdapter extends BaseMultiItemQuickAdapter<HomeIndex.ItemInfoListBean, BaseViewHolder> implements BaseQuickAdapter.SpanSizeLookup, BaseQuickAdapter.OnItemChildClickListener {

    private CountDownTimer timer;
    private int maxHasLoadPosition;
    /**
     * 当前position监听
     */
    private PositionChangedListener listener;

    public void setListener(PositionChangedListener listener) {
        this.listener = listener;
    }

    public void resetMaxHasLoadPosition() {
        maxHasLoadPosition = 0;
    }

    public HomeMultipleRecycleAdapter() {
        setSpanSizeLookup(this);
        addItemType(Constant.TYPE_TOP_BANNER, R.layout.homerecycle_item_top_banner);
        addItemType(Constant.TYPE_ICON_LIST, R.layout.homerecycle_item_icon_list);
        addItemType(Constant.TYPE_NEW_USER, R.layout.homerecycle_item_new_user);
        addItemType(Constant.TYPE_JD_BULLETIN, R.layout.homerecycle_item_jd_bulletin);
        addItemType(Constant.TYPE_JD_SPIKE_HEADER, R.layout.homerecycle_item_spike_header);
        addItemType(Constant.TYPE_JD_SPIKE_CONTENT, R.layout.homerecycle_item_spike_content);
        addItemType(Constant.TYPE_SHOW_EVENT_3, R.layout.homerecycle_item_show_event_3);
        addItemType(Constant.TYPE_FIND_GOOD_STUFF, R.layout.homerecycle_item_find_good_stuff);
        addItemType(Constant.TYPE_WIDTH_PROPORTION_211, R.layout.homerecycle_item_type_211);
        addItemType(Constant.TYPE_TITLE, R.layout.homerecycle_item_type_title);
        addItemType(Constant.TYPE_WIDTH_PROPORTION_22, R.layout.homerecycle_item_type_22);
        addItemType(Constant.TYPE_WIDTH_PROPORTION_1111, R.layout.homerecycle_item_type_1111);
        addItemType(Constant.TYPE_MIDDLE_BANNER, R.layout.homerecycle_item_middle_banner);
        addItemType(Constant.TYPE_SHOW_EVENT_FILL_UP, R.layout.homerecycle_item_show_event_1);
        addItemType(Constant.TYPE_FIND_GOOD_SHOP, R.layout.homerecycle_item_type_find_good_shop);
        addItemType(Constant.TYPE_PREFERRED_LIST, R.layout.homerecycle_item_type_perferred_list);
        addItemType(Constant.TYPE_LIVE, R.layout.homerecycle_item_type_live);
        addItemType(Constant.TYPE_RECOMMENDED_WARE, R.layout.homerecycle_item_type_recommented_ware);
    }

    /**
     * 数据绑定未进行详细的数据验证，在实际使用中不可取
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     * @param position
     */
    @Override
    protected void convert(BaseViewHolder helper, HomeIndex.ItemInfoListBean item, int position) {
        if (listener != null) {
            listener.currentPosition(position);
        }
        if (maxHasLoadPosition < position) {
            maxHasLoadPosition = position;
        }

        if ("topBanner".equals(item.itemType) && maxHasLoadPosition <= position) {
            bindTopBannerData(helper, item, position);
        } else if ("iconList".equals(item.itemType) && maxHasLoadPosition <= position) {
            bindIconListData(helper, item, position);
        } else if ("newUser".equals(item.itemType)) {
            bindNewUserData(helper, item, position);
        } else if ("jdBulletin".equals(item.itemType) && maxHasLoadPosition <= position) {
            bindJDBulletinData(helper, item, position);
        } else if ("jdSpikeHeader".equals(item.itemType) && maxHasLoadPosition <= position) {
            bindJDSpikeHeaderData(helper, item, position);
        } else if ("jdSpikeContent".equals(item.itemType)) {
            bindJDSpikeContentData(helper, item, position);
        } else if ("showEvent".equals(item.itemType)) {
            bindShowEventData(helper, item, position);
        } else if ("findGoodStuff".equals(item.itemType)) {
            bindFindGoodStuffData(helper, item, position);
        } else if ("type_211".equals(item.itemType)) {
            bindType211Data(helper, item, position);
        } else if ("type_Title".equals(item.itemType)) {
            bindTypeTitleData(helper, item, position);
        } else if ("type_22".equals(item.itemType)) {
            bindType22Data(helper, item, position);
        } else if ("type_1111".equals(item.itemType)) {
            bindType1111Data(helper, item, position);
        } else if ("type_middleBanner".equals(item.itemType)) {
            bindTypeMiddleBannerData(helper, item, position);
        } else if ("showEventFillUp".equals(item.itemType)) {
            bindShowEventFillUpData(helper, item, position);
        } else if ("findGoodShop".equals(item.itemType)) {
            bindFindGoodShopData(helper, item, position);
        } else if ("preferredList".equals(item.itemType)) {
            bindPreferredListData(helper, item, position);
        } else if ("live".equals(item.itemType)) {
            bindLiveData(helper, item, position);
        } else if ("recommended_ware".equals(item.itemType)) {
            bindRecommendedWareData(helper, item, position);
        }
    }

    private void bindRecommendedWareData(BaseViewHolder helper, HomeIndex.ItemInfoListBean item, int position) {
        ((ExpandImageView) helper.getView(R.id.recommended_img)).setImageURI(item.itemContentList.get(0).imageUrl);
        helper.setText(R.id.recommended_title, item.itemContentList.get(0).itemTitle);
        helper.setText(R.id.recommended_price, item.itemContentList.get(0).itemSubTitle);
    }

    private void bindLiveData(BaseViewHolder helper, HomeIndex.ItemInfoListBean item, int position) {

        ((ExpandImageView) helper.getView(R.id.type_live_item_one_img1)).setImageURI(item.itemContentList.get(0).imageUrl);
        ((ExpandImageView) helper.getView(R.id.type_live_item_two_img1)).setImageURI(item.itemContentList.get(1).imageUrl);
        ((ExpandImageView) helper.getView(R.id.live_icon)).setImageURI(item.itemContentList.get(0).itemBackgroundImageUrl);
        ((ExpandImageView) helper.getView(R.id.live_icon1)).setImageURI(item.itemContentList.get(1).itemBackgroundImageUrl);


        helper.setText(R.id.type_live_item_one_title, item.itemContentList.get(0).itemTitle);
        helper.setText(R.id.type_live_one_sub_title, item.itemContentList.get(0).itemSubTitle);
        //helper.setText(R.id.type_find_good_shop_item_one_shop_name,item.itemContentList.get(0).itemRecommendedLanguage);

        helper.setText(R.id.type_live_item_two_title, item.itemContentList.get(1).itemTitle);
        helper.setText(R.id.type_live_item_two_sub_title, item.itemContentList.get(1).itemSubTitle);
        //helper.setText(R.id.type_find_good_shop_item_two_shop_name,item.itemContentList.get(1).itemRecommendedLanguage);
    }

    private void bindPreferredListData(BaseViewHolder helper, HomeIndex.ItemInfoListBean item, int position) {

        ((ExpandImageView) helper.getView(R.id.type_preferred_list_item_two_img1)).setImageURI(item.itemContentList.get(1).imageUrl);
        ((ExpandImageView) helper.getView(R.id.type_preferred_list_item_two_img2)).setImageURI(item.itemContentList.get(1).itemBackgroundImageUrl);
        ((ExpandImageView) helper.getView(R.id.type_preferred_list_item_one_img1)).setImageURI(item.itemContentList.get(0).imageUrl);
        ((ExpandImageView) helper.getView(R.id.type_preferred_list_item_one_img2)).setImageURI(item.itemContentList.get(0).itemBackgroundImageUrl);


        helper.setText(R.id.type_preferred_list_item_one_title, item.itemContentList.get(0).itemTitle);
        helper.setText(R.id.type_preferred_list_item_two_title, item.itemContentList.get(1).itemTitle);

    }

    private void bindFindGoodShopData(BaseViewHolder helper, HomeIndex.ItemInfoListBean item, int position) {

        ((ExpandImageView) helper.getView(R.id.type_find_good_shop_item_two_img1)).setImageURI(item.itemContentList.get(1).imageUrl);
        ((ExpandImageView) helper.getView(R.id.type_find_good_shop_item_two_img2)).setImageURI(item.itemContentList.get(1).itemBackgroundImageUrl);
        ((ExpandImageView) helper.getView(R.id.type_find_good_shop_item_one_img1)).setImageURI(item.itemContentList.get(0).imageUrl);
        ((ExpandImageView) helper.getView(R.id.type_find_good_shop_item_one_img2)).setImageURI(item.itemContentList.get(0).itemBackgroundImageUrl);
        helper.setText(R.id.type_find_good_shop_item_one_title, item.itemContentList.get(0).itemTitle);
        helper.setText(R.id.type_find_good_shop_item_one_sub_title, item.itemContentList.get(0).itemSubTitle);
        helper.setText(R.id.type_find_good_shop_item_one_shop_name, item.itemContentList.get(0).itemRecommendedLanguage);

        helper.setText(R.id.type_find_good_shop_item_two_title, item.itemContentList.get(1).itemTitle);
        helper.setText(R.id.type_find_good_shop_item_two_sub_title, item.itemContentList.get(1).itemSubTitle);
        helper.setText(R.id.type_find_good_shop_item_two_shop_name, item.itemContentList.get(1).itemRecommendedLanguage);

    }

    private void bindShowEventFillUpData(BaseViewHolder helper, HomeIndex.ItemInfoListBean item, int position) {
        ((ExpandImageView) helper.getView(R.id.show_event_fill_up)).setImageURI(item.itemContentList.get(0).imageUrl);

    }

    private void bindTypeMiddleBannerData(BaseViewHolder helper, final HomeIndex.ItemInfoListBean item, int position) {
        BGABanner banner = helper.getView(R.id.middle_banner);
        banner.setDelegate(new BGABanner.Delegate<View, HomeIndex.ItemInfoListBean.ItemContentListBean>() {
            @Override
            public void onBannerItemClick(BGABanner banner, View itemView, HomeIndex.ItemInfoListBean.ItemContentListBean model, int position) {
                Toast.makeText(itemView.getContext(), "" + item.itemContentList.get(position).clickUrl, Toast.LENGTH_SHORT).show();
            }
        });
        banner.setAdapter(new BGABanner.Adapter<View, HomeIndex.ItemInfoListBean.ItemContentListBean>() {
            @Override
            public void fillBannerItem(BGABanner banner, View itemView, HomeIndex.ItemInfoListBean.ItemContentListBean model, int position) {
                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.type_item_middle_banner_content);
                simpleDraweeView.setImageURI(Uri.parse(model.imageUrl));
            }
        });

        banner.setData(R.layout.homerecycle_middle_banner_content, item.itemContentList, null);
    }

    private void bindType1111Data(BaseViewHolder helper, HomeIndex.ItemInfoListBean item, int position) {
        ((ExpandImageView) helper.getView(R.id.type1111_item_one_img)).setImageURI(item.itemContentList.get(0).imageUrl);
        ((ExpandImageView) helper.getView(R.id.type1111_item_two_img)).setImageURI(item.itemContentList.get(1).imageUrl);
        ((ExpandImageView) helper.getView(R.id.type1111_item_three_img)).setImageURI(item.itemContentList.get(2).imageUrl);
        ((ExpandImageView) helper.getView(R.id.type1111_item_four_img)).setImageURI(item.itemContentList.get(3).imageUrl);
        helper.setText(R.id.type1111_item_one_title, item.itemContentList.get(0).itemTitle);
        helper.setText(R.id.type1111_item_one_sub_title, item.itemContentList.get(0).itemSubTitle);
        helper.setText(R.id.type1111_item_three_title, item.itemContentList.get(2).itemTitle);
        helper.setText(R.id.type1111_item_three_sub_title, item.itemContentList.get(2).itemSubTitle);

        helper.setText(R.id.type1111_item_two_title, item.itemContentList.get(1).itemTitle);
        helper.setText(R.id.type1111_item_two_sub_title, item.itemContentList.get(1).itemSubTitle);
        helper.setText(R.id.type1111_item_four_title, item.itemContentList.get(3).itemTitle);
        helper.setText(R.id.type1111_item_four_sub_title, item.itemContentList.get(3).itemSubTitle);
        setRecommendedLanguage(helper, item, R.id.type1111_item_one_subscript, 0);
        setRecommendedLanguage(helper, item, R.id.type1111_item_two_subscript, 1);
        setRecommendedLanguage(helper, item, R.id.type1111_item_three_subscript, 2);
        setRecommendedLanguage(helper, item, R.id.type1111_item_four_subscript, 3);

    }

    private void bindType22Data(BaseViewHolder helper, HomeIndex.ItemInfoListBean item, int position) {
        ((ExpandImageView) helper.getView(R.id.type22_item_one_img)).setImageURI(item.itemContentList.get(0).imageUrl);
        ((ExpandImageView) helper.getView(R.id.type22_item_two_img)).setImageURI(item.itemContentList.get(1).imageUrl);
        helper.setText(R.id.type22_item_one_title, item.itemContentList.get(0).itemTitle);
        helper.setText(R.id.type22_item_one_sub_title, item.itemContentList.get(0).itemSubTitle);

        helper.setText(R.id.type22_item_two_title, item.itemContentList.get(1).itemTitle);
        helper.setText(R.id.type22_item_two_sub_title, item.itemContentList.get(1).itemSubTitle);

        setRecommendedLanguage(helper, item, R.id.type22_item_one_recommendedLanguage, 0);
        setRecommendedLanguage(helper, item, R.id.type22_item_two_recommendedLanguage, 1);
    }

    /**
     * 填充下标 或 推荐 内容
     *
     * @param helper
     * @param item
     * @param id
     * @param index
     */
    private void setRecommendedLanguage(BaseViewHolder helper, HomeIndex.ItemInfoListBean item, int id, int index) {
        String itemRecommendedLanguage = item.itemContentList.get(index).itemRecommendedLanguage;
        if (TextUtils.isEmpty(itemRecommendedLanguage)) {
            helper.getView(id).setVisibility(View.GONE);
        } else {
            helper.getView(id).setVisibility(View.VISIBLE);
            helper.setText(id, itemRecommendedLanguage);
            if ("loveLife".equals(item.module)) {
                helper.getView(id).setBackgroundResource(R.drawable.home_love_life_subscript_gradient_bg);
            } else if ("enjoyQuality".equals(item.module)) {
                helper.getView(id).setBackgroundResource(R.drawable.home_enjoy_quality_subscript_gradient_bg);
            } else if ("buyFeatures".equals(item.module)) {
                helper.getView(id).setBackgroundResource(R.drawable.home_buy_feature_subscript_gradient_bg);
            }
        }
    }

    private void bindTypeTitleData(BaseViewHolder helper, HomeIndex.ItemInfoListBean item, int position) {
        ((ExpandImageView) helper.getView(R.id.type_title_img)).setImageURI(item.itemContentList.get(0).imageUrl);
        if (!TextUtils.isEmpty(item.itemContentList.get(0).itemTitle)) {
            helper.getView(R.id.type_title_more_ll).setVisibility(View.VISIBLE);
            helper.setText(R.id.type_title_more_text, item.itemContentList.get(0).itemTitle);
            if ("loveLife".equals(item.module) || "findGoodShop".equals(item.module) || "preferredList".equals(item.module)
                    || "live".equals(item.module)) {
                ((ExpandImageView) helper.getView(R.id.type_title_arrow_img)).setImageResource(R.drawable.orange_arrow);
            } else if ("goShopping".equals(item.module)) {
                ((ExpandImageView) helper.getView(R.id.type_title_arrow_img)).setImageResource(R.drawable.go_shopping_rt);
            }
        } else {
            helper.getView(R.id.type_title_more_ll).setVisibility(View.GONE);
        }
    }

    private void bindType211Data(BaseViewHolder helper, HomeIndex.ItemInfoListBean item, int position) {
        helper.setText(R.id.item_one_title, item.itemContentList.get(0).itemTitle);
        helper.setText(R.id.item_two_title, item.itemContentList.get(1).itemTitle);
        helper.setText(R.id.item_three_title, item.itemContentList.get(2).itemTitle);
        helper.setText(R.id.item_one_sub_title, item.itemContentList.get(0).itemSubTitle);
        helper.setText(R.id.item_two_sub_title, item.itemContentList.get(1).itemSubTitle);
        helper.setText(R.id.item_three_sub_title, item.itemContentList.get(2).itemSubTitle);
        helper.setText(R.id.item_one_subscript, item.itemContentList.get(0).itemRecommendedLanguage);
        ((ExpandImageView) helper.getView(R.id.item_one_img)).setImageURI(item.itemContentList.get(0).imageUrl);
        ((ExpandImageView) helper.getView(R.id.item_two_img)).setImageURI(item.itemContentList.get(1).imageUrl);
        ((ExpandImageView) helper.getView(R.id.item_three_img)).setImageURI(item.itemContentList.get(2).imageUrl);
        if ("ranking".equals(item.module)) {
            helper.getView(R.id.item_one_subscript).setBackgroundResource(R.drawable.home_love_life_subscript_gradient_bg);
        } else if ("buyFeatures".equals(item.module)) {
            helper.getView(R.id.item_one_subscript).setBackgroundResource(R.drawable.home_buy_feature_subscript_gradient_bg);
        }
    }

    private void bindFindGoodStuffData(BaseViewHolder helper, HomeIndex.ItemInfoListBean item, int position) {
        ((ExpandImageView) helper.getView(R.id.find_good_stuff_left_img)).setImageURI(item.itemContentList.get(0).imageUrl);
        ((ExpandImageView) helper.getView(R.id.find_good_stuff_right_img)).setImageURI(item.itemContentList.get(1).imageUrl);
    }

    private void bindShowEventData(BaseViewHolder helper, HomeIndex.ItemInfoListBean item, int position) {
        ((ExpandImageView) helper.getView(R.id.show_event_left_img)).setImageURI(item.itemContentList.get(0).imageUrl);
        ((ExpandImageView) helper.getView(R.id.show_event_middle_img)).setImageURI(item.itemContentList.get(1).imageUrl);
        ((ExpandImageView) helper.getView(R.id.show_event_right_img)).setImageURI(item.itemContentList.get(2).imageUrl);
    }

    private void bindJDSpikeContentData(BaseViewHolder helper, HomeIndex.ItemInfoListBean item, int position) {

        if (item.itemContentList == null || item.itemContentList.size() <= 0) return;
        RecyclerView recyclerView = helper.getView(R.id.spike_content_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        SpikeContentAdapter adapter = new SpikeContentAdapter(R.layout.homerecycle_item_spike_content, item.itemContentList);
        recyclerView.setAdapter(adapter);
    }


    private void bindJDSpikeHeaderData(final BaseViewHolder helper, HomeIndex.ItemInfoListBean item, int position) {
        helper.setText(R.id.spike_time_field, item.itemContentList.get(0).itemTitle);
        helper.setText(R.id.spike_header_desc, item.itemContentList.get(0).itemSubTitle);
        String time = item.itemContentList.get(0).itemRecommendedLanguage;
        if (TextUtils.isEmpty(time) || !time.matches("^[0-9]*$")) return;
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        timer = new CountDownTimer(Long.parseLong(time), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long temp = millisUntilFinished / 1000;
                long hours = temp / 3600;
                long minutes = (temp - (3600 * hours)) / 60;
                long seconds = temp - (3600 * hours) - (60 * minutes);
                helper.setText(R.id.spike_time_hour, hours > 9 ? "" + hours : "0" + hours);
                helper.setText(R.id.spike_time_minute, minutes > 9 ? "" + minutes : "0" + minutes);
                helper.setText(R.id.spike_time_seconds, seconds > 9 ? "" + seconds : "0" + seconds);

            }

            @Override
            public void onFinish() {
                helper.setText(R.id.spike_time_hour, "00");
                helper.setText(R.id.spike_time_minute, "00");
                helper.setText(R.id.spike_time_seconds, "00");
            }
        }.start();
    }


    private void bindJDBulletinData(BaseViewHolder helper, final HomeIndex.ItemInfoListBean item, int position) {
        UpDownViewSwitcher viewSwitcher = helper.getView(R.id.home_view_switcher);
        viewSwitcher.setSwitcheNextViewListener(new UpDownViewSwitcher.SwitchNextViewListener() {
            @Override
            public void switchTONextView(View nextView, int index) {
                if (nextView == null) return;
                final String tag = item.itemContentList.get(index % item.itemContentList.size()).itemTitle;
                final String tag1 = item.itemContentList.get(index % item.itemContentList.size()).itemSubTitle;
                ((TextView) nextView.findViewById(R.id.textview)).setText(tag1);
                ((TextView) nextView.findViewById(R.id.switch_title_text)).setText(tag);
                nextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(v.getContext().getApplicationContext(), tag, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        viewSwitcher.setContentLayout(R.layout.switch_view);
    }

    private void bindNewUserData(BaseViewHolder helper, HomeIndex.ItemInfoListBean item, int position) {

        ((ExpandImageView) helper.getView(R.id.new_user_bg_img)).setImageURI(item.itemContentList.get(0).imageUrl);
        ((ExpandImageView) helper.getView(R.id.new_user_red_envelopes)).setImageURI(item.itemContentList.get(1).imageUrl);
        ((ExpandImageView) helper.getView(R.id.new_uer_free_postage)).setImageURI(item.itemContentList.get(2).imageUrl);
        ((ExpandImageView) helper.getView(R.id.new_user_basic_necessities_of_life)).setImageURI(item.itemContentList.get(3).imageUrl);
        ((ExpandImageView) helper.getView(R.id.new_user_packs)).setImageURI(item.itemContentList.get(4).imageUrl);
    }

    private void bindIconListData(BaseViewHolder helper, HomeIndex.ItemInfoListBean item, int position) {
        if (item.itemContentList.size() == 10) {
            helper.setText(R.id.icon_list_one_title, item.itemContentList.get(0).itemTitle);
            helper.addOnClickListener(R.id.icon_list_one);
            helper.addOnClickListener(R.id.icon_list_one_title);
            setOnItemChildClickListener(this);
            helper.setText(R.id.icon_list_two_title, item.itemContentList.get(1).itemTitle);
            helper.setText(R.id.icon_list_three_title, item.itemContentList.get(2).itemTitle);
            helper.setText(R.id.icon_list_four_title, item.itemContentList.get(3).itemTitle);
            helper.setText(R.id.icon_list_five_title, item.itemContentList.get(4).itemTitle);
            helper.setText(R.id.icon_list_six_title, item.itemContentList.get(5).itemTitle);
            helper.setText(R.id.icon_list_seven_title, item.itemContentList.get(6).itemTitle);
            helper.setText(R.id.icon_list_eight_title, item.itemContentList.get(7).itemTitle);
            helper.setText(R.id.icon_list_nine_title, item.itemContentList.get(8).itemTitle);
            helper.setText(R.id.icon_list_ten_title, item.itemContentList.get(9).itemTitle);

            ((ExpandImageView) helper.getView(R.id.icon_list_one)).setImageURI(item.itemContentList.get(0).imageUrl);
            ((ExpandImageView) helper.getView(R.id.icon_list_two)).setImageURI(item.itemContentList.get(1).imageUrl);
            ((ExpandImageView) helper.getView(R.id.icon_list_three)).setImageURI(item.itemContentList.get(2).imageUrl);
            ((ExpandImageView) helper.getView(R.id.icon_list_four)).setImageURI(item.itemContentList.get(3).imageUrl);
            ((ExpandImageView) helper.getView(R.id.icon_list_five)).setImageURI(item.itemContentList.get(4).imageUrl);
            ((ExpandImageView) helper.getView(R.id.icon_list_six)).setImageURI(item.itemContentList.get(5).imageUrl);
            ((ExpandImageView) helper.getView(R.id.icon_list_seven)).setImageURI(item.itemContentList.get(6).imageUrl);
            ((ExpandImageView) helper.getView(R.id.icon_list_eight)).setImageURI(item.itemContentList.get(7).imageUrl);
            ((ExpandImageView) helper.getView(R.id.icon_list_nine)).setImageURI(item.itemContentList.get(8).imageUrl);
            ((ExpandImageView) helper.getView(R.id.icon_list_ten)).setImageURI(item.itemContentList.get(9).imageUrl);

            if (!TextUtils.isEmpty(item.itemContentList.get(0).itemBackgroundImageUrl)) {
                ((ExpandImageView) helper.getView(R.id.icon_list_bg)).setImageURI(item.itemContentList.get(0).itemBackgroundImageUrl);
            } else {
                ((ExpandImageView) helper.getView(R.id.icon_list_bg)).setImageResource(0);
            }

            if (!TextUtils.isEmpty(item.itemContentList.get(0).itemSubTitle)) {
                if ("white".equals(item.itemContentList.get(0).itemSubTitle)) {
                    ((TextView) helper.getView(R.id.icon_list_one_title)).setTextColor(mContext.getResources().getColor(R.color.white));
                    ((TextView) helper.getView(R.id.icon_list_two_title)).setTextColor(mContext.getResources().getColor(R.color.white));
                    ((TextView) helper.getView(R.id.icon_list_three_title)).setTextColor(mContext.getResources().getColor(R.color.white));
                    ((TextView) helper.getView(R.id.icon_list_four_title)).setTextColor(mContext.getResources().getColor(R.color.white));
                    ((TextView) helper.getView(R.id.icon_list_five_title)).setTextColor(mContext.getResources().getColor(R.color.white));
                    ((TextView) helper.getView(R.id.icon_list_six_title)).setTextColor(mContext.getResources().getColor(R.color.white));
                    ((TextView) helper.getView(R.id.icon_list_seven_title)).setTextColor(mContext.getResources().getColor(R.color.white));
                    ((TextView) helper.getView(R.id.icon_list_eight_title)).setTextColor(mContext.getResources().getColor(R.color.white));
                    ((TextView) helper.getView(R.id.icon_list_nine_title)).setTextColor(mContext.getResources().getColor(R.color.white));
                    ((TextView) helper.getView(R.id.icon_list_ten_title)).setTextColor(mContext.getResources().getColor(R.color.white));
                }
            } else {
                ((TextView) helper.getView(R.id.icon_list_one_title)).setTextColor(mContext.getResources().getColor(R.color.black_font));
                ((TextView) helper.getView(R.id.icon_list_two_title)).setTextColor(mContext.getResources().getColor(R.color.black_font));
                ((TextView) helper.getView(R.id.icon_list_three_title)).setTextColor(mContext.getResources().getColor(R.color.black_font));
                ((TextView) helper.getView(R.id.icon_list_four_title)).setTextColor(mContext.getResources().getColor(R.color.black_font));
                ((TextView) helper.getView(R.id.icon_list_five_title)).setTextColor(mContext.getResources().getColor(R.color.black_font));
                ((TextView) helper.getView(R.id.icon_list_six_title)).setTextColor(mContext.getResources().getColor(R.color.black_font));
                ((TextView) helper.getView(R.id.icon_list_seven_title)).setTextColor(mContext.getResources().getColor(R.color.black_font));
                ((TextView) helper.getView(R.id.icon_list_eight_title)).setTextColor(mContext.getResources().getColor(R.color.black_font));
                ((TextView) helper.getView(R.id.icon_list_nine_title)).setTextColor(mContext.getResources().getColor(R.color.black_font));
                ((TextView) helper.getView(R.id.icon_list_ten_title)).setTextColor(mContext.getResources().getColor(R.color.black_font));
            }
        }
    }

    /**
     * 绑定banner数据
     *
     * @param helper
     * @param item
     * @param position
     */
    private void bindTopBannerData(BaseViewHolder helper, final HomeIndex.ItemInfoListBean item, int position) {
        BGABanner banner = helper.getView(R.id.banner);
        banner.setDelegate(new BGABanner.Delegate<View, HomeIndex.ItemInfoListBean.ItemContentListBean>() {
            @Override
            public void onBannerItemClick(BGABanner banner, View itemView, HomeIndex.ItemInfoListBean.ItemContentListBean model, int position) {
                Toast.makeText(itemView.getContext(), "" + item.itemContentList.get(position).clickUrl, Toast.LENGTH_SHORT).show();
            }
        });
        banner.setAdapter(new BGABanner.Adapter<View, HomeIndex.ItemInfoListBean.ItemContentListBean>() {
            @Override
            public void fillBannerItem(BGABanner banner, View itemView, HomeIndex.ItemInfoListBean.ItemContentListBean model, int position) {
                SimpleDraweeView simpleDraweeView = (SimpleDraweeView) itemView.findViewById(R.id.sdv_item_fresco_content);
                simpleDraweeView.setImageURI(Uri.parse(model.imageUrl));
            }
        });

        banner.setData(R.layout.homerecycle_top_banner_content, item.itemContentList, null);
    }


    @Override
    public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
        return mData.get(position).getSpanSize();
    }


    @Override
    public boolean onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        int id = view.getId();
        if(id == R.id.icon_list_one){
            ARouter.getInstance().build("/test1/activity").navigation(view.getContext());
        }
        return false;
    }
}


