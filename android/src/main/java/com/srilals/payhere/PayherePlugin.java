package com.srilals.payhere;

import android.util.Log;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/**
 * PayHere Plugin for Flutter SDK
 * Author Srilal Sachintha
 * Based on Payhere Android Payhere SDK
 * ! MARS FIRST !
 */
public class PayherePlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {

    private PayHere payhere;
    private ActivityPluginBinding pluginBinding;
    private MethodChannel channel;


    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "Payhere");
        channel.setMethodCallHandler(this);
    }

    /**
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "Payhere");
        channel.setMethodCallHandler(new PayherePlugin());
    }
     **/

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        if (call.method.equals("OTPPAY")) { //Stable and Working 2.0.32
            Log.d("TAG", "Starting PayHere One-Time Payment");
            String MID = call.argument("MID");
            String MSecret = call.argument("MSecret");
            String NotifyUrl = call.argument("NotifyUrl");
            String Currency = call.argument("Currency");
            double Amount = call.argument("Amount");
            String OrderID = call.argument("OrderID");
            String ItemDesc = call.argument("ItemDesc");
            String CM1 = call.argument("CM1");
            String CM2 = call.argument("CM2");
            String FName = call.argument("FName");
            String LName = call.argument("LName");
            String Email = call.argument("EMail");
            String Phone = call.argument("Phone");
            String Address = call.argument("Address");
            String City = call.argument("City");
            String Country = call.argument("Country");
            //Nullables
            String DeliveryAddressIN = call.argument("DeliveryAddress");
            String DeliveryCityIN = call.argument("DeliveryCity");
            String DeliveryCountryIN = call.argument("DeliveryCountry");

            String DeliveryAddress = null;
            String DeliveryCity = null;
            String DeliveryCountry = null;

            if (DeliveryAddressIN != null) {
                DeliveryAddress = DeliveryAddressIN;
            }

            if (DeliveryCityIN != null) {
                DeliveryCity = DeliveryCityIN;
            }

            if (DeliveryCountryIN != null) {
                DeliveryCountry = DeliveryCountryIN;
            }

            String MODE = call.argument("MODE");

            payhere.oneTimePayment(result, MID, MSecret, NotifyUrl, Currency, Amount, OrderID, ItemDesc, CM1, CM2, FName, LName, Email, Phone, Address, City, Country, DeliveryAddress, DeliveryCity, DeliveryCountry, MODE);

        } else if (call.method.equals("RPPAY")) {
            Log.d("TAG", "RP PAY Call Received");
            String MID = call.argument("MID").toString();
            String MSecret = call.argument("MSecret").toString();
            String NotifyUrl = call.argument("NotifyUrl");

            String Currency = call.argument("Currency").toString();
            double Amount = Double.parseDouble(call.argument("Amount").toString());

            String Recurrence = call.argument("Recurrence").toString();
            String Duration = call.argument("Duration").toString();

            String OrderID = call.argument("OrderID").toString();
            String ItemDesc = call.argument("ItemDesc").toString();
            String CM1 = call.argument("CM1").toString();
            String CM2 = call.argument("CM2").toString();
            String FName = call.argument("FName").toString();
            String LName = call.argument("LName").toString();
            String Email = call.argument("EMail").toString();
            String Phone = call.argument("Phone").toString();
            String Address = call.argument("Address").toString();
            String City = call.argument("City").toString();
            String Country = call.argument("Country").toString();

            double StartUpFee = Double.parseDouble(call.argument("StartUpFee").toString());

            String MODE = call.argument("MODE").toString();

            //payhereOTP.recurringPaymentTest(result);
            payhere.recurringPayment(result, MID, MSecret, NotifyUrl, Currency, Amount, OrderID, ItemDesc, CM1, CM2, FName, LName, Email, Phone, Address, City, Country, Recurrence, Duration, StartUpFee, MODE);
        } else {
            result.notImplemented();
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        this.payhere = new PayHere(binding.getActivity(), channel);
        this.pluginBinding = binding;
        binding.addActivityResultListener(payhere);
    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {
        onDetachedFromActivity();
    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
        onAttachedToActivity(binding);
    }

    @Override
    public void onDetachedFromActivity() {
        pluginBinding.removeActivityResultListener(payhere);
        pluginBinding = null;
    }

}
