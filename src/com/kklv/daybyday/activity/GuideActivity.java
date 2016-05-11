package com.kklv.daybyday.activity;

import java.util.ArrayList;

import com.kklv.daybyday.R;
import com.kklv.daybyday.R.drawable;
import com.kklv.daybyday.R.id;
import com.kklv.daybyday.R.layout;
import com.kklv.daybyday.adapter.ViewPagerAdapter;
import com.kklv.daybyday.utils.SharedPreferencesUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * 引导页
 * @author lvzhendong
 * @email lvzhendong1993@gmail.com
 *
 */
public class GuideActivity extends Activity{
	private RelativeLayout mRl;	//整个界面
	private ViewPager mGuideVP;
	private LinearLayout mDotsLL;	//小红点
	private Button mEnterBtn;	//立即进入按钮
	
	private ArrayList<View> viewList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guide);
		
		bindId();
		showOrJump();
		initPager();
		
		mGuideVP.setAdapter(new ViewPagerAdapter(viewList));
		mGuideVP.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				for(int i=0;i<mDotsLL.getChildCount();i++){
					if(i==arg0){
						mDotsLL.getChildAt(i).setSelected(true);
					}else{
						mDotsLL.getChildAt(i).setSelected(false);
					}
				}
				//在最后一页显示“立即进入”按钮
				if(arg0==mDotsLL.getChildCount()-1){
					mEnterBtn.setVisibility(View.VISIBLE);
				}else{
					mEnterBtn.setVisibility(View.GONE);
				}
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		//跳转到主界面
		mEnterBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				jumpToMainActivity();
			}
		});
	}
	/**
	 * 显示引导页，或者跳转到主界面
	 */
	private void showOrJump(){
		if(!isFirstInstall()){
			jumpToMainActivity();
		}else{
			mRl.setVisibility(View.VISIBLE);
		}
	}
	/**
	 * 应用是否第一次安装
	 * @return
	 */
	private boolean isFirstInstall(){
		
		if(!SharedPreferencesUtils.contains(this, SharedPreferencesUtils.FIRST_INSTALL) 
				|| "".equals(SharedPreferencesUtils.get(this, SharedPreferencesUtils.FIRST_INSTALL, ""))){
			SharedPreferencesUtils.put(this, SharedPreferencesUtils.FIRST_INSTALL, 
					SharedPreferencesUtils.FIRST_INSTALL);
			return true;
		}
		return false;
	}
	
	private void bindId(){
		mRl=(RelativeLayout) findViewById(R.id.rl_guide);
		mGuideVP=(ViewPager) findViewById(R.id.vp_guide);
		mDotsLL=(LinearLayout) findViewById(R.id.ll_guide);
		mEnterBtn=(Button) findViewById(R.id.ibtn_guide);
	}
	
	private void initPager(){
		viewList=new ArrayList<View>();
		int[] backgroudIamges=new int[]{R.drawable.guide1,R.drawable.guide2,
				R.drawable.guide3,R.drawable.guide4};
		int[] textImages=new int[]{R.drawable.guide_text1,R.drawable.guide_text2,
				R.drawable.guide_text3,R.drawable.guide_text4
		};
		for(int i=0;i<backgroudIamges.length;i++){
			viewList.add(initBackView(backgroudIamges[i], textImages[i]));
		}
		initDots(backgroudIamges.length);
	}
	
	/**
	 * 初始化导航圆点
	 * @param count 小红点个数
	 */
	private void initDots(int count){
		for(int i=0;i<count;i++){
			mDotsLL.addView(initDot());
		}
	}
	/**
	 * 生成一个点
	 * @return
	 */
	private View initDot(){
		return LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_dot, null);
	}
	/**
	 * 初始化背景
	 * @param backgroundId
	 * @param textId
	 * @return
	 */
	private View initBackView(int backgroundId,int textId){
		View view=LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_guide_rl, null);
		ImageView imageIV=(ImageView) view.findViewById(R.id.iv_background_guide);
		ImageView textIV=(ImageView) view.findViewById(R.id.iv_text_guide);	//背景上面的文字
		imageIV.setImageResource(backgroundId);
		textIV.setImageResource(textId);
		return view;
	}
	
	private void jumpToMainActivity(){
		startActivity(new Intent(this,MainActivity.class));
		finish();
	}
}
