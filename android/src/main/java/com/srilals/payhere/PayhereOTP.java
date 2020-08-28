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
import lk.payhere.androidsdk.model.Item;
import lk.payhere.androidsdk.model.StatusResponse;

public class PayhereOTP implements ActivityResultListener {

    private final Activity activity;
    private final MethodChannel channel;
    private Result pendingResult;

    public PayhereOTP(Activity activity, MethodChannel channel) {
        this.activity = activity;
        this.channel = channel;
    }


    void oneTimePayment(Result result, String MID, String MSecret, String Currency, double Amount, String OrderID, String ItemDesc, String CM1, String CM2, String FName, String LName, String Email, String Phone, String Address, String City, String Country, String DeliveryAddress, String DeliveryCity, String DeliveryCountry, int Quantity, String MODE) {
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
        activity.startActivityForResult(intent, 1100110011);
    }

    void recurringPaymentTest(Result result){
        this.pendingResult = result;
        InitRequest req = new InitRequest();

        req.setMerchantId("1213586");       // Your Merchant ID
        req.setMerchantSecret("4pH81nUGhXF8hivSd4bAgc4ZGRjX4m7Rh8MOFpE8EZTL"); // Your Merchant Secret (Add your app at Settings > Domains & Credentials, to get this)
        req.setCurrency("LKR");             // Currency code LKR/USD/GBP/EUR/AUD
        req.setAmount(120.00);              // Final Amount to be charged
        req.setRecurrence("1 Month");       // Recurrence of the Subscription
        req.setDuration("Forever");         // Duration of the Subscription
        req.setOrderId("testsub");        // Unique Reference ID
        req.setCustom1("This is the custom message 1");
        req.setCustom2("This is the custom message 2");
        req.getCustomer().setFirstName("Saman");
        req.getCustomer().setLastName("Perera");
        req.getCustomer().setEmail("sampanp@gmail.com");
        req.getCustomer().setPhone("+947771234567");
        req.getCustomer().getAddress().setAddress("No.1, Galle Road");
        req.getCustomer().getAddress().setCity("Colombo");
        req.getCustomer().getAddress().setCountry("Sri Lanka");
        req.setItemsDescription("PRO Plan Subscription");

// Optional Param
        req.setStartupFee(0);               // +/- Adjustment to the fist charge

        Intent intent = new Intent(activity, PHMainActivity.class);
        intent.putExtra(PHConstants.INTENT_EXTRA_DATA, req);
        PHConfigs.setBaseUrl(PHConfigs.SANDBOX_URL);
        activity.startActivityForResult(intent, 1100110011);
    }

    void recurringPayment(Result result, String MID, String MSecret, String Currency, double Amount, String OrderID, String ItemDesc, String CM1, String CM2, String FName, String LName, String Email, String Phone, String Address, String City, String Country, String Recurrence, String Duration, double StartUpFee, String MODE){
        this.pendingResult = result;

        InitRequest IR = new InitRequest();
        IR.setMerchantId(MID);       // Your Merchant ID
        IR.setMerchantSecret(MSecret); // Your Merchant Secret (Add your app at Settings > Domains & Credentials, to get this)
        IR.setCurrency(Currency);             // Currency code LKR/USD/GBP/EUR/AUD
        IR.setAmount(Amount);              // Final Amount to be charged

        //Specific to RP
        IR.setRecurrence(Recurrence);       // Recurrence of the Subscription
        IR.setDuration(Duration);        // Duration of the Subscription

        //IR.setNotifyUrl("https://slunicodes.com/");
        //IR.setReturnUrl("https://slunicodes.com/");
        //IR.setCancelUrl("https://slunicodes.com/");

        //
        IR.setOrderId(OrderID);        // Unique Reference ID
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
        activity.startActivityForResult(intent, 1100110011);
    }


    @Override
    public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
        HashMap<String, Object> map = new HashMap<>();
        if (requestCode == 1100110011 && data != null && data.hasExtra(PHConstants.INTENT_EXTRA_RESULT)) { //Checks if Intent has returned the correct data.
            PHResponse<StatusResponse> response = (PHResponse<StatusResponse>) data.getSerializableExtra(PHConstants.INTENT_EXTRA_RESULT); //Get the PH Response
            if (response.isSuccess()){ //Checks if Payment is a Success
                map.put("STATUS","SUCCESS"); //Status if Success
                map.put("CODE","2"); //Status Code
                map.put("SIGN", response.getData().getSign()); //Payment Sign Code
                map.put("PAYMENT_NO",response.getData().getPaymentNo()); //Payment Code
                pendingResult.success(map); //Puts Map to Result OBJ
                channel.invokeMethod("Result",map); //Calls the Platform Method Channel for Send back The Status
            } else { //If Payment Error Occurs
                Log.d("TAG",response.toString());
                map.put("STATUS","ERROR"); //Sets Status to Error
                map.put("CODE","-1"); //Sets Error Code
                pendingResult.success(map); //Puts Map to Result OBJ
                channel.invokeMethod("Result",map); //Calls the Platform Method Channel for Send back The Status
            }
            return true;
        } else {
            map.put("STATUS","CANCELED"); //Sets Status to Cancel
            map.put("CODE","0"); //Sets Cancel code
            pendingResult.success(map);  //Puts Map to Result OBJ
            channel.invokeMethod("Result",map); //Calls the Platform Method Channel for Send back The Status
            return true;
        }
    }

}
