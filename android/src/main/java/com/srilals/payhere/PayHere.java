package com.srilals.payhere;

import android.app.Activity;
import android.content.Intent;

import java.util.HashMap;

import io.flutter.Log;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.ActivityResultListener;
import lk.payhere.androidsdk.PHConfigs;
import lk.payhere.androidsdk.PHConstants;
import lk.payhere.androidsdk.PHMainActivity;
import lk.payhere.androidsdk.PHResponse;
import lk.payhere.androidsdk.model.InitRequest;
import lk.payhere.androidsdk.model.StatusResponse;

public class PayHere implements ActivityResultListener {


    private final Activity activity;
    private final MethodChannel channel;
    private Result pendingResult;
    private final int rCode = 11011011;

    public PayHere(Activity activity, MethodChannel channel) {
        this.activity = activity;
        this.channel = channel;
    }


    void oneTimePayment(Result result, String MID, String MSecret, String NotifyUrl, String Currency, double Amount, String OrderID, String ItemDesc, String CM1, String CM2, String FName, String LName, String Email, String Phone, String Address, String City, String Country, String DeliveryAddress, String DeliveryCity, String DeliveryCountry, String MODE) {
        this.pendingResult = result;
        InitRequest IR = new InitRequest();
        IR.setMerchantId(MID);
        IR.setMerchantSecret(MSecret);
        IR.setNotifyUrl(NotifyUrl);

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
        if (DeliveryAddress != null){
            IR.getCustomer().getDeliveryAddress().setAddress(DeliveryAddress);
        }

        if (DeliveryCity != null){
            IR.getCustomer().getDeliveryAddress().setCity(DeliveryCity);
        }

        if (DeliveryCountry != null){
            IR.getCustomer().getDeliveryAddress().setCountry(DeliveryCountry);
        }

        Intent intent = new Intent(activity, PHMainActivity.class);
        intent.putExtra(PHConstants.INTENT_EXTRA_DATA, IR);

        PHConfigs.setBaseUrl(PHConfigs.SANDBOX_URL);
        if (MODE.equals("PRODUCTION")){
            PHConfigs.setBaseUrl(PHConfigs.LIVE_URL);
        }
        activity.startActivityForResult(intent, this.rCode);
    }

    void recurringPayment(Result result, String MID, String MSecret, String NotifyUrl, String Currency, double Amount, String OrderID, String ItemDesc, String CM1, String CM2, String FName, String LName, String Email, String Phone, String Address, String City, String Country, String Recurrence, String Duration, double StartUpFee, String MODE){
        this.pendingResult = result;

        InitRequest IR = new InitRequest();
        IR.setMerchantId(MID);
        IR.setMerchantSecret(MSecret);
        IR.setNotifyUrl(NotifyUrl);

        IR.setCurrency(Currency);
        IR.setAmount(Amount);

        //Specific to RP
        IR.setRecurrence(Recurrence);
        IR.setDuration(Duration);

        //
        IR.setOrderId(OrderID);
        IR.setCustom1(CM1);
        IR.setCustom2(CM2);
        IR.getCustomer().setFirstName(FName);
        IR.getCustomer().setLastName(LName);
        IR.getCustomer().setEmail(Email);
        IR.getCustomer().setPhone(Phone);
        IR.getCustomer().getAddress().setAddress(Address);
        IR.getCustomer().getAddress().setCity(City);
        IR.getCustomer().getAddress().setCountry(Country);
        IR.setItemsDescription(ItemDesc);

        // Optional Parameters
        IR.setStartupFee(StartUpFee);               //+/- Adjustment to the fist charge

        Intent intent = new Intent(activity, PHMainActivity.class);
        intent.putExtra(PHConstants.INTENT_EXTRA_DATA, IR);

        PHConfigs.setBaseUrl(PHConfigs.SANDBOX_URL);

        if (MODE.equals("PRODUCTION")){
            PHConfigs.setBaseUrl(PHConfigs.LIVE_URL);
        }
        activity.startActivityForResult(intent, this.rCode);
    }


    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        HashMap<String, Object> map = new HashMap<>(); //Calls the Platform Method Channel for Send back The Status
        if (requestCode == this.rCode && data != null && data.hasExtra(PHConstants.INTENT_EXTRA_RESULT)) { //Checks if Intent has returned the correct data.
            PHResponse<StatusResponse> response = (PHResponse<StatusResponse>) data.getSerializableExtra(PHConstants.INTENT_EXTRA_RESULT); //Get the PH Response
            Log.d("PHRES",response.toString());
            Log.d("PHRES", String.valueOf(response.getStatus()));
            if (response.getStatus() == 1){ //Checks if Payment is a Success
                map.put("STATUS","SUCCESS"); //Status if Success
                map.put("CODE",response.getStatus()); //Status Code
                map.put("SIGN", response.getData().getSign()); //Payment Sign Code
                map.put("PAYMENT_NO", response.getData().getPaymentNo()); //Payment Code
                map.put("MESSAGE", response.getData().getMessage());
                map.put("RAW", response.toString()); //Raw Response from PayHere
            } else if (response.getStatus() == -6){ // If Payment is canceled by user.
                Log.d("TAG",response.toString());
                map.put("STATUS","CANCELED"); //Sets Status to Error
                map.put("CODE", response.getStatus());
                map.put("MESSAGE","User canceled the request"); //Sets Error Code
                map.put("RAW", response.toString()); //Raw Response from PayHere
            } else { //If Payment Error Occurs
                Log.d("TAG",response.toString());
                map.put("STATUS","ERROR"); //Sets Status to Error
                map.put("CODE", response.getStatus()); //Sets Error Code
                map.put("MESSAGE",response.getData().getMessage()); //Sets Error Data
                map.put("RAW", response.toString()); //Raw Response from PayHere
            }
        } else {
            map.put("STATUS","CANCELED"); //Sets Status to Cancel
            map.put("CODE",-6); //Sets Cancel code
            map.put("MESSAGE", "User canceled the request");
        }
        pendingResult.success(map); //Puts Map to Result OBJ
        channel.invokeMethod("Result",map); //Calls the Platform Method Channel for Send back The Status
        return true;
    }

}
