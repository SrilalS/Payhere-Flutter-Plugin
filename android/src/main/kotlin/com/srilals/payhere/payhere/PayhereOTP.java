package com.srilals.payhere.payhere;

import android.app.Activity;
import android.content.Intent;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import io.flutter.Log;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.ActivityResultListener;
import lk.payhere.androidsdk.PHConfigs;
import lk.payhere.androidsdk.PHConstants;
import lk.payhere.androidsdk.PHMainActivity;
import lk.payhere.androidsdk.PHResponse;
import lk.payhere.androidsdk.model.InitRequest;
import lk.payhere.androidsdk.model.Item;
import lk.payhere.androidsdk.model.StatusResponse;

public class PayhereOTP implements ActivityResultListener {

    private final Activity activity;
    private final MethodChannel channel;
    private Result pendingResult;
    private Map<String, Object> pendingReply;
    final Gson gson = new Gson();

    public PayhereOTP(Activity activity, MethodChannel channel) {
        this.activity = activity;
        this.channel = channel;
    }


    void onTimePayment(Result result, String MID, String MSecret, String Currency, double Amount, String OrderID, String ItemDesc, String CM1, String CM2, String FName, String LName, String Email, String Phone, String Address, String City, String Country, String DeliveryAddress, String DeliveryCity, String DeliveryCountry, int Quantity, String MODE) {
        this.pendingResult = result;

        InitRequest IR = new InitRequest();
        IR.setMerchantId(MID);
        IR.setMerchantSecret(MSecret);
        IR.setCurrency(Currency);
        IR.setAmount(Amount);
        IR.setOrderId(OrderID);
        IR.setItemsDescription(ItemDesc);
        IR.setCustom1(CM1);
        IR.setCustom2(CM2);
        IR.getCustomer().setFirstName(FName);
        IR.getCustomer().setLastName(LName);
        IR.getCustomer().setEmail(Email);
        IR.getCustomer().setPhone(Phone);
        IR.getCustomer().getAddress().setAddress(Address);
        IR.getCustomer().getAddress().setCity(City);
        IR.getCustomer().getAddress().setCountry(Country);
        //OPT
        IR.getCustomer().getDeliveryAddress().setAddress(DeliveryAddress);
        IR.getCustomer().getDeliveryAddress().setCity(DeliveryCity);
        IR.getCustomer().getDeliveryAddress().setCountry(DeliveryCountry);
        IR.getItems().add(new Item(null, ItemDesc, Quantity, Amount));



        Intent intent = new Intent(activity, PHMainActivity.class);
        intent.putExtra(PHConstants.INTENT_EXTRA_DATA, IR);

        PHConfigs.setBaseUrl(PHConfigs.SANDBOX_URL);
        if (MODE.equals("PRODUCTION")){
            PHConfigs.setBaseUrl(PHConfigs.LIVE_URL);
        }
        activity.startActivityForResult(intent, 456); //unique request ID like private final static int PAYHERE_REQUEST = 11010;
    }


    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        HashMap<String, Object> map = new HashMap<>();
        if (requestCode == 456 && data != null && data.hasExtra(PHConstants.INTENT_EXTRA_RESULT)) {
            PHResponse<StatusResponse> response = (PHResponse<StatusResponse>) data.getSerializableExtra(PHConstants.INTENT_EXTRA_RESULT);

            if (response.isSuccess()){
                map.put("status","SUCCESS");
                map.put("code","1");
                pendingResult.success(map);
                Log.d("TAG","SUCCESS");
                channel.invokeMethod("Result",map);
            } else {
                map.put("status","ERROR");
                map.put("code","0");
                Log.d("TAG","{ERROR}");
                pendingResult.success(map);
                channel.invokeMethod("Result",map);

            }
            return true;
        } else {
            map.put("status","CANCELED");
            map.put("code","0");
            pendingResult.success(map);
            Log.d("TAG","{USER CANCELED}");
            channel.invokeMethod("Result",map);
            return true;
        }
    }

}
