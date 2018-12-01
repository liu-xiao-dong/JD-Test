package com.sxjs.jd.composition.main.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.example.app_common.service.ITestService;
import com.sxjs.common.base.BaseFragment;
import com.sxjs.jd.R;

/**
 * @author liuxiaodong
 * @date 2018/12/1
 * @description
 */
public class MyFragment extends BaseFragment {

    private TextView textView;

    public static MyFragment newInstance() {
        return new MyFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = view.findViewById(R.id.serviceText);
        view.findViewById(R.id.testService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ITestService service = ARouter.getInstance().navigation(ITestService.class);
                if(service != null){
                    String testPackageName = service.getTestPackageName();
                    textView.setText(testPackageName);
                }else{
                    Toast.makeText(getContext(),"该服务所在模块未参加编译",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
