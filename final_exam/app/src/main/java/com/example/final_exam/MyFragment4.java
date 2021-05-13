package com.example.final_exam;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class MyFragment4 extends Fragment implements View.OnClickListener{

    //me里的组件初始化
    public LinearLayout use_mes;
    public TextView phone,plant,set;
    public ImageView icon_img,order_img,money_img,reward_img;
    public String phonenum="";

    public MyFragment4() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_user,container,false);
        init(view);
        return view;
    }

    private void init(View me) {

        use_mes=(LinearLayout)me.findViewById(R.id.user_message);
        use_mes.setOnClickListener(this);
        phone=(TextView)me.findViewById(R.id.phone_numbers);
        phone.setText("用户名："+MainActivity.USER_NAME);
        order_img=(ImageView)me.findViewById(R.id.order);
        order_img.setOnClickListener(this);
        money_img=(ImageView)me.findViewById(R.id.money);
        money_img.setOnClickListener(this);
        reward_img=(ImageView)me.findViewById(R.id.reward);
        reward_img.setOnClickListener(this);
        plant=(TextView)me.findViewById(R.id.plant);
        plant.setOnClickListener(this);
        set=(TextView)me.findViewById(R.id.sets);
        set.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.order:
                Intent order_intent=new Intent(getContext(),OrderActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("phone",phonenum );
                order_intent.putExtras(bundle);
                startActivity(order_intent);
                break;
            //me_money
            case R.id.money:
                Intent money_intent=new Intent(getContext(),MoneyActivity.class);
                Bundle money_bundle=new Bundle();
                money_bundle.putString("phone",phonenum );
                money_intent.putExtras(money_bundle);
                startActivity(money_intent);
                break;
            //me_reward
            case R.id.reward:
                Toast.makeText(getContext(), "你还没有奖学券", Toast.LENGTH_SHORT).show();
                break;
            case R.id.plant:
                Intent plant_intent=new Intent(getContext(),PlantActivity.class);
                startActivity(plant_intent);
                break;
            case R.id.sets:
                Intent set_intent=new Intent(getContext(),SetActivity.class);
                Bundle bundle1 = new Bundle();
                startActivity(set_intent);
            default:
                break;
        }
    }
}
